package Bean;

import java.io.Serializable;

public class movie implements Serializable {
    private static final long serialVersionUID = 1L;


    private int movieID;
    private String title;
    private String director;
    private String firstActor;
    private String secondActor;
    private String thirdActor;
    private String genre;
    private int yearOfProduction;
    private String imagePath;
    private double rating;
    private String description;
    private String status;

    public movie() {
    }

    public movie(int movieID, String title, String director, String firstActor, String secondActor, String thirdActor,
                 String genre, int yearOfProduction, String imagePath,String Status, String description, double rating ) {
        this.movieID = movieID;
        this.title = title;
        this.director = director;
        this.firstActor = firstActor;
        this.secondActor = secondActor;
        this.thirdActor = thirdActor;
        this.genre = genre;
        this.yearOfProduction = yearOfProduction;
        this.imagePath = imagePath;
        this.status=status;
        this.description= description;
        this.rating=rating;


    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getFirstActor() {
        return firstActor;
    }

    public void setFirstActor(String firstActor) {
        this.firstActor = firstActor;
    }

    public String getSecondActor() {
        return secondActor;
    }

    public void setSecondActor(String secondActor) {
        this.secondActor = secondActor;
    }

    public String getThirdActor() {
        return thirdActor;
    }

    public void setThirdActor(String thirdActor) {
        this.thirdActor = thirdActor;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }
}













