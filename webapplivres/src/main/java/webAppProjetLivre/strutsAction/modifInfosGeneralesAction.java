package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import webAppProjetLivre.classesTravail.DateTool;
import webAppProjetLivre.classesTravail.UpdateUtilisateurDao;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class modifInfosGeneralesAction extends ActionSupport implements SessionAware,ServletRequestAware {
    //
    private Utilisateur UserAModif;
    private UpdateUtilisateurDao accesModifUtilisateur;
    private Map<String, Object> session;
    private String commandPage;
    //
    private String prenom;
    private String nom;
    private String dateNaissance;
    private String email;
    //
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCommandPage() {
        return commandPage;
    }

    public void setCommandPage(String commandPage) {
        this.commandPage = commandPage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //
    public Utilisateur getUserAModif() {
        return UserAModif;
    }

    public void setUserAModif(Utilisateur userAModif) {
        UserAModif = userAModif;
    }

    public UpdateUtilisateurDao getAccesModifUtilisateur() {
        return accesModifUtilisateur;
    }

    public void setAccesModifUtilisateur(UpdateUtilisateurDao accesModifUtilisateur) {
        this.accesModifUtilisateur = accesModifUtilisateur;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }
    //
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        String referer=httpServletRequest.getHeader("Referer");
        this.setCommandPage(referer);
    }
    //

    public String execute() {
        //
        Utilisateur userEnCours=(Utilisateur) session.get("userGuest");
        UserAModif= new Utilisateur();
        this.UserAModif.setPrenomUtilisateur(this.prenom);
        this.UserAModif.setNomUtilisateur(this.nom);
        this.UserAModif.setEmailUtilisateur(this.email);
        this.UserAModif.setDateNaissanceUtilisateur(DateTool.dateFRtoEN(this.dateNaissance));
        this.UserAModif.setIdUtilisateur(userEnCours.getIdUtilisateur());
        //
        accesModifUtilisateur.updateInfos(UserAModif,getText("WSDLLocationUtilisateur"));
        // On rebalance le profile dans la session
        session.put("userGuest",UserAModif);
        //
        commandPage=commandPage+"#ModalValidation";
        return SUCCESS;
    }
}
