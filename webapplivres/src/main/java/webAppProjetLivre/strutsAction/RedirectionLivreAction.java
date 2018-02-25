package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import webAppProjetLivre.classesTravail.RequestExemplaireDao;
import webAppProjetLivre.classesTravail.RequestLivreDao;
import webAppProjetLivre.generated.serviceExemplaire.Exemplaire;
import webAppProjetLivre.generated.serviceLivre.Livre;

import java.util.List;

public class RedirectionLivreAction extends ActionSupport{
    private int id;//idLivre à requester
    //
    private RequestLivreDao infosLivre;
    private Livre livreAAfficher;
    //
    private List<Exemplaire> exemplairesDispo;
    private RequestExemplaireDao lesExemplaires;
    private List<String> informationDesindexee;
    //
    public List<String> getInformationDesindexee() {
        return informationDesindexee;
    }

    public void setInformationDesindexee(List<String> informationDesindexee) {
        this.informationDesindexee = informationDesindexee;
    }

    public List<Exemplaire> getExemplairesDispo() {
        return exemplairesDispo;
    }

    public void setExemplairesDispo(List<Exemplaire> exemplairesDispo) {
        this.exemplairesDispo = exemplairesDispo;
    }

    public RequestExemplaireDao getLesExemplaires() {
        return lesExemplaires;
    }

    public void setLesExemplaires(RequestExemplaireDao lesExemplaires) {
        this.lesExemplaires = lesExemplaires;
    }

    public RequestLivreDao getInfosLivre() {
        return infosLivre;
    }

    public void setInfosLivre(RequestLivreDao infosLivre) {
        this.infosLivre = infosLivre;
    }

    public Livre getLivreAAfficher() {
        return livreAAfficher;
    }

    public void setLivreAAfficher(Livre livreAAfficher) {
        this.livreAAfficher = livreAAfficher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //
    public String execute() {
        //
        livreAAfficher=infosLivre.infoLivre(id,getText("WSDLLocationLivres"));
        exemplairesDispo=lesExemplaires.exemplairesDisponibles(id,getText("WSDLLocationExemplaire"));
        informationDesindexee=infosLivre.infosDesindexee(id,getText("WSDLLocationLivres"));
        //
        return SUCCESS;
    }
}
