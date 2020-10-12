package dao;

import entities.Watch;

import java.sql.*;
import java.util.ArrayList;
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
    public List<Watch> getWatchesByCountryId(int countryId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Watch> resultList = new ArrayList<>();
        try {
            String sql = "SELECT w.id, w.mark, w.type FROM public.watch w " +
                    "JOIN vendor v on v.id = w.vendor_id " +
                    "JOIN county c on c.id = v.\"cuntryId\" " +
                    "WHERE c.id=?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, countryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String mark = rs.getString("mark");
                resultList.add(new Watch(id, mark, type));
            }
            return resultList;
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }
}
