package DaoImpl;

import DaoInterfaces.DaoNotation;
import ServicesBeans.Notation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;


@Repository
public class BddNotation extends Impl implements DaoNotation {

    @Override
    public void ajouterNotation(Notation notationInput) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                final String AJOUTNOTATION = "INSERT INTO notation(idutilisateur,idlivre,valuenotation) VALUES(?,?,?)";
                jdbcTemplate.update(AJOUTNOTATION,new Object[]{notationInput.getIdUtilisateur(), notationInput.getIdLivre(), notationInput.getValueNotation()});
            }
        });
        //
    }

    @Override
    public double notationLivre(int idLivre) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        Notation notationOutput=vTransactionTemplate.execute(new TransactionCallback<Notation>() {
            @Override
            public Notation doInTransaction(TransactionStatus transactionStatus) {
                final String NOTATION = "SELECT AVG(valuenotation)::double precision AS moy FROM notation WHERE idlivre=? GROUP BY idlivre;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(NOTATION,new Object[] {idLivre});
                Notation laNotation= new Notation();
                for (Map row : rows) {
                    laNotation.setValueNotation((double)(row.get("moy")));
                }
                //
                return laNotation;
            }
        });
        //
        return notationOutput.getValueNotation();
    }
}
