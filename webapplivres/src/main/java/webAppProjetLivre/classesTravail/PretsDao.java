package webAppProjetLivre.classesTravail;

import webAppProjetLivre.generated.servicePret.Reservation;
import webAppProjetLivre.generated.servicePret.ServicePret;
import webAppProjetLivre.generated.servicePret.ServicePretService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PretsDao {
    private List<Reservation> resultat;
    private URL urlWsdl;

    public List<Reservation> listPretsUtilisateur(int idUtilisateur, String wsdlAdress){
        try{
            urlWsdl=new URL(wsdlAdress);
        }catch(Exception e){

        }
        //
        ServicePretService monservice= new ServicePretService(urlWsdl);
        ServicePret accesPret=monservice.getServicePretPort();
        resultat=accesPret.listerInfosPretUtilisateur(idUtilisateur);
        //
        return resultat;
    }

    public void prolongerPrets(ArrayList<String> idPrets, String wsdlAdress){
        try{
            urlWsdl=new URL(wsdlAdress);
        }catch(Exception e){

        }
        //
        ServicePretService monservice= new ServicePretService(urlWsdl);
        ServicePret accesPret=monservice.getServicePretPort();
        //
        for (String indice:idPrets) {
            accesPret.prolongerPret(Integer.parseInt(indice));
        }
        //
    }


}
