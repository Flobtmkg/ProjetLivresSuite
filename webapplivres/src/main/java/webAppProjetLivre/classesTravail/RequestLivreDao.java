package webAppProjetLivre.classesTravail;

import webAppProjetLivre.generated.serviceLivre.Livre;
import webAppProjetLivre.generated.serviceLivre.ServiceLivre;
import webAppProjetLivre.generated.serviceLivre.ServiceLivreService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestLivreDao {
    private Livre resultat;
    private List<String> resultat2;
    private URL urlWsdl;

    public Livre infoLivre(int idLivre, String wsdlAdress){
        //
        try{
            urlWsdl=new URL(wsdlAdress);
        }catch(Exception e){

        }
        //
        ServiceLivreService monservice= new ServiceLivreService(urlWsdl);
        ServiceLivre accesLivre=monservice.getServiceLivrePort();
        resultat=accesLivre.infoLivre(idLivre);
        //
        return resultat;
    }

    public List<String> infosDesindexee(int idLivreInput, String wsdlAdress){
        try{
            urlWsdl=new URL(wsdlAdress);
        }catch(Exception e){

        }
        //
        ServiceLivreService monservice= new ServiceLivreService(urlWsdl);
        ServiceLivre accesLivre=monservice.getServiceLivrePort();
        resultat2=accesLivre.desindexation(idLivreInput);
        //
        return resultat2;
    }
}
