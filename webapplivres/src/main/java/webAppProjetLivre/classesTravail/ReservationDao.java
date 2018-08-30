package webAppProjetLivre.classesTravail;

import webAppProjetLivre.generated.serviceReservation.PreReservation;
import webAppProjetLivre.generated.serviceReservation.ServiceReservation;
import webAppProjetLivre.generated.serviceReservation.ServiceReservationService;

import java.net.URL;
import java.util.List;

public class ReservationDao {
    private List<PreReservation> resultat;
    private URL urlWsdl;

    public void ajoutReservation(int idLivre, int idUtilisateur, String wsdlAdress) throws Exception{
        urlWsdl=new URL(wsdlAdress);
        //
        ServiceReservationService monservice= new ServiceReservationService(urlWsdl);
        ServiceReservation accesReservation=monservice.getServiceReservationPort();
        accesReservation.addReservation(idLivre,idUtilisateur);
        //
    }

    public void addDateDisponibiliteReservation(int idReservation, String wsdlAdress) throws Exception{
        urlWsdl=new URL(wsdlAdress);
        //
        ServiceReservationService monservice= new ServiceReservationService(urlWsdl);
        ServiceReservation accesReservation=monservice.getServiceReservationPort();
        accesReservation.addDateDisponibiliteReservation(idReservation);
        //
    }

    public void annulationManuelleReservation(int idReservation, String wsdlAdress) throws Exception{
        urlWsdl=new URL(wsdlAdress);
        //
        ServiceReservationService monservice = new ServiceReservationService(urlWsdl);
        ServiceReservation accesReservation=monservice.getServiceReservationPort();
        accesReservation.annulationManuelleReservation(idReservation);
        //
    }

    public List<PreReservation> getListeAttente(int idLivre, String wsdlAdress) throws Exception{
        urlWsdl=new URL(wsdlAdress);
        //
        ServiceReservationService monservice = new ServiceReservationService(urlWsdl);
        ServiceReservation accesReservation=monservice.getServiceReservationPort();
        List<PreReservation> reservations = accesReservation.getListeAttente(idLivre);
        //
        return reservations;
    }

    public List<PreReservation> getListeReservationByUser(int idUtilisateur, String wsdlAdress) throws Exception{
        urlWsdl=new URL(wsdlAdress);
        //
        ServiceReservationService monservice = new ServiceReservationService(urlWsdl);
        ServiceReservation accesReservation=monservice.getServiceReservationPort();
        List<PreReservation> reservations = accesReservation.getListeReservationByUser(idUtilisateur);
        //
        return reservations;
    }

    public List<PreReservation> getReservationEffectiveByLivre(int idLivre, String wsdlAdress) throws Exception{
        urlWsdl=new URL(wsdlAdress);
        //
        ServiceReservationService monservice = new ServiceReservationService(urlWsdl);
        ServiceReservation accesReservation=monservice.getServiceReservationPort();
        List<PreReservation> reservations = accesReservation.getReservationEffectiveByLivre(idLivre);
        //
        return reservations;
    }

}
