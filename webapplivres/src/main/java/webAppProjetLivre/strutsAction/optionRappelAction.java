package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import webAppProjetLivre.classesTravail.RequestManager;
import webAppProjetLivre.classesTravail.UpdateUtilisateurDao;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class optionRappelAction extends ActionSupport implements SessionAware, ServletRequestAware {


    private Map<String, Object> thisSession;
    private String commandPage;
    private Utilisateur user;
    private boolean rappelParam;
    private UpdateUtilisateurDao accesUtilisateur;
    private HttpServletRequest currentRequest;


    public UpdateUtilisateurDao getAccesUtilisateur() {
        return accesUtilisateur;
    }

    public void setAccesUtilisateur(UpdateUtilisateurDao accesUtilisateur) {
        this.accesUtilisateur = accesUtilisateur;
    }

    public String getCommandPage() {
        return commandPage;
    }

    public void setCommandPage(String commandPage) {
        this.commandPage = commandPage;
    }

    public Map<String, Object> getThisSession() {
        return thisSession;
    }

    public void setThisSession(Map<String, Object> thisSession) {
        this.thisSession = thisSession;
    }


    @Override
    public String execute() throws Exception {

        //Debut recherche des variables d'env qui doivent être prioritaires;
        String getEnv=System.getenv("WSDLLocationUtilisateur");
        if(getEnv!=null && getEnv.equals("")==false){
            accesUtilisateur.updateOptionRappel(user.getIdUtilisateur(),rappelParam,getEnv);
        }else{
            accesUtilisateur.updateOptionRappel(user.getIdUtilisateur(),rappelParam,getText("WSDLLocationUtilisateur"));
        }
        //Fin recherche des variables d'env qui doivent être prioritaires;
        //
        user.setOptionRappel(rappelParam);
        thisSession.put("userGuest",user);
        commandPage= RequestManager.pagePrecedenteParametrable(currentRequest,"#ModalValidation");
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.user=(Utilisateur) map.get("userGuest");
        this.thisSession=map;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.rappelParam=Boolean.valueOf(httpServletRequest.getParameter("rappel"));
        this.currentRequest=httpServletRequest;
    }
}
