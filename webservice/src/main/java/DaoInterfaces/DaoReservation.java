package DaoInterfaces;

import ServicesBeans.PreReservation;

import java.util.List;

public interface DaoReservation {

    List<PreReservation> getListeAttente(int idLivre);
    List<PreReservation> getListeReservationByUser(int idUtilisateur);
    List<PreReservation> getReservationEffectiveByLivre(int idLivre);
    List<PreReservation> getAllReservationsEffectives();
    List<PreReservation> getEnvoiEmailListeReservationTempsEffectfDepasse();
    PreReservation getReservationById(int idReservation);
    void annulerReservation(int idReservation);
    void defDateDisponibiliteReservation(int idReservation);
    void affectationPretReservation(int idReservation, int idPret);
    void affectationsEmailDepassementSent(List<Integer> listIdReservation);
    void addReservation(PreReservation reservationInput, boolean addDateDispo);
}
