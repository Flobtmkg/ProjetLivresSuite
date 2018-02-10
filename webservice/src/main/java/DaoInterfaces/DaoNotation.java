package DaoInterfaces;

import ServicesBeans.Notation;

public interface DaoNotation {
    void ajouterNotation(Notation notationInput);
    double notationLivre(int idLivre);
}
