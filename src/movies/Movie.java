package movies;

import java.util.ArrayList;

public class Movie {
    private String name;
    private int year;
    private int duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;

    private int numLikes = 0;

    private float rating = 0;

    private int numRatings = 0;

    /**
     *
     * @return list of ratings for movie
     */
    public ArrayList<Float> getRatingsList() {
        return ratingsList;
    }

    private ArrayList<Float> ratingsList;

    /**
     *
     * @return number of likes for movie
     */
    public int getNumLikes() {
        return numLikes;
    }

    /**
     * increase number of likes of movie
     */
    public void increaseNumLikes() {
        this.numLikes = this.numLikes + 1;
    }

    /**
     *
     * @param rate to add and increase number of ratings
     */
    public void increaseNumRatings(final float rateToAdd) {
        this.numRatings = this.numRatings + 1;
        if (getRatingsList() == null) {
            ratingsList = new ArrayList<>();
        }
        getRatingsList().add(rateToAdd);
    }

    /**
     *
     * @param rate to add and calculate again rating for movie
     */
    public void calculateRating(final float rateToAdd) {
        increaseNumRatings(rateToAdd);
        float sum = 0;
        for (float r : ratingsList) {
            sum += r;
        }
        rating = sum / ratingsList.size();
    }

    /**
     *
     * @param numLikes for movie
     */
    public void setNumLikes(final int numLikesToSet) {
        this.numLikes = numLikesToSet;
    }

    /**
     *
     * @return rating of movie
     */
    public float getRating() {
        return rating;
    }

    /**
     *
     * @param rating to set for movie
     */
    public void setRating(final float ratingToSet) {
        this.rating = ratingToSet;
    }

    /**
     *
     * @return number of ratings of movie
     */
    public int getNumRatings() {
        return numRatings;
    }

    /**
     *
     * @param numRatings to set for movie
     */
    public void setNumRatings(final int numRatingsToSet) {
        this.numRatings = numRatingsToSet;
    }

    /**
     *
     * @return name of movie
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name to set for movie
     */
    public void setName(final String nameToSet) {
        this.name = nameToSet;
    }

    /**
     *
     * @return year of movie
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @param year to set for movie
     */
    public void setYear(final int yearToSet) {
        this.year = yearToSet;
    }

    /**
     *
     * @return duration of movie
     */
    public int getDuration() {
        return duration;
    }

    /**
     *
     * @param duration to set for movie
     */
    public void setDuration(final int durationToSet) {
        this.duration = durationToSet;
    }

    /**
     *
     * @return list of genres fo movie
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     *
     * @param genres list to set for movie
     */
    public void setGenres(final ArrayList<String> genresToSet) {
        this.genres = genresToSet;
    }

    /**
     *
     * @return list of actors of movie
     */
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     *
     * @param actors list to set for movie
     */
    public void setActors(final ArrayList<String> actorsToSet) {
        this.actors = actorsToSet;
    }

    /**
     *
     * @return list of countries
     */
    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    /**
     *
     * @param countriesBanned list to set for movie
     */
    public void setCountriesBanned(final ArrayList<String> countriesBannedToSet) {
        this.countriesBanned = countriesBannedToSet;
    }

    private ArrayList<String> countriesBanned;
}
