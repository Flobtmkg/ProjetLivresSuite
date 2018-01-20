package beans;

public class Reservation {

    //
    Utilisateur utilisateurReservation;
    Pret pretReservation;
    Exemplaire exemplaireReservation;
    Livre livreReservation;
    //

    public Livre getLivreReservation() {
        return livreReservation;
    }

    public void setLivreReservation(Livre livreReservation) {
        this.livreReservation = livreReservation;
    }

    public Utilisateur getUtilisateurReservation() {
        return utilisateurReservation;
    }

    public void setUtilisateurReservation(Utilisateur utilisateurReservation) {
        this.utilisateurReservation = utilisateurReservation;
    }

    public Pret getPretReservation() {
        return pretReservation;
    }

    public void setPretReservation(Pret pretReservation) {
        this.pretReservation = pretReservation;
    }

    public Exemplaire getExemplaireReservation() {
        return exemplaireReservation;
    }

    public void setExemplaireReservation(Exemplaire exemplaireReservation) {
        this.exemplaireReservation = exemplaireReservation;
    }

}
