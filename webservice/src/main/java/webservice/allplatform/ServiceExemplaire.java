package webservice.allplatform;

import beans.Exemplaire;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

@WebService
public class ServiceExemplaire extends Service {

    @WebMethod
    public Exemplaire premierExemplaireDisponible(int idLivre){
        Exemplaire outputExemplaire=monDaoExemplaire.premierExemplaireDisponible(idLivre);
        return outputExemplaire;
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
