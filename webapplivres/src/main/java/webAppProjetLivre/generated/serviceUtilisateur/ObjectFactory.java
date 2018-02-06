
package webAppProjetLivre.generated.serviceUtilisateur;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated.serviceUtilisateur package. 
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

    private final static QName _AutentifierResponse_QNAME = new QName("http://allplatform.webservice/", "autentifierResponse");
    private final static QName _AjoutUtilisateur_QNAME = new QName("http://allplatform.webservice/", "ajoutUtilisateur");
    private final static QName _Autentifier_QNAME = new QName("http://allplatform.webservice/", "autentifier");
    private final static QName _AjoutUtilisateurResponse_QNAME = new QName("http://allplatform.webservice/", "ajoutUtilisateurResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated.serviceUtilisateur
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AutentifierResponse }
     * 
     */
    public AutentifierResponse createAutentifierResponse() {
        return new AutentifierResponse();
    }

    /**
     * Create an instance of {@link AjoutUtilisateur }
     * 
     */
    public AjoutUtilisateur createAjoutUtilisateur() {
        return new AjoutUtilisateur();
    }

    /**
     * Create an instance of {@link Autentifier }
     * 
     */
    public Autentifier createAutentifier() {
        return new Autentifier();
    }

    /**
     * Create an instance of {@link AjoutUtilisateurResponse }
     * 
     */
    public AjoutUtilisateurResponse createAjoutUtilisateurResponse() {
        return new AjoutUtilisateurResponse();
    }

    /**
     * Create an instance of {@link Utilisateur }
     * 
     */
    public Utilisateur createUtilisateur() {
        return new Utilisateur();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AutentifierResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://allplatform.webservice/", name = "autentifierResponse")
    public JAXBElement<AutentifierResponse> createAutentifierResponse(AutentifierResponse value) {
        return new JAXBElement<AutentifierResponse>(_AutentifierResponse_QNAME, AutentifierResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjoutUtilisateur }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://allplatform.webservice/", name = "ajoutUtilisateur")
    public JAXBElement<AjoutUtilisateur> createAjoutUtilisateur(AjoutUtilisateur value) {
        return new JAXBElement<AjoutUtilisateur>(_AjoutUtilisateur_QNAME, AjoutUtilisateur.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Autentifier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://allplatform.webservice/", name = "autentifier")
    public JAXBElement<Autentifier> createAutentifier(Autentifier value) {
        return new JAXBElement<Autentifier>(_Autentifier_QNAME, Autentifier.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjoutUtilisateurResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://allplatform.webservice/", name = "ajoutUtilisateurResponse")
    public JAXBElement<AjoutUtilisateurResponse> createAjoutUtilisateurResponse(AjoutUtilisateurResponse value) {
        return new JAXBElement<AjoutUtilisateurResponse>(_AjoutUtilisateurResponse_QNAME, AjoutUtilisateurResponse.class, null, value);
    }

}
