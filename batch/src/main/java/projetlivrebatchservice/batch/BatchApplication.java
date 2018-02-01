package projetlivrebatchservice.batch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import projetlivrebatchservice.batch.beans.EmailConnexion;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableScheduling
@ComponentScan(basePackages = "projetlivrebatchservice")
@PropertySource("classpath:application.properties")
@Configuration
public class BatchApplication {

    //récup des properties
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
    //

    public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
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
