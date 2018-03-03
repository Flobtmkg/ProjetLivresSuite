package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class DeconnexionAction extends ActionSupport implements SessionAware,ServletRequestAware {
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
        String referer=httpServletRequest.getHeader("Referer");
        this.setCommandPage(referer);
    }
    public String execute() {
        //
        session.remove("userGuest");
        return SUCCESS;
    }
}
