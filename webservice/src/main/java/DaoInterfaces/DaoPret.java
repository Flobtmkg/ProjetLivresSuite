package DaoInterfaces;

import ServicesBeans.Pret;
import ServicesBeans.Reservation;

import java.util.ArrayList;

public interface DaoPret {
    void ajouterPret(Pret newPret);
    ArrayList<Pret> listerInfosPretUtilisateur(int idutilisateur);
    ArrayList<Reservation> listerUtilisateursPretsNonRendus();
    Pret etatPret(int idPret);
    void prolongerPret(int idPret,String datefinpret);
    void retourPret(int idPret);
}
