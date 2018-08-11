package webservice.allplatform;

import ServicesBeans.Exemplaire;
import ServicesBeans.PreReservation;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class ServiceExemplaire extends Service {

    @WebMethod
    public ArrayList<Exemplaire> ExemplaireDisponible(boolean outputMode,int idLivre){
        ArrayList<Exemplaire> outputExemplaires=monDaoExemplaire.ExemplaireDisponible(outputMode,idLivre);
        return outputExemplaires;
    }

    @WebMethod
    public ArrayList<Exemplaire> listerExemplaire(int idLivre){
        ArrayList<Exemplaire> outputListExemplaire=monDaoExemplaire.listerExemplaire(idLivre);
        return outputListExemplaire;
    }

    @WebMethod
    public void AjouterExemplaire(int idLivre, String coteExemplaire, String remarqueExemplaire){
        Exemplaire exemplaireInput = new Exemplaire();
        exemplaireInput.setIdExemplaire(0);
        exemplaireInput.setIdLivre(idLivre);
        exemplaireInput.setCoteExemplaire(coteExemplaire);
        exemplaireInput.setRemarqueExemplaire(remarqueExemplaire);
        monDaoExemplaire.AjouterExemplaire(exemplaireInput);
        // Vérification de la liste d'attente pour affecter la première reservation en attente si elle existe
        List<PreReservation> attenteLivreEnCours = monDaoReservation.getListeAttente(idLivre);
        if(!attenteLivreEnCours.isEmpty()){
            // On défini une date de disponibilité pour la première réservation de la liste d'attente.
            // la résrvation en question devient donc effective
            monDaoReservation.defDateDisponibiliteReservation(attenteLivreEnCours.get(0).getIdReservation());
        }
    }

}
