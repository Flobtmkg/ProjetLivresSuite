
package webAppProjetLivre.generated.serviceExemplaire;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java pour ExemplaireDisponibleResponse complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ExemplaireDisponibleResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://allplatform.webservice/}exemplaire" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExemplaireDisponibleResponse1", propOrder = {
    "_return"
})
public class ExemplaireDisponibleResponse {

    @XmlElement(name = "return")
    protected List<Exemplaire> _return;

    /**
     * Obtient la valeur de la propri�t� return.
     * 
     * @return
     *     possible object is
     *     {@link List<Exemplaire> }
     *     
     */
    public List<Exemplaire> getReturn() {
        if (_return == null) {
            _return = new ArrayList<Exemplaire>();
        }
        return _return;
    }

    /**
     * D�finit la valeur de la propri�t� return.
     * 
     * @param value
     *     allowed object is
     *     {@link List<Exemplaire> }
     *     
     */
    public void setReturn(List<Exemplaire> value) {
        this._return = value;
    }

}
