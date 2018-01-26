package DaoImpl;

import DaoInterfaces.DaoUtilisateur;
import beans.Utilisateur;
import classesTravail.CodageGuillemets;
import jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class BddUtilisateur extends Impl implements DaoUtilisateur {
    //
    @Override
    public Utilisateur autentifier(String emailInput, String mdpInput) {
        //
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        Utilisateur utilisateurOutput=vTransactionTemplate.execute(new TransactionCallback<Utilisateur>() {
            @Override
            public Utilisateur doInTransaction(TransactionStatus transactionStatus) {
                final String AUTENTIFIER = "SELECT * FROM utilisateur WHERE emailutilisateur=?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(AUTENTIFIER,new Object[] {emailInput});
                Utilisateur lUtilisateur=new Utilisateur();
                for (Map row : rows) {
                    lUtilisateur.setIdUtilisateur((int)(row.get("idutilisateur")));
                    lUtilisateur.setNomUtilisateur(CodageGuillemets.getTexteDecode((String)(row.get("nomutilisateur"))));
                    lUtilisateur.setPrenomUtilisateur(CodageGuillemets.getTexteDecode((String)(row.get("prenomutilisateur"))));
                    lUtilisateur.setEmailUtilisateur(CodageGuillemets.getTexteDecode((String)(row.get("emailutilisateur"))));
                    lUtilisateur.setMdpUtilisateur(CodageGuillemets.getTexteDecode((String)(row.get("mdputilisateur"))));
                    lUtilisateur.setDateNaissanceUtilisateur((Date)(row.get("datenaissanceutilisateur")));
                }
                //
                return lUtilisateur;
            }
        });
        //
        if(utilisateurOutput.getMdpUtilisateur()!=null){
            if(BCrypt.checkpw(mdpInput,utilisateurOutput.getMdpUtilisateur())==false){
                return new Utilisateur();
            }else{
                return utilisateurOutput;
            }
        }else{
            return new Utilisateur();
        }

        //
    }

    @Override
    public void ajouterUtilisateur(Utilisateur inputUtilisateur) {
        //Chiffrage
        String mdpChiffre= BCrypt.hashpw(inputUtilisateur.getMdpUtilisateur(), BCrypt.gensalt());
        inputUtilisateur.setMdpUtilisateur(mdpChiffre);
        //
        //Conversion de date
        LocalDate date1=LocalDate.parse(inputUtilisateur.getDateNaissanceUtilisateur());
        long millisecondsSince1970A =date1.toEpochDay()*86400000;
        java.sql.Date sqlDate1=new java.sql.Date(millisecondsSince1970A);
        //
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                final String AJOUTUTILISATEUR = "INSERT INTO utilisateur(nomutilisateur,prenomutilisateur,emailutilisateur,mdputilisateur,datenaissanceutilisateur) VALUES(?,?,?,?,?);";
                jdbcTemplate.update(AJOUTUTILISATEUR,new Object[]{CodageGuillemets.getTexteEncode(inputUtilisateur.getNomUtilisateur()),CodageGuillemets.getTexteEncode(inputUtilisateur.getPrenomUtilisateur()),CodageGuillemets.getTexteEncode(inputUtilisateur.getEmailUtilisateur()),CodageGuillemets.getTexteEncode(inputUtilisateur.getMdpUtilisateur()),sqlDate1});
            }
        });
        //
    }
}
