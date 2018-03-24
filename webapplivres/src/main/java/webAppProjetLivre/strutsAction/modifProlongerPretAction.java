package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import webAppProjetLivre.classesTravail.PretsDao;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

public class modifProlongerPretAction extends ActionSupport implements SessionAware,ServletRequestAware{
    //
    private Map<String, Object> session;
    private String commandPage;
    private ArrayList<String> indicesPrets;
    private PretsDao accesPret;
    //

    public PretsDao getAccesPret() {
        return accesPret;
    }

    public void setAccesPret(PretsDao accesPret) {
        this.accesPret = accesPret;
    }

    public ArrayList<String> getIndicesPrets() {
        return indicesPrets;
    }

    public void setIndicesPrets(ArrayList<String> indicesPrets) {
        this.indicesPrets = indicesPrets;
    }


    public String getCommandPage() {
        return commandPage;
    }

    public void setCommandPage(String commandPage) {
        this.commandPage = commandPage;
    }
    //
    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }
    //
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        String referer=httpServletRequest.getHeader("Referer");
        this.setCommandPage(referer);
        // Ne sont renvoyés en POST que les checkbox Activées, les autres ne sont pas envoyées
        // On récupère la liste des n° des prets à prolonger (précédement concaténés aux noms des checkbox)
        indicesPrets=new ArrayList<>();
        indicesPrets.addAll(httpServletRequest.getParameterMap().keySet());
        int i;
        for(i=0;i<indicesPrets.size();i++){
            indicesPrets.set(i,indicesPrets.get(i).replace("check",""));
        }
        //
    }
    //

    public String execute() {
        //
        //Debut recherche des variables d'env qui doivent être prioritaires;
        String getEnv=System.getenv("WSDLLocationPret");
        if(getEnv!=null && getEnv.equals("")==false){
            this.accesPret.prolongerPrets(indicesPrets,getEnv);
        }else{
            this.accesPret.prolongerPrets(indicesPrets,getText("WSDLLocationPret"));
        }
        //Fin recherche des variables d'env qui doivent être prioritaires;
        //
        commandPage=commandPage+"#ModalValidation";
        return SUCCESS;
    }
}
