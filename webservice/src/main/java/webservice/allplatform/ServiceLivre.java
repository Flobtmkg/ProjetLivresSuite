package webservice.allplatform;

import beans.Livre;
import beans.Recherche;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@WebService
public class ServiceLivre extends Service {

    @WebMethod
    public Livre infoLivre(int idLivre){
        Livre OutputLivre=monDaoLivre.infoLivre(idLivre);
        return OutputLivre;
    }

    @WebMethod
    public void ajouterLivre(String titreLivre,String auteurLivre,String editeurLivre,String datePublicationLivre,String indexationLivre,String descriptionLivre){
        Livre newLivreInput= new Livre();
        newLivreInput.setIdLivre(0);
        newLivreInput.setTitreLivre(titreLivre);
        newLivreInput.setAuteurLivre(auteurLivre);
        newLivreInput.setEditeurLivre(editeurLivre);
        newLivreInput.setDatepublicationLivre(datePublicationLivre);
        newLivreInput.setIndexationLivre(indexationLivre);
        newLivreInput.setDescriptionLivre(descriptionLivre);
        monDaoLivre.ajouterLivre(newLivreInput);
    }

    @WebMethod
    public ArrayList<Livre> rechercherLivreComplxe(boolean modeRecherche,String titreLivre, int idExemplaire, String inputType, String inputDomaine, String inputTheme){
        //def des ArrayList
        ArrayList<Livre> listParExemplaire;
        ArrayList<Livre> listParTitre;
        ArrayList<Livre> listParType;
        ArrayList<Livre> listParDomaine;
        ArrayList<Livre> listParTheme;
        //ArrayList des ArrayList<Livres> non nulls
        ArrayList<ArrayList<Livre>> listDesLists=new ArrayList<>();
        // Traitement des NULL
        if(idExemplaire==0){
        }else{
            listParExemplaire=rechercheParExemplaire(idExemplaire);
            listDesLists.add(listParExemplaire);
        }
        if(titreLivre==null){
        }else{
            listParTitre=rechercheParTitreLivre(titreLivre);
            listDesLists.add(listParTitre);
        }
        if(inputType==null){
        }else{
            listParType=rechercheParType(inputType);
            listDesLists.add(listParType);
        }
        if(inputDomaine==null){
        }else{
            listParDomaine=rechercheParDomaine(inputDomaine);
            listDesLists.add(listParDomaine);
        }
        if(inputTheme==null){
        }else{
            listParTheme=rechercheParTheme(inputTheme);
            listDesLists.add(listParTheme);
        }
        // def de l'Array de retour
        ArrayList<Livre> outputLivres;
        //
        if(modeRecherche==false){//  OR
            //OR
            ArrayList<Livre> listLivres = new ArrayList<>();
            for (ArrayList<Livre> chaqueList:listDesLists) {//On fusionne les Arrays
                listLivres.addAll(chaqueList);
            }
            //
            Set livreSet= new HashSet();//On def un Set
            livreSet.addAll(listLivres);//On place les elements de la list fusionnée dans le set ce qui élimine les doublons
            outputLivres= new ArrayList(livreSet);//On rebascule le résultat dans un array
            //
        }else{//  AND
            //AND
            outputLivres= new ArrayList<>();
            for (Livre chaqueLivre:listDesLists.get(0)) {//On se place par defaut dans la premiere liste issue d'un parametre non-null
                int i=0;
                for (ArrayList<Livre> chaqueList:listDesLists) {//On check chaque list pour verifier si elles contiennent toutes l'élément de réference en cours, lui même issu de la premiere liste issue d'un parametre non-null
                    if(chaqueList.contains(chaqueLivre)){
                        i=i+1;
                    }
                }
                if(i==listDesLists.size()){
                    outputLivres.add(chaqueLivre);//Chaque élément présent dans toutes les listes est ajouté à la liste des outputs
                }
            }
        }
        //
       return outputLivres;
    }
    @WebMethod
    public ArrayList<Livre> rechercheParTitreLivre(String titreLivre){
        ArrayList<Livre> OutputLivre=monDaoLivre.rechercheTitre(titreLivre);
        return OutputLivre;
    }
    @WebMethod
    public ArrayList<Livre> rechercheParExemplaire(int idExemplaire){
        ArrayList<Livre> OutputLivre=monDaoLivre.rechercheExemplaire(idExemplaire);
        return OutputLivre;
    }
    @WebMethod
    public ArrayList<Livre> rechercheParType(String valeurType){
        //
        ArrayList<String> elementsIndexation =monDaoLivre.chercheElementIndexation();
        int nbchr = monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("type")+1);
        valeurType=valeurType.substring(0,nbchr);
        ArrayList<Livre> OutputLivre=monDaoLivre.rechercheType(valeurType,nbchr);
        return OutputLivre;
    }
    @WebMethod
    public ArrayList<Livre> rechercheParDomaine(String valeurDomaine){
        ArrayList<String> elementsIndexation =monDaoLivre.chercheElementIndexation();
        int nbchr2 = monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("domaine")+1);
        valeurDomaine=valeurDomaine.substring(0,nbchr2);
        //
        int departChr = monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("type")+1)+1;
        //
        ArrayList<Livre> OutputLivre=monDaoLivre.rechercheDomaine(valeurDomaine,departChr,nbchr2);
        //
        return OutputLivre;
    }
    @WebMethod
    public ArrayList<Livre> rechercheParTheme(String valeurTheme){
        ArrayList<String> elementsIndexation =monDaoLivre.chercheElementIndexation();
        int nbchr3 = monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("theme")+1);
        valeurTheme=valeurTheme.substring(0,nbchr3);
        //
        int departChr = monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("domaine")+1)+ monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("type")+1)+1;
        //
        ArrayList<Livre> OutputLivre=monDaoLivre.rechercheTheme(valeurTheme,departChr,nbchr3);
        //
        return OutputLivre;
    }
}
