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
        //Debut recherche des variables d'env qui doivent être prioritaires;
        String getEnv=System.getenv("WSDLLocationLivres");
        if(getEnv!=null && getEnv.equals("")==false){
            livreAAfficher=infosLivre.infoLivre(id,getEnv);
        }else{
            livreAAfficher=infosLivre.infoLivre(id,getText("WSDLLocationLivres"));
        }
        //Fin recherche des variables d'env qui doivent être prioritaires;
        getEnv=System.getenv("WSDLLocationExemplaire");
        //Debut recherche des variables d'env qui doivent être prioritaires;
        if(getEnv!=null && getEnv.equals("")==false){
            exemplairesDispo=lesExemplaires.exemplairesDisponibles(id,getEnv);
        }else{
            exemplairesDispo=lesExemplaires.exemplairesDisponibles(id,getText("WSDLLocationExemplaire"));
        }
        //Fin recherche des variables d'env qui doivent être prioritaires;
        getEnv=System.getenv("WSDLLocationLivres");
        //Debut recherche des variables d'env qui doivent être prioritaires;
        if(getEnv!=null && getEnv.equals("")==false){
            informationDesindexee=infosLivre.infosDesindexee(id,getEnv);
        }else{
            informationDesindexee=infosLivre.infosDesindexee(id,getText("WSDLLocationLivres"));
        }
        //Fin recherche des variables d'env qui doivent être prioritaires;
        //
        return SUCCESS;
    }
}
