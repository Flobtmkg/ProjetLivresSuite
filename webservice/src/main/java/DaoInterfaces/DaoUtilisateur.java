package DaoInterfaces;

import ServicesBeans.Utilisateur;

public interface DaoUtilisateur {
    Utilisateur autentifier(String emailInput, String mdpInput);
    void ajouterUtilisateur(Utilisateur inputUtilisateur);
    boolean isEmailExistInBase(String inputEmail);
    void modifierUtilisateur(Utilisateur inputUtilisateur);
    void defOptionRappel(int idUtilisateur, boolean optionRappel);
    Utilisateur getUserByID(int idUtilisateur);
}
