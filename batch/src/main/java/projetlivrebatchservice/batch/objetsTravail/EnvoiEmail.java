package projetlivrebatchservice.batch.objetsTravail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

    public class EnvoiEmail {

        public static String envoi(String host, String FromWho, String UserName, String PassWord, String toWho, String subject, String message) {

            String erreureventuelle="";
            Email rappel= new SimpleEmail();
            try {
                rappel.setHostName(host);//en passant par Gmail smtp.googlemail.com
                rappel.setAuthenticator(new DefaultAuthenticator(UserName, PassWord));//on passe par un compte Gmail il faut donc s'identifier
                rappel.setSSLOnConnect(true);//
                rappel.setFrom(FromWho);//Définit le champ From de l'email lors des tests: suggestions.projetescalade@gmail.com
                rappel.setSubject(subject);
                rappel.setMsg(message);
                rappel.addTo(toWho);//A qui l'email doit être envoyé
                //rappel.setSslSmtpPort("587");
                rappel.send();
                return erreureventuelle;
            }catch(Exception e) {
                //erreur
                erreureventuelle=e.getMessage();
                return erreureventuelle;
            }
        }
    }
