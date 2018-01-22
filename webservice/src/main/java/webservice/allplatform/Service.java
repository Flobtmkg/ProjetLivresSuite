package webservice.allplatform;

import DaoImpl.BddExemplaire;
import DaoImpl.BddLivre;
import DaoImpl.BddPret;
import DaoImpl.BddUtilisateur;
import DaoInterfaces.DaoExemplaire;
import DaoInterfaces.DaoLivre;
import DaoInterfaces.DaoPret;
import DaoInterfaces.DaoUtilisateur;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springContext.Application;

public class Service {
    ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
    protected DaoPret monDaoPret=(BddPret)context.getBean("bddPret");
    protected DaoLivre monDaoLivre=(BddLivre)context.getBean("bddLivre");
    protected DaoExemplaire monDaoExemplaire=(BddExemplaire)context.getBean("bddExemplaire");
    protected DaoUtilisateur monDaoUtilisateur=(BddUtilisateur)context.getBean("bddUtilisateur");
}
