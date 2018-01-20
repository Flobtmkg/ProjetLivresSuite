package DaoInterfaces;

import beans.Livre;

public interface DaoLivre {
    Livre infoLivre (int idLivre);
    void ajouterLivre(Livre newLivre);
}
