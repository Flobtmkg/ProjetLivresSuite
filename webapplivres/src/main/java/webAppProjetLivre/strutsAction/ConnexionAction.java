package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import webAppProjetLivre.classesTravail.RequestAutentificationDao;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ConnexionAction extends ActionSupport implements SessionAware,ServletRequestAware {
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
        String referer=httpServletRequest.getHeader("Referer");
        this.setCommandPage(referer);
    }

    public String execute() {
        //session.put("something",);
        this.setIdentifiedUser(autentificationDao.authentifier(idco,mdp,getText("WSDLLocationUtilisateur")));
        session.put("userGuest",this.getIdentifiedUser());
        if(identifiedUser.getIdUtilisateur()!=0){
            return SUCCESS;
        }else{
            commandPage=commandPage+"#ModalErreur";
            return ERROR;
        }
    }
}
