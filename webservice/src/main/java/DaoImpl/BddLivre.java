package DaoImpl;

import DaoInterfaces.DaoLivre;
import beans.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class BddLivre implements DaoLivre {
    //
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PlatformTransactionManager ptm;
    @Autowired
    private void affectationJdbcTemplate(DataSource dataSource){
        this.jdbcTemplate= new JdbcTemplate(dataSource);
    }
    //
    @Override
    public Livre infoLivre(int idLivre) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        Livre LivreOutput=vTransactionTemplate.execute(new TransactionCallback<Livre>() {
            @Override
            public Livre doInTransaction(TransactionStatus transactionStatus) {
                final String LIVRE = "SELECT * FROM livre WHERE idLivre=?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(LIVRE,new Object[] {idLivre});
                Livre leLivre=new Livre();
                for (Map row : rows) {
                    leLivre.setIdLivre((int)(row.get("idlivre")));
                    leLivre.setTitreLivre((String) (row.get("titrelivre")));
                    leLivre.setAuteurLivre((String)(row.get("auteurlivre")));
                    leLivre.setEditeurLivre((String)(row.get("editeurlivre")));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre((String)(row.get("indexationlivre")));
                }
                //
                return leLivre;
            }
        });
        //
        return LivreOutput;
    }

    @Override
    public void ajouterLivre(Livre newLivre) {
        //Conversion de date
        LocalDate date1=LocalDate.parse(newLivre.getDatepublicationLivre());
        long millisecondsSince1970A =date1.toEpochDay()*86400000;
        java.sql.Date sqlDate1=new java.sql.Date(millisecondsSince1970A);
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                final String AJOUTLIVRE = "INSERT INTO livre(idlivre,titrelivre,auteurlivre,editeurlivre,datepublicationlivre,indexationlivre) VALUES(?,?,?,?,?,?)";
                jdbcTemplate.update(AJOUTLIVRE,new Object[]{newLivre.getIdLivre(),newLivre.getTitreLivre(),newLivre.getAuteurLivre(),newLivre.getEditeurLivre(),sqlDate1,newLivre.getIndexationLivre()});
            }
        });
        //
    }
}
