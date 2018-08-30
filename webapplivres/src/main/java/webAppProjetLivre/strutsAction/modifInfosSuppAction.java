package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import webAppProjetLivre.classesTravail.RequestManager;
import webAppProjetLivre.classesTravail.RequestAutentificationDao;
import webAppProjetLivre.classesTravail.UpdateUtilisateurDao;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class modifInfosSuppAction extends ActionSupport implements SessionAware,ServletRequestAware {

    private HttpServletRequest currentRequest;


    //
    private RequestAutentificationDao verifUserIdentifie;
    private Utilisateur UserAModif;
    private UpdateUtilisateurDao accesModifUtilisateur;
    private Map<String, Object> session;
    private String commandPage;
    //
    private String motDePasseA;
    private String motDePasseN;
    private String motDePasseN2;
    //

    public RequestAutentificationDao getVerifUserIdentifie() {
        return verifUserIdentifie;
    }

    public void setVerifUserIdentifie(RequestAutentificationDao verifUserIdentifie) {
        this.verifUserIdentifie = verifUserIdentifie;
    }

    public String getMotDePasseA() {
        return motDePasseA;
    }

    public void setMotDePasseA(String motDePasseA) {
        this.motDePasseA = motDePasseA;
    }

    public String getMotDePasseN() {
        return motDePasseN;
    }

    public void setMotDePasseN(String motDePasseN) {
        this.motDePasseN = motDePasseN;
    }

    public String getMotDePasseN2() {
        return motDePasseN2;
    }

    public void setMotDePasseN2(String motDePasseN2) {
        this.motDePasseN2 = motDePasseN2;
    }
    public String getCommandPage() {
        return commandPage;
    }

    public void setCommandPage(String commandPage) {
        this.commandPage = commandPage;
    }
    //
    public Utilisateur getUserAModif() {
        return UserAModif;
    }

    public void setUserAModif(Utilisateur userAModif) {
        UserAModif = userAModif;
    }

    public UpdateUtilisateurDao getAccesModifUtilisateur() {
        return accesModifUtilisateur;
    }

    public void setAccesModifUtilisateur(UpdateUtilisateurDao accesModifUtilisateur) {
        this.accesModifUtilisateur = accesModifUtilisateur;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }
    //
    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.currentRequest=httpServletRequest;
    }
    //

    public String execute() {
        //
        //
        Utilisateur userEnCours=(Utilisateur) session.get("userGuest");
        UserAModif= new Utilisateur();
        // On check si le mot de passe entré confirme l'authentification
        Utilisateur userIdentifieParMotDePasse;
        //Debut recherche des variables d'env qui doivent être prioritaires;
        String getEnv=System.getenv("WSDLLocationUtilisateur");
        if(getEnv!=null && getEnv.equals("")==false){
            userIdentifieParMotDePasse = verifUserIdentifie.authentifier(userEnCours.getEmailUtilisateur(),motDePasseA,getEnv);
        }else{
            userIdentifieParMotDePasse = verifUserIdentifie.authentifier(userEnCours.getEmailUtilisateur(),motDePasseA,getText("WSDLLocationUtilisateur"));
        }
        //Fin recherche des variables d'env qui doivent être prioritaires;
        //
        if(userIdentifieParMotDePasse!=null){
            // les deux mots de passe entrés correspondent et l'identité est confirmée
            if(this.motDePasseN2.equals(this.motDePasseN) && userIdentifieParMotDePasse.getIdUtilisateur()!=0){
                this.UserAModif.setPrenomUtilisateur(userEnCours.getPrenomUtilisateur());
                this.UserAModif.setNomUtilisateur(userEnCours.getNomUtilisateur());
                this.UserAModif.setEmailUtilisateur(userEnCours.getEmailUtilisateur());
                this.UserAModif.setDateNaissanceUtilisateur(userEnCours.getDateNaissanceUtilisateur());
                this.UserAModif.setIdUtilisateur(userEnCours.getIdUtilisateur());
                this.UserAModif.setMdpUtilisateur(this.motDePasseN);
                //
                //Debut recherche des variables d'env qui doivent être prioritaires;
                if(getEnv!=null && getEnv.equals("")==false){
                    accesModifUtilisateur.updateInfosSupp(UserAModif,getEnv);
                }else{
                    accesModifUtilisateur.updateInfosSupp(UserAModif,getText("WSDLLocationUtilisateur"));
                }
                //Fin recherche des variables d'env qui doivent être prioritaires;
                // On rebalance le profile dans la session
                session.put("userGuest",UserAModif);
                //
                commandPage=RequestManager.pagePrecedenteParametrable(currentRequest,"#ModalValidation");
                return SUCCESS;
            }else{
                //
                commandPage=RequestManager.pagePrecedenteParametrable(currentRequest,"#Modalerreur");
                return ERROR;
            }
        }else{
            //
            commandPage=RequestManager.pagePrecedenteParametrable(currentRequest,"#Modalerreur");
            return ERROR;
        }
    }

}
