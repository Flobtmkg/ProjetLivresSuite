package webservice.allplatform;


import ServicesBeans.EtatPreReservation;
import ServicesBeans.Exemplaire;
import ServicesBeans.PreReservation;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class ServiceReservation extends Service {

    // liste d'attente pour un livre
    @WebMethod
    public List<PreReservation> getListeAttente(int idLivre){
        List<PreReservation> listAttente = monDaoReservation.getListeAttente(idLivre);
        return listAttente;
    }

    // liste des reservations demandées par utilisateur
    @WebMethod
    public List<PreReservation> getListeReservationByUser(int idUtilisateur){
        List<PreReservation> listReservation = monDaoReservation.getListeReservationByUser(idUtilisateur);
        return listReservation;
    }

    // reservations dans la période des 48h pour le livre (idLivre)
    @WebMethod
    public List<PreReservation> getReservationEffectiveByLivre(int idLivre){
        List<PreReservation> reservationsEffectives = monDaoReservation.getReservationEffectiveByLivre(idLivre);
        return reservationsEffectives;
    }

    // Liste des reservations période des 48h dépassées pour l'ensemble des livres pour envoi d'email
    @WebMethod
    public List<PreReservation> getListeReservationTempsEffectfDepasse(){
        List<PreReservation> listReservation = monDaoReservation.getEnvoiEmailListeReservationTempsEffectfDepasse();
        return listReservation;
    }

    @WebMethod
    public void annulationManuelleReservation(int idReservation){
        // On vérifie si la réservation en cours est effective
        PreReservation reservationEnCours = getPreReservationById(idReservation);
        List<PreReservation> listReservationsEffectives = monDaoReservation.getReservationEffectiveByLivre(reservationEnCours.getIdLivre());
        // On annule la reservation
        monDaoReservation.annulerReservation(idReservation);
        // Test de réservation en cours effective
        if(reservationEnCours.getEtatReservation().equals(EtatPreReservation.EFFECTIF.toString())){
            List<PreReservation> attenteLivreEnCours = monDaoReservation.getListeAttente(reservationEnCours.getIdLivre());
            if(!attenteLivreEnCours.isEmpty()){
                // On défini une date de disponibilité pour la première réservation de la liste d'attente.
                // la résrvation en question devient donc effective
                monDaoReservation.defDateDisponibiliteReservation(attenteLivreEnCours.get(0).getIdReservation());
            }
        }
    }

    // def la date du jour comme date de disponibilité de la reservation qui devient alors effective
    @WebMethod
    public void addDateDisponibiliteReservation(int idReservation){
        monDaoReservation.defDateDisponibiliteReservation(idReservation);
    }

    @WebMethod
    public void addReservation(int idLivre, int idUtilisateur){
        // Construction de l'objet de reservation à ajouter
        PreReservation reservationInput = new PreReservation();
        reservationInput.setIdLivre(idLivre);
        reservationInput.setIdUtilisateur(idUtilisateur);
        // test de disponibilité
        List<Exemplaire> listExDispo = monDaoExemplaire.ExemplaireDisponible(false,reservationInput.getIdLivre());
        List<PreReservation> reservationsEffectives = monDaoReservation.getReservationEffectiveByLivre(reservationInput.getIdLivre());
        if((listExDispo.size() - reservationsEffectives.size())<=0){
            // liste attente
            monDaoReservation.addReservation(reservationInput,false);
        }else{
            // reservation effective
            monDaoReservation.addReservation(reservationInput,true);
        }
    }

    @WebMethod
    public void affectationPretReservation(int idReservation, int idPret){
        monDaoReservation.affectationPretReservation(idReservation,idPret);
    }

    @WebMethod
    public void emailDepassementEnvoye(List<Integer> idReservationList){
        monDaoReservation.affectationsEmailDepassementSent(idReservationList);
    }

    private PreReservation getPreReservationById(int idReservation){
        return monDaoReservation.getReservationById(idReservation);
    }

}
