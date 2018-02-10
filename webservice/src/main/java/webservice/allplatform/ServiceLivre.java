package webservice.allplatform;

import ServicesBeans.Livre;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;

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
    public ArrayList<Livre> rechercherLivreComplxe(boolean modeRecherche,String titreLivre, String auteurLivre, String editeurLivre, int idExemplaire, String inputType, String inputDomaine, String inputTheme){
        //def des ArrayList
        ArrayList<Livre> listParExemplaire;
        ArrayList<Livre> listParTitre;
        ArrayList<Livre> listParType;
        ArrayList<Livre> listParDomaine;
        ArrayList<Livre> listParTheme;
        ArrayList<Livre> listParAuteur;
        ArrayList<Livre> listParEditeur;
        //
        //ArrayList des ArrayList<Livres> non nulls
        ArrayList<ArrayList<Livre>> listDesLists=new ArrayList<>();
        // Traitement des NULL
        if(idExemplaire!=0){
            listParExemplaire=rechercheParExemplaire(idExemplaire);
            listDesLists.add(listParExemplaire);
        }
        if(titreLivre!=null){
            listParTitre=rechercheParTitreLivre(titreLivre);
            listDesLists.add(listParTitre);
        }
        if(auteurLivre!=null){
            listParAuteur=rechercheParAuteurLivre(auteurLivre);
            listDesLists.add(listParAuteur);
        }
        if(editeurLivre!=null){
            listParEditeur=rechercheParEditeurLivre(editeurLivre);
            listDesLists.add(listParEditeur);
        }
        if(inputType!=null){
            listParType=rechercheParType(inputType);
            listDesLists.add(listParType);
        }
        if(inputDomaine!=null){
            listParDomaine=rechercheParDomaine(inputDomaine);
            listDesLists.add(listParDomaine);
        }
        if(inputTheme!=null){
            listParTheme=rechercheParTheme(inputTheme);
            listDesLists.add(listParTheme);
        }
        // def de l'Array de retour
        ArrayList<Livre> outputLivres;
        //
        if(modeRecherche==false){//  OR
            //OR
            ArrayList<Livre> listLivres0 = new ArrayList<>();
            for (ArrayList<Livre> chaqueList:listDesLists) {//On fusionne les Arrays
                listLivres0.addAll(chaqueList);
            }
            //On verifie que les résulats ne sont présent qu'une seule fois
            ArrayList<Integer> listID= new ArrayList<>();
            ArrayList<Livre> listLivres =new ArrayList<>();
            for (Livre chaqueLivre:listLivres0) {
                if(listID.contains(chaqueLivre.getIdLivre())==false){
                        listLivres.add(chaqueLivre);
                        listID.add(chaqueLivre.getIdLivre());
                }
            }
            //
            outputLivres = listLivres;//On rebascule le résultat dans un array
            //
        }else{//  AND
            //AND
            outputLivres= new ArrayList<>();
            for (Livre chaqueLivre:listDesLists.get(0)) {//On se place par defaut dans la premiere liste issue d'un parametre non-null
                int i=0;
                for (ArrayList<Livre> chaqueList:listDesLists) {//On check chaque list pour verifier si elles contiennent toutes l'élément de réference en cours, lui même issu de la premiere liste issue d'un parametre non-null
                    boolean presentDansLaListe=false;
                    for (Livre chaqueLivre2:chaqueList){
                        if(chaqueLivre2.getIdLivre()==chaqueLivre.getIdLivre()){
                            presentDansLaListe=true;
                        }
                    }
                    if(presentDansLaListe==true){
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
    public ArrayList<Livre> rechercheParAuteurLivre(String auteurLivre){
        ArrayList<Livre> OutputLivre=monDaoLivre.rechercheAuteur(auteurLivre);
        return OutputLivre;
    }
    @WebMethod
    public ArrayList<Livre> rechercheParEditeurLivre(String EditeurLivre){
        ArrayList<Livre> OutputLivre=monDaoLivre.rechercheEditeur(EditeurLivre);
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
        if(valeurType.length()>nbchr){
            valeurType=valeurType.substring(0,nbchr);
        }
        //
        ArrayList<Livre> OutputLivre=monDaoLivre.rechercheType(valeurType,nbchr);
        return OutputLivre;
    }
    @WebMethod
    public ArrayList<Livre> rechercheParDomaine(String valeurDomaine){
        ArrayList<String> elementsIndexation =monDaoLivre.chercheElementIndexation();
        int nbchr2 = monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("domaine")+1);
        if(valeurDomaine.length()>nbchr2){
            valeurDomaine=valeurDomaine.substring(0,nbchr2);
        }
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
        if(valeurTheme.length()>nbchr3){
            valeurTheme=valeurTheme.substring(0,nbchr3);
        }
        //
        int departChr = monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("domaine")+1)+ monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("type")+1)+1;
        //
        ArrayList<Livre> OutputLivre=monDaoLivre.rechercheTheme(valeurTheme,departChr,nbchr3);
        //
        return OutputLivre;
    }
}
