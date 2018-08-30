package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import webAppProjetLivre.beansAdditionels.PreReservationExtend;
import webAppProjetLivre.classesTravail.*;
import webAppProjetLivre.generated.serviceLivre.Livre;
import webAppProjetLivre.generated.servicePret.Reservation;
import webAppProjetLivre.generated.serviceReservation.PreReservation;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

public class RedirectionEspaceUtilisateurAction extends ActionSupport implements SessionAware,ServletRequestAware {


    private HttpServletRequest currentRequest;


    //Date de naissance formalisée à la française
    private String datefr;
    private LocalDate mainteant;
    private int age;
    private List<Reservation> reservationUtilisateur;
    private ArrayList<Reservation> reservationUtilisateurProlongeables;
    private ArrayList<Reservation> reservationUtilisateurRetard;
    private List<PreReservationExtend> reservationsExt;
    private PretsDao accesPrets;
    private ReservationDao reservations;
    private RequestLivreDao accesLivre;
    private int id;
    //
    //

    public List<PreReservationExtend> getReservationsExt() {
        return reservationsExt;
    }

    public void setReservationsExt(List<PreReservationExtend> reservationsExt) {
        this.reservationsExt = reservationsExt;
    }

    public RequestLivreDao getAccesLivre() {
        return accesLivre;
    }

    public void setAccesLivre(RequestLivreDao accesLivre) {
        this.accesLivre = accesLivre;
    }

    public ReservationDao getReservations() {
        return reservations;
    }

    public void setReservations(ReservationDao reservations) {
        this.reservations = reservations;
    }

    public ArrayList<Reservation> getReservationUtilisateurRetard() {
        return reservationUtilisateurRetard;
    }

    public void setReservationUtilisateurRetard(ArrayList<Reservation> reservationUtilisateurRetard) {
        this.reservationUtilisateurRetard = reservationUtilisateurRetard;
    }

    public ArrayList<Reservation> getReservationUtilisateurProlongeables() {
        return reservationUtilisateurProlongeables;
    }

    public void setReservationUtilisateurProlongeables(ArrayList<Reservation> reservationUtilisateurProlongeables) {
        this.reservationUtilisateurProlongeables = reservationUtilisateurProlongeables;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Reservation> getReservationUtilisateur() {
        return reservationUtilisateur;
    }

    public void setReservationUtilisateur(List<Reservation> reservationUtilisateur) {
        this.reservationUtilisateur = reservationUtilisateur;
    }

    public PretsDao getAccesPrets() {
        return accesPrets;
    }

    public void setAccesPrets(PretsDao accesPrets) {
        this.accesPrets = accesPrets;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getMainteant() {
        return mainteant;
    }

    public void setMainteant(LocalDate mainteant) {
        this.mainteant = mainteant;
    }


    private Map<String, Object> session;

    public String getDatefr() {
        return datefr;
    }

    public void setDatefr(String datefr) {
        this.datefr = datefr;
    }
    //
    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }
    //
    //
    public String execute() throws Exception{
        //
        Utilisateur identifiedUser;
        identifiedUser=(Utilisateur) session.get("userGuest");
        //
        //
        if(identifiedUser==null || identifiedUser.getIdUtilisateur()==0){//non connecté
            return ERROR;
        }else{//connecté
            //
            getReservationsExtByUser(identifiedUser);

            //Debut recherche des variables d'env qui doivent être prioritaires;
            String getEnv=System.getenv("WSDLLocationPret");
            if(getEnv!=null && getEnv.equals("")==false){
                this.reservationUtilisateur=accesPrets.listPretsUtilisateur(identifiedUser.getIdUtilisateur(),getEnv);
            }else{
                this.reservationUtilisateur=accesPrets.listPretsUtilisateur(identifiedUser.getIdUtilisateur(),getText("WSDLLocationPret"));
            }
            //Fin recherche des variables d'env qui doivent être prioritaires;
            //
            this.setDatefr(DateTool.dateENtoFR(identifiedUser.getDateNaissanceUtilisateur()));
            this.age=DateTool.getAge(identifiedUser.getDateNaissanceUtilisateur());
            this.mainteant=LocalDate.now();
            //
            // Récupération des réservations prolongeables!
            reservationUtilisateurProlongeables=new ArrayList<>();
            LocalDate dateACheck;
            for (Reservation res:reservationUtilisateur) {
                dateACheck=LocalDate.parse(res.getPretReservation().getDateFinPret());
                if(dateACheck.isAfter(mainteant) && res.getPretReservation().isProlongePret()==false){
                    reservationUtilisateurProlongeables.add(res);
                }
            }
            //
            // Récupération des réservations en retard!
            reservationUtilisateurRetard=new ArrayList<>();
            for (Reservation res:reservationUtilisateur) {
                dateACheck=LocalDate.parse(res.getPretReservation().getDateFinPret());
                if(dateACheck.isBefore(mainteant) && res.getPretReservation().isRenduPret()==false){
                    reservationUtilisateurRetard.add(res);
                }
            }
            //
            return SUCCESS;
        }
        //

    }


    private void getReservationsExtByUser(Utilisateur identifiedUser) throws Exception{
        reservationsExt= new ArrayList<PreReservationExtend>();
        //Debut recherche des variables d'env qui doivent être prioritaires;
        String getEnv=System.getenv("WSDLLocationReservation");
        List<PreReservation> reservationsOutput;
        if(getEnv!=null && getEnv.equals("")==false){
            reservationsOutput = reservations.getListeReservationByUser(identifiedUser.getIdUtilisateur(),getEnv);
        }else{
            reservationsOutput = reservations.getListeReservationByUser(identifiedUser.getIdUtilisateur(),getText("WSDLLocationReservation"));
        }
        for (PreReservation res:reservationsOutput){
            //PreReservationExtend ext;
            //ext.setReservation(res);
            //ext.setState(CalculEtatReservation.getEtatReservation(res));

            // prochain retour prévu
            //Debut recherche des variables d'env qui doivent être prioritaires;
            getEnv=System.getenv("WSDLLocationPret");
            List<Reservation> pretsLivre;
            if(getEnv!=null && getEnv.equals("")==false){
                pretsLivre = accesPrets.listPretsLivre(res.getIdLivre(),getEnv);
            }else{
                pretsLivre = accesPrets.listPretsLivre(res.getIdLivre(),getText("WSDLLocationPret"));
            }
            // la liste est ordonnée par date de fin de prêt croissante
            String premiereDateFinPret="";
            if(pretsLivre!=null){
                premiereDateFinPret=pretsLivre.get(0).getPretReservation().getDateFinPret();
                premiereDateFinPret=DateTool.dateENtoFR(premiereDateFinPret);
            }

            //ext.setDateProchainRetourPrevu(premiereDateFinPret);
            int pos;
            if(CalculEtatReservation.getEtatReservation(res).equals(EtatPreReservation.EFFECTIF)){
                pos=1;
                //ext.setPositionListAttente(1);
            }else{
                List<PreReservation> listAttente;
                if(getEnv!=null && getEnv.equals("")==false) {
                    listAttente=reservations.getListeAttente(res.getIdLivre(), getEnv);
                }else{
                    listAttente=reservations.getListeAttente(res.getIdLivre(), getText("WSDLLocationReservation"));
                }
                List<PreReservation> listEffective;
                if(getEnv!=null && getEnv.equals("")==false) {
                    listEffective=reservations.getReservationEffectiveByLivre(id, getEnv);
                }else{
                    listEffective=reservations.getReservationEffectiveByLivre(id, getText("WSDLLocationReservation"));
                }
                int i = listAttente.indexOf(res);
                if(i<0){
                    i=0;
                }
                pos =listEffective.size() + i + 1;
            }


            // get Livre
            //Debut recherche des variables d'env qui doivent être prioritaires;
            getEnv=System.getenv("WSDLLocationLivres");
            Livre currentLivre;
            if(getEnv!=null && getEnv.equals("")==false){
                currentLivre = accesLivre.infoLivre(res.getIdLivre(),getEnv);
            }else{
                currentLivre = accesLivre.infoLivre(res.getIdLivre(),getText("WSDLLocationLivres"));
            }

            PreReservationExtend ext;
            ext = new PreReservationExtend(res,CalculEtatReservation.getEtatReservation(res),premiereDateFinPret,pos,currentLivre);
            reservationsExt.add(ext);
        }
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.currentRequest=httpServletRequest;
    }


}
