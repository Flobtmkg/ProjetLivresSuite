
package webAppProjetLivre.generated.servicePret;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour utilisateur complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="utilisateur">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateNaissanceUtilisateur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailUtilisateur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idUtilisateur" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mdpUtilisateur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nomUtilisateur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prenomUtilisateur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "utilisateur1", propOrder = {
    "dateNaissanceUtilisateur",
    "emailUtilisateur",
    "idUtilisateur",
    "mdpUtilisateur",
    "nomUtilisateur",
    "prenomUtilisateur"
})
public class Utilisateur {

    protected String dateNaissanceUtilisateur;
    protected String emailUtilisateur;
    protected int idUtilisateur;
    protected String mdpUtilisateur;
    protected String nomUtilisateur;
    protected String prenomUtilisateur;

    /**
     * Obtient la valeur de la propri�t� dateNaissanceUtilisateur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateNaissanceUtilisateur() {
        return dateNaissanceUtilisateur;
    }

    /**
     * D�finit la valeur de la propri�t� dateNaissanceUtilisateur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateNaissanceUtilisateur(String value) {
        this.dateNaissanceUtilisateur = value;
    }

    /**
     * Obtient la valeur de la propri�t� emailUtilisateur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    /**
     * D�finit la valeur de la propri�t� emailUtilisateur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailUtilisateur(String value) {
        this.emailUtilisateur = value;
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

    /**
     * Obtient la valeur de la propri�t� mdpUtilisateur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMdpUtilisateur() {
        return mdpUtilisateur;
    }

    /**
     * D�finit la valeur de la propri�t� mdpUtilisateur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMdpUtilisateur(String value) {
        this.mdpUtilisateur = value;
    }

    /**
     * Obtient la valeur de la propri�t� nomUtilisateur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    /**
     * D�finit la valeur de la propri�t� nomUtilisateur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomUtilisateur(String value) {
        this.nomUtilisateur = value;
    }

    /**
     * Obtient la valeur de la propri�t� prenomUtilisateur.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    /**
     * D�finit la valeur de la propri�t� prenomUtilisateur.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrenomUtilisateur(String value) {
        this.prenomUtilisateur = value;
    }

}
