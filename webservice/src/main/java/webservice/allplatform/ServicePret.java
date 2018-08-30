package webservice.allplatform;

import DaoInterfaces.DaoPret;
import ServicesBeans.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebService
public class ServicePret extends Service{

    @WebMethod
    public void ajouterPret(int idexemplaire,int idutilisateur,String datedebutpret,boolean prolongepret,boolean rendupret){
        Pret newPretInput= new Pret();
        newPretInput.setIdExemplaire(idexemplaire);
        newPretInput.setIdUtilisateur(idutilisateur);
        newPretInput.setDateDebutPret(LocalDate.parse(datedebutpret));
        LocalDate ld=LocalDate.parse(datedebutpret);
        ld=ld.plusWeeks(borrowWeekDuration.getDureePret());
        newPretInput.setDateFinPret(ld);
        newPretInput.setProlongePret(prolongepret);
        newPretInput.setRenduPret(rendupret);
        monDaoPret.ajouterPret(newPretInput);
    }

    @WebMethod
    public ArrayList<Reservation> listerInfosPretUtilisateur(int idutilisateur){
        ArrayList<Reservation> OutputList=monDaoPret.listerInfosPretUtilisateur(idutilisateur);
        return OutputList;
    }

    @WebMethod
    public ArrayList<Reservation> listerInfosPretLivre(int idLivre){
        ArrayList<Reservation> OutputList=monDaoPret.listerInfosPretLivre(idLivre);
        return OutputList;
    }

    @WebMethod
    public ArrayList<Reservation> listerUtilisateursPretsNonRendus(){
        ArrayList<Reservation> OutputList=monDaoPret.listerUtilisateursPretsNonRendus();
        return OutputList;
    }

    @WebMethod
    public Pret etatPret(int idPret){
        Pret OutputPret=monDaoPret.etatPret(idPret);
        return OutputPret;
    }

    @WebMethod
    public void prolongerPret(int idPret){
        Pret pretActuel = etatPret(idPret);
        LocalDate ld = LocalDate.parse(pretActuel.getDateFinPret());
        ld=ld.plusWeeks(borrowWeekDuration.getDureePret());
        monDaoPret.prolongerPret(idPret,ld.toString());
    }

    @WebMethod
    public void retourPret(int idPret){
        monDaoPret.retourPret(idPret);
        // Vérification de la liste d'attente pour affecter la première reservation en attente si elle existe
        Pret pretEnCours = monDaoPret.etatPret(idPret);
        Exemplaire exemplaireEnCours = monDaoExemplaire.getExemplaireById(pretEnCours.getIdExemplaire());
        List<PreReservation> attenteLivreEnCours = monDaoReservation.getListeAttente(exemplaireEnCours.getIdLivre());
        if(!attenteLivreEnCours.isEmpty()){
            // On défini une date de disponibilité pour la première réservation de la liste d'attente.
            // la résrvation en question devient donc effective
            monDaoReservation.defDateDisponibiliteReservation(attenteLivreEnCours.get(0).getIdReservation());
        }
    }

}
