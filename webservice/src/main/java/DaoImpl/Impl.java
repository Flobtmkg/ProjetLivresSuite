package DaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public class Impl {
    //
    protected JdbcTemplate jdbcTemplate;
    @Autowired
    protected PlatformTransactionManager ptm;
    @Autowired
    protected void affectationJdbcTemplate(DataSource dataSource){
        this.jdbcTemplate= new JdbcTemplate(dataSource);
    }
    //
}
