package users;

import movies.Movie;

import java.util.ArrayList;

public class User {

    private Credentials credentials;

    /**
     *
     * @return credentials of user
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     *
     * @param credentials to set for user
     */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }

    private int numFreePremiumMovies = 15;

    private ArrayList<Movie> purchasedMovies;

    private ArrayList<Movie> watchedMovies;

    private ArrayList<Movie> likedMovies;

    private ArrayList<Movie> ratedMovies;

    private ArrayList<Movie> availableMovies;

    /**
     *
     * @return available movies of user
     */
    public ArrayList<Movie> getAvailableMovies() {
        return availableMovies;
    }

    /**
     *
     * @param allMovies to transfer for user if he s not banned
     */
    public void setAvailableMovies(final ArrayList<Movie> allMovies) {
        availableMovies = new ArrayList<>();
        for (Movie movie : allMovies) {
            if (!movie.getCountriesBanned().contains(this.getCredentials().getCountry())) {
                availableMovies.add(movie);
            }
        }
    }

    /**
     *
     * @param cost to decrease all balance
     */
    public void decreaseBalance(final int cost) {
        int balance = Integer.parseInt(this.getCredentials().getBalance());
        balance -= cost;
        String balanceString = Integer.toString(balance);
        getCredentials().setBalance(balanceString);
    }

    /**
     *
     * @param tokens amount to decrease
     */
    public void decreaseTokens(final int tokens) {
        tokensCount -= tokens;
    }

    /**
     *
     * @param tokens amount to increase
     */
    public void increaseTokens(final int tokens) {
        tokensCount += tokens;
    }
    private int tokensCount = 0;

    /**
     *
     * @return tokens amount of user
     */
    public int getTokensCount() {
        return tokensCount;
    }

    /**
     *
     * @param tokensCount to set for user
     */
    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    /**
     *
     * @return number of free movies for user
     */
    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    /**
     *
     * @param numFreePremiumMovies to set for user
     */
    public void setNumFreePremiumMovies(final int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    /**
     *
     * @return list of purchased movies of user
     */
    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    /**
     *
     * @param purchasedMovies to set for user
     */
    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    /**
     *
     * @return list of watched movies of user
     */
    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    /**
     *
     * @param watchedMovies list to set for user
     */
    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    /**
     *
     * @return list of liked movies of user
     */
    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    /**
     *
     * @param likedMovies list to set for user
     */
    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    /**
     *
     * @return list of rated movies of user
     */
    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    /**
     *
     * @param ratedMovies list to set for user
     */
    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public User() {
        watchedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
        purchasedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        tokensCount = 0;
    }
}
