
package webAppProjetLivre.generated.servicePret;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour reservation complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="reservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="exemplaireReservation" type="{http://allplatform.webservice/}exemplaire" minOccurs="0"/>
 *         &lt;element name="livreReservation" type="{http://allplatform.webservice/}livre" minOccurs="0"/>
 *         &lt;element name="pretReservation" type="{http://allplatform.webservice/}pret" minOccurs="0"/>
 *         &lt;element name="utilisateurReservation" type="{http://allplatform.webservice/}utilisateur" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservation1", propOrder = {
    "exemplaireReservation",
    "livreReservation",
    "pretReservation",
    "utilisateurReservation"
})
public class Reservation {

    protected Exemplaire exemplaireReservation;
    protected Livre livreReservation;
    protected Pret pretReservation;
    protected Utilisateur utilisateurReservation;

    /**
     * Obtient la valeur de la propri�t� exemplaireReservation.
     * 
     * @return
     *     possible object is
     *     {@link Exemplaire }
     *     
     */
    public Exemplaire getExemplaireReservation() {
        return exemplaireReservation;
    }

    /**
     * D�finit la valeur de la propri�t� exemplaireReservation.
     * 
     * @param value
     *     allowed object is
     *     {@link Exemplaire }
     *     
     */
    public void setExemplaireReservation(Exemplaire value) {
        this.exemplaireReservation = value;
    }

    /**
     * Obtient la valeur de la propri�t� livreReservation.
     * 
     * @return
     *     possible object is
     *     {@link Livre }
     *     
     */
    public Livre getLivreReservation() {
        return livreReservation;
    }

    /**
     * D�finit la valeur de la propri�t� livreReservation.
     * 
     * @param value
     *     allowed object is
     *     {@link Livre }
     *     
     */
    public void setLivreReservation(Livre value) {
        this.livreReservation = value;
    }

    /**
     * Obtient la valeur de la propri�t� pretReservation.
     * 
     * @return
     *     possible object is
     *     {@link Pret }
     *     
     */
    public Pret getPretReservation() {
        return pretReservation;
    }

    /**
     * D�finit la valeur de la propri�t� pretReservation.
     * 
     * @param value
     *     allowed object is
     *     {@link Pret }
     *     
     */
    public void setPretReservation(Pret value) {
        this.pretReservation = value;
    }

    /**
     * Obtient la valeur de la propri�t� utilisateurReservation.
     * 
     * @return
     *     possible object is
     *     {@link Utilisateur }
     *     
     */
    public Utilisateur getUtilisateurReservation() {
        return utilisateurReservation;
    }

    /**
     * D�finit la valeur de la propri�t� utilisateurReservation.
     * 
     * @param value
     *     allowed object is
     *     {@link Utilisateur }
     *     
     */
    public void setUtilisateurReservation(Utilisateur value) {
        this.utilisateurReservation = value;
    }

}
