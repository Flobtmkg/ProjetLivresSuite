
package webAppProjetLivre.generated.servicePret;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;



/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ServicePretService", targetNamespace = "http://allplatform.webservice/")
public class ServicePretService
    extends Service
{

    private final static URL SERVICEPRETSERVICE_WSDL_LOCATION;
    private final static WebServiceException SERVICEPRETSERVICE_EXCEPTION;
    private final static QName SERVICEPRETSERVICE_QNAME = new QName("http://allplatform.webservice/", "ServicePretService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SERVICEPRETSERVICE_WSDL_LOCATION = url;
        SERVICEPRETSERVICE_EXCEPTION = e;
    }

    public ServicePretService() {
        super(__getWsdlLocation(), SERVICEPRETSERVICE_QNAME);
    }

    public ServicePretService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SERVICEPRETSERVICE_QNAME, features);
    }

    public ServicePretService(URL wsdlLocation) {
        super(wsdlLocation, SERVICEPRETSERVICE_QNAME);
    }

    public ServicePretService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICEPRETSERVICE_QNAME, features);
    }

    public ServicePretService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ServicePretService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ServicePret
     */
    @WebEndpoint(name = "ServicePretPort")
    public ServicePret getServicePretPort() {
        return super.getPort(new QName("http://allplatform.webservice/", "ServicePretPort"), ServicePret.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ServicePret
     */
    @WebEndpoint(name = "ServicePretPort")
    public ServicePret getServicePretPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://allplatform.webservice/", "ServicePretPort"), ServicePret.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SERVICEPRETSERVICE_EXCEPTION!= null) {
            throw SERVICEPRETSERVICE_EXCEPTION;
        }
        return SERVICEPRETSERVICE_WSDL_LOCATION;
    }

}
