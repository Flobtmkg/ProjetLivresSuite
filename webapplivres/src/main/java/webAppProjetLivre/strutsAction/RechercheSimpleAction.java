package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import webAppProjetLivre.classesTravail.ListerDao;
import webAppProjetLivre.classesTravail.RechercheSimpleDao;
import webAppProjetLivre.generated.serviceLivre.Livre;

import java.util.List;

public class RechercheSimpleAction extends ActionSupport {
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
        if(listeLivre!=null){
            listeLivre.clear();
        }
        //
        if(motCles.equals("")==false){
            this.setListeLivre(this.getRecherche().recherche(motCles,getText("WSDLLocationLivres")));
        }
        this.setDomaines(this.getListes().rechercherListDomaines(getText("WSDLLocationLivres")));
        this.setThemes(this.getListes().rechercherListTheme(getText("WSDLLocationLivres")));
        this.setTypes(this.getListes().rechercherListType(getText("WSDLLocationLivres")));
        return SUCCESS;
    }
}
