package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import webAppProjetLivre.classesTravail.ListerDao;
import webAppProjetLivre.classesTravail.RechercheSimpleDao;
import webAppProjetLivre.generated.serviceLivre.Livre;

import java.util.List;

public class RechercheComplexeAction extends ActionSupport {
    //données renvoyées via le formulaire
    private String titre;
    private String auteur;
    private String editeur;
    private String leType;
    private String leDomaine;
    private String leTheme;
    private String ouOuEt;
    private boolean mode;

    public boolean isMode() {
        return mode;
    }
    //

    public String getOuOuEt() {
        return ouOuEt;
    }

    public void setOuOuEt(String ouOuEt) {
        this.ouOuEt = ouOuEt;
        if(ouOuEt.equals("OU")==true){
            this.mode=false;
        }else{
            this.mode=true;
        }

    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getLeType() {
        return leType;
    }

    public void setLeType(String leType) {
        this.leType = leType;
    }

    public String getLeDomaine() {
        return leDomaine;
    }

    public void setLeDomaine(String leDomaine) {
        this.leDomaine = leDomaine;
    }

    public String getLeTheme() {
        return leTheme;
    }

    public void setLeTheme(String leTheme) {
        this.leTheme = leTheme;
    }
    //
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


    public String execute() {
        //
        if(listeLivre!=null){
            listeLivre.clear();
        }
        //
        if(this.leType.equals("Selectionnez un type")==true){
            this.setLeType("");
        }
        if(this.leDomaine.equals("Selectionnez un domaine")==true){
            this.setLeDomaine("");
        }
        if(this.leTheme.equals("Selectionnez un thème")==true){
            this.setLeTheme("");
        }
        this.setListeLivre(this.getRecherche().rechercheComplexe(mode,titre,auteur,editeur,leType,leDomaine,leTheme,getText("WSDLLocationLivres")));
        this.setDomaines(this.getListes().rechercherListDomaines(getText("WSDLLocationLivres")));
        this.setThemes(this.getListes().rechercherListTheme(getText("WSDLLocationLivres")));
        this.setTypes(this.getListes().rechercherListType(getText("WSDLLocationLivres")));
        return SUCCESS;
    }
}
