package dao;

import entities.Country;

import java.sql.SQLException;
import java.util.List;

public interface CountryDAO {
    public List<Country> getCountries() throws SQLException;
}
