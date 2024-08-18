package Data;
import Bean.Employee;
import Bean.Subscriber;
import Bean.movie;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.*;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MovieDAO {
    private DataSource datasource;

    private void closeSQL(Connection connection, Statement statement, ResultSet resultset) {
        try {
            if (resultset != null) {
                resultset.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }


    public void addMovie(movie theMovie) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Context context = new InitialContext();
            DataSource datasource = (DataSource)context.lookup("java:comp/env/jdbc/redfox"
            );
            connection = datasource.getConnection();

            String sql = "Insert into movie_Table (movieID,title, DirectorName, firstActor,  secondActor,  thirdActor,\n" +
                    "                 Gerne, yearOfProduction,ImagePath,Status,Description,Rating)values (?, ?, ?, ?, ?, ?, ?,?,?,?,?, ?)";

            statement = connection.prepareStatement(sql);

            statement = connection.prepareStatement(sql);
            statement = connection.prepareStatement(sql);

            statement.setInt(1, theMovie.getMovieID());
            statement.setString(2, theMovie.getTitle());
            statement.setString(3, theMovie.getDirector());
            statement.setString(4, theMovie.getFirstActor());
            statement.setString(5, theMovie.getSecondActor());
            statement.setString(6, theMovie.getThirdActor());
            statement.setString(7, theMovie.getGenre());
            statement.setInt(8, theMovie.getYearOfProduction());
            statement.setString(9, theMovie.getImagePath());
            statement.setString(10, theMovie.getStatus());
            statement.setString(11,theMovie.getDescription());
            statement.setDouble(12,theMovie.getRating());



            PreparedStatement checkIfMovieExist= connection.prepareStatement("select * from movie_Table  where movieID  = ?");
            checkIfMovieExist.setInt(1,theMovie.getMovieID());
            ResultSet resultSet=checkIfMovieExist.executeQuery();
            if (resultSet.next()) {
                System.out.println("Movie Already Exist");
            } else {
                statement.executeUpdate();
                statement.close();
                System.out.println("Movie Created");
            }


        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            this.closeSQL(connection, statement, (ResultSet) null);

        }

    }

    public void addSubscriber(Subscriber newSubscriber ) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Context context = new InitialContext();
            DataSource datasource = (DataSource) context.lookup("java:comp/env/jdbc/redfox"
            );
            connection = datasource.getConnection();
            String sql ="insert into user_table values(?,?)";
            statement = connection.prepareStatement(sql);

            statement = connection.prepareStatement(sql);
            statement = connection.prepareStatement(sql);
            statement.setString(1,newSubscriber.getEmail());
            statement.setString(2,newSubscriber.getPassword());
            statement.executeUpdate();
            statement.close();
            System.out.println("User");
            PreparedStatement addDetailsSubscriber= connection.prepareStatement("insert into subscriber values(?,?,?,?,?)");
            addDetailsSubscriber.setInt(1,newSubscriber.getSubscriberID());
            addDetailsSubscriber.setString(2,newSubscriber.getFirstName());
            addDetailsSubscriber.setString(3,newSubscriber.getLastname());
            addDetailsSubscriber.setString(4,newSubscriber.getEmail());
            addDetailsSubscriber.setString(5,newSubscriber.getPreferedGenre());
            addDetailsSubscriber.executeUpdate();
            addDetailsSubscriber.close();
            System.out.println("User");


        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void addEmployee(Employee employee) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/redfox"
                ,"root", "Whales123");

        PreparedStatement addDetailsUser=connection.prepareStatement("insert into user_table values(?,?)");
        addDetailsUser.setString(1,employee.getEmail());
        addDetailsUser.setString(2,employee.getPassword());
        addDetailsUser.executeUpdate();
        addDetailsUser.close();

        PreparedStatement addDetailsEmployee= connection.prepareStatement("insert into employee values(?,?,?,?,?)");
        addDetailsEmployee.setString(1,employee.getEmployeeID());
        addDetailsEmployee.setString(2,employee.getFirstName());
        addDetailsEmployee.setString(3,employee.getLastName());
        addDetailsEmployee.setString(4,employee.getRole());
        addDetailsEmployee.setString(5,employee.getEmail());
        addDetailsEmployee.executeUpdate();
        addDetailsEmployee.close();



    }

    public static ArrayList<movie> selectMovieByGenre(String genre) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost/redfox"
                ,"root", "Whales123");

        String sql = " select * from movie_table where Gerne = ? and Status ='Available' ";
        PreparedStatement addDetailsEmployee= connection.prepareStatement(sql);
        statement = connection.prepareStatement(sql);

        statement.setString(1,genre);
        ResultSet selectedMovies = statement.executeQuery();


        ArrayList<movie> movieArrayList= new ArrayList<>();

        if(selectedMovies.next()){

            do {

                int movieID = selectedMovies.getInt(1);
                String title= selectedMovies.getString(2);
                String director= selectedMovies.getString(3);
                String firstActor= selectedMovies.getString(4);
                String secondActor= selectedMovies.getString(5);
                String thirdActor =selectedMovies.getString(6);
                genre = selectedMovies.getString(7);
                int yearOfProduction= selectedMovies.getInt(8);
                String imagePath= selectedMovies.getString(9);
                String status= selectedMovies.getString(10);
                String description =selectedMovies.getString(11);
                double rating =selectedMovies.getDouble(12);



                movie movies = new movie(movieID, title, director, firstActor, secondActor, thirdActor,
                        genre, yearOfProduction, imagePath, status, description, rating);
                movieArrayList.add(movies);


            }while (selectedMovies.next());

            return movieArrayList;



        }


        return null;
    }

    public static void main(String[]args) throws SQLException, NamingException {
        Employee employee= new Employee();
        MovieDAO movieDAO = new MovieDAO();
        employee.setEmployeeID("45678");
        employee.setFirstName("Kgosi");
        employee.setLastName("Ad++min");
        employee.setRole("Sales");
        employee.setEmail("cse@gmail.com");
        employee.setPassword("12345");
        movieDAO.addEmployee(employee);



    }




}
