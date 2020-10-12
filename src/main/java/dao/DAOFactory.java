package dao;

public abstract class DAOFactory {
    public abstract CountryDAO getCountryDAO();

    public abstract WatchDAO getWatchDAO();

    public static DAOFactory getDAOFactory () throws Exception {
        return new DAOFactoryImpl();
    }
}
