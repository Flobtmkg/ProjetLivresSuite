package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import java.util.Map;

public class RedirectionEspaceUtilisateurAction extends ActionSupport implements SessionAware{
    private Map<String, Object> session;
    private Utilisateur identifiedUser;
    //
    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }
    //
    public String execute() {
        //
        identifiedUser=(Utilisateur) session.get("userGuest");
        //
        if(identifiedUser==null || identifiedUser.getIdUtilisateur()==0){//non connecté
            return ERROR;
        }else{//connecté
            return SUCCESS;
        }
        //

    }
}
