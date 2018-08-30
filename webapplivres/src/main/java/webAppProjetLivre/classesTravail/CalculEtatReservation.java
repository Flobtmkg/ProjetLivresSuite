package webAppProjetLivre.classesTravail;

import webAppProjetLivre.generated.serviceReservation.PreReservation;

import java.util.Calendar;

public class CalculEtatReservation {


    public static EtatPreReservation getEtatReservation(PreReservation reserv) {

        Calendar today = Calendar.getInstance();
        Calendar dateLimite = (Calendar)reserv.getDateDisponibilite().toGregorianCalendar().clone();
        dateLimite.add(Calendar.HOUR, 48);
        if(reserv.getDateDisponibilite()!=null && reserv.isAnnule()==false && reserv.getIdPret()==0) {

            if (today.before(dateLimite)) {
                // Periode d'effectivité de la reservation
                return EtatPreReservation.EFFECTIF;
            } else {
                // Periode d'effectivité de la reservation dépassé
                return EtatPreReservation.EFFECTIVITE_DEPASSE;
            }
        }else if(reserv.isAnnule()==true && reserv.getIdPret()==0) {
            // la reservation est annulée manuellement par l'utilisateur
            return EtatPreReservation.ANNULE;
        }else if(reserv.getIdPret()==0){
            // la reservation est en liste d'attente
            return EtatPreReservation.ATTENTE;
        }else{
            // la reservation à été validée un pret est associé à cette reservation
            return EtatPreReservation.TERMINE;
        }
    }

}
