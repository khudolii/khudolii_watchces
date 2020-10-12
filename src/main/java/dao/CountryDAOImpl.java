package dao;

import entities.Country;
import exceptions.CantFindCountryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {
    private Connection connection;

    public CountryDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Country> getCountries() throws SQLException {
        try {
            List<Country> result = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("select * from county");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String shortName = rs.getString("short_name");
                result.add(new Country(id, name, shortName));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}

   /* public List<DriverProfile> getDriverProfileListByTruckId(Long truckId) throws DAOAppException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DriverProfile> resultList = new ArrayList<>();
        try {
            Connection connection = pgDAOFactory.getConnection();
            String sql = "SELECT dp.driver_id, dp.first_name, dp.last_name, dp.org_id, dp.login_name FROM fleet.driver_profile dp"
                    + " JOIN public.truck_device_signal tds ON dp.driver_id = tds.driver_profile_1_id OR dp.driver_id = tds.driver_profile_2_id "
                    + " JOIN public.truck t ON tds.truck_id = t.truck_id" + " WHERE t.truck_id = ?";
            ps = connection.prepareStatement(sql);
            setSQLParameter(ps, 1, truckId, Types.BIGINT);
            rs = ps.executeQuery();
            while (rs.next()) {
                resultList.add((DriverProfile) populateValueObject(rs, DriverProfile.class));
            }
            return resultList;
        } catch (Exception e) {
            throw new DAOAppException(e.getMessage());
        } finally {
            finalize(rs, ps);
        }
    }*/