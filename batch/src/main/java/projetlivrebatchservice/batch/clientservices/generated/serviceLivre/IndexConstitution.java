
package projetlivrebatchservice.batch.clientservices.generated.serviceLivre;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour indexConstitution complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="indexConstitution">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idIndexation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nbrChr" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "indexConstitution1", propOrder = {
    "idIndexation",
    "nbrChr"
})
public class IndexConstitution {

    protected int idIndexation;
    protected int nbrChr;

    /**
     * Obtient la valeur de la propri�t� idIndexation.
     * 
     */
    public int getIdIndexation() {
        return idIndexation;
    }

    /**
     * D�finit la valeur de la propri�t� idIndexation.
     * 
     */
    public void setIdIndexation(int value) {
        this.idIndexation = value;
    }

    /**
     * Obtient la valeur de la propri�t� nbrChr.
     * 
     */
    public int getNbrChr() {
        return nbrChr;
    }

    /**
     * D�finit la valeur de la propri�t� nbrChr.
     * 
     */
    public void setNbrChr(int value) {
        this.nbrChr = value;
    }

}
