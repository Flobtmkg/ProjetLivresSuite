package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import webAppProjetLivre.classesTravail.EnvoiEmail;

import javax.servlet.http.HttpServletRequest;

public class SendContactAction extends ActionSupport implements ServletRequestAware {
    private EnvoiEmail sendEmail;
    private String nom;
    private String prenom;
    private String email;
    private String objet;
    private String message;
    private String commandPage;

    public String getCommandPage() {
        return commandPage;
    }

    public void setCommandPage(String commandPage) {
        this.commandPage = commandPage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public EnvoiEmail getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(EnvoiEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        String referer=httpServletRequest.getHeader("Referer");
        this.setCommandPage(referer);
    }

    public String execute() {
        //
        String separateur=System.getProperty("line.separator");
        message="From: "+prenom+" "+nom+separateur+"Email: "+email+separateur+separateur+message;
        //
        String errorEnvoi=sendEmail.envoi(getText("emailHost"),email,getText("emailUsername"),getText("emailPassword"),getText("emailUsername"),objet,message);
        if(errorEnvoi.equals("")){
            commandPage=commandPage+"#ModalValidation";
            return SUCCESS;
        }else{
            commandPage=commandPage+"#ModalErreurSend";
            return ERROR;
        }
        //

    }
}
