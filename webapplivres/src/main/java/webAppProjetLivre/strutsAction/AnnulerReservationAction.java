package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import webAppProjetLivre.classesTravail.RequestManager;
import webAppProjetLivre.classesTravail.ReservationDao;

import javax.servlet.http.HttpServletRequest;

public class AnnulerReservationAction extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest currentRequest;
    private String resid;
    private String commandPage;
    private ReservationDao accesReservation;


    public String getCommandPage() {
        return commandPage;
    }

    public void setCommandPage(String commandPage) {
        this.commandPage = commandPage;
    }

    public ReservationDao getAccesReservation() {
        return accesReservation;
    }

    public void setAccesReservation(ReservationDao accesReservation) {
        this.accesReservation = accesReservation;
    }


    public String getResid() {
        return resid;
    }

    public void setResid(String resid) {
        this.resid = resid;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        currentRequest=httpServletRequest;
        resid=httpServletRequest.getParameter("resid");
    }

    public String execute() throws Exception{
        //Debut recherche des variables d'env qui doivent être prioritaires;
        String getEnv=System.getenv("WSDLLocationReservation");
        if(getEnv!=null && getEnv.equals("")==false){
            accesReservation.annulationManuelleReservation(Integer.parseInt(resid),getEnv);
        }else{
            accesReservation.annulationManuelleReservation(Integer.parseInt(resid),getText("WSDLLocationReservation"));
        }
        commandPage = RequestManager.pagePrecedenteParametrable(currentRequest,"#ModalAnnule");
        return SUCCESS;
    }
}
