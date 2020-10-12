package dao;

import entities.Watch;

import java.sql.SQLException;
import java.util.List;

public interface WatchDAO {
    public List<Watch> getWatches() throws SQLException;

    public List<Watch> getWatchesByCountryId(long countryId) throws SQLException;


}
