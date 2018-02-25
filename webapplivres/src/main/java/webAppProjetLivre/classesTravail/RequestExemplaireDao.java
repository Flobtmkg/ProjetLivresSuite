package webAppProjetLivre.classesTravail;

import webAppProjetLivre.generated.serviceExemplaire.Exemplaire;
import webAppProjetLivre.generated.serviceExemplaire.ServiceExemplaire;
import webAppProjetLivre.generated.serviceExemplaire.ServiceExemplaireService;

import java.net.URL;
import java.util.List;

public class RequestExemplaireDao {
    private List<Exemplaire> resultat;
    private URL urlWsdl;

    public List<Exemplaire> exemplairesDisponibles(int idLivre, String wsdlAdress){
        try{
            urlWsdl=new URL(wsdlAdress);
        }catch(Exception e){

        }
        //
        ServiceExemplaireService monservice= new ServiceExemplaireService(urlWsdl);
        ServiceExemplaire accesExemplaire=monservice.getServiceExemplairePort();
        resultat=accesExemplaire.ExemplaireDisponible(false,idLivre);//
        //
        return resultat;
    }
}
