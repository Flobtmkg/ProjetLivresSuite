package DaoImpl;

import DaoInterfaces.DaoExemplaire;
import beans.Exemplaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BddExemplaire extends Impl implements DaoExemplaire {
    //
    @Override
    public Exemplaire premierExemplaireDisponible(int idLivre) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        Exemplaire exemplaireOutput=vTransactionTemplate.execute(new TransactionCallback<Exemplaire>() {
            @Override
            public Exemplaire doInTransaction(TransactionStatus transactionStatus) {
                final String PREMIEREXEMPLAIRE = "SELECT * FROM (SELECT * FROM pret FULL JOIN exemplaire ON pret.idexemplaire=exemplaire.idexemplaire WHERE rendupret=false or rendupret IS NULL ORDER BY datefinpret DESC) AS derniersprets WHERE idlivre=? AND (((datefinpret<CURRENT_DATE AND rendupret=true) OR (datefinpret>CURRENT_DATE AND rendupret=true)) OR (datefinpret IS NULL)) ORDER BY datefinpret DESC LIMIT 1;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(PREMIEREXEMPLAIRE,new Object[] {idLivre});
                Exemplaire lExemplaire=new Exemplaire();
                for (Map row : rows) {
                    lExemplaire.setIdExemplaire((int)(row.get("idexemplaire")));
                    lExemplaire.setIdLivre((int)(row.get("idlivre")));
                    lExemplaire.setCoteExemplaire((String)(row.get("coteexemplaire")));
                    lExemplaire.setRemarqueExemplaire((String)(row.get("remarqueexemplaire")));
                }
                //
                return lExemplaire;
            }
        });
        //
        return exemplaireOutput;
    }

    @Override
    public ArrayList<Exemplaire> listerExemplaire(int idLivre) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        ArrayList<Exemplaire> exemplaireOutput2=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Exemplaire>>() {
            @Override
            public ArrayList<Exemplaire> doInTransaction(TransactionStatus transactionStatus) {
                final String LISTEREXEMPLAIRE = "SELECT * FROM exemplaire WHERE idlivre=?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(LISTEREXEMPLAIRE,new Object[] {idLivre});
                ArrayList<Exemplaire> lesExemplaires=new ArrayList<Exemplaire>();
                for (Map row : rows) {
                    Exemplaire newexemplaire=new Exemplaire();
                    newexemplaire.setIdExemplaire((int)(row.get("idexemplaire")));
                    newexemplaire.setIdLivre((int)(row.get("idlivre")));
                    newexemplaire.setCoteExemplaire((String)(row.get("coteexemplaire")));
                    newexemplaire.setRemarqueExemplaire((String)(row.get("remarqueexemplaire")));
                    lesExemplaires.add(newexemplaire);
                }
                //
                return lesExemplaires;
            }
        });
        //
        return exemplaireOutput2;
    }

    @Override
    public void AjouterExemplaire(Exemplaire exemplaireInput) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                final String AJOUTEXEMPLAIRE = "INSERT INTO exemplaire(idlivre,coteexemplaire,remarqueexemplaire) VALUES(?,?,?)";
                jdbcTemplate.update(AJOUTEXEMPLAIRE,new Object[]{exemplaireInput.getIdLivre(),exemplaireInput.getCoteExemplaire(),exemplaireInput.getRemarqueExemplaire()});
            }
        });
        //
    }
}
