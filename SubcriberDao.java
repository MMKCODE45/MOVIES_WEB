package Data;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class SubcriberDao {
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public static void movieRating(double rating, int movieID,int subscriberID) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/redfox"
                ,"root", "Whales123");
        PreparedStatement getRatingInfo = connection.prepareStatement("select * from Rating where MovieID = ? and SubscriberID = ?");
        getRatingInfo.setInt(1,movieID);
        getRatingInfo.setInt(2,subscriberID);
        ResultSet ratingInfo = getRatingInfo.executeQuery();

        //Update rating
        if (ratingInfo.next() && ratingInfo.getString(2).equals(movieID) && ratingInfo.getString(3).equals(subscriberID)){
            PreparedStatement updateMovieRating = connection.prepareStatement("update Rating set Rating = ? where MovieID = ? and  SubscriberID= ?");
            updateMovieRating.setDouble(1,rating);
            updateMovieRating.setInt(2,movieID);
            updateMovieRating.setInt(3,subscriberID);
            updateMovieRating.executeUpdate();
            updateMovieRating.close();

            PreparedStatement selectedMovieRatings = connection.prepareStatement("select * from Rating where MovieID = ?");
            selectedMovieRatings.setInt(1,movieID);
            ResultSet selectedMovieRatingsResults = selectedMovieRatings.executeQuery();
            ArrayList<Double> ratings = new ArrayList<>();
            while (selectedMovieRatingsResults.next()){
                //Gets all the subscribers ratings of a specific movies and adds them into the Arraylist
                ratings.add(selectedMovieRatingsResults.getDouble("Rating"));
            }
            //This iterator will be looping through our collection(arraylist)
            Iterator<Double> doubleIterator = ratings.iterator();
            double sumOfMovieRatings = 0;
            while (doubleIterator.hasNext()){
                //This array will be used to calculate the sum of all the rating from subscribers of a specific movie
                // that are in the iterator
                double additionArray[] = new double[ratings.size()];
                for (int i = 0;i < ratings.size();i++){
                    additionArray[i] = doubleIterator.next();
                    sumOfMovieRatings = sumOfMovieRatings + additionArray[i];
                }

            }
            double averageMovieRate = Double.parseDouble(decimalFormat.format(sumOfMovieRatings/ratings.size()));
            PreparedStatement insertNewRating = connection.prepareStatement("update Movie set Rating = ? where MovieID = ?");
            insertNewRating.setDouble(1,averageMovieRate);
            insertNewRating.setInt(2,movieID);
            insertNewRating.executeUpdate();
            insertNewRating.close();
            System.out.println("Rating Updated");

            //Insert rating
        } else {
            PreparedStatement insertMovieRating = connection.prepareStatement("insert into Rating values(?,?,?)");
            insertMovieRating.setDouble(1,rating);
            insertMovieRating.setInt(2,movieID);
            insertMovieRating.setInt(3,subscriberID);
            insertMovieRating.executeUpdate();
            insertMovieRating.close();

            PreparedStatement selectedMovieRatings = connection.prepareStatement("select * from Rating where MovieID = ?");
            selectedMovieRatings.setInt(1,movieID);
            ResultSet selectedMovieRatingsResults = selectedMovieRatings.executeQuery();
            ArrayList<Double> ratings = new ArrayList<>();
            while (selectedMovieRatingsResults.next()){
                //Gets all the subscribers ratings of a specific movies and adds them into the Arraylist
                ratings.add(selectedMovieRatingsResults.getDouble("Rating"));
            }
            //This iterator will be looping through our collection(arraylist)
            Iterator<Double> doubleIterator = ratings.iterator();
            double sumOfMovieRatings = 0;
            while (doubleIterator.hasNext()){
                //This array will be used to calculate the sum of all the rating from subscribers of a specific movie
                // that are in the iterator
                double additionArray[] = new double[ratings.size()];
                for (int i = 0;i < ratings.size();i++){
                    additionArray[i] = doubleIterator.next();
                    sumOfMovieRatings = sumOfMovieRatings + additionArray[i];
                }

            }
            double averageMovieRate = Double.parseDouble(decimalFormat.format(sumOfMovieRatings/ratings.size()));
            PreparedStatement insertNewRating = connection.prepareStatement("update Movie set Rating = ? where MovieID = ?");
            insertNewRating.setDouble(1,averageMovieRate);
            insertNewRating.setInt(2,movieID);
            insertNewRating.executeUpdate();
            insertNewRating.close();
            System.out.println("Rating Inserted");

        }

    }
}
