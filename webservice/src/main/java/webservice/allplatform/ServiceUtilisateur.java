package webservice.allplatform;

import DaoInterfaces.DaoUtilisateur;
import ServicesBeans.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
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
    public boolean ajoutUtilisateur(String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, String mdpUtilisateur, String datenaissanceutilisateur){
        Utilisateur utilisateurInput= new Utilisateur();
        utilisateurInput.setIdUtilisateur(0);
        utilisateurInput.setNomUtilisateur(nomUtilisateur);
        utilisateurInput.setPrenomUtilisateur(prenomUtilisateur);
        utilisateurInput.setEmailUtilisateur(emailUtilisateur);
        utilisateurInput.setMdpUtilisateur(mdpUtilisateur);
        utilisateurInput.setDateNaissanceUtilisateur(datenaissanceutilisateur);
        if(isEmailExist(emailUtilisateur)==false){ // si pas présent dans la base alors on peut inscrire
            monDaoUtilisateur.ajouterUtilisateur(utilisateurInput);
            return true;
        }else{
            return false;
        }
    }

    private boolean isEmailExist(String emailInput){
        boolean isExist=monDaoUtilisateur.isEmailExistInBase(emailInput);
        return isExist;
    }

    @WebMethod    public void modifUtilisateur(int idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, String mdpUtilisateur, String datenaissanceutilisateur){
        Utilisateur utilisateurInput= new Utilisateur();
        utilisateurInput.setIdUtilisateur(idUtilisateur);
        utilisateurInput.setNomUtilisateur(nomUtilisateur);
        utilisateurInput.setPrenomUtilisateur(prenomUtilisateur);
        utilisateurInput.setEmailUtilisateur(emailUtilisateur);
        if(mdpUtilisateur==null){
            mdpUtilisateur="";
        }
        utilisateurInput.setMdpUtilisateur(mdpUtilisateur);
        utilisateurInput.setDateNaissanceUtilisateur(datenaissanceutilisateur);
        monDaoUtilisateur.modifierUtilisateur(utilisateurInput);
    }

}
