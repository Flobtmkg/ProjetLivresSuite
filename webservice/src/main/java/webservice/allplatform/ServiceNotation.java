package webservice.allplatform;


import ServicesBeans.Notation;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ServiceNotation extends Service {

    @WebMethod
    public double notationLivre(int idLivre){
        double OutputNotation=monDaoNotation.notationLivre(idLivre);
        return OutputNotation;
    }

    @WebMethod
    public void ajouterNotation(int idUtilisateur,int idLivre,double valueNotation){
        Notation newNotationInput= new Notation();
        newNotationInput.setIdUtilisateur(idUtilisateur);
        newNotationInput.setIdLivre(idLivre);
        newNotationInput.setValueNotation(valueNotation);
        monDaoNotation.ajouterNotation(newNotationInput);
    }
}
