package webAppProjetLivre.classesTravail;
import webAppProjetLivre.generated.serviceLivre.Livre;
import webAppProjetLivre.generated.serviceLivre.ServiceLivre;
import webAppProjetLivre.generated.serviceLivre.ServiceLivreService;

import java.net.URL;
import java.util.List;

public class RechercheSimpleDao {
    private List<Livre> resultat;
    private URL urlWsdl;
    public List<Livre> recherche(String motsClés, String wsdlAdress){
        try{
            urlWsdl=new URL(wsdlAdress);
        }catch(Exception e){

        }
        //
        ServiceLivreService monservice= new ServiceLivreService(urlWsdl);
        ServiceLivre accesLivre=monservice.getServiceLivrePort();
        resultat=accesLivre.rechercherLivreComplxe(false,motsClés,motsClés,motsClés,0,motsClés,motsClés,motsClés);
        //
        return resultat;
    }
}
