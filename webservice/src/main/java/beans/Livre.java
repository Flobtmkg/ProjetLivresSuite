package beans;

import java.sql.Date;
import java.time.LocalDate;

public class Livre {
    private int idLivre;
    private String titreLivre;
    private String auteurLivre;
    private String editeurLivre;
    private String datepublicationLivre;
    private String indexationLivre;
    private String descriptionLivre;

    public String getDescriptionLivre() {
        return descriptionLivre;
    }

    public void setDescriptionLivre(String descriptionLivre) {
        this.descriptionLivre = descriptionLivre;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public String getTitreLivre() {
        return titreLivre;
    }

    public void setTitreLivre(String titreLivre) {
        this.titreLivre = titreLivre;
    }

    public String getAuteurLivre() {
        return auteurLivre;
    }

    public void setAuteurLivre(String auteurLivre) {
        this.auteurLivre = auteurLivre;
    }

    public String getEditeurLivre() {
        return editeurLivre;
    }

    public void setEditeurLivre(String editeurLivre) {
        this.editeurLivre = editeurLivre;
    }

    public String getDatepublicationLivre() {
        return datepublicationLivre;
    }

    public void setDatepublicationLivre(String datepublicationLivre) {
        this.datepublicationLivre = datepublicationLivre;
    }

    public void setDatepublicationLivre(Date datepublicationLivre) {
        this.datepublicationLivre = datepublicationLivre.toString();
    }

    public void setDatepublicationLivre(LocalDate datepublicationLivre) {
        this.datepublicationLivre = datepublicationLivre.toString();
    }

    public String getIndexationLivre() {
        return indexationLivre;
    }

    public void setIndexationLivre(String indexationLivre) {
        this.indexationLivre = indexationLivre;
    }

}
