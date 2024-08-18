package Controller;

import Bean.Employee;
import Bean.user;
import Data.MovieDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddEmployeeServlet", value = "/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {

    MovieDAO movieDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "";
        String message = null;
        HttpSession httpSession = request.getSession();

        String employeeID= request.getParameter("EmployeeID");
        String firstName= request.getParameter("Firstname");
        String lastName= request.getParameter("Lastname");
        String role = request.getParameter("role");
        String email=request.getParameter("Email");
        String password= request.getParameter("password");

        if (employeeID.isEmpty()|| firstName.isEmpty()|| lastName.isEmpty()|| role.isEmpty()|| email.isEmpty()|| password.isEmpty()){
            message = "Please fill out all the boxes";
            url = "/addEmployee.jsp";
            getServletContext().getRequestDispatcher(url).forward(request,response);
        } else{
            Employee employee= new Employee ();
            employee.setEmployeeID(employeeID);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setRole(role);
            employee.setEmail(email);
            employee.setPassword(password);


            try {
                movieDAO.addEmployee(employee);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        url = "/addEmployee.jsp";
        request.setAttribute("message",message);
        getServletContext().getRequestDispatcher(url).forward(request, response);



        }






    }

