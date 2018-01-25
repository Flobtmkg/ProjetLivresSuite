package DaoInterfaces;

import beans.Notation;

public interface DaoNotation {
    void ajouterNotation(Notation notationInput);
    double notationLivre(int idLivre);
}
