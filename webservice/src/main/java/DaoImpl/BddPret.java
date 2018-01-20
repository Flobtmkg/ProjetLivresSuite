package DaoImpl;

import DaoInterfaces.DaoPret;
import beans.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BddPret implements DaoPret {
    //
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PlatformTransactionManager ptm;
    @Autowired
    private void affectationJdbcTemplate(DataSource dataSource){
        this.jdbcTemplate= new JdbcTemplate(dataSource);
    }

    @Override
    public void ajouterPret(Pret newPret) {
        //Conversion de date
        LocalDate date1=LocalDate.parse(newPret.getDateDebutPret());
        LocalDate date2=LocalDate.parse(newPret.getDateFinPret());;
        long millisecondsSince1970A =date1.toEpochDay()*86400000;
        long millisecondsSince1970B =date2.toEpochDay()*86400000;
        java.sql.Date sqlDate1=new java.sql.Date(millisecondsSince1970A);
        java.sql.Date sqlDate2=new java.sql.Date(millisecondsSince1970B);
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                final String AJOUTPRET = "INSERT INTO pret(idutilisateur,idexemplaire,datedebutpret,datefinpret,prolongepret,rendupret) VALUES(?,?,?,?,?,?)";
                jdbcTemplate.update(AJOUTPRET,new Object[]{newPret.getIdUtilisateur(),newPret.getIdExemplaire(),sqlDate1,sqlDate2,newPret.isProlongePret(),newPret.isRenduPret()});
            }
        });
        //
    }

    @Override
    public ArrayList<Pret> listerInfosPretUtilisateur(int idutilisateur) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        ArrayList<Pret> pretOutput2=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Pret>>() {
            @Override
            public ArrayList<Pret> doInTransaction(TransactionStatus transactionStatus) {
                final String LISTERPRET = "SELECT * FROM pret WHERE idutilisateur=?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(LISTERPRET,new Object[] {idutilisateur});
                ArrayList<Pret> lesPrets=new ArrayList<Pret>();
                for (Map row : rows) {
                    Pret chaquePret=new Pret();
                    chaquePret.setIdPret((int)(row.get("idpret")));
                    chaquePret.setIdExemplaire((int)(row.get("idexemplaire")));
                    chaquePret.setIdUtilisateur((int)(row.get("idutilisateur")));
                    chaquePret.setDateDebutPret((Date)(row.get("datedebutpret")));
                    chaquePret.setDateFinPret((Date)(row.get("datefinpret")));
                    chaquePret.setProlongePret((boolean)(row.get("prolongepret")));
                    chaquePret.setRenduPret((boolean)(row.get("rendupret")));
                    lesPrets.add(chaquePret);
                }
                //
                return lesPrets;
            }
        });
        //
        return pretOutput2;
    }

    @Override
    public ArrayList<Reservation> listerUtilisateursPretsNonRendus() {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        ArrayList<Reservation> reservationOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Reservation>>() {
            @Override
            public ArrayList<Reservation> doInTransaction(TransactionStatus transactionStatus) {
                final String LISTERRESERVATION = "SELECT * FROM utilisateur INNER JOIN pret ON utilisateur.idutilisateur=pret.idutilisateur INNER JOIN exemplaire ON exemplaire.idexemplaire=pret.idexemplaire INNER JOIN livre ON exemplaire.idlivre=livre.idlivre WHERE rendupret=false AND datefinpret<CURRENT_DATE;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(LISTERRESERVATION);
                ArrayList<Reservation> lesReservations=new ArrayList<Reservation>();
                for (Map row : rows) {
                    Reservation chaqueReservation=new Reservation();
                    //
                    Pret chaquePret=new Pret();
                    //
                    chaquePret.setIdPret((int)(row.get("idpret")));
                    chaquePret.setIdExemplaire((int)(row.get("idexemplaire")));
                    chaquePret.setIdUtilisateur((int)(row.get("idutilisateur")));
                    chaquePret.setDateDebutPret((Date)(row.get("datedebutpret")));
                    chaquePret.setDateFinPret((Date)(row.get("datefinpret")));
                    chaquePret.setProlongePret((boolean)(row.get("prolongepret")));
                    chaquePret.setRenduPret((boolean)(row.get("rendupret")));
                    //
                    Utilisateur chaqueUtilisateur=new Utilisateur();
                    //
                    chaqueUtilisateur.setIdUtilisateur((int)(row.get("idutilisateur")));
                    chaqueUtilisateur.setNomUtilisateur((String) (row.get("nomutilisateur")));
                    chaqueUtilisateur.setPrenomUtilisateur((String)(row.get("prenomutilisateur")));
                    chaqueUtilisateur.setEmailUtilisateur((String)(row.get("emailutilisateur")));
                    chaqueUtilisateur.setMdpUtilisateur((String)(row.get("mdputilisateur")));
                    chaqueUtilisateur.setDateNaissanceUtilisateur((Date)(row.get("datenaissanceutilisateur")));
                    //
                    Exemplaire chaqueExemplaire=new Exemplaire();
                    //
                    chaqueExemplaire.setIdExemplaire((int)(row.get("idexemplaire")));
                    chaqueExemplaire.setIdLivre((int)(row.get("idlivre")));
                    chaqueExemplaire.setCoteExemplaire((String) (row.get("coteexemplaire")));
                    chaqueExemplaire.setRemarqueExemplaire((String) (row.get("remarqueexemplaire")));
                    //
                    Livre chaqueLivre=new Livre();
                    //
                    chaqueLivre.setIdLivre((int)(row.get("idlivre")));
                    chaqueLivre.setTitreLivre((String) (row.get("titrelivre")));
                    chaqueLivre.setAuteurLivre((String)(row.get("auteurlivre")));
                    chaqueLivre.setEditeurLivre((String)(row.get("editeurlivre")));
                    chaqueLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    chaqueLivre.setIndexationLivre((String) (row.get("indexationlivre")));
                    //
                    chaqueReservation.setPretReservation(chaquePret);
                    chaqueReservation.setUtilisateurReservation(chaqueUtilisateur);
                    chaqueReservation.setExemplaireReservation(chaqueExemplaire);
                    chaqueReservation.setLivreReservation(chaqueLivre);
                    //
                    lesReservations.add(chaqueReservation);
                }
                //
                return lesReservations;
            }
        });
        //
        return reservationOutput;
    }

    @Override
    public Pret etatPret(int idPret) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        Pret pretOutput3=vTransactionTemplate.execute(new TransactionCallback<Pret>() {
            @Override
            public Pret doInTransaction(TransactionStatus transactionStatus) {
                final String PRET = "SELECT * FROM pret WHERE idpret=?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(PRET,new Object[] {idPret});
                Pret lePret=new Pret();
                for (Map row : rows) {
                    lePret.setIdPret((int)(row.get("idpret")));
                    lePret.setIdExemplaire((int)(row.get("idexemplaire")));
                    lePret.setIdUtilisateur((int)(row.get("idutilisateur")));
                    lePret.setDateDebutPret((Date)(row.get("datedebutpret")));
                    lePret.setDateFinPret((Date)(row.get("datefinpret")));
                    lePret.setProlongePret((boolean)(row.get("prolongepret")));
                    lePret.setRenduPret((boolean)(row.get("rendupret")));
                }
                //
                return lePret;
            }
        });
        //
        return pretOutput3;
    }

    @Override
    public void prolongerPret(int idPret) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                final String PROLONGERPRET = "UPDATE pret SET prolongepret=true WHERE idpret=?;";
                jdbcTemplate.update(PROLONGERPRET,new Object[]{idPret});
            }
        });
        //
    }

    @Override
    public void retourPret(int idPret) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                final String RETOURPRET = "UPDATE pret SET rendupret=true WHERE idpret=?;";
                jdbcTemplate.update(RETOURPRET,new Object[]{idPret});
            }
        });
        //
    }
}
