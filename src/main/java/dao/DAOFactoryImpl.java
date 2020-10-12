package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactoryImpl extends DAOFactory {
    private Connection connection = null;

    public DAOFactoryImpl() throws Exception {
        this.connection = getConnectionFromPool();
    }
    private Connection getConnectionFromPool() throws Exception {
        InitialContext ctx = new InitialContext();
        String DATA_SOURCE = "java:comp/env/jdbc/example";
        DataSource ds = (DataSource) ctx.lookup(DATA_SOURCE);
        return ds.getConnection();
    }
    @Override
    public CountryDAO getCountryDAO() {
        return new CountryDAOImpl(connection);
    }

    @Override
    public WatchDAO getWatchDAO() {
        return new WatchDAOImpl(connection);
    }
}
