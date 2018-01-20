package webservice.allplatform;

import beans.Livre;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class ServiceLivre extends Service {

    @WebMethod
    public Livre infoLivre(int idLivre){
        Livre OutputLivre=monDaoLivre.infoLivre(idLivre);
        return OutputLivre;
    }

    @WebMethod
    public void ajouterLivre(int idLivre,String titreLivre,String auteurLivre,String editeurLivre,String datePublicationLivre,String indexationLivre){
        Livre newLivreInput= new Livre();
        newLivreInput.setIdLivre(idLivre);
        newLivreInput.setTitreLivre(titreLivre);
        newLivreInput.setAuteurLivre(auteurLivre);
        newLivreInput.setEditeurLivre(editeurLivre);
        newLivreInput.setDatepublicationLivre(datePublicationLivre);
        newLivreInput.setIndexationLivre(indexationLivre);
        monDaoLivre.ajouterLivre(newLivreInput);
    }
}
