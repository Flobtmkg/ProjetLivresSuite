package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import webAppProjetLivre.classesTravail.RechercheSimpleDao;
import webAppProjetLivre.generated.serviceLivre.Livre;

import java.util.List;

public class RechercheSimpleAction extends ActionSupport {
    private String motCles;
    private List<Livre> listeLivre;
    private RechercheSimpleDao recherche;

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
        this.setListeLivre(this.getRecherche().recherche(motCles,getText("WSDLLocationLivres")));
        return SUCCESS;
    }
}
