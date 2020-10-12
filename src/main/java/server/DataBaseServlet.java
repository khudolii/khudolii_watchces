package server;


import beans.CountryBean;
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
    DAOFactory daoFactory;

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
        try {
            CountryBean countryBean = new CountryBean();

            daoFactory.getCountryDAO().getCountries();
            //countryBean.setCountries(countryDAOImpl.findAll());
            request.setAttribute("countryBean", countryBean);
            request.getRequestDispatcher("/showcountries.jsp").forward(request, response);
        } catch (CantFindCountryException | SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
