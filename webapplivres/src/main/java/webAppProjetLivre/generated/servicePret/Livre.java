
package webAppProjetLivre.generated.servicePret;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour livre complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="livre">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auteurLivre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datepublicationLivre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descriptionLivre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="editeurLivre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idLivre" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="indexationLivre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titreLivre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "livre", propOrder = {
    "auteurLivre",
    "datepublicationLivre",
    "descriptionLivre",
    "editeurLivre",
    "idLivre",
    "indexationLivre",
    "titreLivre"
})
public class Livre {

    protected String auteurLivre;
    protected String datepublicationLivre;
    protected String descriptionLivre;
    protected String editeurLivre;
    protected int idLivre;
    protected String indexationLivre;
    protected String titreLivre;

    /**
     * Obtient la valeur de la propri�t� auteurLivre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuteurLivre() {
        return auteurLivre;
    }

    /**
     * D�finit la valeur de la propri�t� auteurLivre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuteurLivre(String value) {
        this.auteurLivre = value;
    }

    /**
     * Obtient la valeur de la propri�t� datepublicationLivre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatepublicationLivre() {
        return datepublicationLivre;
    }

    /**
     * D�finit la valeur de la propri�t� datepublicationLivre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatepublicationLivre(String value) {
        this.datepublicationLivre = value;
    }

    /**
     * Obtient la valeur de la propri�t� descriptionLivre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptionLivre() {
        return descriptionLivre;
    }

    /**
     * D�finit la valeur de la propri�t� descriptionLivre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptionLivre(String value) {
        this.descriptionLivre = value;
    }

    /**
     * Obtient la valeur de la propri�t� editeurLivre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditeurLivre() {
        return editeurLivre;
    }

    /**
     * D�finit la valeur de la propri�t� editeurLivre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditeurLivre(String value) {
        this.editeurLivre = value;
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
     * Obtient la valeur de la propri�t� indexationLivre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndexationLivre() {
        return indexationLivre;
    }

    /**
     * D�finit la valeur de la propri�t� indexationLivre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndexationLivre(String value) {
        this.indexationLivre = value;
    }

    /**
     * Obtient la valeur de la propri�t� titreLivre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitreLivre() {
        return titreLivre;
    }

    /**
     * D�finit la valeur de la propri�t� titreLivre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitreLivre(String value) {
        this.titreLivre = value;
    }

}
