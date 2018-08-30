
package projetlivrebatchservice.batch.clientservices.generated.serviceExemplaire;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ExemplaireDisponible complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ExemplaireDisponible">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="arg1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExemplaireDisponible1", propOrder = {
    "arg0",
    "arg1"
})
public class ExemplaireDisponible {

    protected boolean arg0;
    protected int arg1;

    /**
     * Obtient la valeur de la propri�t� arg0.
     * 
     */
    public boolean isArg0() {
        return arg0;
    }

    /**
     * D�finit la valeur de la propri�t� arg0.
     * 
     */
    public void setArg0(boolean value) {
        this.arg0 = value;
    }

    /**
     * Obtient la valeur de la propri�t� arg1.
     * 
     */
    public int getArg1() {
        return arg1;
    }

    /**
     * D�finit la valeur de la propri�t� arg1.
     * 
     */
    public void setArg1(int value) {
        this.arg1 = value;
    }

}
