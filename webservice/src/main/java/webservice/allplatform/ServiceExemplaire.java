package webservice.allplatform;

import ServicesBeans.Exemplaire;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

@WebService
public class ServiceExemplaire extends Service {

    @WebMethod
    public ArrayList<Exemplaire> ExemplaireDisponible(boolean outputMode,int idLivre){
        ArrayList<Exemplaire> outputExemplaires=monDaoExemplaire.ExemplaireDisponible(outputMode,idLivre);
        return outputExemplaires;
    }

    @WebMethod
    public ArrayList<Exemplaire> listerExemplaire(int idLivre){
        ArrayList<Exemplaire> outputListExemplaire=monDaoExemplaire.listerExemplaire(idLivre);
        return outputListExemplaire;
    }

    @WebMethod
    public void AjouterExemplaire(int idLivre, String coteExemplaire, String remarqueExemplaire){
        Exemplaire exemplaireInput = new Exemplaire();
        exemplaireInput.setIdExemplaire(0);
        exemplaireInput.setIdLivre(idLivre);
        exemplaireInput.setCoteExemplaire(coteExemplaire);
        exemplaireInput.setRemarqueExemplaire(remarqueExemplaire);
        monDaoExemplaire.AjouterExemplaire(exemplaireInput);
    }

}
