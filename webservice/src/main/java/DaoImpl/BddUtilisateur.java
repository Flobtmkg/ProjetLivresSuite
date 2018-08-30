package DaoImpl;

import DaoInterfaces.DaoUtilisateur;
import ServicesBeans.Utilisateur;
import classesTravail.CodageGuillemets;
import jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
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
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //
        ArrayList<Utilisateur> utilisateursOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Utilisateur>>() {
            @Override
            public ArrayList<Utilisateur> doInTransaction(TransactionStatus transactionStatus) {
                final String AUTENTIFIER = "SELECT * FROM utilisateur WHERE emailutilisateur=?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(AUTENTIFIER,new Object[] {emailInput});
                ArrayList<Utilisateur> multiAdressesPourTest = new ArrayList<Utilisateur>();
                for (Map row : rows) {
                    Utilisateur lUtilisateur=new Utilisateur();
                    lUtilisateur.setIdUtilisateur((int)(row.get("idutilisateur")));
                    lUtilisateur.setNomUtilisateur(CodageGuillemets.getTexteDecode((String)(row.get("nomutilisateur"))));
                    lUtilisateur.setPrenomUtilisateur(CodageGuillemets.getTexteDecode((String)(row.get("prenomutilisateur"))));
                    lUtilisateur.setEmailUtilisateur(CodageGuillemets.getTexteDecode((String)(row.get("emailutilisateur"))));
                    lUtilisateur.setMdpUtilisateur(CodageGuillemets.getTexteDecode((String)(row.get("mdputilisateur"))));
                    lUtilisateur.setDateNaissanceUtilisateur((Date)(row.get("datenaissanceutilisateur")));
                    multiAdressesPourTest.add(lUtilisateur);
                }
                //
                return multiAdressesPourTest;
            }
        });


        //Plusieurs adresses email peuvent être identiques notement en situation de test, ce cas est donc traité ici
        Utilisateur newUtilisateur=new Utilisateur();
        for (Utilisateur chaqueUtilisateur:utilisateursOutput) {
            if(chaqueUtilisateur.getMdpUtilisateur()!=null){
                if(BCrypt.checkpw(mdpInput,chaqueUtilisateur.getMdpUtilisateur())==true){
                    newUtilisateur=chaqueUtilisateur;
                }
            }
        }
        return newUtilisateur;
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
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                final String AJOUTUTILISATEUR = "INSERT INTO utilisateur(nomutilisateur,prenomutilisateur,emailutilisateur,mdputilisateur,datenaissanceutilisateur) VALUES(?,?,?,?,?);";
                jdbcTemplate.update(AJOUTUTILISATEUR,new Object[]{CodageGuillemets.getTexteEncode(inputUtilisateur.getNomUtilisateur()),CodageGuillemets.getTexteEncode(inputUtilisateur.getPrenomUtilisateur()),CodageGuillemets.getTexteEncode(inputUtilisateur.getEmailUtilisateur()),CodageGuillemets.getTexteEncode(inputUtilisateur.getMdpUtilisateur()),sqlDate1});
            }
        });


        //
    }

    @Override
    public boolean isEmailExistInBase(String inputEmail) {
            //
            TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
            vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
            //
            Utilisateur utilisateurOutput=vTransactionTemplate.execute(new TransactionCallback<Utilisateur>() {
                @Override
                public Utilisateur doInTransaction(TransactionStatus transactionStatus) {
                    final String TESTEREMAIL = "SELECT * FROM utilisateur WHERE emailutilisateur=?;";
                    //
                    List<Map<String,Object>> rows = jdbcTemplate.queryForList(TESTEREMAIL,new Object[] {inputEmail});
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


            // Test si l'utilisateur existe
            if(utilisateurOutput.getIdUtilisateur()!=0){
                //utilisateur existe
                return true;
            }else{
                //utilisateur n'existe pas
                return false;
            }
    }

    @Override
    public void modifierUtilisateur(Utilisateur inputUtilisateur) {
        if(inputUtilisateur.getMdpUtilisateur().equals("")){ //Pas de modification du mdp
            //
            //Conversion de date
            LocalDate date1=LocalDate.parse(inputUtilisateur.getDateNaissanceUtilisateur());
            long millisecondsSince1970A =date1.toEpochDay()*86400000;
            java.sql.Date sqlDate1=new java.sql.Date(millisecondsSince1970A);
            //
            //
            TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
            vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
            vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    final String AJOUTUTILISATEUR = "UPDATE utilisateur SET nomutilisateur=?,prenomutilisateur=?,emailutilisateur=?,datenaissanceutilisateur=? WHERE idutilisateur=? ;";
                    jdbcTemplate.update(AJOUTUTILISATEUR,new Object[]{CodageGuillemets.getTexteEncode(inputUtilisateur.getNomUtilisateur()),CodageGuillemets.getTexteEncode(inputUtilisateur.getPrenomUtilisateur()),CodageGuillemets.getTexteEncode(inputUtilisateur.getEmailUtilisateur()),sqlDate1,inputUtilisateur.getIdUtilisateur()});
                }
            });


        }else{ //Modification du mdp
            //Chiffrage
            String mdpChiffre= BCrypt.hashpw(inputUtilisateur.getMdpUtilisateur(), BCrypt.gensalt());
            //
            //Conversion de date
            LocalDate date1=LocalDate.parse(inputUtilisateur.getDateNaissanceUtilisateur());
            long millisecondsSince1970A =date1.toEpochDay()*86400000;
            java.sql.Date sqlDate1=new java.sql.Date(millisecondsSince1970A);
            //
            //
            TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
            vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
            vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    final String AJOUTUTILISATEUR = "UPDATE utilisateur SET nomutilisateur=?,prenomutilisateur=?,emailutilisateur=?,mdputilisateur=?,datenaissanceutilisateur=? WHERE idutilisateur=? ;";
                    jdbcTemplate.update(AJOUTUTILISATEUR,new Object[]{CodageGuillemets.getTexteEncode(inputUtilisateur.getNomUtilisateur()),CodageGuillemets.getTexteEncode(inputUtilisateur.getPrenomUtilisateur()),CodageGuillemets.getTexteEncode(inputUtilisateur.getEmailUtilisateur()),CodageGuillemets.getTexteEncode(mdpChiffre),sqlDate1,inputUtilisateur.getIdUtilisateur()});
                }
            });


        }

        //
    }

    @Override
    public Utilisateur getUserByID(int idUtilisateur) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //
        Utilisateur utilisateurOutput=vTransactionTemplate.execute(new TransactionCallback<Utilisateur>() {
            @Override
            public Utilisateur doInTransaction(TransactionStatus transactionStatus) {
                final String USERBYID = "SELECT * FROM utilisateur WHERE idutilisateur=?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(USERBYID,new Object[] {idUtilisateur});
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
        return utilisateurOutput;
    }
}
