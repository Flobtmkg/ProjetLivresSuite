package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import webAppProjetLivre.classesTravail.DateTool;
import webAppProjetLivre.classesTravail.PretsDao;
import webAppProjetLivre.generated.servicePret.Pret;
import webAppProjetLivre.generated.servicePret.Reservation;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

public class RedirectionEspaceUtilisateurAction extends ActionSupport implements SessionAware {
    //Date de naissance formalisée à la française
    private String datefr;
    private LocalDate mainteant;
    private int age;
    private List<Reservation> reservationUtilisateur;
    private ArrayList<Reservation> reservationUtilisateurProlongeables;
    private ArrayList<Reservation> reservationUtilisateurRetard;
    private PretsDao accesPrets;
    private int id;
    //
    //

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
    public String execute() {
        //
        Utilisateur identifiedUser;
        identifiedUser=(Utilisateur) session.get("userGuest");
        //
        //
        if(identifiedUser==null || identifiedUser.getIdUtilisateur()==0){//non connecté
            return ERROR;
        }else{//connecté
            //
            this.reservationUtilisateur=accesPrets.listPretsUtilisateur(identifiedUser.getIdUtilisateur(),getText("WSDLLocationPret"));
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
            // Récupération des réservations prolongeables!
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
}
