package webservice.allplatform;

import beans.Pret;
import beans.Reservation;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.time.LocalDate;
import java.util.ArrayList;

@WebService
public class ServicePret extends Service{

    @WebMethod
    public void ajouterPret(int idexemplaire,int idutilisateur,String datedebutpret,String datefinpret,boolean prolongepret,boolean rendupret){
        Pret newPretInput= new Pret();
        newPretInput.setIdExemplaire(idexemplaire);
        newPretInput.setIdUtilisateur(idutilisateur);
        newPretInput.setDateDebutPret(LocalDate.parse(datedebutpret));
        newPretInput.setDateFinPret(LocalDate.parse(datefinpret));
        newPretInput.setProlongePret(prolongepret);
        newPretInput.setRenduPret(rendupret);
        monDaoPret.ajouterPret(newPretInput);
    }
    @WebMethod
    public ArrayList<Pret> listerInfosPretUtilisateur(int idutilisateur){
        ArrayList<Pret> OutputList=monDaoPret.listerInfosPretUtilisateur(idutilisateur);
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
        monDaoPret.prolongerPret(idPret);
    }

    @WebMethod
    public void retourPret(int idPret){
        monDaoPret.retourPret(idPret);
    }
}
