package Controller;
import Bean.Admin;
import Bean.Subscriber;
import Bean.user;
import Data.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginControllerServlet", value = "/LoginControllerServlet")
public class LoginControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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


        Subscriber subscriber= new Subscriber();



        UserDao userDao = new UserDao();

        try {
            subscriber = userDao.login(email, password);
            if (subscriber==null) {
                message = "Login Unsuccessful";
                request.setAttribute("error",message);
                getServletContext().getRequestDispatcher("/SubscriberLogin.jsp").forward(request,response);

            } else {

                HttpSession session = request.getSession();
                session.setAttribute("auth", subscriber);
                getServletContext().getRequestDispatcher("/cat.jsp").forward(request,response);

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }




    }











