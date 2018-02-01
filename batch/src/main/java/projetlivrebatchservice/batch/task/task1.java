package projetlivrebatchservice.batch.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import projetlivrebatchservice.batch.beans.EmailConnexion;
import projetlivrebatchservice.batch.beans.WsdlLocationObject;
import projetlivrebatchservice.batch.clientservices.generated.servicePret.Reservation;
import projetlivrebatchservice.batch.clientservices.generated.servicePret.ServicePret;
import projetlivrebatchservice.batch.clientservices.generated.servicePret.ServicePretService;
import projetlivrebatchservice.batch.objetsTravail.EnvoiEmail;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


@Component
public class task1 {
    @Autowired
    EmailConnexion emailAEnvoyer;
    String separateur=System.getProperty("line.separator");
    @Autowired
    private WsdlLocationObject wsdl1;
    private URL testUrl;

    @Scheduled(cron="0 0 2 * * *")//  cron="0 0 2 * * *" => Tout les jours à 2h00 ; cron="0 20 22 * * *" => Tout les jours à 22h20
    public void work() {
        try{
            testUrl=new URL(wsdl1.getWsdlLocationPret());
        }catch(Exception e){

        }
        System.out.println("--> "+LocalDateTime.now()+"; Récupétation des données via le webservice.");
        ServicePretService monservice= new ServicePretService(testUrl);
        ServicePret accesPret=monservice.getServicePretPort();
        List<Reservation> pretsNonRendus=accesPret.listerUtilisateursPretsNonRendus();
        long actualTime;
        long pauseEmail=0;
        System.out.println("--> "+LocalDateTime.now()+"; Batch job start.");
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
        System.out.println("--> "+LocalDateTime.now()+"; Batch job done.");
    }

    public static String conversionFormatDate(String inputDate){
        String[] tabDate=inputDate.split("-");
        String outputDate=tabDate[2]+"/"+tabDate[1]+"/"+tabDate[0];
        return outputDate;
    }

}
