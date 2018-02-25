package DaoInterfaces;

import ServicesBeans.IndexConstitution;
import ServicesBeans.Livre;

import java.util.ArrayList;


public interface DaoLivre {
    Livre infoLivre (int idLivre);
    void ajouterLivre(Livre newLivre);
    ArrayList<Livre> rechercheTitre (String motcle);
    ArrayList<Livre> rechercheAuteur (String motcle);
    ArrayList<Livre> rechercheEditeur (String motcle);
    ArrayList<Livre> rechercheExemplaire(int idExemplaire);
    ArrayList<String> chercheElementIndexation();
    int nbchrElementIndexation(int elementIndexation);
    ArrayList<Livre> rechercheType(String valueType, int valueNbChr);
    ArrayList<Livre> rechercheDomaine(String valueDomaine, int valueNbChr2);
    ArrayList<Livre> rechercheTheme(String valueTheme,int valueNbChr3);
    ArrayList<IndexConstitution> rechercheIndexConstitution();
    String reference(String ref, String typeRef);
    ArrayList<String> listerTypes();
    ArrayList<String> listerDomaines();
    ArrayList<String> listerThemes();
}
