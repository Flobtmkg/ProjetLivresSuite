package DaoImpl;

import DaoInterfaces.DaoLivre;
import ServicesBeans.IndexConstitution;
import ServicesBeans.Livre;
import classesTravail.CodageGuillemets;
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
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
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
                    leLivre.setTitreLivre(CodageGuillemets.getTexteDecode((String) (row.get("titrelivre"))));
                    leLivre.setAuteurLivre(CodageGuillemets.getTexteDecode((String)(row.get("auteurlivre"))));
                    leLivre.setEditeurLivre(CodageGuillemets.getTexteDecode((String)(row.get("editeurlivre"))));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre(CodageGuillemets.getTexteDecode((String)(row.get("indexationlivre"))));
                    leLivre.setDescriptionLivre(CodageGuillemets.getTexteDecode((String)(row.get("descriptionlivre"))));
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
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                final String AJOUTLIVRE = "INSERT INTO livre(idlivre,titrelivre,auteurlivre,editeurlivre,datepublicationlivre,indexationlivre) VALUES(?,?,?,?,?,?)";
                jdbcTemplate.update(AJOUTLIVRE,new Object[]{newLivre.getIdLivre(), CodageGuillemets.getTexteEncode(newLivre.getTitreLivre()),CodageGuillemets.getTexteEncode(newLivre.getAuteurLivre()),CodageGuillemets.getTexteEncode(newLivre.getEditeurLivre()),sqlDate1,CodageGuillemets.getTexteEncode(newLivre.getIndexationLivre())});
            }
        });


        //
    }

    @Override
    public ArrayList<Livre> rechercheTitre(String motcle) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //pré-traitement mot clé
        final String motcle2="%" + motcle.toUpperCase() + "%";
        //
        ArrayList<Livre> livreOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Livre>>() {
            @Override
            public ArrayList<Livre> doInTransaction(TransactionStatus transactionStatus) {
                final String LIVRES = "SELECT * FROM livre WHERE UPPER(titrelivre) LIKE ? OR UPPER(descriptionLivre) LIKE ?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(LIVRES,new Object[] {(motcle2),(motcle2)});
                ArrayList<Livre> lesLivres = new ArrayList<Livre>();
                for (Map row : rows) {
                    Livre leLivre=new Livre();
                    leLivre.setIdLivre((int)(row.get("idlivre")));
                    leLivre.setTitreLivre(CodageGuillemets.getTexteDecode((String) (row.get("titrelivre"))));
                    leLivre.setAuteurLivre(CodageGuillemets.getTexteDecode((String)(row.get("auteurlivre"))));
                    leLivre.setEditeurLivre(CodageGuillemets.getTexteDecode((String)(row.get("editeurlivre"))));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre(CodageGuillemets.getTexteDecode((String)(row.get("indexationlivre"))));
                    leLivre.setDescriptionLivre(CodageGuillemets.getTexteDecode((String)(row.get("descriptionlivre"))));
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
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
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
                    leLivre.setTitreLivre(CodageGuillemets.getTexteDecode((String) (row.get("titrelivre"))));
                    leLivre.setAuteurLivre(CodageGuillemets.getTexteDecode((String)(row.get("auteurlivre"))));
                    leLivre.setEditeurLivre(CodageGuillemets.getTexteDecode((String)(row.get("editeurlivre"))));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre(CodageGuillemets.getTexteDecode((String)(row.get("indexationlivre"))));
                    leLivre.setDescriptionLivre(CodageGuillemets.getTexteDecode((String)(row.get("descriptionlivre"))));
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
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
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
                    leLivre.setTitreLivre(CodageGuillemets.getTexteDecode((String) (row.get("titrelivre"))));
                    leLivre.setAuteurLivre(CodageGuillemets.getTexteDecode((String)(row.get("auteurlivre"))));
                    leLivre.setEditeurLivre(CodageGuillemets.getTexteDecode((String)(row.get("editeurlivre"))));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre(CodageGuillemets.getTexteDecode((String)(row.get("indexationlivre"))));
                    leLivre.setDescriptionLivre(CodageGuillemets.getTexteDecode((String)(row.get("descriptionlivre"))));
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
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
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
                    leLivre.setTitreLivre(CodageGuillemets.getTexteDecode((String) (row.get("titrelivre"))));
                    leLivre.setAuteurLivre(CodageGuillemets.getTexteDecode((String)(row.get("auteurlivre"))));
                    leLivre.setEditeurLivre(CodageGuillemets.getTexteDecode((String)(row.get("editeurlivre"))));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre(CodageGuillemets.getTexteDecode((String)(row.get("indexationlivre"))));
                    leLivre.setDescriptionLivre(CodageGuillemets.getTexteDecode((String)(row.get("descriptionlivre"))));
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
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
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
                    valueType=(CodageGuillemets.getTexteDecode((String)(row.get("valueindexation"))));
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
            vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
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
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //
        //pré-traitement mot clé
        final String motcle2="%" + valueType.toUpperCase() + "%";
        //
        ArrayList<Livre> livreOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Livre>>() {
            @Override
            public ArrayList<Livre> doInTransaction(TransactionStatus transactionStatus) {
                final String INDEXATION = "SELECT * FROM livre WHERE UPPER(SUBSTR(indexationlivre,((SELECT SUM(nbrchrconstitutionindex) FROM constitutionindex WHERE idelementindexation<=(SELECT idelementindexation FROM elementindexation WHERE valueindexation='type'))-(SELECT nbrchrconstitutionindex FROM constitutionindex WHERE idelementindexation=(SELECT idelementindexation FROM elementindexation WHERE valueindexation='type'))+1)::int,?)) LIKE ?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(INDEXATION,new Object[] {valueNbChr,motcle2});
                ArrayList<Livre> lesLivres = new ArrayList<Livre>();
                for (Map row : rows) {
                    Livre leLivre=new Livre();
                    leLivre.setIdLivre((int)(row.get("idlivre")));
                    leLivre.setTitreLivre(CodageGuillemets.getTexteDecode((String) (row.get("titrelivre"))));
                    leLivre.setAuteurLivre(CodageGuillemets.getTexteDecode((String)(row.get("auteurlivre"))));
                    leLivre.setEditeurLivre(CodageGuillemets.getTexteDecode((String)(row.get("editeurlivre"))));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre(CodageGuillemets.getTexteDecode((String)(row.get("indexationlivre"))));
                    leLivre.setDescriptionLivre(CodageGuillemets.getTexteDecode((String)(row.get("descriptionlivre"))));
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
    public ArrayList<Livre> rechercheDomaine(String valueDomaine, int valueNbChr2) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //
        //pré-traitement mot clé
        final String motcle2="%" + valueDomaine.toUpperCase() + "%";
        //
        ArrayList<Livre> livreOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Livre>>() {
            @Override
            public ArrayList<Livre> doInTransaction(TransactionStatus transactionStatus) {
                final String INDEXATION = "SELECT * FROM livre WHERE UPPER(SUBSTR(indexationlivre,((SELECT SUM(nbrchrconstitutionindex) FROM constitutionindex WHERE idelementindexation<=(SELECT idelementindexation FROM elementindexation WHERE valueindexation='domaine'))-(SELECT nbrchrconstitutionindex FROM constitutionindex WHERE idelementindexation=(SELECT idelementindexation FROM elementindexation WHERE valueindexation='domaine'))+1)::int,?)) LIKE ?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(INDEXATION,new Object[] {valueNbChr2,motcle2});
                ArrayList<Livre> lesLivres = new ArrayList<Livre>();
                for (Map row : rows) {
                    Livre leLivre=new Livre();
                    leLivre.setIdLivre((int)(row.get("idlivre")));
                    leLivre.setTitreLivre(CodageGuillemets.getTexteDecode((String) (row.get("titrelivre"))));
                    leLivre.setAuteurLivre(CodageGuillemets.getTexteDecode((String)(row.get("auteurlivre"))));
                    leLivre.setEditeurLivre(CodageGuillemets.getTexteDecode((String)(row.get("editeurlivre"))));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre(CodageGuillemets.getTexteDecode((String)(row.get("indexationlivre"))));
                    leLivre.setDescriptionLivre(CodageGuillemets.getTexteDecode((String)(row.get("descriptionlivre"))));
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
    public ArrayList<Livre> rechercheTheme(String valueTheme, int valueNbChr3) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //
        //pré-traitement mot clé
        final String motcle2="%" + valueTheme.toUpperCase() + "%";
        //
        ArrayList<Livre> livreOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<Livre>>() {
            @Override
            public ArrayList<Livre> doInTransaction(TransactionStatus transactionStatus) {
                final String INDEXATION = "SELECT * FROM livre WHERE UPPER(SUBSTR(indexationlivre,((SELECT SUM(nbrchrconstitutionindex) FROM constitutionindex WHERE idelementindexation<=(SELECT idelementindexation FROM elementindexation WHERE valueindexation='theme'))-(SELECT nbrchrconstitutionindex FROM constitutionindex WHERE idelementindexation=(SELECT idelementindexation FROM elementindexation WHERE valueindexation='theme'))+1)::int,?)) LIKE ?;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(INDEXATION,new Object[] {valueNbChr3,motcle2});
                ArrayList<Livre> lesLivres = new ArrayList<Livre>();
                for (Map row : rows) {
                    Livre leLivre=new Livre();
                    leLivre.setIdLivre((int)(row.get("idlivre")));
                    leLivre.setTitreLivre(CodageGuillemets.getTexteDecode((String) (row.get("titrelivre"))));
                    leLivre.setAuteurLivre(CodageGuillemets.getTexteDecode((String)(row.get("auteurlivre"))));
                    leLivre.setEditeurLivre(CodageGuillemets.getTexteDecode((String)(row.get("editeurlivre"))));
                    leLivre.setDatepublicationLivre((Date)(row.get("datepublicationlivre")));
                    leLivre.setIndexationLivre(CodageGuillemets.getTexteDecode((String)(row.get("indexationlivre"))));
                    leLivre.setDescriptionLivre(CodageGuillemets.getTexteDecode((String)(row.get("descriptionlivre"))));
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
    public ArrayList<IndexConstitution> rechercheIndexConstitution() {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //
        ArrayList<IndexConstitution> constitutionOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<IndexConstitution>>() {
            @Override
            public ArrayList<IndexConstitution> doInTransaction(TransactionStatus transactionStatus) {
                final String CONSTITUTION = "SELECT * FROM constitutionindex ORDER BY idconstitutionindex ASC;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(CONSTITUTION);
                ArrayList<IndexConstitution> lesindex = new ArrayList<IndexConstitution>();
                for (Map row : rows) {
                    IndexConstitution newIndexConstitution= new IndexConstitution();
                    newIndexConstitution.setIdIndexation((int)row.get("idelementindexation"));
                    newIndexConstitution.setNbrChr((int)row.get("nbrchrconstitutionindex"));
                    lesindex.add(newIndexConstitution);
                }
                //
                return lesindex;
            }
        });


        //
        return constitutionOutput;
    }

    @Override
    public String reference(String ref, String typeRef) {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //pré-traitement mot clé
        String motcle;
        if(typeRef.equals("theme")==false){
            motcle="%" + ref.toUpperCase() + "%";
        }else{
            motcle=ref.toUpperCase();
        }

        //
        String refOutput=vTransactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus transactionStatus) {
                final String REFERENCE;
                if(typeRef!="theme"){
                    REFERENCE= "SELECT nom"+typeRef+" FROM "+typeRef+" WHERE UPPER(nom"+typeRef+") LIKE ?;";
                }else{
                    REFERENCE= "SELECT nom"+typeRef+" FROM "+typeRef+" WHERE UPPER(nom"+typeRef+") = ?;";
                }
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(REFERENCE,new Object[] {motcle});
                String valeurChamp="";
                for (Map row : rows) {
                    valeurChamp=CodageGuillemets.getTexteDecode((String) (row.get("nom"+typeRef)));
                }
                //
                return valeurChamp;
            }
        });


        //
        return refOutput;
    }

    @Override
    public ArrayList<String> listerTypes() {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //
        ArrayList<String> typesOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<String>>() {
            @Override
            public ArrayList<String> doInTransaction(TransactionStatus transactionStatus) {
                final String TYPES = "SELECT nomtype FROM type;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(TYPES);
                ArrayList<String> lesTypes = new ArrayList<String>();
                for (Map row : rows) {
                    lesTypes.add(CodageGuillemets.getTexteDecode((String) (row.get("nomtype"))));
                }
                //
                return lesTypes;
            }
        });


        //
        return typesOutput;
    }

    public ArrayList<String> listerDomaines() {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //
        ArrayList<String> domaineOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<String>>() {
            @Override
            public ArrayList<String> doInTransaction(TransactionStatus transactionStatus) {
                final String DOMAINES = "SELECT nomdomaine FROM domaine;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(DOMAINES);
                ArrayList<String> lesDomaines = new ArrayList<String>();
                for (Map row : rows) {
                    lesDomaines.add(CodageGuillemets.getTexteDecode((String) (row.get("nomdomaine"))));
                }
                //
                return lesDomaines;
            }
        });


        //
        return domaineOutput;
    }

    @Override
    public ArrayList<String> listerThemes() {
        //
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(ptm);
        vTransactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        //
        ArrayList<String> themeOutput=vTransactionTemplate.execute(new TransactionCallback<ArrayList<String>>() {
            @Override
            public ArrayList<String> doInTransaction(TransactionStatus transactionStatus) {
                final String THEME = "SELECT nomtheme FROM theme;";
                //
                List<Map<String,Object>> rows = jdbcTemplate.queryForList(THEME);
                ArrayList<String> lesThemes = new ArrayList<String>();
                for (Map row : rows) {
                    lesThemes.add(CodageGuillemets.getTexteDecode((String) (row.get("nomtheme"))));
                }
                //
                return lesThemes;
            }
        });


        //
        return themeOutput;
    }
}
