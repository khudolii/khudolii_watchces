package server;


import beans.CountryBean;
import beans.WatchBean;
import dao.CountryDAOImpl;
import dao.DAOFactory;
import exceptions.CantFindCountryException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "DataBaseServlet", urlPatterns = {"*.html"})
public class DataBaseServlet extends HttpServlet {
    private DAOFactory daoFactory;
    private CountryBean countryBean = null;
    private WatchBean watchBean = null;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            daoFactory = DAOFactory.getDAOFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter("actionName");
        if (actionName == null) {
            if (countryBean == null) {
                try {
                    countryBean = new CountryBean();
                    countryBean.setCountries(daoFactory.getCountryDAO().getCountries());
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            }
            request.setAttribute("countryBean", countryBean);
            request.getRequestDispatcher("/showcountries.jsp").forward(request, response);
        } else if (actionName.equals("getWatchesByCountryId")) {
            actionGetWatchesByCountryId(request, response);
        }

    }

    private void actionGetWatchesByCountryId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int countyId = Integer.parseInt(request.getParameter("countryId"));
        if (countyId != 0) {
            sendErrorMessage(request, response);
        }
        watchBean = new WatchBean();
        try {
            watchBean.setWatches(daoFactory.getWatchDAO().getWatchesByCountryId(countyId));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            sendErrorMessage(request, response);
        }
        request.setAttribute("watchBean", watchBean);
        request.getRequestDispatcher("/watches.jsp").forward(request, response);
    }

    private void sendErrorMessage(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        try {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
