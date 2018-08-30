package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import webAppProjetLivre.classesTravail.*;
import webAppProjetLivre.generated.serviceExemplaire.Exemplaire;
import webAppProjetLivre.generated.serviceLivre.Livre;
import webAppProjetLivre.generated.servicePret.Pret;
import webAppProjetLivre.generated.servicePret.Reservation;
import webAppProjetLivre.generated.serviceReservation.PreReservation;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class RedirectionLivreAction extends ActionSupport implements ServletRequestAware, SessionAware{


    private HttpServletRequest currentRequest;

    private int id;//idLivre à requester
    //
    private Utilisateur user;
    private RequestLivreDao infosLivre;
    private ReservationDao reservations;
    private PretsDao prets;
    private Livre livreAAfficher;
    private boolean livreReserve;
    private boolean livreprete;
    private boolean attenteComplete;
    private int nbrEx;
    private int nbrAttente;
    private int nbrEffectif;
    private String premiereDateFinPret;
    //
    private List<Exemplaire> exemplairesDispo;
    private RequestExemplaireDao lesExemplaires;
    private List<String> informationDesindexee;
    //



    public String getPremiereDateFinPret() {
        return premiereDateFinPret;
    }

    public void setPremiereDateFinPret(String premiereDateFinPret) {
        this.premiereDateFinPret = premiereDateFinPret;
    }


    public int getNbrEx() {
        return nbrEx;
    }

    public void setNbrEx(int nbrEx) {
        this.nbrEx = nbrEx;
    }

    public int getNbrAttente() {
        return nbrAttente;
    }

    public void setNbrAttente(int nbrAttente) {
        this.nbrAttente = nbrAttente;
    }

    public int getNbrEffectif() {
        return nbrEffectif;
    }

    public void setNbrEffectif(int nbrEffectif) {
        this.nbrEffectif = nbrEffectif;
    }

    public boolean isAttenteComplete() {
        return attenteComplete;
    }

    public void setAttenteComplete(boolean attenteComplete) {
        this.attenteComplete = attenteComplete;
    }

    public ReservationDao getReservations() {
        return reservations;
    }

    public void setReservations(ReservationDao reservations) {
        this.reservations = reservations;
    }

    public PretsDao getPrets() {
        return prets;
    }

    public void setPrets(PretsDao prets) {
        this.prets = prets;
    }


    public boolean isLivreReserve() {
        return livreReserve;
    }

    public void setLivreReserve(boolean livreReserve) {
        this.livreReserve = livreReserve;
    }

    public boolean isLivreprete() {
        return livreprete;
    }

    public void setLivreprete(boolean livreprete) {
        this.livreprete = livreprete;
    }

    public List<String> getInformationDesindexee() {
        return informationDesindexee;
    }

    public void setInformationDesindexee(List<String> informationDesindexee) {
        this.informationDesindexee = informationDesindexee;
    }

    public List<Exemplaire> getExemplairesDispo() {
        return exemplairesDispo;
    }

    public void setExemplairesDispo(List<Exemplaire> exemplairesDispo) {
        this.exemplairesDispo = exemplairesDispo;
    }

    public RequestExemplaireDao getLesExemplaires() {
        return lesExemplaires;
    }

    public void setLesExemplaires(RequestExemplaireDao lesExemplaires) {
        this.lesExemplaires = lesExemplaires;
    }

    public RequestLivreDao getInfosLivre() {
        return infosLivre;
    }

    public void setInfosLivre(RequestLivreDao infosLivre) {
        this.infosLivre = infosLivre;
    }

    public Livre getLivreAAfficher() {
        return livreAAfficher;
    }

    public void setLivreAAfficher(Livre livreAAfficher) {
        this.livreAAfficher = livreAAfficher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //
    public String execute() throws Exception{
        // initialisation des valeurs
        nbrEx=0;
        livreReserve=false;
        livreprete=false;
        attenteComplete=false;
        nbrAttente=0;
        nbrEffectif=0;
        premiereDateFinPret="";
        //

        //Debut recherche des variables d'env qui doivent être prioritaires;
        String getEnv=System.getenv("WSDLLocationExemplaire");
        List<Exemplaire> exemplairesUser;
        if(getEnv!=null && getEnv.equals("")==false){
            exemplairesUser = lesExemplaires.listerExemplaires(id,getEnv);
        }else{
            exemplairesUser = lesExemplaires.listerExemplaires(id,getText("WSDLLocationExemplaire"));
        }
        nbrEx = exemplairesUser.size();



        //Debut recherche des variables d'env qui doivent être prioritaires;
        getEnv=System.getenv("WSDLLocationReservation");
        List<PreReservation> reservationsOutput;
        if(user!=null){
            if(getEnv!=null && getEnv.equals("")==false){
                reservationsOutput = reservations.getListeReservationByUser(user.getIdUtilisateur(),getEnv);
            }else{
                reservationsOutput = reservations.getListeReservationByUser(user.getIdUtilisateur(),getText("WSDLLocationReservation"));
            }
            for (PreReservation res:reservationsOutput){
                if(res.getIdLivre()==id && (CalculEtatReservation.getEtatReservation(res).equals(EtatPreReservation.EFFECTIF) || CalculEtatReservation.getEtatReservation(res).equals(EtatPreReservation.ATTENTE))){
                    livreReserve=true;
                    break;
                }
            }
        }
        if(getEnv!=null && getEnv.equals("")==false) {
            reservationsOutput=reservations.getListeAttente(id, getEnv);
        }else{
            reservationsOutput=reservations.getListeAttente(id, getText("WSDLLocationReservation"));
        }
        nbrAttente = reservationsOutput.size();
        if(getEnv!=null && getEnv.equals("")==false) {
            reservationsOutput=reservations.getReservationEffectiveByLivre(id, getEnv);
        }else{
            reservationsOutput=reservations.getReservationEffectiveByLivre(id, getText("WSDLLocationReservation"));
        }
        nbrEffectif = reservationsOutput.size();


        // Regle de gestion de la liste d'attente maxi
        if((nbrAttente + nbrEffectif) < (nbrEx * 2)){
            this.attenteComplete = false;
        }else{
            this.attenteComplete = true;
        }


        if(user!=null){
            //Debut recherche des variables d'env qui doivent être prioritaires;
            getEnv=System.getenv("WSDLLocationPret");
            List<Reservation> pretUser;
            if(getEnv!=null && getEnv.equals("")==false){
                pretUser = prets.listPretsUtilisateur(user.getIdUtilisateur(),getEnv);
            }else{
                pretUser = prets.listPretsUtilisateur(user.getIdUtilisateur(),getText("WSDLLocationPret"));
            }
            for (Reservation pr:pretUser){
                if(pr.getLivreReservation().getIdLivre()==id && !pr.getPretReservation().isRenduPret()){
                    livreprete=true;
                    break;
                }
            }
        }



        //Debut recherche des variables d'env qui doivent être prioritaires;
        getEnv=System.getenv("WSDLLocationPret");
        List<Reservation> pretsLivre;
        if(getEnv!=null && getEnv.equals("")==false){
            pretsLivre = prets.listPretsLivre(id,getEnv);
        }else{
            pretsLivre = prets.listPretsLivre(id,getText("WSDLLocationPret"));
        }
        // la liste est ordonnée par date de fin de prêt croissante
        if(pretsLivre!=null){
            premiereDateFinPret=pretsLivre.get(0).getPretReservation().getDateFinPret();
            premiereDateFinPret=DateTool.dateENtoFR(premiereDateFinPret);
        }



        //Debut recherche des variables d'env qui doivent être prioritaires;
        getEnv=System.getenv("WSDLLocationLivres");
        if(getEnv!=null && getEnv.equals("")==false){
            livreAAfficher=infosLivre.infoLivre(id,getEnv);
        }else{
            livreAAfficher=infosLivre.infoLivre(id,getText("WSDLLocationLivres"));
        }



        getEnv=System.getenv("WSDLLocationExemplaire");
        //Debut recherche des variables d'env qui doivent être prioritaires;
        if(getEnv!=null && getEnv.equals("")==false){
            exemplairesDispo=lesExemplaires.exemplairesDisponibles(id,getEnv);
        }else{
            exemplairesDispo=lesExemplaires.exemplairesDisponibles(id,getText("WSDLLocationExemplaire"));
        }



        getEnv=System.getenv("WSDLLocationLivres");
        //Debut recherche des variables d'env qui doivent être prioritaires;
        if(getEnv!=null && getEnv.equals("")==false){
            informationDesindexee=infosLivre.infosDesindexee(id,getEnv);
        }else{
            informationDesindexee=infosLivre.infosDesindexee(id,getText("WSDLLocationLivres"));
        }
        //Fin recherche des variables d'env qui doivent être prioritaires;
        //
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.currentRequest=httpServletRequest;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.user=(Utilisateur) map.get("userGuest");
    }
}
