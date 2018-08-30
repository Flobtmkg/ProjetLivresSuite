package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import webAppProjetLivre.classesTravail.RequestManager;
import webAppProjetLivre.classesTravail.RequestAutentificationDao;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ConnexionAction extends ActionSupport implements SessionAware,ServletRequestAware {

    private HttpServletRequest currentRequest;


    private String commandPage;
    private String idco;
    private String mdp;
    private Utilisateur identifiedUser;
    private RequestAutentificationDao autentificationDao;
    private Map<String, Object> session;
    //

    public Utilisateur getIdentifiedUser() {
        return identifiedUser;
    }

    public void setIdentifiedUser(Utilisateur identifiedUser) {
        this.identifiedUser = identifiedUser;
    }

    public RequestAutentificationDao getAutentificationDao() {
        return autentificationDao;
    }

    public void setAutentificationDao(RequestAutentificationDao autentificationDao) {
        this.autentificationDao = autentificationDao;
    }

    public String getCommandPage() {
        return commandPage;
    }

    public void setCommandPage(String commandPage) {
        this.commandPage = commandPage;
    }

    public String getIdco() {
        return idco;
    }

    public void setIdco(String idco) {
        this.idco = idco;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.currentRequest=httpServletRequest;
        //String referer=httpServletRequest.getHeader("Referer");
        //this.setCommandPage(referer);
    }

    public String execute() {
        //Debut recherche des variables d'env qui doivent être prioritaires;
        String getEnv=System.getenv("WSDLLocationUtilisateur");
        if(getEnv!=null && getEnv.equals("")==false){
            identifiedUser = autentificationDao.authentifier(idco,mdp,getEnv);
        }else{
            identifiedUser = autentificationDao.authentifier(idco,mdp,getText("WSDLLocationUtilisateur"));
        }
        session.put("userGuest",this.getIdentifiedUser());
        if(identifiedUser.getIdUtilisateur()!=0){
            commandPage=RequestManager.pagePrecedenteParametrable(currentRequest,"");
            return SUCCESS;
        }else{
            commandPage=RequestManager.pagePrecedenteParametrable(currentRequest,"#ModalErreur");
            return ERROR;
        }
    }


}
