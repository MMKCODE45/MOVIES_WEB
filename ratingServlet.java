package Controller;

import Bean.movie;
import Data.MovieDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ratingServlet", value = "/ratingServlet")
public class ratingServlet extends HttpServlet {
    MovieDAO movieDAO;

    public void ratingServlett(){
        //.super();
    }
    public void init(ServletConfig config) throws ServletException {


        movieDAO= new MovieDAO();
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.addMovie(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);


    }


    private void addMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            Exception
    {
        String url = "";
        String message = null;


        int movieID = Integer.parseInt(request.getParameter("MovieID"));
        String title =request.getParameter("title");
        String director=request.getParameter("directorName");
        String firstActor=request.getParameter("FirstActorName");
        String secondActor=request.getParameter("SecondActorName");
        String thirdActor =request.getParameter("ThirdActorName");
        String genre=request.getParameter("genre");
        int yearOfProduction = Integer.parseInt(request.getParameter("year"));
        String imagePath=request.getParameter("image_path");
        String status=request.getParameter("Status");
        String description=request.getParameter("description");
        double rating = Double.parseDouble(request.getParameter("Rating"));

        if(director.isEmpty() || title.isEmpty() ) {
            message = "Please fill out all the boxes";
            url = "/addMovie.jsp";
        }


        movie newMovie = new movie(movieID, title, director, firstActor, secondActor, thirdActor,
                genre, yearOfProduction, imagePath, status, description, rating);





        try {
            movieDAO.addMovie(newMovie);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addMovie.jsp");
        dispatcher.forward(request, response);





    }



}





