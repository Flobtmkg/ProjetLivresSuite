package webAppProjetLivre.strutsAction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import webAppProjetLivre.classesTravail.DateTool;
import webAppProjetLivre.generated.serviceUtilisateur.Utilisateur;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Map;

public class RedirectionEspaceUtilisateurAction extends ActionSupport implements SessionAware {
    //Date de naissance formalisée à la française
    String datefr;
    LocalDate mainteant;
    int age;
    //
    //

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getMainteant() {
        return mainteant;
    }

    public void setMainteant(LocalDate mainteant) {
        this.mainteant = mainteant;
    }


    private Map<String, Object> session;

    public String getDatefr() {
        return datefr;
    }

    public void setDatefr(String datefr) {
        this.datefr = datefr;
    }
    //
    @Override
    public void setSession(Map<String, Object> map) {
        this.session=map;
    }
    //
    //
    public String execute() {
        //
        Utilisateur identifiedUser;
        identifiedUser=(Utilisateur) session.get("userGuest");
        //
        if(identifiedUser==null || identifiedUser.getIdUtilisateur()==0){//non connecté
            return ERROR;
        }else{//connecté
            //
            this.setDatefr(DateTool.dateENtoFR(identifiedUser.getDateNaissanceUtilisateur()));
            this.mainteant=LocalDate.now();
            this.age=DateTool.getAge(identifiedUser.getDateNaissanceUtilisateur());
            //
            return SUCCESS;
        }
        //

    }
}
