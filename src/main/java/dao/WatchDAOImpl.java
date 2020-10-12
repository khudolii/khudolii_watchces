package dao;

import entities.Watch;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WatchDAOImpl implements WatchDAO {
    private Connection connection;

    public WatchDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Watch> getWatches() throws SQLException {
        return null;
    }

    @Override
    public List<Watch> getWatchesByCountryId(long countryId) throws SQLException {
        return null;
    }
}
