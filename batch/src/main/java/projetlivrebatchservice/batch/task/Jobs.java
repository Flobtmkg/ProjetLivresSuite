
package projetlivrebatchservice.batch.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import projetlivrebatchservice.batch.BatchApplication;
import projetlivrebatchservice.batch.beans.EmailConnexion;
import projetlivrebatchservice.batch.beans.WsdlLocationObject;
import projetlivrebatchservice.batch.clientservices.generated.serviceExemplaire.Exemplaire;
import projetlivrebatchservice.batch.clientservices.generated.serviceExemplaire.ServiceExemplaire;
import projetlivrebatchservice.batch.clientservices.generated.serviceExemplaire.ServiceExemplaireService;
import projetlivrebatchservice.batch.clientservices.generated.serviceLivre.Livre;
import projetlivrebatchservice.batch.clientservices.generated.serviceLivre.ServiceLivre;
import projetlivrebatchservice.batch.clientservices.generated.serviceLivre.ServiceLivreService;
import projetlivrebatchservice.batch.clientservices.generated.servicePret.Pret;
import projetlivrebatchservice.batch.clientservices.generated.servicePret.Reservation;
import projetlivrebatchservice.batch.clientservices.generated.servicePret.ServicePret;
import projetlivrebatchservice.batch.clientservices.generated.servicePret.ServicePretService;
import projetlivrebatchservice.batch.clientservices.generated.serviceReservation.PreReservation;
import projetlivrebatchservice.batch.clientservices.generated.serviceReservation.ServiceReservation;
import projetlivrebatchservice.batch.clientservices.generated.serviceReservation.ServiceReservationService;
import projetlivrebatchservice.batch.clientservices.generated.serviceUtilisateur.ServiceUtilisateur;
import projetlivrebatchservice.batch.clientservices.generated.serviceUtilisateur.ServiceUtilisateurService;
import projetlivrebatchservice.batch.clientservices.generated.serviceUtilisateur.Utilisateur;
import projetlivrebatchservice.batch.objetsTravail.EnvoiEmail;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class Jobs {
    @Autowired
    EmailConnexion emailAEnvoyer;
    String separateur=System.getProperty("line.separator");
    @Autowired
    private WsdlLocationObject wsdl1;
    @Autowired
    private int nbJoursRappelAvantFinPret;




    @Scheduled(cron = "${cronBegin}")//  cron="0 0 2 * * *" => Tout les jours à 2h00 ; cron="0 20 22 * * *" => Tout les jours à 22h20
    public void JobManager() throws Exception{
        System.out.println("--> "+LocalDateTime.now()+"; Job manager start.");
        // job1
        jobPretNonRendus();
        // job2
        jobRappelFinPret();
        // job3
        jobEffectiviteReservations();
        System.out.println("--> "+LocalDateTime.now()+"; All Jobs are done.");
    }


    private void jobRappelFinPret() throws Exception{
        System.out.println("--> "+LocalDateTime.now()+"; Batch job start : jobRappelFinPret.");
        URL serviceUrl;
        serviceUrl=new URL(wsdl1.getWsdlLocationPret());
        ServicePretService monservice= new ServicePretService(serviceUrl);
        ServicePret accesPret=monservice.getServicePretPort();
        List<Reservation> pretEnRappel=accesPret.listerPretByDaysBeforeDateFinPret(nbJoursRappelAvantFinPret);

        long actualTime;
        long pauseEmail=0;
        for (Reservation res:pretEnRappel) {
            if(res.getUtilisateurReservation().isOptionRappel()==true){
                //On évite de surcharger le SMTP en demandes trop rapprochées surtout tant qu'on utilise le Google SMTP
                while (System.currentTimeMillis()<pauseEmail){
                    //waitin' hard while we threadin' ♪
                }
                actualTime = System.currentTimeMillis();
                pauseEmail = actualTime+emailAEnvoyer.getConnexionPauseInterEmail();// un mail toutes les 15 secondes par defaut
                //
                String textEmail;
                textEmail="Bonjour "+res.getUtilisateurReservation().getPrenomUtilisateur()+" "+res.getUtilisateurReservation().getNomUtilisateur() +","+separateur+separateur+"nous vous informons que votre emprunt de l'exemplaire "+res.getExemplaireReservation().getCoteExemplaire()+" du livre "+ res.getLivreReservation().getTitreLivre()+" de "+ res.getLivreReservation().getAuteurLivre()+" est arrivé à moins de " + nbJoursRappelAvantFinPret + " jours du terme de sa période de réservation ayant court du "+conversionFormatDate(res.getPretReservation().getDateDebutPret())+" au "+conversionFormatDate(res.getPretReservation().getDateFinPret())+"."+separateur+separateur+"Nous vous rappelons qu'il est impératif de nous retourner cet exemplaire avant la date d'expiration de l'emprunt."+separateur+separateur+"D'avance merci."+separateur+separateur+"La gestion des retours de votre bibliothèque.";
                //
                String erreur1=EnvoiEmail.envoi(emailAEnvoyer.getConnexionHost(),emailAEnvoyer.getConnexionFromWho(),emailAEnvoyer.getConnexionUsername(),emailAEnvoyer.getConnexionPassword(),res.getUtilisateurReservation().getEmailUtilisateur(),"Retour de vos emprunts de livres",textEmail);
                //
                if(erreur1.equals("")==false){
                    System.out.println("--> "+LocalDateTime.now()+"; UtilisateurID "+res.getUtilisateurReservation().getIdUtilisateur()+"; PretID "+res.getPretReservation().getIdPret()+"; Email "+res.getUtilisateurReservation().getEmailUtilisateur() + "  ->  " +erreur1);
                }
            }
        }
        System.out.println("--> "+LocalDateTime.now()+"; Batch job end : jobRappelFinPret.");
    }


    private void jobEffectiviteReservations()throws Exception{
        System.out.println("--> "+LocalDateTime.now()+"; Batch job start : jobEffectiviteReservations.");
        // Récupération des services
        URL serviceUrlReservation;
        URL serviceUrlUser;
        URL serviceUrlLivre;
        serviceUrlReservation = new URL(wsdl1.getWsdlLocationReservation());
        serviceUrlUser = new URL(wsdl1.getWsdlLocationUtilisateur());
        serviceUrlLivre = new URL(wsdl1.getWsdlLocationLivre());
        ServiceReservationService monServiceReservation = new ServiceReservationService(serviceUrlReservation);
        ServiceUtilisateurService monServiceUtilisateur = new ServiceUtilisateurService(serviceUrlUser);
        ServiceLivreService monServiceLivre = new ServiceLivreService(serviceUrlLivre);
        ServiceReservation accesServiceReservation = monServiceReservation.getServiceReservationPort();
        ServiceUtilisateur accesServiceUtilisateur = monServiceUtilisateur.getServiceUtilisateurPort();
        ServiceLivre accesServiceLivre = monServiceLivre.getServiceLivrePort();

        // Récupération de la liste des reservations en dépassement de date d'effectivité
        List<PreReservation> depassementEffectivite = accesServiceReservation.getListeReservationTempsEffectfDepasse();
        // liste des réservations passées en effectivité


        // Activation des réservation de tous les premiers des listes d'attente si periode d'effectivité dépassée
        for (PreReservation ResDepasse:depassementEffectivite) {
            // On affecte une date de disponibilité aux suivants dans les listes d'attente
            // Vérification de la liste d'attente pour affecter la première reservation en attente si elle existe
            List<PreReservation> attenteLivreEnCours = accesServiceReservation.getListeAttente(ResDepasse.getIdLivre());
            if(!attenteLivreEnCours.isEmpty()){
                // On défini une date de disponibilité pour la première réservation de la liste d'attente.
                // la résrvation en question devient donc effective
                accesServiceReservation.addDateDisponibiliteReservation(attenteLivreEnCours.get(0).getIdReservation());
            }
        }



        // Envoi des emails de dépassement
        long actualTime;
        long pauseEmail=0;
        List<Integer> idReservationEmailsEnvoyes = new ArrayList<>();
        for (PreReservation res:depassementEffectivite) {
            //On évite de surcharger le SMTP en demandes trop rapprochées surtout tant qu'on utilise le Google SMTP
            while (System.currentTimeMillis()<pauseEmail){
                //waitin' hard while we threadin' ♪
            }
            actualTime = System.currentTimeMillis();
            pauseEmail = actualTime+emailAEnvoyer.getConnexionPauseInterEmail();// un mail toutes les 15 secondes par defaut

            // Def des beens utiles pour la composition du message
            Utilisateur currentUser = accesServiceUtilisateur.getUserById(res.getIdUtilisateur());
            Livre currentLivre = accesServiceLivre.infoLivre(res.getIdLivre());

            String textEmail;
            textEmail="Bonjour "+currentUser.getPrenomUtilisateur()+" "+currentUser.getNomUtilisateur() +","+separateur+separateur+"nous vous informons que votre reservation n° " + res.getIdReservation() + " du livre "+ currentLivre.getTitreLivre()+" de "+ currentLivre.getAuteurLivre()+" est arrivé au terme de sa période d'effectivité de 48h avant retrait en bibliothèque."+separateur+separateur+"Cette réservation est par conséquent annulée et votre place dans la liste d'attente à été cédée à la personne suivante."+separateur+separateur+"Nous vous remercions de votre compréhension."+separateur+separateur+"La gestion des retours de votre bibliothèque.";
            //
            String erreur1=EnvoiEmail.envoi(emailAEnvoyer.getConnexionHost(),emailAEnvoyer.getConnexionFromWho(),emailAEnvoyer.getConnexionUsername(),emailAEnvoyer.getConnexionPassword(),currentUser.getEmailUtilisateur(),"Votre réservations de livre n°" + res.getIdReservation(),textEmail);
            //
            if(erreur1.equals("")==false){
                System.out.println("--> "+LocalDateTime.now()+"; UtilisateurID "+currentUser.getIdUtilisateur()+"; ReservationID "+res.getIdReservation()+"; Email "+currentUser.getEmailUtilisateur() + "  ->  " +erreur1);
            }else{
                // ajout à la liste des emails déjà envoyés
                idReservationEmailsEnvoyes.add(res.getIdReservation());
            }
        }
        // inscription dans la base du fait que ces mails ont déjà été envoyé
        accesServiceReservation.emailDepassementEnvoye(idReservationEmailsEnvoyes);



        // Envoi des emails d'effectivité
        List<PreReservation> reservationsEffectives = accesServiceReservation.getReservationsEffectives();
        pauseEmail=0;
        for (PreReservation res:reservationsEffectives) {
            //On évite de surcharger le SMTP en demandes trop rapprochées surtout tant qu'on utilise le Google SMTP
            while (System.currentTimeMillis()<pauseEmail){
                //waitin' hard while we threadin' ♪
            }
            actualTime = System.currentTimeMillis();
            pauseEmail = actualTime+emailAEnvoyer.getConnexionPauseInterEmail();// un mail toutes les 15 secondes par defaut

            // Def des beens utiles pour la composition du message
            Utilisateur currentUser = accesServiceUtilisateur.getUserById(res.getIdUtilisateur());
            Livre currentLivre = accesServiceLivre.infoLivre(res.getIdLivre());

            String textEmail;
            textEmail="Bonjour "+currentUser.getPrenomUtilisateur()+" "+currentUser.getNomUtilisateur() +","+separateur+separateur+"nous vous informons que votre reservation n° " + res.getIdReservation() + " du livre "+ currentLivre.getTitreLivre()+" de "+ currentLivre.getAuteurLivre()+" est désormais effective et vous disposez de 48h pour effectuer le retrait en bibliothèque."+separateur+separateur+"Passé ce délai, votre réservation sera automatiquement annulée et cédera sa place à la personne suivante dans la liste d'attente."+separateur+separateur+"Nous vous remercions de votre compréhension."+separateur+separateur+"La gestion des retours de votre bibliothèque.";
            //
            String erreur1=EnvoiEmail.envoi(emailAEnvoyer.getConnexionHost(),emailAEnvoyer.getConnexionFromWho(),emailAEnvoyer.getConnexionUsername(),emailAEnvoyer.getConnexionPassword(),currentUser.getEmailUtilisateur(),"Votre réservations de livre n°" + res.getIdReservation(),textEmail);
            //
            if(erreur1.equals("")==false){
                System.out.println("--> "+LocalDateTime.now()+"; UtilisateurID "+currentUser.getIdUtilisateur()+"; ReservationID "+res.getIdReservation()+"; Email "+currentUser.getEmailUtilisateur() + "  ->  " +erreur1);
            }
        }

        System.out.println("--> "+LocalDateTime.now()+"; Batch job end : jobEffectiviteReservations.");
    }




    private void jobPretNonRendus() throws Exception{
        System.out.println("--> "+LocalDateTime.now()+"; Batch job start : jobPretNonRendus.");
        URL serviceUrl;
        serviceUrl=new URL(wsdl1.getWsdlLocationPret());
        ServicePretService monservice= new ServicePretService(serviceUrl);
        ServicePret accesPret=monservice.getServicePretPort();
        List<Reservation> pretsNonRendus=accesPret.listerUtilisateursPretsNonRendus();
        long actualTime;
        long pauseEmail=0;
        for (Reservation res:pretsNonRendus) {
            //On évite de surcharger le SMTP en demandes trop rapprochées surtout tant qu'on utilise le Google SMTP
            while (System.currentTimeMillis()<pauseEmail){
                //waitin' hard while we threadin' ♪
            }
            actualTime = System.currentTimeMillis();
            pauseEmail = actualTime+emailAEnvoyer.getConnexionPauseInterEmail();// un mail toutes les 15 secondes par defaut
            //
            String textEmail;
            String[] tabDate=res.getPretReservation().getDateFinPret().split("-");
            LocalDateTime datedefin=LocalDateTime.of(Integer.parseInt(tabDate[0]),Integer.parseInt(tabDate[1]),Integer.parseInt(tabDate[2]),0,0,0);
            LocalDateTime aujourdhui=LocalDateTime.now();
            Duration retard=Duration.between(aujourdhui,datedefin);
            textEmail="Bonjour "+res.getUtilisateurReservation().getPrenomUtilisateur()+" "+res.getUtilisateurReservation().getNomUtilisateur() +","+separateur+separateur+"nous vous informons que votre emprunt de l'exemplaire "+res.getExemplaireReservation().getCoteExemplaire()+" du livre "+ res.getLivreReservation().getTitreLivre()+" de "+ res.getLivreReservation().getAuteurLivre()+" est arrivé au terme de sa période de réservation ayant court du "+conversionFormatDate(res.getPretReservation().getDateDebutPret())+" au "+conversionFormatDate(res.getPretReservation().getDateFinPret())+", ce qui représente un retard de "+Math.abs(retard.toDays())+" jours."+separateur+separateur+"Nous vous prions de bien vouloir nous retourner cet exemplaire au plus vite."+separateur+separateur+"D'avance merci."+separateur+separateur+"La gestion des retours de votre bibliothèque.";
            //
            String erreur1=EnvoiEmail.envoi(emailAEnvoyer.getConnexionHost(),emailAEnvoyer.getConnexionFromWho(),emailAEnvoyer.getConnexionUsername(),emailAEnvoyer.getConnexionPassword(),res.getUtilisateurReservation().getEmailUtilisateur(),"Retour de vos emprunts de livres",textEmail);
            //
            if(erreur1.equals("")==false){
                System.out.println("--> "+LocalDateTime.now()+"; UtilisateurID "+res.getUtilisateurReservation().getIdUtilisateur()+"; PretID "+res.getPretReservation().getIdPret()+"; Email "+res.getUtilisateurReservation().getEmailUtilisateur() + "  ->  " +erreur1);
            }
        }
        System.out.println("--> "+LocalDateTime.now()+"; Batch job end : jobPretNonRendus.");
    }




    public static String conversionFormatDate(String inputDate){
        String[] tabDate=inputDate.split("-");
        String outputDate=tabDate[2]+"/"+tabDate[1]+"/"+tabDate[0];
        return outputDate;
    }

}
