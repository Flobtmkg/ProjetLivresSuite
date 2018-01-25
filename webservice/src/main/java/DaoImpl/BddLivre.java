package DaoImpl;

import DaoInterfaces.DaoLivre;
import beans.Livre;
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
public class BddLivre extends Impl implements DaoLivre {
    //
    @Override
    public Livre infoLivre(int idLivre) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        Livre livreOutput=vTransactionTemplate.execute(new TransactionCallback<Livre>() {
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
                    leLivre.setDescriptionLivre((String)(row.get("descriptionlivre")));
                }
                //
                return leLivre;
            }
        });
        //
        return livreOutput;
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

    @Override
    public ArrayList<Livre> rechercheTitre(String motcle) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //pré-traitement mot clé
        final String motcle2="%" + motcle.toUpperCase() + "%";
        //
        ArrayList<Livre> livreOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Livre>>() {
            @Override
            public ArrayList<Livre> doInTransaction(TransactionStatus transactionStatus) {
                final String LIVRES = "SELECT * FROM livre WHERE UPPER(titrelivre) LIKE ? OR UPPER(descriptionLivre) LIKE ?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(LIVRES,new Object[] {motcle2,motcle2});
                ArrayList<Livre> lesLivres = new ArrayList<Livre>();
                for (Map row : rows) {
                    Livre leLivre=new Livre();
                    leLivre.setIdLivre((int)(row.get("idlivre")));
                    leLivre.setTitreLivre((String) (row.get("titrelivre")));
                    leLivre.setAuteurLivre((String)(row.get("auteurlivre")));
                    leLivre.setEditeurLivre((String)(row.get("editeurlivre")));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre((String)(row.get("indexationlivre")));
                    leLivre.setDescriptionLivre((String)(row.get("descriptionlivre")));
                    lesLivres.add(leLivre);
                }
                //
                return lesLivres;
            }
        });
        //
        return livreOutput;
    }

    @Override
    public ArrayList<Livre> rechercheAuteur(String motcle) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //pré-traitement mot clé
        final String motcle2="%" + motcle.toUpperCase() + "%";
        //
        ArrayList<Livre> livreOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Livre>>() {
            @Override
            public ArrayList<Livre> doInTransaction(TransactionStatus transactionStatus) {
                final String LIVRES = "SELECT * FROM livre WHERE UPPER(auteurlivre) LIKE ? ;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(LIVRES,new Object[] {motcle2});
                ArrayList<Livre> lesLivres = new ArrayList<Livre>();
                for (Map row : rows) {
                    Livre leLivre=new Livre();
                    leLivre.setIdLivre((int)(row.get("idlivre")));
                    leLivre.setTitreLivre((String) (row.get("titrelivre")));
                    leLivre.setAuteurLivre((String)(row.get("auteurlivre")));
                    leLivre.setEditeurLivre((String)(row.get("editeurlivre")));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre((String)(row.get("indexationlivre")));
                    leLivre.setDescriptionLivre((String)(row.get("descriptionlivre")));
                    lesLivres.add(leLivre);
                }
                //
                return lesLivres;
            }
        });
        //
        return livreOutput;
    }

    @Override
    public ArrayList<Livre> rechercheEditeur(String motcle) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //pré-traitement mot clé
        final String motcle2="%" + motcle.toUpperCase() + "%";
        //
        ArrayList<Livre> livreOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Livre>>() {
            @Override
            public ArrayList<Livre> doInTransaction(TransactionStatus transactionStatus) {
                final String LIVRES = "SELECT * FROM livre WHERE UPPER(editeurlivre) LIKE ? ;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(LIVRES,new Object[] {motcle2});
                ArrayList<Livre> lesLivres = new ArrayList<Livre>();
                for (Map row : rows) {
                    Livre leLivre=new Livre();
                    leLivre.setIdLivre((int)(row.get("idlivre")));
                    leLivre.setTitreLivre((String) (row.get("titrelivre")));
                    leLivre.setAuteurLivre((String)(row.get("auteurlivre")));
                    leLivre.setEditeurLivre((String)(row.get("editeurlivre")));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre((String)(row.get("indexationlivre")));
                    leLivre.setDescriptionLivre((String)(row.get("descriptionlivre")));
                    lesLivres.add(leLivre);
                }
                //
                return lesLivres;
            }
        });
        //
        return livreOutput;
    }

    @Override
    public ArrayList<Livre> rechercheExemplaire(int idExemplaire) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        ArrayList<Livre> livreOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Livre>>() {
            @Override
            public ArrayList<Livre> doInTransaction(TransactionStatus transactionStatus) {
                final String LIVRES = "SELECT * FROM livre INNER JOIN exemplaire ON livre.idlivre=exemplaire.idlivre WHERE idexemplaire=?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(LIVRES,new Object[] {idExemplaire});
                ArrayList<Livre> lesLivres = new ArrayList<Livre>();
                for (Map row : rows) {
                    Livre leLivre=new Livre();
                    leLivre.setIdLivre((int)(row.get("idlivre")));
                    leLivre.setTitreLivre((String) (row.get("titrelivre")));
                    leLivre.setAuteurLivre((String)(row.get("auteurlivre")));
                    leLivre.setEditeurLivre((String)(row.get("editeurlivre")));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre((String)(row.get("indexationlivre")));
                    leLivre.setDescriptionLivre((String)(row.get("descriptionlivre")));
                    lesLivres.add(leLivre);
                }
                //
                return lesLivres;
            }
        });
        //
        return livreOutput;
    }

    @Override
    public ArrayList<String> chercheElementIndexation() {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        ArrayList<String> StringOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<String>>() {
            @Override
            public ArrayList<String> doInTransaction(TransactionStatus transactionStatus) {
                final String LIVRES = "SELECT * FROM elementindexation;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(LIVRES);
                ArrayList<String> lesString = new ArrayList<String>();
                for (Map row : rows) {
                    String valueType;
                    valueType=((String)(row.get("valueindexation")));
                    lesString.add(valueType);
                }
                //
                return lesString;
            }
        });
        //
        return StringOutput;
    }

    @Override
    public int nbchrElementIndexation(int elementIndexation) {
            //
            TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
            //
            int valOut=vTransactionTemplate.execute(new TransactionCallback<Integer>() {
                @Override
                public Integer doInTransaction(TransactionStatus transactionStatus) {
                    final String NBCHR = "SELECT nbrchrconstitutionindex FROM constitutionindex WHERE idelementindexation=?;";
                    //
                    List<Map<String,Object>> rows = jdbcTemplate.queryForList(NBCHR,new Object[] {elementIndexation});
                    int lenbChr=0;
                    for (Map row : rows) {
                        lenbChr=((int)(row.get("nbrchrconstitutionindex")));
                    }
                    //
                    return lenbChr;
                }
            });
            //
            return valOut;
    }

    @Override
    public ArrayList<Livre> rechercheType(String valueType,int valueNbChr) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        ArrayList<Livre> livreOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Livre>>() {
            @Override
            public ArrayList<Livre> doInTransaction(TransactionStatus transactionStatus) {
                final String INDEXATION = "SELECT * FROM livre WHERE SUBSTR(indexationlivre,1,?)=?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(INDEXATION,new Object[] {valueNbChr,valueType});
                ArrayList<Livre> lesLivres = new ArrayList<Livre>();
                for (Map row : rows) {
                    Livre leLivre=new Livre();
                    leLivre.setIdLivre((int)(row.get("idlivre")));
                    leLivre.setTitreLivre((String) (row.get("titrelivre")));
                    leLivre.setAuteurLivre((String)(row.get("auteurlivre")));
                    leLivre.setEditeurLivre((String)(row.get("editeurlivre")));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre((String)(row.get("indexationlivre")));
                    leLivre.setDescriptionLivre((String)(row.get("descriptionlivre")));
                    lesLivres.add(leLivre);
                }
                //
                return lesLivres;
            }
        });
        //
        return livreOutput;
    }

    @Override
    public ArrayList<Livre> rechercheDomaine(String valueDomaine,int nbchrdepart, int valueNbChr2) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        ArrayList<Livre> livreOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Livre>>() {
            @Override
            public ArrayList<Livre> doInTransaction(TransactionStatus transactionStatus) {
                final String INDEXATION = "SELECT * FROM livre WHERE SUBSTR(indexationlivre,?,?)=?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(INDEXATION,new Object[] {nbchrdepart,valueNbChr2,valueDomaine});
                ArrayList<Livre> lesLivres = new ArrayList<Livre>();
                for (Map row : rows) {
                    Livre leLivre=new Livre();
                    leLivre.setIdLivre((int)(row.get("idlivre")));
                    leLivre.setTitreLivre((String) (row.get("titrelivre")));
                    leLivre.setAuteurLivre((String)(row.get("auteurlivre")));
                    leLivre.setEditeurLivre((String)(row.get("editeurlivre")));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre((String)(row.get("indexationlivre")));
                    leLivre.setDescriptionLivre((String)(row.get("descriptionlivre")));
                    lesLivres.add(leLivre);
                }
                //
                return lesLivres;
            }
        });
        //
        return livreOutput;
    }

    @Override
    public ArrayList<Livre> rechercheTheme(String valueTheme, int nbchrdepart, int valueNbChr3) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        //
        ArrayList<Livre> livreOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Livre>>() {
            @Override
            public ArrayList<Livre> doInTransaction(TransactionStatus transactionStatus) {
                final String INDEXATION = "SELECT * FROM livre WHERE SUBSTR(indexationlivre,?,?)=?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(INDEXATION,new Object[] {nbchrdepart,valueNbChr3,valueTheme});
                ArrayList<Livre> lesLivres = new ArrayList<Livre>();
                for (Map row : rows) {
                    Livre leLivre=new Livre();
                    leLivre.setIdLivre((int)(row.get("idlivre")));
                    leLivre.setTitreLivre((String) (row.get("titrelivre")));
                    leLivre.setAuteurLivre((String)(row.get("auteurlivre")));
                    leLivre.setEditeurLivre((String)(row.get("editeurlivre")));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre((String)(row.get("indexationlivre")));
                    leLivre.setDescriptionLivre((String)(row.get("descriptionlivre")));
                    lesLivres.add(leLivre);
                }
                //
                return lesLivres;
            }
        });
        //
        return livreOutput;
    }
}
