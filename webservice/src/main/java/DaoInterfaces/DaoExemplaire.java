package DaoInterfaces;

import ServicesBeans.Exemplaire;

import java.util.ArrayList;

public interface DaoExemplaire {
    ArrayList<Exemplaire> ExemplaireDisponible(boolean mode,int idLivre);
    ArrayList<Exemplaire> listerExemplaire(int idLivre);
    void AjouterExemplaire(Exemplaire exemplaireInput);
}
