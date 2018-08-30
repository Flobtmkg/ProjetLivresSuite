package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import webAppProjetLivre.classesTravail.RequestManager;
import webAppProjetLivre.classesTravail.ReservationDao;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class AjoutReservationAction extends ActionSupport implements SessionAware, ServletRequestAware {

    private String commandPage;
    private HttpServletRequest currentRequest;


    private Map<String, Object> session;
    private ReservationDao accesReservation;
    int idLivre;


    public String getCommandPage() {
        return commandPage;
    }

    public void setCommandPage(String commandPage) {
        this.commandPage = commandPage;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public ReservationDao getAccesReservation() {
        return accesReservation;
    }

    public void setAccesReservation(ReservationDao accesReservation) {
        this.accesReservation = accesReservation;
    }


    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }

    public String execute() {
        Utilisateur user = (Utilisateur) session.get("userGuest");
        if(user!=null){
            //connecté
            //test des règles de gestions

            //Debut recherche des variables d'env qui doivent être prioritaires;
            String getEnv=System.getenv("WSDLLocationReservation");
            try{
                if(getEnv!=null && getEnv.equals("")==false){
                    accesReservation.ajoutReservation(idLivre,user.getIdUtilisateur(),getEnv);
                }else{
                    accesReservation.ajoutReservation(idLivre,user.getIdUtilisateur(),getText("WSDLLocationReservation"));
                }
            }catch (Exception e){

            }
            commandPage=RequestManager.pagePrecedenteParametrable(currentRequest,"#ModalValidation");
        }
        return SUCCESS;
    }


    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.currentRequest=httpServletRequest;
    }


}
