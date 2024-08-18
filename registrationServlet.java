package Controller;

import Bean.Subscriber;
import Data.MovieDAO;
import Data.UserDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "registrationServlet", value = "/registrationServlet")
public class registrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    UserDao userDao;
    MovieDAO movieDAO;

    public registrationServlet() {
        super();
        movieDAO = new MovieDAO();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


       // String message = null;
        HttpSession httpSession = request.getSession();



       /* if (firstName.isEmpty() || lastName.isEmpty() ||email.isEmpty() || password.isEmpty() ||
                type.isEmpty()) {
            message = "Please fill out all the boxes";
            String url = "/registration.jsp";
        }else if (UserDao.addUser(firstName,lastName,email,password,type)){

        }*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        int subscriberId = request.getIntHeader("subid");
        String preferedGenre = request.getParameter("Prefered_Genre");


        Subscriber subscriber =new Subscriber(subscriberId,firstName,lastName,preferedGenre,email,password);
        try {
            movieDAO.addSubscriber(subscriber);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
        dispatcher.forward(request, response);

    }
}
