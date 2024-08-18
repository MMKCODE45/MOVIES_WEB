package Controller;

import Bean.Admin;
import Data.UserDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminLoginServlet", value = "/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "";
        String message = null;
        HttpSession httpSession = request.getSession();

        String email =request.getParameter("email");
        String password=request.getParameter("password");

        Admin admin= new Admin();



        UserDao userDao = new UserDao();

        try {
            admin = userDao.authentication(email, password);
            if (admin==null) {
                message = "Login Unsuccessful";
                request.setAttribute("error",message);
                getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);

            } else {

                HttpSession session = request.getSession();
                session.setAttribute("auth", admin);
                getServletContext().getRequestDispatcher("/adminPanel.jsp").forward(request,response);

            }
        } catch (SQLException | NamingException ex) {
            throw new RuntimeException(ex);
        }


    }
}
