
package webAppProjetLivre.generated.serviceNotation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated.serviceNotation package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _NotationLivreResponse_QNAME = new QName("http://allplatform.webservice/", "notationLivreResponse");
    private final static QName _NotationLivre_QNAME = new QName("http://allplatform.webservice/", "notationLivre");
    private final static QName _AjouterNotationResponse_QNAME = new QName("http://allplatform.webservice/", "ajouterNotationResponse");
    private final static QName _AjouterNotation_QNAME = new QName("http://allplatform.webservice/", "ajouterNotation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated.serviceNotation
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NotationLivreResponse }
     * 
     */
    public NotationLivreResponse createNotationLivreResponse() {
        return new NotationLivreResponse();
    }

    /**
     * Create an instance of {@link NotationLivre }
     * 
     */
    public NotationLivre createNotationLivre() {
        return new NotationLivre();
    }

    /**
     * Create an instance of {@link AjouterNotationResponse }
     * 
     */
    public AjouterNotationResponse createAjouterNotationResponse() {
        return new AjouterNotationResponse();
    }

    /**
     * Create an instance of {@link AjouterNotation }
     * 
     */
    public AjouterNotation createAjouterNotation() {
        return new AjouterNotation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotationLivreResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://allplatform.webservice/", name = "notationLivreResponse")
    public JAXBElement<NotationLivreResponse> createNotationLivreResponse(NotationLivreResponse value) {
        return new JAXBElement<NotationLivreResponse>(_NotationLivreResponse_QNAME, NotationLivreResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NotationLivre }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://allplatform.webservice/", name = "notationLivre")
    public JAXBElement<NotationLivre> createNotationLivre(NotationLivre value) {
        return new JAXBElement<NotationLivre>(_NotationLivre_QNAME, NotationLivre.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjouterNotationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://allplatform.webservice/", name = "ajouterNotationResponse")
    public JAXBElement<AjouterNotationResponse> createAjouterNotationResponse(AjouterNotationResponse value) {
        return new JAXBElement<AjouterNotationResponse>(_AjouterNotationResponse_QNAME, AjouterNotationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjouterNotation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://allplatform.webservice/", name = "ajouterNotation")
    public JAXBElement<AjouterNotation> createAjouterNotation(AjouterNotation value) {
        return new JAXBElement<AjouterNotation>(_AjouterNotation_QNAME, AjouterNotation.class, null, value);
    }

}
