package DaoInterfaces;

import ServicesBeans.Exemplaire;

import java.util.ArrayList;

public interface DaoExemplaire {
    Exemplaire premierExemplaireDisponible(int idLivre);
    ArrayList<Exemplaire> listerExemplaire(int idLivre);
    void AjouterExemplaire(Exemplaire exemplaireInput);
}
