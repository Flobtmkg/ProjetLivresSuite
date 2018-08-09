package ServicesBeans;

import java.util.Date;

public class PreReservation {


    int idReservation;
    int idLivre;
    int idUtilisateur;
    int idPret;
    Date dateReservation;
    Date dateDisponibilite;
    boolean isannule;

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdPret() {
        return idPret;
    }

    public void setIdPret(int idPret) {
        this.idPret = idPret;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Date getDateDisponibilite() {
        return dateDisponibilite;
    }

    public void setDateDisponibilite(Date dateDisponibilite) {
        this.dateDisponibilite = dateDisponibilite;
    }

    public boolean isIsannule() {
        return isannule;
    }

    public void setIsannule(boolean isannule) {
        this.isannule = isannule;
    }

}
