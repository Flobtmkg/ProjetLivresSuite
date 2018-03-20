package projetlivrebatchservice.batch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import projetlivrebatchservice.batch.beans.EmailConnexion;
import projetlivrebatchservice.batch.beans.WsdlLocationObject;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableScheduling
@ComponentScan(basePackages = "projetlivrebatchservice")
@PropertySource("classpath:application.properties")
@Configuration
public class BatchApplication { //implements SchedulingConfigurer

    //récup des properties email
    @Value("${emailHost}")
    private String emailHost;
    @Value("${emailFromWho}")
    private String emailFromWho;
    @Value("${emailUsername}")
    private String emailUsername;
    @Value("${emailPassword}")
    private String emailPassword;
    @Value("${pauseInterEmail}")
    private long pauseInterEmail;
    //récup des properties WSDLLocation
    @Value("${WSDLLocationUtilisateur}")
    private String wsdlLocationUtilisateur;
    @Value("${WSDLLocationLivres}")
    private String wsdlLocationLivre;
    @Value("${WSDLLocationNotation}")
    private String wsdlLocationNotation;
    @Value("${WSDLLocationExemplaire}")
    private String wsdlLocationExemplaire;
    @Value("${WSDLLocationPret}")
    private String wsdlLocationPret;
    //

    public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
		System.out.println("Batch scheduler launched...");
	}

    //@Override
    //public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    //    taskRegistrar.setTaskScheduler(taskScheduler());
    //}

    //@Bean
    //public TaskScheduler taskScheduler() {
    //return new ConcurrentTaskScheduler(); //single threaded by default
    //}

    @Bean
    public WsdlLocationObject configWsdl(){
        WsdlLocationObject wsdlConfig= new WsdlLocationObject();
        wsdlConfig.setWsdlLocationUtilisateur(wsdlLocationUtilisateur);
        wsdlConfig.setWsdlLocationLivre(wsdlLocationLivre);
        wsdlConfig.setWsdlLocationNotation(wsdlLocationNotation);
        wsdlConfig.setWsdlLocationExemplaire(wsdlLocationExemplaire);
        wsdlConfig.setWsdlLocationPret(wsdlLocationPret);
        return wsdlConfig;
    }

    @Bean
	public EmailConnexion configEmail(){
        EmailConnexion beanEmail= new EmailConnexion();
        beanEmail.setConnexionHost(emailHost);
        beanEmail.setConnexionFromWho(emailFromWho);
        beanEmail.setConnexionUsername(emailUsername);
        beanEmail.setConnexionPassword(emailPassword);
        beanEmail.setConnexionPauseInterEmail(pauseInterEmail);
        return beanEmail;
    }

}
