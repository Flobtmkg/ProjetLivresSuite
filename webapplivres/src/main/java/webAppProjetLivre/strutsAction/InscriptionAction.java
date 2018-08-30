package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import webAppProjetLivre.classesTravail.RequestManager;
import webAppProjetLivre.classesTravail.InscriptionDao;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Map;

public class InscriptionAction extends ActionSupport implements SessionAware,ServletRequestAware {


    private HttpServletRequest currentRequest;



    private Map<String, Object> session;
    private Utilisateur eventuelUtilisateurConnecte;
    private Utilisateur nouvelUtilisateur;
    private String commandPage;
    private String email;
    private String motDePasse;
    private String prenom;
    private String nom;
    private String dateNaissance;
    private InscriptionDao inscriptionUtilisateur;

    public InscriptionDao getInscriptionUtilisateur() {
        return inscriptionUtilisateur;
    }

    public void setInscriptionUtilisateur(InscriptionDao inscriptionUtilisateur) {
        this.inscriptionUtilisateur = inscriptionUtilisateur;
    }

    public String getCommandPage() {
        return commandPage;
    }

    public void setCommandPage(String commandPage) {
        this.commandPage = commandPage;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        String[] tab=dateNaissance.split("/");
        dateNaissance=tab[2]+"-"+tab[1]+"-"+tab[0];
        this.dateNaissance = dateNaissance;
    }


    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }

    public String execute() {
        //
        //
        nouvelUtilisateur=new Utilisateur();
        this.nouvelUtilisateur.setNomUtilisateur(nom);
        this.nouvelUtilisateur.setPrenomUtilisateur(prenom);
        this.nouvelUtilisateur.setEmailUtilisateur(email);
        this.nouvelUtilisateur.setMdpUtilisateur(motDePasse);
        this.nouvelUtilisateur.setDateNaissanceUtilisateur(dateNaissance);
        //test Date
        try{
            LocalDate objetDeTestDeDate; // ne sert qu'au test de validité de la Date
            objetDeTestDeDate=LocalDate.parse(dateNaissance);
            //
            this.eventuelUtilisateurConnecte=(Utilisateur)session.get("userGuest");
            if(eventuelUtilisateurConnecte==null || eventuelUtilisateurConnecte.getIdUtilisateur()==0){
                boolean isSuccess;
                //Debut recherche des variables d'env qui doivent être prioritaires;
                String getEnv=System.getenv("WSDLLocationUtilisateur");

                if(getEnv!=null && getEnv.equals("")==false){
                    isSuccess = inscriptionUtilisateur.inscrire(nouvelUtilisateur,getEnv);
                }else{
                    isSuccess = inscriptionUtilisateur.inscrire(nouvelUtilisateur,getText("WSDLLocationUtilisateur"));
                }
                //Fin recherche des variables d'env qui doivent être prioritaires;
                if(isSuccess==true){
                    commandPage=RequestManager.pagePrecedenteParametrable(currentRequest,"#ModalValidationInscription");
                    //
                    return SUCCESS;
                }else{
                    commandPage=RequestManager.pagePrecedenteParametrable(currentRequest,"#ModalerreurInscription");
                    return ERROR;
                }
            }else{
                commandPage=RequestManager.pagePrecedenteParametrable(currentRequest,"#ModalerreurInscription");
                return ERROR;
            }
        }catch (Exception e){
            commandPage=RequestManager.pagePrecedenteParametrable(currentRequest,"#ModalerreurInscription");
            return ERROR;
        }
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.currentRequest=httpServletRequest;
    }

}
