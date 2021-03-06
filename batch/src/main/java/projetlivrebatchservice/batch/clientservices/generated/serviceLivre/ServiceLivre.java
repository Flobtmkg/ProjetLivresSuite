
package projetlivrebatchservice.batch.clientservices.generated.serviceLivre;

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
@WebService(name = "ServiceLivre", targetNamespace = "http://allplatform.webservice/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServiceLivre {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "desindexation", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.Desindexation")
    @ResponseWrapper(localName = "desindexationResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.DesindexationResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/desindexationRequest", output = "http://allplatform.webservice/ServiceLivre/desindexationResponse")
    public List<String> desindexation(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns clientservices.generated.serviceLivre.Livre
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "infoLivre", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.InfoLivre")
    @ResponseWrapper(localName = "infoLivreResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.InfoLivreResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/infoLivreRequest", output = "http://allplatform.webservice/ServiceLivre/infoLivreResponse")
    public Livre infoLivre(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "ajouterLivre", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.AjouterLivre")
    @ResponseWrapper(localName = "ajouterLivreResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.AjouterLivreResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/ajouterLivreRequest", output = "http://allplatform.webservice/ServiceLivre/ajouterLivreResponse")
    public void ajouterLivre(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    String arg1,
            @WebParam(name = "arg2", targetNamespace = "")
                    String arg2,
            @WebParam(name = "arg3", targetNamespace = "")
                    String arg3,
            @WebParam(name = "arg4", targetNamespace = "")
                    String arg4,
            @WebParam(name = "arg5", targetNamespace = "")
                    String arg5);

    /**
     * 
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listerThemes", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.ListerThemes")
    @ResponseWrapper(localName = "listerThemesResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.ListerThemesResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/listerThemesRequest", output = "http://allplatform.webservice/ServiceLivre/listerThemesResponse")
    public List<String> listerThemes();

    /**
     * 
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listerType", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.ListerType")
    @ResponseWrapper(localName = "listerTypeResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.ListerTypeResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/listerTypeRequest", output = "http://allplatform.webservice/ServiceLivre/listerTypeResponse")
    public List<String> listerType();

    /**
     * 
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "listerDomaines", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.ListerDomaines")
    @ResponseWrapper(localName = "listerDomainesResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.ListerDomainesResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/listerDomainesRequest", output = "http://allplatform.webservice/ServiceLivre/listerDomainesResponse")
    public List<String> listerDomaines();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<clientservices.generated.serviceLivre.Livre>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rechercheParType", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheParType")
    @ResponseWrapper(localName = "rechercheParTypeResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheParTypeResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/rechercheParTypeRequest", output = "http://allplatform.webservice/ServiceLivre/rechercheParTypeResponse")
    public List<Livre> rechercheParType(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg7
     * @param arg6
     * @param arg8
     * @return
     *     returns java.util.List<clientservices.generated.serviceLivre.Livre>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rechercherLivreComplxe", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercherLivreComplxe")
    @ResponseWrapper(localName = "rechercherLivreComplxeResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercherLivreComplxeResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/rechercherLivreComplxeRequest", output = "http://allplatform.webservice/ServiceLivre/rechercherLivreComplxeResponse")
    public List<Livre> rechercherLivreComplxe(
            @WebParam(name = "arg0", targetNamespace = "")
                    boolean arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    boolean arg1,
            @WebParam(name = "arg2", targetNamespace = "")
                    String arg2,
            @WebParam(name = "arg3", targetNamespace = "")
                    String arg3,
            @WebParam(name = "arg4", targetNamespace = "")
                    String arg4,
            @WebParam(name = "arg5", targetNamespace = "")
                    int arg5,
            @WebParam(name = "arg6", targetNamespace = "")
                    String arg6,
            @WebParam(name = "arg7", targetNamespace = "")
                    String arg7,
            @WebParam(name = "arg8", targetNamespace = "")
                    String arg8);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<clientservices.generated.serviceLivre.Livre>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rechercheParTitreLivre", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheParTitreLivre")
    @ResponseWrapper(localName = "rechercheParTitreLivreResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheParTitreLivreResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/rechercheParTitreLivreRequest", output = "http://allplatform.webservice/ServiceLivre/rechercheParTitreLivreResponse")
    public List<Livre> rechercheParTitreLivre(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<clientservices.generated.serviceLivre.Livre>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rechercheParTheme", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheParTheme")
    @ResponseWrapper(localName = "rechercheParThemeResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheParThemeResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/rechercheParThemeRequest", output = "http://allplatform.webservice/ServiceLivre/rechercheParThemeResponse")
    public List<Livre> rechercheParTheme(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<clientservices.generated.serviceLivre.Livre>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rechercheParDomaine", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheParDomaine")
    @ResponseWrapper(localName = "rechercheParDomaineResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheParDomaineResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/rechercheParDomaineRequest", output = "http://allplatform.webservice/ServiceLivre/rechercheParDomaineResponse")
    public List<Livre> rechercheParDomaine(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rechercheReferences", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheReferences")
    @ResponseWrapper(localName = "rechercheReferencesResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheReferencesResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/rechercheReferencesRequest", output = "http://allplatform.webservice/ServiceLivre/rechercheReferencesResponse")
    public String rechercheReferences(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<clientservices.generated.serviceLivre.Livre>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rechercheParExemplaire", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheParExemplaire")
    @ResponseWrapper(localName = "rechercheParExemplaireResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheParExemplaireResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/rechercheParExemplaireRequest", output = "http://allplatform.webservice/ServiceLivre/rechercheParExemplaireResponse")
    public List<Livre> rechercheParExemplaire(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<clientservices.generated.serviceLivre.Livre>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rechercheParAuteurLivre", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheParAuteurLivre")
    @ResponseWrapper(localName = "rechercheParAuteurLivreResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheParAuteurLivreResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/rechercheParAuteurLivreRequest", output = "http://allplatform.webservice/ServiceLivre/rechercheParAuteurLivreResponse")
    public List<Livre> rechercheParAuteurLivre(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * 
     * @return
     *     returns java.util.List<clientservices.generated.serviceLivre.IndexConstitution>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "constitutionindex", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.Constitutionindex")
    @ResponseWrapper(localName = "constitutionindexResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.ConstitutionindexResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/constitutionindexRequest", output = "http://allplatform.webservice/ServiceLivre/constitutionindexResponse")
    public List<IndexConstitution> constitutionindex();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<clientservices.generated.serviceLivre.Livre>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rechercheParEditeurLivre", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheParEditeurLivre")
    @ResponseWrapper(localName = "rechercheParEditeurLivreResponse", targetNamespace = "http://allplatform.webservice/", className = "clientservices.generated.serviceLivre.RechercheParEditeurLivreResponse")
    @Action(input = "http://allplatform.webservice/ServiceLivre/rechercheParEditeurLivreRequest", output = "http://allplatform.webservice/ServiceLivre/rechercheParEditeurLivreResponse")
    public List<Livre> rechercheParEditeurLivre(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

}
