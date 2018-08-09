package webservice.allplatform;

import DaoInterfaces.DaoLivre;
import ServicesBeans.IndexConstitution;
import ServicesBeans.Livre;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Arrays;

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
    public ArrayList<Livre> rechercherLivreComplxe(boolean modeRecherche, boolean expressionExacte, String titreLivre, String auteurLivre, String editeurLivre, int idExemplaire, String inputType, String inputDomaine, String inputTheme){
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
        //
        // --> Traitement des NULL et des cas avec multi-mots-clés: ces derniers seront concidérés comme des OR
        //  si l'option "expression exacte" n'est pas cochée, et ce, quelque soit la condition master <--
        //
        if(idExemplaire!=0){
            listParExemplaire=rechercheParExemplaire(idExemplaire);
            listDesLists.add(listParExemplaire);
        }
        if(titreLivre!=null && titreLivre.equals("")==false){          // --> TITRE
            String[] multimotsclés=titreLivre.split(" ");
            ArrayList<Livre> listeIntermediaire= new ArrayList<Livre>();
            ArrayList<Livre> listeDeTravail;
            if(expressionExacte==false){  // multi-mots-clés
                //fusion des recherches
                for (String mot:multimotsclés) {
                    listeDeTravail=rechercheParTitreLivre(mot);
                    listeIntermediaire.addAll(listeDeTravail);
                }
                //
                ArrayList<Integer> listID= new ArrayList<>();
                listParTitre=new ArrayList<>();
                //Check la non-duplicité des livres
                for (Livre chaqueLivre:listeIntermediaire) {
                    if(listID.contains(chaqueLivre.getIdLivre())==false){
                        listParTitre.add(chaqueLivre);
                        listID.add(chaqueLivre.getIdLivre());
                    }
                }
            }else{  // expression exacte
                listParTitre=rechercheParTitreLivre(titreLivre);
            }
            //Output
            listDesLists.add(listParTitre);
        }
        if(auteurLivre!=null && auteurLivre.equals("")==false){          // --> AUTEUR
            //
            String[] multimotsclés=auteurLivre.split(" ");
            ArrayList<Livre> listeIntermediaire= new ArrayList<Livre>();
            ArrayList<Livre> listeDeTravail;
            if(expressionExacte==false){  // multi-mots-clés
                //fusion des recherches
                for (String mot:multimotsclés) {
                    listeDeTravail=rechercheParAuteurLivre(mot);
                    listeIntermediaire.addAll(listeDeTravail);
                }
                //
                ArrayList<Integer> listID= new ArrayList<>();
                listParAuteur=new ArrayList<>();
                //Check la non-duplicité des livres
                for (Livre chaqueLivre:listeIntermediaire) {
                    if(listID.contains(chaqueLivre.getIdLivre())==false){
                        listParAuteur.add(chaqueLivre);
                        listID.add(chaqueLivre.getIdLivre());
                    }
                }
            }else{  // expression exacte
                listParAuteur=rechercheParAuteurLivre(auteurLivre);
            }
            //Output
            listDesLists.add(listParAuteur);
            //
        }
        if(editeurLivre!=null && editeurLivre.equals("")==false){          // --> EDITEUR
            //
            String[] multimotsclés=editeurLivre.split(" ");
            ArrayList<Livre> listeIntermediaire= new ArrayList<Livre>();
            ArrayList<Livre> listeDeTravail;
            if(expressionExacte==false){  // multi-mots-clés
                //fusion des recherches
                for (String mot:multimotsclés) {
                    listeDeTravail=rechercheParEditeurLivre(mot);
                    listeIntermediaire.addAll(listeDeTravail);
                }
                //
                ArrayList<Integer> listID= new ArrayList<>();
                listParEditeur=new ArrayList<>();
                //Check la non-duplicité des livres
                for (Livre chaqueLivre:listeIntermediaire) {
                    if(listID.contains(chaqueLivre.getIdLivre())==false){
                        listParEditeur.add(chaqueLivre);
                        listID.add(chaqueLivre.getIdLivre());
                    }
                }
            }else{  // expression exacte
                listParEditeur=rechercheParEditeurLivre(editeurLivre);
            }
            //Output
            listDesLists.add(listParEditeur);
            //
        }
        if(inputType!=null && inputType.equals("")==false){          // --> TYPE
            listParType=rechercheParType(inputType);
            listDesLists.add(listParType);
        }
        if(inputDomaine!=null && inputDomaine.equals("")==false){          // --> DOMAINE
            listParDomaine=rechercheParDomaine(inputDomaine);
            listDesLists.add(listParDomaine);
        }
        if(inputTheme!=null && inputTheme.equals("")==false){          // --> THEME
            listParTheme=rechercheParTheme(inputTheme);
            listDesLists.add(listParTheme);
        }
        //
        //-------------------------------------> Traitement des contitions Master <----------------------------------
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
        ArrayList<String> elementsIndexation = monDaoLivre.chercheElementIndexation();
        int nbchr = monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("type")+1);
        if(valeurType.length()>nbchr){
            valeurType=valeurType.substring(0,nbchr);
        }
        //
        ArrayList<Livre> OutputLivre=monDaoLivre.rechercheType(valeurType,nbchr);
        return OutputLivre;
    }
    @WebMethod
    public ArrayList<String> listerType(){
        ArrayList<String> listDesTypes=monDaoLivre.listerTypes();
        return listDesTypes;
    }
    @WebMethod
    public ArrayList<Livre> rechercheParDomaine(String valeurDomaine){
        ArrayList<String> elementsIndexation =monDaoLivre.chercheElementIndexation();
        int nbchr2 = monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("domaine")+1);
        if(valeurDomaine.length()>nbchr2){
            valeurDomaine=valeurDomaine.substring(0,nbchr2);
        }
        //int departChr = monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("type")+1)+1;
        //
        ArrayList<Livre> OutputLivre=monDaoLivre.rechercheDomaine(valeurDomaine,nbchr2);
        //
        return OutputLivre;
    }
    @WebMethod
    public ArrayList<String> listerDomaines(){
        ArrayList<String> listDesDomaines=monDaoLivre.listerDomaines();
        return listDesDomaines;
    }
    @WebMethod
    public ArrayList<Livre> rechercheParTheme(String valeurTheme){
        ArrayList<String> elementsIndexation =monDaoLivre.chercheElementIndexation();
        int nbchr3 = monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("theme")+1);
        if(valeurTheme.length()>nbchr3){
            valeurTheme=valeurTheme.substring(0,nbchr3);
        }
        //int departChr = monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("domaine")+1)+ monDaoLivre.nbchrElementIndexation(elementsIndexation.indexOf("type")+1)+1;
        //
        ArrayList<Livre> OutputLivre=monDaoLivre.rechercheTheme(valeurTheme,nbchr3);
        //
        return OutputLivre;
    }
    @WebMethod
    public ArrayList<String> listerThemes(){
        ArrayList<String> listDesThemes=monDaoLivre.listerThemes();
        return listDesThemes;
    }
    @WebMethod
    public ArrayList<String> desindexation(int idlivre){
        //Cette fonction interprete le champ indexation d'un livre
        //et retrouve le type, le domaine et les thèmes du livre, renvoyé sous forme de list
        //en respectant la structure modulaire du champ d'indexation
        Livre infodulivre=infoLivre(idlivre);
        //
        String contenuIndexation=infodulivre.getIndexationLivre();
        String leType="";
        String leDomaine="";
        String themes="";
        ArrayList<String> lesThemes;
        ArrayList<IndexConstitution> tableauIndex;
        //
        tableauIndex=constitutionindex();
        ArrayList<String> elementsIndexation =monDaoLivre.chercheElementIndexation();
        //
        int somme=0;
        //
        for (IndexConstitution chaqueIndex:tableauIndex) {
            if((elementsIndexation.indexOf("type")+1)==chaqueIndex.getIdIndexation()){
                somme=somme+chaqueIndex.getNbrChr();
                if(somme==chaqueIndex.getNbrChr()){ // Premier élément
                    leType=contenuIndexation.substring(0,chaqueIndex.getNbrChr());
                }else{
                    leType=contenuIndexation.substring(somme-chaqueIndex.getNbrChr(),somme);
                }
            }else if((elementsIndexation.indexOf("domaine")+1)==chaqueIndex.getIdIndexation()){
                somme=somme+chaqueIndex.getNbrChr();
                if(somme==chaqueIndex.getNbrChr()){ // Premier élément
                    leDomaine=contenuIndexation.substring(0,chaqueIndex.getNbrChr());
                }else{
                    leDomaine=contenuIndexation.substring(somme-chaqueIndex.getNbrChr(),somme);
                }
            }else if((elementsIndexation.indexOf("theme")+1)==chaqueIndex.getIdIndexation()){
                if(somme==0){ // Premier élément
                    //Boucle pour repérer la prochaine majuscule
                    int i=1;
                    while(contenuIndexation.charAt(i)!=contenuIndexation.toUpperCase().charAt(i) || contenuIndexation.charAt(i)=='&' || contenuIndexation.charAt(i)==' ') {
                        i = i + 1;
                    }
                    somme=somme+i;
                    themes=contenuIndexation.substring(0,i);
                }else{
                    int i=somme+1;
                    try{
                        while(contenuIndexation.charAt(i)!=contenuIndexation.toUpperCase().charAt(i) || contenuIndexation.charAt(i)=='&' || contenuIndexation.charAt(i)==' ') {
                            i = i + 1;
                        }
                        themes=contenuIndexation.substring(somme,i);
                    }catch (Exception e){ //dépasse taille tableau
                        themes=contenuIndexation.substring(somme);
                    }
                }
            }
        }
        //
        lesThemes= new ArrayList<String>(Arrays.asList(themes.split("&")));
        //On va chercher les réferences complètes
        //
        leType=rechercheReferences(leType,"type");
        leDomaine=rechercheReferences(leDomaine,"domaine");
        int o=0;
        for (String theme:lesThemes) {
            lesThemes.set(o,rechercheReferences(theme,"theme"));
            o=o+1;
        }
        // Formalisation de la liste de sortie
        ArrayList<String> outputList= new ArrayList<>();
        outputList.add(leType);
        outputList.add(leDomaine);
        outputList.addAll(lesThemes);
        //
        return outputList;
    }

    public ArrayList<IndexConstitution> constitutionindex(){
        ArrayList<IndexConstitution> listIndex=monDaoLivre.rechercheIndexConstitution();
        return listIndex;
    }

    public String rechercheReferences(String refInput, String typeIndexation){
        String ref=monDaoLivre.reference(refInput,typeIndexation);
        return ref;
    }
}
