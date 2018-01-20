package beans;

public class Exemplaire {
    //
    private int idExemplaire;
    private int idLivre;
    private String coteExemplaire;
    private String remarqueExemplaire;
    //

    public int getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(int idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public String getCoteExemplaire() {
        return coteExemplaire;
    }

    public void setCoteExemplaire(String coteExemplaire) {
        this.coteExemplaire = coteExemplaire;
    }

    public String getRemarqueExemplaire() {
        return remarqueExemplaire;
    }

    public void setRemarqueExemplaire(String remarqueExemplaire) {
        this.remarqueExemplaire = remarqueExemplaire;
    }

}
