
package webAppProjetLivre.generated.serviceExemplaire;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour exemplaire complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="exemplaire">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="coteExemplaire" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idExemplaire" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="idLivre" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="remarqueExemplaire" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "exemplaire", propOrder = {
    "coteExemplaire",
    "idExemplaire",
    "idLivre",
    "remarqueExemplaire"
})
public class Exemplaire {

    protected String coteExemplaire;
    protected int idExemplaire;
    protected int idLivre;
    protected String remarqueExemplaire;

    /**
     * Obtient la valeur de la propri�t� coteExemplaire.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoteExemplaire() {
        return coteExemplaire;
    }

    /**
     * D�finit la valeur de la propri�t� coteExemplaire.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoteExemplaire(String value) {
        this.coteExemplaire = value;
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
     * Obtient la valeur de la propri�t� remarqueExemplaire.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarqueExemplaire() {
        return remarqueExemplaire;
    }

    /**
     * D�finit la valeur de la propri�t� remarqueExemplaire.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarqueExemplaire(String value) {
        this.remarqueExemplaire = value;
    }

}
