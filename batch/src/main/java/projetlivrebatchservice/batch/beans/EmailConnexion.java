package projetlivrebatchservice.batch.beans;

public class EmailConnexion {
    private String connexionHost;
    private String connexionFromWho;
    private String connexionUsername;
    private String connexionPassword;
    private long connexionPauseInterEmail;

    public long getConnexionPauseInterEmail() {
        return connexionPauseInterEmail;
    }

    public void setConnexionPauseInterEmail(long connexionPauseInterEmail) {
        this.connexionPauseInterEmail = connexionPauseInterEmail;
    }


    public String getConnexionHost() {
        return connexionHost;
    }

    public void setConnexionHost(String connexionHost) {
        this.connexionHost = connexionHost;
    }

    public String getConnexionFromWho() {
        return connexionFromWho;
    }

    public void setConnexionFromWho(String connexionFromWho) {
        this.connexionFromWho = connexionFromWho;
    }

    public String getConnexionUsername() {
        return connexionUsername;
    }

    public void setConnexionUsername(String connexionUsername) {
        this.connexionUsername = connexionUsername;
    }

    public String getConnexionPassword() {
        return connexionPassword;
    }

    public void setConnexionPassword(String connexionPassword) {
        this.connexionPassword = connexionPassword;
    }
}
