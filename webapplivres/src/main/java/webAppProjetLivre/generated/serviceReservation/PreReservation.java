
package webAppProjetLivre.generated.serviceReservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour preReservation complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="preReservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="annule" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="dateDisponibilite" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dateReservation" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="emailDepassementSent" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idLivre" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idPret" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idReservation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idUtilisateur" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "preReservation", propOrder = {
    "annule",
    "dateDisponibilite",
    "dateReservation",
    "emailDepassementSent",
    "idLivre",
    "idPret",
    "idReservation",
    "idUtilisateur"
})
public class PreReservation {

    protected boolean annule;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateDisponibilite;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateReservation;
    protected boolean emailDepassementSent;
    protected int idLivre;
    protected int idPret;
    protected int idReservation;
    protected int idUtilisateur;

    /**
     * Obtient la valeur de la propri�t� annule.
     * 
     */
    public boolean isAnnule() {
        return annule;
    }

    /**
     * D�finit la valeur de la propri�t� annule.
     * 
     */
    public void setAnnule(boolean value) {
        this.annule = value;
    }

    /**
     * Obtient la valeur de la propri�t� dateDisponibilite.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateDisponibilite() {
        return dateDisponibilite;
    }

    /**
     * D�finit la valeur de la propri�t� dateDisponibilite.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateDisponibilite(XMLGregorianCalendar value) {
        this.dateDisponibilite = value;
    }

    /**
     * Obtient la valeur de la propri�t� dateReservation.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateReservation() {
        return dateReservation;
    }

    /**
     * D�finit la valeur de la propri�t� dateReservation.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateReservation(XMLGregorianCalendar value) {
        this.dateReservation = value;
    }

    /**
     * Obtient la valeur de la propri�t� emailDepassementSent.
     * 
     */
    public boolean isEmailDepassementSent() {
        return emailDepassementSent;
    }

    /**
     * D�finit la valeur de la propri�t� emailDepassementSent.
     * 
     */
    public void setEmailDepassementSent(boolean value) {
        this.emailDepassementSent = value;
    }

    /**
     * Obtient la valeur de la propri�t� idLivre.
     * 
     */
    public int getIdLivre() {
        return idLivre;
    }

    /**
     * D�finit la valeur de la propri�t� idLivre.
     * 
     */
    public void setIdLivre(int value) {
        this.idLivre = value;
    }

    /**
     * Obtient la valeur de la propri�t� idPret.
     * 
     */
    public int getIdPret() {
        return idPret;
    }

    /**
     * D�finit la valeur de la propri�t� idPret.
     * 
     */
    public void setIdPret(int value) {
        this.idPret = value;
    }

    /**
     * Obtient la valeur de la propri�t� idReservation.
     * 
     */
    public int getIdReservation() {
        return idReservation;
    }

    /**
     * D�finit la valeur de la propri�t� idReservation.
     * 
     */
    public void setIdReservation(int value) {
        this.idReservation = value;
    }

    /**
     * Obtient la valeur de la propri�t� idUtilisateur.
     * 
     */
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * D�finit la valeur de la propri�t� idUtilisateur.
     * 
     */
    public void setIdUtilisateur(int value) {
        this.idUtilisateur = value;
    }

}
