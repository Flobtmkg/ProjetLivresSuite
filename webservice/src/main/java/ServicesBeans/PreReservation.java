package ServicesBeans;

import java.util.Calendar;
import java.util.Date;

public class PreReservation {


    int idReservation;
    int idLivre;
    int idUtilisateur;
    int idPret;
    Date dateReservation;
    Date dateDisponibilite;
    boolean annule;
    boolean emailDepassementSent;

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

    public boolean isAnnule() {
        return annule;
    }

    public void setAnnule(boolean annule) {
        this.annule = annule;
    }

    public boolean isEmailDepassementSent() {
        return emailDepassementSent;
    }

    public void setEmailDepassementSent(boolean emailDepassementSent) {
        this.emailDepassementSent = emailDepassementSent;
    }


    public String getEtatReservation() {
        Calendar todayLess48 = Calendar.getInstance();
        todayLess48.add(Calendar.HOUR,-48);
        if(dateDisponibilite!=null && annule==false && idPret==0) {
            if (dateDisponibilite.before(todayLess48.getTime())) {
                // Periode d'effectivité de la reservation
                return EtatPreReservation.EFFECTIF.toString();
            } else {
                // Periode d'effectivité de la reservation dépassé
                return EtatPreReservation.EFFECTIVITE_DEPASSE.toString();
            }
        }else if(annule==true && idPret==0) {
            // la reservation est annulée manuellement par l'utilisateur
            return EtatPreReservation.ANNULE.toString();
        }else if(idPret==0){
            // la reservation est en liste d'attente
            return EtatPreReservation.ATTENTE.toString();
        }else{
            // la reservation à été validée un pret est associé à cette reservation
            return EtatPreReservation.TERMINE.toString();
        }
    }


}
