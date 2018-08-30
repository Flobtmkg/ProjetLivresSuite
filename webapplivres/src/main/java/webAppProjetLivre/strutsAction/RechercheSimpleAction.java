package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import webAppProjetLivre.classesTravail.RequestManager;
import webAppProjetLivre.classesTravail.ListerDao;
import webAppProjetLivre.classesTravail.RechercheSimpleDao;
import webAppProjetLivre.generated.serviceLivre.Livre;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RechercheSimpleAction extends ActionSupport implements ServletRequestAware {


    private HttpServletRequest currentRequest;

    private String motCles;
    private List<Livre> listeLivre;
    private RechercheSimpleDao recherche;
    private ListerDao listes;
    private List<String> types;
    private List<String> domaines;
    private List<String> themes;
    //



    public ListerDao getListes() {
        return listes;
    }

    public void setListes(ListerDao listes) {
        this.listes = listes;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getDomaines() {
        return domaines;
    }

    public void setDomaines(List<String> domaines) {
        this.domaines = domaines;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }



    public RechercheSimpleDao getRecherche() {
        return recherche;
    }

    public void setRecherche(RechercheSimpleDao recherche) {
        this.recherche = recherche;
    }

    public List<Livre> getListeLivre() {
        return listeLivre;
    }

    public void setListeLivre(List<Livre> listeLivre) {
        this.listeLivre = listeLivre;
    }

    public String getMotCles() {
        return motCles;
    }

    public void setMotCles(String motCles) {
        this.motCles = motCles;
    }

    public String execute() {
        //
        //
        if(listeLivre!=null){
            listeLivre.clear();
        }
        //
        String getEnv=System.getenv("WSDLLocationLivres");
        if(motCles.equals("")==false){
            //Debut recherche des variables d'env qui doivent être prioritaires;
            if(getEnv!=null && getEnv.equals("")==false){
                this.setListeLivre(this.getRecherche().recherche(motCles,getEnv));
            }else{
                this.setListeLivre(this.getRecherche().recherche(motCles,getText("WSDLLocationLivres")));
            }
            //Fin recherche des variables d'env qui doivent être prioritaires;
        }
        //Debut recherche des variables d'env qui doivent être prioritaires;
        if(getEnv!=null && getEnv.equals("")==false){
            this.setDomaines(this.getListes().rechercherListDomaines(getEnv));
        }else{
            this.setDomaines(this.getListes().rechercherListDomaines(getText("WSDLLocationLivres")));
        }
        //Fin recherche des variables d'env qui doivent être prioritaires;
        //Debut recherche des variables d'env qui doivent être prioritaires;
        if(getEnv!=null && getEnv.equals("")==false){
            this.setThemes(this.getListes().rechercherListTheme(getEnv));
        }else{
            this.setThemes(this.getListes().rechercherListTheme(getText("WSDLLocationLivres")));
        }
        //Fin recherche des variables d'env qui doivent être prioritaires;
        //Debut recherche des variables d'env qui doivent être prioritaires;
        if(getEnv!=null && getEnv.equals("")==false){
            this.setTypes(this.getListes().rechercherListType(getEnv));
        }else{
            this.setTypes(this.getListes().rechercherListType(getText("WSDLLocationLivres")));
        }
        //Fin recherche des variables d'env qui doivent être prioritaires;
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.currentRequest=httpServletRequest;
    }


}
