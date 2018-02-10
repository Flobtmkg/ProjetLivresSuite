package ServicesBeans;


import java.time.LocalDate;
import java.sql.Date;


public class Pret {
    private int idPret;
    private int idUtilisateur;
    private int idExemplaire;
    private String dateDebutPret;
    private String dateFinPret;
    private boolean prolongePret;
    private boolean renduPret;
    //
    public int getIdPret() {
        return idPret;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public int getIdExemplaire() {
        return idExemplaire;
    }

    public String getDateDebutPret() {
        return dateDebutPret;
    }

    public String getDateFinPret() {
        return dateFinPret;
    }

    public boolean isProlongePret() {
        return prolongePret;
    }

    public boolean isRenduPret() {
        return renduPret;
    }

    public void setIdPret(int idPret) {
        this.idPret = idPret;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setIdExemplaire(int idExemplaire) {
        this.idExemplaire = idExemplaire;
    }


    public void setDateDebutPret(LocalDate dateDebutPret) {
        this.dateDebutPret = dateDebutPret.toString();
    }

    public void setDateDebutPret(Date dateDebutPretinput) {
        this.dateDebutPret = dateDebutPretinput.toString();
    }

    public void setDateDebutPret(String dateDebutPretinput) {
        this.dateDebutPret = dateDebutPretinput;
    }

    public void setDateFinPret(LocalDate dateFinPret) {
        this.dateFinPret = dateFinPret.toString();
    }

    public void setDateFinPret(Date dateFinPretinput) {
        this.dateFinPret = dateFinPretinput.toString();
    }

    public void setDateFinPret(String dateFinPretinput) {
        this.dateFinPret = dateFinPretinput;
    }

    public void setProlongePret(boolean prolongePret) {
        this.prolongePret = prolongePret;
    }

    public void setRenduPret(boolean renduPret) {
        this.renduPret = renduPret;
    }

}
