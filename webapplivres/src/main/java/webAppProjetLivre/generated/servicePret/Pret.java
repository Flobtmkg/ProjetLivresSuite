
package webAppProjetLivre.generated.servicePret;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour pret complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="pret">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateDebutPret" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateFinPret" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idExemplaire" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idPret" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idUtilisateur" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="prolongePret" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="renduPret" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pret1", propOrder = {
    "dateDebutPret",
    "dateFinPret",
    "idExemplaire",
    "idPret",
    "idUtilisateur",
    "prolongePret",
    "renduPret"
})
public class Pret {

    protected String dateDebutPret;
    protected String dateFinPret;
    protected int idExemplaire;
    protected int idPret;
    protected int idUtilisateur;
    protected boolean prolongePret;
    protected boolean renduPret;

    /**
     * Obtient la valeur de la propri�t� dateDebutPret.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateDebutPret() {
        return dateDebutPret;
    }

    /**
     * D�finit la valeur de la propri�t� dateDebutPret.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateDebutPret(String value) {
        this.dateDebutPret = value;
    }

    /**
     * Obtient la valeur de la propri�t� dateFinPret.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateFinPret() {
        return dateFinPret;
    }

    /**
     * D�finit la valeur de la propri�t� dateFinPret.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateFinPret(String value) {
        this.dateFinPret = value;
    }

    /**
     * Obtient la valeur de la propri�t� idExemplaire.
     * 
     */
    public int getIdExemplaire() {
        return idExemplaire;
    }

    /**
     * D�finit la valeur de la propri�t� idExemplaire.
     * 
     */
    public void setIdExemplaire(int value) {
        this.idExemplaire = value;
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
     * Obtient la valeur de la propri�t� prolongePret.
     * 
     */
    public boolean isProlongePret() {
        return prolongePret;
    }

    /**
     * D�finit la valeur de la propri�t� prolongePret.
     * 
     */
    public void setProlongePret(boolean value) {
        this.prolongePret = value;
    }

    /**
     * Obtient la valeur de la propri�t� renduPret.
     * 
     */
    public boolean isRenduPret() {
        return renduPret;
    }

    /**
     * D�finit la valeur de la propri�t� renduPret.
     * 
     */
    public void setRenduPret(boolean value) {
        this.renduPret = value;
    }

}
