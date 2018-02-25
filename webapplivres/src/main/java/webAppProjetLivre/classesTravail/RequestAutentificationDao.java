package webAppProjetLivre.classesTravail;

import webAppProjetLivre.generated.serviceUtilisateur.ServiceUtilisateur;
import webAppProjetLivre.generated.serviceUtilisateur.ServiceUtilisateurService;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import java.net.URL;

public class RequestAutentificationDao {
    private Utilisateur resultat;
    private URL urlWsdl;

    public Utilisateur authentifier(String email, String mdp, String wsdlAdress){
        try{
            urlWsdl=new URL(wsdlAdress);
        }catch(Exception e){

        }
        //
        ServiceUtilisateurService monservice= new ServiceUtilisateurService(urlWsdl);
        ServiceUtilisateur accesUtilisateur=monservice.getServiceUtilisateurPort();
        resultat=accesUtilisateur.autentifier(email,mdp);//
        //
        return resultat;
    }
}
