package users;

import application.Application;
import interfaces.Observer;
import movies.Genre;
import movies.Movie;
import notification.Notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class User implements Observer {

    private Credentials credentials;

    private ArrayList<String> subscribedGenres;

    public ArrayList<String> getSubscribedGenres() {
        return subscribedGenres;
    }

    public void setSubscribedGenres(ArrayList<String> subscribedGenres) {
        this.subscribedGenres = subscribedGenres;
    }

    public void addSubscribedGenre(String genre) {
        subscribedGenres.add(genre);
    }

    private ArrayList<Genre> likedGenres;

    public ArrayList<Genre> getLikedGenres() {
        return likedGenres;
    }

    public void addGenre(Genre genreToAdd) {
        likedGenres.add(genreToAdd);
    }

    public void sortGenres() {
        likedGenres.sort((o1, o2) -> {
            if (o1.getNumberOfLikes() != o2.getNumberOfLikes()) {
                return o2.getNumberOfLikes() - o1.getNumberOfLikes();
            }
            return o1.getType().compareTo(o2.getType());
        });
    }

    private ArrayList<Movie> sortedMovies;

    public ArrayList<Movie> getSortedMovies() {
        return sortedMovies;
    }

    public void setSortedMovies(ArrayList<Movie> sortedMovies) {
        this.sortedMovies = sortedMovies;
    }

    public void sortMovies() {
        if (sortedMovies == null) {
            sortedMovies = new ArrayList<>();
        }
        setSortedMovies(availableMovies);
        sortedMovies.sort((o1, o2) -> o2.getNumLikes() - o1.getNumLikes());
    }

    public void setLikedGenres(ArrayList<Genre> likedGenres) {
        this.likedGenres = likedGenres;
    }

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

    public void deleteMovie(Movie movieToDelete) {
        for (int i = 0; i < availableMovies.size(); i++) {
            if (availableMovies.get(i).getName().equals(movieToDelete.getName())) {
                availableMovies.remove(availableMovies.get(i));
                Notification notification = new Notification("DELETE", movieToDelete.getName());
                notifications.add(notification);
                break;
            }
        }
        for (int i = 0; i < purchasedMovies.size(); i++) {
            if (purchasedMovies.get(i).getName().equals(movieToDelete.getName())) {
                purchasedMovies.remove(i);
                if (credentials.getAccountType().equals("standard")) {
                    increaseTokens(2);
                } else if (credentials.getAccountType().equals("premium")) {
                    setNumFreePremiumMovies(getNumFreePremiumMovies() + 1);
                }
                break;
            }
        }
        for (int i = 0; i < watchedMovies.size(); i++) {
            if (watchedMovies.get(i).getName().equals(movieToDelete.getName())) {
                watchedMovies.remove(i);
                break;
            }
        }
        for (int i = 0; i < likedMovies.size(); i++) {
            if (likedMovies.get(i).getName().equals(movieToDelete.getName())) {
                likedMovies.remove(i);
                break;
            }
        }
        for (int i = 0; i < ratedMovies.size(); i++) {
            if (ratedMovies.get(i).getName().equals(movieToDelete.getName())) {
                ratedMovies.remove(i);
                break;
            }
        }
    }

    public void addMovie(Movie movieToAdd) {
        for (String country : movieToAdd.getCountriesBanned()) {
            if (country.equals(credentials.getCountry())) {
                return;
            }
        }
        for (String genre : subscribedGenres) {
            for (String movieGenre : movieToAdd.getGenres()) {
                if (genre.equals(movieGenre)) {
                    availableMovies.add(movieToAdd);
                    Notification notification = new Notification("ADD", movieToAdd.getName());
                    notifications.add(notification);
                    break;
                }
            }
        }

    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public User() {
        watchedMovies = new ArrayList<>();
        ratedMovies = new ArrayList<>();
        purchasedMovies = new ArrayList<>();
        likedMovies = new ArrayList<>();
        notifications = new ArrayList<>();
        likedGenres = new ArrayList<>();
        subscribedGenres = new ArrayList<>();
        tokensCount = 0;
    }

    private ArrayList<Notification> notifications;

    public void addRecommendation(Movie movie) {
        Notification notification;
        if (movie == null) {
            notification = new Notification("Recommendation", "No recommendation");
        } else {
            notification = new Notification("Recommendation", movie.getName());
        }
        notifications.add(notification);
    }
    @Override
    public void update(Movie movie, String type) {
        switch (type) {
            case "ADD" -> addMovie(movie);
            case "DELETE" -> deleteMovie(movie);
            case "RECOMMENDATION" -> addRecommendation(movie);
        }
    }
}
