
package projetlivrebatchservice.batch.clientservices.generated.serviceLivre;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour infoLivre complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="infoLivre">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "infoLivre", propOrder = {
    "arg0"
})
public class InfoLivre {

    protected int arg0;

    /**
     * Obtient la valeur de la propri�t� arg0.
     * 
     */
    public int getArg0() {
        return arg0;
    }

    /**
     * D�finit la valeur de la propri�t� arg0.
     * 
     */
    public void setArg0(int value) {
        this.arg0 = value;
    }

}
