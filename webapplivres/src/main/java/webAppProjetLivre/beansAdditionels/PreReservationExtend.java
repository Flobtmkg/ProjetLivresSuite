package webAppProjetLivre.beansAdditionels;

import webAppProjetLivre.classesTravail.EtatPreReservation;
import webAppProjetLivre.generated.serviceLivre.Livre;
import webAppProjetLivre.generated.serviceReservation.PreReservation;

import java.util.GregorianCalendar;

public class PreReservationExtend {

    // datas
    EtatPreReservation state;
    int positionListAttente;
    String dateProchainRetourPrevu;
    PreReservation reservation;
    Livre livrereserve;



    // elements préconfigurés pour l'affichage
    String dateResFR;
    String stateStr;




    public PreReservationExtend(PreReservation reservationInput,EtatPreReservation stateInput, String dateProchainRetourPrevuInput, int positionListAttenteInput, Livre livrereserveInput ){
        this.state=stateInput;
        this.reservation=reservationInput;
        this.dateProchainRetourPrevu=dateProchainRetourPrevuInput;
        this.positionListAttente=positionListAttenteInput;
        this.livrereserve=livrereserveInput;
        // alimentation des champs d'affichage
        alimentationDesChamps();
    }


    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }


    public PreReservation getReservation() {
        return reservation;
    }

    public void setReservation(PreReservation reservation) {
        this.reservation = reservation;
    }


    public Livre getLivrereserve() {
        return livrereserve;
    }

    public void setLivrereserve(Livre livrereserve) {
        this.livrereserve = livrereserve;
    }


    public EtatPreReservation getState() {
        return state;
    }

    public void setState(EtatPreReservation state) {
        this.state = state;
    }

    public int getPositionListAttente() {
        return positionListAttente;
    }

    public void setPositionListAttente(int positionListAttente) {
        this.positionListAttente = positionListAttente;
    }


    public String getDateResFR() {
        return dateResFR;
    }

    public void setDateResFR(String dateResFR) {
        this.dateResFR = dateResFR;
    }


    public String getDateProchainRetourPrevu() {
        return dateProchainRetourPrevu;
    }

    public void setDateProchainRetourPrevu(String dateProchainRetourPrevu) {
        this.dateProchainRetourPrevu = dateProchainRetourPrevu;
    }

    private void alimentationDesChamps(){
        String d = String.valueOf(reservation.getDateReservation().toGregorianCalendar().get(GregorianCalendar.DAY_OF_MONTH));
        String m = String.valueOf(reservation.getDateReservation().toGregorianCalendar().get(GregorianCalendar.MONTH));
        String y = String.valueOf(reservation.getDateReservation().toGregorianCalendar().get(GregorianCalendar.YEAR));
        if(d.length()<2){
            d="0" + d;
        }
        if(m.length()<2){
            m="0" + m;
        }
        this.dateResFR = d + "/" + m + "/" + y;
        this.stateStr = state.toString();
    }

}
