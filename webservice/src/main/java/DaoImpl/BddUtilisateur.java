package DaoImpl;

import DaoInterfaces.DaoUtilisateur;
import beans.Utilisateur;
import jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;

@Repository
public class BddUtilisateur extends Impl implements DaoUtilisateur {
    //
    @Override
    public Utilisateur autentifier(String emailInput, String mdpInput) {
        //
        String mdpchiffre= BCrypt.hashpw(mdpInput, BCrypt.gensalt());
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        Utilisateur utilisateurOutput=vTransactionTemplate.execute(new TransactionCallback<Utilisateur>() {
            @Override
            public Utilisateur doInTransaction(TransactionStatus transactionStatus) {
                final String AUTENTIFIER = "SELECT * FROM utilisateur WHERE emailutilisateur=? AND mdputilisateur=?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(AUTENTIFIER,new Object[] {emailInput,mdpchiffre});
                Utilisateur lUtilisateur=new Utilisateur();
                for (Map row : rows) {
                    lUtilisateur.setIdUtilisateur((int)(row.get("idutilisateur")));
                    lUtilisateur.setNomUtilisateur((String)(row.get("nomutilisateur")));
                    lUtilisateur.setPrenomUtilisateur((String)(row.get("prenomutilisateur")));
                    lUtilisateur.setEmailUtilisateur((String)(row.get("emailutilisateur")));
                    lUtilisateur.setMdpUtilisateur((String)(row.get("mdputilisateur")));
                    lUtilisateur.setDateNaissanceUtilisateur((String)(row.get("datenaissanceutilisateur")));
                }
                //
                return lUtilisateur;
            }
        });
        //
        return utilisateurOutput;
    }

    @Override
    public void ajouterUtilisateur(Utilisateur inputUtilisateur) {
        //Chiffrage
        String mdpChiffre= BCrypt.hashpw(inputUtilisateur.getMdpUtilisateur(), BCrypt.gensalt());
        inputUtilisateur.setMdpUtilisateur(mdpChiffre);
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                final String AJOUTUTILISATEUR = "INSERT INTO utilisateur(nomutilisateur,prenomutilisateur,emailutilisateur,mdputilisateur,datenaissanceutilisateur) VALUES(?,?,?,?,?)";
                jdbcTemplate.update(AJOUTUTILISATEUR,new Object[]{inputUtilisateur.getNomUtilisateur(),inputUtilisateur.getPrenomUtilisateur(),inputUtilisateur.getEmailUtilisateur(),inputUtilisateur.getMdpUtilisateur(),inputUtilisateur.getDateNaissanceUtilisateur()});
            }
        });
        //
    }
}
