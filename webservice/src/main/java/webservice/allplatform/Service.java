package webservice.allplatform;

import DaoImpl.BddLivre;
import DaoImpl.BddPret;
import DaoInterfaces.DaoLivre;
import DaoInterfaces.DaoPret;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springContext.Application;

public class Service {
    ApplicationContext context2 = new AnnotationConfigApplicationContext(Application.class);
    DaoPret monDaoPret=(BddPret)context2.getBean("bddPret");
    DaoLivre monDaoLivre=(BddLivre)context2.getBean("bddLivre");
}
