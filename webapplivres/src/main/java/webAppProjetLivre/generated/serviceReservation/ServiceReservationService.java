
package webAppProjetLivre.generated.serviceReservation;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ServiceReservationService", targetNamespace = "http://allplatform.webservice/", wsdlLocation = "http://lfr017644:8080/webservice/ServiceReservationService?wsdl")
public class ServiceReservationService
    extends Service
{

    private final static URL SERVICERESERVATIONSERVICE_WSDL_LOCATION;
    private final static WebServiceException SERVICERESERVATIONSERVICE_EXCEPTION;
    private final static QName SERVICERESERVATIONSERVICE_QNAME = new QName("http://allplatform.webservice/", "ServiceReservationService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://lfr017644:8080/webservice/ServiceReservationService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SERVICERESERVATIONSERVICE_WSDL_LOCATION = url;
        SERVICERESERVATIONSERVICE_EXCEPTION = e;
    }

    public ServiceReservationService() {
        super(__getWsdlLocation(), SERVICERESERVATIONSERVICE_QNAME);
    }

    public ServiceReservationService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERVICERESERVATIONSERVICE_QNAME, features);
    }

    public ServiceReservationService(URL wsdlLocation) {
        super(wsdlLocation, SERVICERESERVATIONSERVICE_QNAME);
    }

    public ServiceReservationService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICERESERVATIONSERVICE_QNAME, features);
    }

    public ServiceReservationService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServiceReservationService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ServiceReservation
     */
    @WebEndpoint(name = "ServiceReservationPort")
    public ServiceReservation getServiceReservationPort() {
        return super.getPort(new QName("http://allplatform.webservice/", "ServiceReservationPort"), ServiceReservation.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServiceReservation
     */
    @WebEndpoint(name = "ServiceReservationPort")
    public ServiceReservation getServiceReservationPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://allplatform.webservice/", "ServiceReservationPort"), ServiceReservation.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SERVICERESERVATIONSERVICE_EXCEPTION!= null) {
            throw SERVICERESERVATIONSERVICE_EXCEPTION;
        }
        return SERVICERESERVATIONSERVICE_WSDL_LOCATION;
    }

}