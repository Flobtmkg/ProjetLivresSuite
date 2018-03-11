package webservice.allplatform;

import ServicesBeans.Pret;
import ServicesBeans.Reservation;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.time.LocalDate;
import java.util.ArrayList;

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
    }

}
