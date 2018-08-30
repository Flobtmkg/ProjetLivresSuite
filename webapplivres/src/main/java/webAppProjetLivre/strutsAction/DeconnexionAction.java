package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import webAppProjetLivre.classesTravail.RequestManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class DeconnexionAction extends ActionSupport implements SessionAware,ServletRequestAware {

    HttpServletRequest currentRequest;

    private Map<String, Object> session;
    private String commandPage;

    public String getCommandPage() {
        return commandPage;
    }

    public void setCommandPage(String commandPage) {
        this.commandPage = commandPage;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.currentRequest=httpServletRequest;
    }
    public String execute() {
        //
        commandPage=RequestManager.pagePrecedenteParametrable(currentRequest,"");
        //
        session.remove("userGuest");
        session.remove("page0");
        session.remove("page1");
        return SUCCESS;
    }


}
