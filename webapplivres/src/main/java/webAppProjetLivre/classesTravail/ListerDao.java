package webAppProjetLivre.classesTravail;

import webAppProjetLivre.generated.serviceLivre.ServiceLivre;
import webAppProjetLivre.generated.serviceLivre.ServiceLivreService;

import java.net.URL;
import java.util.List;

public class ListerDao {
    private List<String> lesDomaines;
    private List<String> lesTypes;
    private List<String> lesThemes;
    private URL urlWsdl;
    //
    //
    public List<String> rechercherListDomaines(String wsdlAdress){
        try{
            urlWsdl=new URL(wsdlAdress);
        }catch(Exception e){

        }
        //
        ServiceLivreService monservice= new ServiceLivreService(urlWsdl);
        ServiceLivre accesLivre=monservice.getServiceLivrePort();
        lesDomaines=accesLivre.listerDomaines();
        //
        return lesDomaines;
    }
    //
    public List<String> rechercherListTheme(String wsdlAdress){
        try{
            urlWsdl=new URL(wsdlAdress);
        }catch(Exception e){

        }
        //
        ServiceLivreService monservice= new ServiceLivreService(urlWsdl);
        ServiceLivre accesLivre=monservice.getServiceLivrePort();
        lesThemes=accesLivre.listerThemes();
        //
        return lesThemes;
    }
    //
    public List<String> rechercherListType(String wsdlAdress){
        try{
            urlWsdl=new URL(wsdlAdress);
        }catch(Exception e){

        }
        //
        ServiceLivreService monservice= new ServiceLivreService(urlWsdl);
        ServiceLivre accesLivre=monservice.getServiceLivrePort();
        lesTypes=accesLivre.listerType();
        //
        return lesTypes;
    }
}
