package DaoInterfaces;

import beans.Pret;
import beans.Reservation;

import java.util.ArrayList;

public interface DaoPret {
    void ajouterPret(Pret newPret);
    ArrayList<Pret> listerInfosPretUtilisateur(int idutilisateur);
    ArrayList<Reservation> listerUtilisateursPretsNonRendus();
    Pret etatPret(int idPret);
    void prolongerPret(int idPret);
    void retourPret(int idPret);
}
