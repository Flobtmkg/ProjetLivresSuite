package webservice.allplatform;

import ServicesBeans.Utilisateur;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ServiceUtilisateur extends Service {

    @WebMethod
    public Utilisateur autentifier(String emailInput, String mdpInput){
        Utilisateur utilisateurOutput=monDaoUtilisateur.autentifier(emailInput,mdpInput);
        return utilisateurOutput;
    }

    @WebMethod
    public void ajoutUtilisateur(String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, String mdpUtilisateur, String datenaissanceutilisateur){
        Utilisateur utilisateurInput= new Utilisateur();
        utilisateurInput.setIdUtilisateur(0);
        utilisateurInput.setNomUtilisateur(nomUtilisateur);
        utilisateurInput.setPrenomUtilisateur(prenomUtilisateur);
        utilisateurInput.setEmailUtilisateur(emailUtilisateur);
        utilisateurInput.setMdpUtilisateur(mdpUtilisateur);
        utilisateurInput.setDateNaissanceUtilisateur(datenaissanceutilisateur);
        monDaoUtilisateur.ajouterUtilisateur(utilisateurInput);
    }

}
