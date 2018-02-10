package DaoInterfaces;

import ServicesBeans.Utilisateur;

public interface DaoUtilisateur {
    Utilisateur autentifier(String emailInput, String mdpInput);
    void ajouterUtilisateur(Utilisateur inputUtilisateur);
}
