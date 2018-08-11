package DaoImpl;


import DaoInterfaces.DaoReservation;
import ServicesBeans.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.*;

@Repository
public class BddReservation extends Impl implements DaoReservation {

    // Mapper de liste de prereservation
    private List<PreReservation> preReservationListMapper(List<Map<String,Object>> rows){
        ArrayList<PreReservation> listeOutput=new ArrayList<PreReservation>();
        for (Map row : rows) {
            PreReservation chaquePreReservation=new PreReservation();
            //Correspondance avec les colonnes en table
            chaquePreReservation.setIdReservation((int)row.get("idreservation"));
            chaquePreReservation.setIdLivre((int)row.get("idlivre"));
            chaquePreReservation.setIdUtilisateur((int)row.get("idutilisateur"));
            try{ //catch la possibilité d'un idPret non affecté
                chaquePreReservation.setIdPret((int)row.get("idpret"));
            }catch (Exception ex){
                // we keep it null
            }
            chaquePreReservation.setDateReservation((Date)row.get("date_res"));
            try { //catch la possibilité d'un date_dispo non affecté
                chaquePreReservation.setDateDisponibilite((Date)row.get("date_dispo"));
            }catch(Exception ex){
                // we keep it null
            }
            chaquePreReservation.setAnnule((boolean)row.get("is_annule"));
            chaquePreReservation.setEmailDepassementSent((boolean)row.get("isemaildepassementsent"));
            listeOutput.add(chaquePreReservation);
        }
        return listeOutput;
    }


    @Override
    public List<PreReservation> getListeAttente(int idLivre) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //
        List<PreReservation> preReservationOutput=vTransactionTemplate.execute(new TransactionCallback<List<PreReservation>>() {
            @Override
            public List<PreReservation> doInTransaction(TransactionStatus transactionStatus) {
                final String LISTEATTENTE = "SELECT * FROM reservation WHERE idlivre=? AND idpret IS null AND is_annule=false AND date_dispo IS null ORDER BY date_res ASC;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(LISTEATTENTE,new Object[] {idLivre});
                List<PreReservation> listAttente=preReservationListMapper(rows);

                return listAttente;
            }
        });
        return preReservationOutput;
    }

    @Override
    public List<PreReservation> getListeReservationByUser(int idUtilisateur) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //
        List<PreReservation> preReservationOutput=vTransactionTemplate.execute(new TransactionCallback<List<PreReservation>>() {
            @Override
            public List<PreReservation> doInTransaction(TransactionStatus transactionStatus) {
                final String RESERVATIONBYUSER = "SELECT * FROM reservation WHERE idutilisateur=?";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(RESERVATIONBYUSER,new Object[] {idUtilisateur});
                List<PreReservation> listReservation=preReservationListMapper(rows);
                return listReservation;
            }
        });
        return preReservationOutput;
    }

    @Override
    public List<PreReservation> getReservationEffectiveByLivre(int idLivre) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //
        List<PreReservation> preReservationOutput=vTransactionTemplate.execute(new TransactionCallback<List<PreReservation>>() {
            @Override
            public List<PreReservation> doInTransaction(TransactionStatus transactionStatus) {
                final String LISTEFFECTIVE="SELECT * FROM reservation WHERE idlivre=? AND date_dispo + INTERVAL '48 hours' > NOW() AND is_annule=false AND idpret IS NULL;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(LISTEFFECTIVE,new Object[] {idLivre});
                List<PreReservation> listEffective=preReservationListMapper(rows);

                return listEffective;
            }
        });
        return preReservationOutput;
    }

    @Override
    public List<PreReservation> getEnvoiEmailListeReservationTempsEffectfDepasse() {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //
        List<PreReservation> preReservationOutput=vTransactionTemplate.execute(new TransactionCallback<List<PreReservation>>() {
            @Override
            public List<PreReservation> doInTransaction(TransactionStatus transactionStatus) {
                final String LISTEFFECTIVE="SELECT * FROM reservation WHERE date_dispo + INTERVAL '48 hours' < NOW() AND is_annule=false AND idpret IS NULL AND isemaildepassementsent=false;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(LISTEFFECTIVE);
                List<PreReservation> listeffective=preReservationListMapper(rows);
                return listeffective;
            }
        });
        return preReservationOutput;
    }

    @Override
    public PreReservation getReservationById(int idReservation) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //
        List<PreReservation> preReservationOutput=vTransactionTemplate.execute(new TransactionCallback<List<PreReservation>>() {
            @Override
            public List<PreReservation> doInTransaction(TransactionStatus transactionStatus) {
                final String RESERVATION="SELECT * FROM reservation WHERE idreservation=?";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(RESERVATION,new Object[]{idReservation});
                List<PreReservation> outputList = preReservationListMapper(rows);
                return outputList;
            }
        });
        return preReservationOutput.get(0);
    }

    @Override
    public void annulerReservation(int idReservation) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                final String ANNULERRESERVATION = "UPDATE reservation SET is_annule = true WHERE idreservation=?;";
                jdbcTemplate.update(ANNULERRESERVATION,new Object[]{idReservation});
            }
        });
    }


    @Override
    public void defDateDisponibiliteReservation(int idReservation) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                final String DEFTODAYDISPORESERVATION = "UPDATE reservation SET date_dispo = NOW() WHERE idreservation=?;";
                jdbcTemplate.update(DEFTODAYDISPORESERVATION,new Object[]{idReservation});
            }
        });
    }

    @Override
    public void affectationPretReservation(int idReservation, int idPret) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                final String DEFPRETRESERVATION = "UPDATE reservation SET idpret = ? WHERE idreservation=?;";
                jdbcTemplate.update(DEFPRETRESERVATION,new Object[]{idPret,idReservation});
            }
        });
    }

    @Override
    public void affectationsEmailDepassementSent(List<Integer> listIdReservation) {
        //
        //int[] tabInt = Arrays.stream(listIdReservation.toArray()).map(Integer.class::cast).mapToInt(Integer::intValue).toArray();
        if(listIdReservation.size()>0){
            TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
            vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
            vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    String DEFPRETRESERVATION = "UPDATE reservation SET isemaildepassementsent = true WHERE ";
                    for (int i:listIdReservation) {
                        DEFPRETRESERVATION=DEFPRETRESERVATION + "idreservation = ? OR ";
                    }
                    // kill the last "AND"
                    DEFPRETRESERVATION=DEFPRETRESERVATION.substring(0,DEFPRETRESERVATION.length()-4);
                    jdbcTemplate.update(DEFPRETRESERVATION,listIdReservation.toArray());
                }
            });
        }

    }

    @Override
    public void addReservation(PreReservation reservationInput, boolean addDateDispo) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                String AJOUTRESERVATION;
                if(addDateDispo==true){
                    AJOUTRESERVATION="INSERT INTO reservation(idlivre,idutilisateur,date_res,date_dispo) VALUES(?,?,NOW(),NOW())";
                }else{
                    AJOUTRESERVATION="INSERT INTO reservation(idlivre,idutilisateur,date_res) VALUES(?,?,NOW())";
                }
                jdbcTemplate.update(AJOUTRESERVATION,new Object[]{reservationInput.getIdLivre(),reservationInput.getIdUtilisateur()});
            }
        });

    }

}
