package ServicesBeans;

public enum EtatPreReservation {
    EFFECTIF("Reservation en periode d'effectivité"),
    EFFECTIVITE_DEPASSE("Date d'effectivité de la réservation dépassé"),
    ANNULE("Reservation annulée"),
    ATTENTE("Reservation en liste d'attente"),
    TERMINE("Reservation terminée");

    private String etat;

    EtatPreReservation(String etat){
        this.etat = etat;
    }

    public String toString(){
        return etat;
    }
}
