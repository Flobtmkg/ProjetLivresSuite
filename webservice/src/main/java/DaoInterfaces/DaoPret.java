package DaoInterfaces;

import ServicesBeans.Pret;
import ServicesBeans.Reservation;

import java.util.ArrayList;

public interface DaoPret {
    void ajouterPret(Pret newPret);
    ArrayList<Reservation> listerPretByDaysBeforeDateFinPret(int days);
    ArrayList<Reservation> listerInfosPretUtilisateur(int idutilisateur);
    ArrayList<Reservation> listerInfosPretLivre(int idLivre);
    ArrayList<Reservation> listerUtilisateursPretsNonRendus();
    Pret etatPret(int idPret);
    void prolongerPret(int idPret,String datefinpret);
    void retourPret(int idPret);
}
