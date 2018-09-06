package webAppProjetLivre.classesTravail;

import webAppProjetLivre.generated.serviceUtilisateur.ServiceUtilisateur;
import webAppProjetLivre.generated.serviceUtilisateur.ServiceUtilisateurService;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import java.net.URL;

public class UpdateUtilisateurDao {
    private URL urlWsdl;

    public void updateInfos(Utilisateur utilisateurInput, String wsdlAdress){
        try{
            urlWsdl=new URL(wsdlAdress);
        }catch(Exception e){

        }
        //
        ServiceUtilisateurService monservice= new ServiceUtilisateurService(urlWsdl);
        ServiceUtilisateur accesUtilisateur=monservice.getServiceUtilisateurPort();
        // Le mot de passe n'est pas mis à jour dans cette procédure le paramètre est remplacé par ""
        accesUtilisateur.modifUtilisateur(utilisateurInput.getIdUtilisateur(),utilisateurInput.getNomUtilisateur(),utilisateurInput.getPrenomUtilisateur(),utilisateurInput.getEmailUtilisateur(),"",utilisateurInput.getDateNaissanceUtilisateur());
        //
    }

    public void updateInfosSupp(Utilisateur utilisateurInput, String wsdlAdress){
        try{
            urlWsdl=new URL(wsdlAdress);
        }catch(Exception e){

        }
        //
        ServiceUtilisateurService monservice= new ServiceUtilisateurService(urlWsdl);
        ServiceUtilisateur accesUtilisateur=monservice.getServiceUtilisateurPort();
        //
        accesUtilisateur.modifUtilisateur(utilisateurInput.getIdUtilisateur(),utilisateurInput.getNomUtilisateur(),utilisateurInput.getPrenomUtilisateur(),utilisateurInput.getEmailUtilisateur(),utilisateurInput.getMdpUtilisateur(),utilisateurInput.getDateNaissanceUtilisateur());
        //
    }

    public void updateOptionRappel(int idUtilisateur, boolean optionRappel, String wsdlAdress){
        try{
            urlWsdl=new URL(wsdlAdress);
        }catch(Exception e){

        }
        //
        ServiceUtilisateurService monservice= new ServiceUtilisateurService(urlWsdl);
        ServiceUtilisateur accesUtilisateur=monservice.getServiceUtilisateurPort();
        //
        accesUtilisateur.defOptionRappelUtilisateur(idUtilisateur,optionRappel);
        //
    }

}
