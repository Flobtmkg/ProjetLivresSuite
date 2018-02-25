package DaoImpl;

import DaoInterfaces.DaoExemplaire;
import ServicesBeans.Exemplaire;
import classesTravail.CodageGuillemets;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BddExemplaire extends Impl implements DaoExemplaire {
    //
    @Override
    public ArrayList<Exemplaire> ExemplaireDisponible(boolean mode,int idLivre) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        if(mode==true) { //mode1: un seul exemplaire est sorti, le premier disponible...
            Exemplaire exemplaireOutput = vTransactionTemplate.execute(new TransactionCallback<Exemplaire>() {
                @Override
                public Exemplaire doInTransaction(TransactionStatus transactionStatus) {
                    final String PREMIEREXEMPLAIRE = "SELECT * FROM (SELECT resultatexemplaire.idexemplaire,idlivre,coteexemplaire,remarqueexemplaire,idpret,idutilisateur,datedebutpret,datefinpret,prolongepret,rendupret FROM(SELECT * FROM exemplaire WHERE idlivre=?) AS resultatexemplaire FULL JOIN pret ON pret.idexemplaire=resultatexemplaire.idexemplaire WHERE idlivre IS NOT NULL) AS resultatpret FULL JOIN (SELECT pret.idexemplaire AS lexemplaire,MAX(pret.datefinpret) FROM pret GROUP BY pret.idexemplaire) AS maxdate ON resultatpret.idexemplaire=maxdate.lexemplaire WHERE idlivre IS NOT NULL AND (datefinpret=max OR max IS NULL) AND (rendupret=true OR rendupret IS NULL) ORDER BY datefinpret ASC LIMIT 1;";
                    //
                    List<Map<String, Object>> rows = jdbcTemplate.queryForList(PREMIEREXEMPLAIRE, new Object[]{idLivre});
                    Exemplaire lExemplaire = new Exemplaire();
                    for (Map row : rows) {
                        lExemplaire.setIdExemplaire((int) (row.get("idexemplaire")));
                        lExemplaire.setIdLivre((int) (row.get("idlivre")));
                        lExemplaire.setCoteExemplaire(CodageGuillemets.getTexteDecode((String) (row.get("coteexemplaire"))));
                        lExemplaire.setRemarqueExemplaire(CodageGuillemets.getTexteDecode((String) (row.get("remarqueexemplaire"))));
                    }
                    //
                    return lExemplaire;
                }
            });
            //
            //Création d'une liste bidon pour se conformer au type de sortie
            ArrayList<Exemplaire> listExemplaireOutput = new ArrayList<Exemplaire>();
            listExemplaireOutput.add(exemplaireOutput);
            return listExemplaireOutput;
        }else{ //mode0: sort tout les exemplaires disponibles

            ArrayList<Exemplaire> exemplaireOutput2=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Exemplaire>>() {
                @Override
                public ArrayList<Exemplaire> doInTransaction(TransactionStatus transactionStatus) {
                    final String EXEMPLAIRESDISPO = "SELECT * FROM (SELECT resultatexemplaire.idexemplaire,idlivre,coteexemplaire,remarqueexemplaire,idpret,idutilisateur,datedebutpret,datefinpret,prolongepret,rendupret FROM(SELECT * FROM exemplaire WHERE idlivre=?) AS resultatexemplaire FULL JOIN pret ON pret.idexemplaire=resultatexemplaire.idexemplaire WHERE idlivre IS NOT NULL) AS resultatpret FULL JOIN (SELECT pret.idexemplaire AS lexemplaire,MAX(pret.datefinpret) FROM pret GROUP BY pret.idexemplaire) AS maxdate ON resultatpret.idexemplaire=maxdate.lexemplaire WHERE idlivre IS NOT NULL AND (datefinpret=max OR max IS NULL) AND (rendupret=true OR rendupret IS NULL) ORDER BY datefinpret ASC;";
                    //
                    List<Map<String,Object>> rows = jdbcTemplate.queryForList(EXEMPLAIRESDISPO,new Object[] {idLivre});
                    ArrayList<Exemplaire> lesExemplaires=new ArrayList<Exemplaire>();
                    for (Map row : rows) {
                        Exemplaire newexemplaire=new Exemplaire();
                        newexemplaire.setIdExemplaire((int)(row.get("idexemplaire")));
                        newexemplaire.setIdLivre((int)(row.get("idlivre")));
                        newexemplaire.setCoteExemplaire(CodageGuillemets.getTexteDecode((String)(row.get("coteexemplaire"))));
                        newexemplaire.setRemarqueExemplaire(CodageGuillemets.getTexteDecode((String)(row.get("remarqueexemplaire"))));
                        lesExemplaires.add(newexemplaire);
                    }
                    //
                    return lesExemplaires;
                }
            });
            //
            return exemplaireOutput2;
        }
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
                    newexemplaire.setCoteExemplaire(CodageGuillemets.getTexteDecode((String)(row.get("coteexemplaire"))));
                    newexemplaire.setRemarqueExemplaire(CodageGuillemets.getTexteDecode((String)(row.get("remarqueexemplaire"))));
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
                jdbcTemplate.update(AJOUTEXEMPLAIRE,new Object[]{exemplaireInput.getIdLivre(), CodageGuillemets.getTexteEncode(exemplaireInput.getCoteExemplaire()),CodageGuillemets.getTexteEncode(exemplaireInput.getRemarqueExemplaire())});
            }
        });
        //
    }
}
