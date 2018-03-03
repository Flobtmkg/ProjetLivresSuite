package webAppProjetLivre.classesTravail;

import webAppProjetLivre.generated.serviceUtilisateur.ServiceUtilisateur;
import webAppProjetLivre.generated.serviceUtilisateur.ServiceUtilisateurService;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import java.net.URL;

public class InscriptionDao {
    private boolean resultat;
    private URL urlWsdl;

    public boolean inscrire(Utilisateur utilisateurInput,String wsdlAdress){
        try{
            urlWsdl=new URL(wsdlAdress);
        }catch(Exception e){

        }
        //
        ServiceUtilisateurService monservice= new ServiceUtilisateurService(urlWsdl);
        ServiceUtilisateur accesUtilisateur=monservice.getServiceUtilisateurPort();
        resultat=accesUtilisateur.ajoutUtilisateur(utilisateurInput.getNomUtilisateur(),utilisateurInput.getPrenomUtilisateur(),utilisateurInput.getEmailUtilisateur(),utilisateurInput.getMdpUtilisateur(),utilisateurInput.getDateNaissanceUtilisateur());//
        //
        return resultat;
    }
}
