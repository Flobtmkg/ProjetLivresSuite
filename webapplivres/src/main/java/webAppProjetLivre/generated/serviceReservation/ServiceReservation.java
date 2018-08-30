
package webAppProjetLivre.generated.serviceReservation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ServiceReservation", targetNamespace = "http://allplatform.webservice/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServiceReservation {


    /**
     * 
     * @return
     *     returns java.util.List<clientservices.generated.serviceReservation.PreReservation>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getListeReservationTempsEffectfDepasse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.GetListeReservationTempsEffectfDepasse")
    @ResponseWrapper(localName = "getListeReservationTempsEffectfDepasseResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.GetListeReservationTempsEffectfDepasseResponse")
    @Action(input = "http://allplatform.webservice/ServiceReservation/getListeReservationTempsEffectfDepasseRequest", output = "http://allplatform.webservice/ServiceReservation/getListeReservationTempsEffectfDepasseResponse")
    public List<PreReservation> getListeReservationTempsEffectfDepasse();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<clientservices.generated.serviceReservation.PreReservation>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getListeAttente", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.GetListeAttente")
    @ResponseWrapper(localName = "getListeAttenteResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.GetListeAttenteResponse")
    @Action(input = "http://allplatform.webservice/ServiceReservation/getListeAttenteRequest", output = "http://allplatform.webservice/ServiceReservation/getListeAttenteResponse")
    public List<PreReservation> getListeAttente(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addReservation", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.AddReservation")
    @ResponseWrapper(localName = "addReservationResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.AddReservationResponse")
    @Action(input = "http://allplatform.webservice/ServiceReservation/addReservationRequest", output = "http://allplatform.webservice/ServiceReservation/addReservationResponse")
    public void addReservation(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    int arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<clientservices.generated.serviceReservation.PreReservation>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getReservationEffectiveByLivre", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.GetReservationEffectiveByLivre")
    @ResponseWrapper(localName = "getReservationEffectiveByLivreResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.GetReservationEffectiveByLivreResponse")
    @Action(input = "http://allplatform.webservice/ServiceReservation/getReservationEffectiveByLivreRequest", output = "http://allplatform.webservice/ServiceReservation/getReservationEffectiveByLivreResponse")
    public List<PreReservation> getReservationEffectiveByLivre(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<clientservices.generated.serviceReservation.PreReservation>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getListeReservationByUser", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.GetListeReservationByUser")
    @ResponseWrapper(localName = "getListeReservationByUserResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.GetListeReservationByUserResponse")
    @Action(input = "http://allplatform.webservice/ServiceReservation/getListeReservationByUserRequest", output = "http://allplatform.webservice/ServiceReservation/getListeReservationByUserResponse")
    public List<PreReservation> getListeReservationByUser(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "annulationManuelleReservation", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.AnnulationManuelleReservation")
    @ResponseWrapper(localName = "annulationManuelleReservationResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.AnnulationManuelleReservationResponse")
    @Action(input = "http://allplatform.webservice/ServiceReservation/annulationManuelleReservationRequest", output = "http://allplatform.webservice/ServiceReservation/annulationManuelleReservationResponse")
    public void annulationManuelleReservation(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "affectationPretReservation", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.AffectationPretReservation")
    @ResponseWrapper(localName = "affectationPretReservationResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.AffectationPretReservationResponse")
    @Action(input = "http://allplatform.webservice/ServiceReservation/affectationPretReservationRequest", output = "http://allplatform.webservice/ServiceReservation/affectationPretReservationResponse")
    public void affectationPretReservation(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    int arg1);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addDateDisponibiliteReservation", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.AddDateDisponibiliteReservation")
    @ResponseWrapper(localName = "addDateDisponibiliteReservationResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.AddDateDisponibiliteReservationResponse")
    @Action(input = "http://allplatform.webservice/ServiceReservation/addDateDisponibiliteReservationRequest", output = "http://allplatform.webservice/ServiceReservation/addDateDisponibiliteReservationResponse")
    public void addDateDisponibiliteReservation(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "emailDepassementEnvoye", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.EmailDepassementEnvoye")
    @ResponseWrapper(localName = "emailDepassementEnvoyeResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.EmailDepassementEnvoyeResponse")
    @Action(input = "http://allplatform.webservice/ServiceReservation/emailDepassementEnvoyeRequest", output = "http://allplatform.webservice/ServiceReservation/emailDepassementEnvoyeResponse")
    public void emailDepassementEnvoye(
            @WebParam(name = "arg0", targetNamespace = "")
                    List<Integer> arg0);

    /**
     * 
     * @return
     *     returns java.util.List<clientservices.generated.serviceReservation.PreReservation>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getReservationsEffectives", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.GetReservationsEffectives")
    @ResponseWrapper(localName = "getReservationsEffectivesResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceReservation.GetReservationsEffectivesResponse")
    @Action(input = "http://allplatform.webservice/ServiceReservation/getReservationsEffectivesRequest", output = "http://allplatform.webservice/ServiceReservation/getReservationsEffectivesResponse")
    public List<PreReservation> getReservationsEffectives();

}
