package pages;

import movies.Movie;

import java.util.ArrayList;

public class SeeDetailsPage extends Page {
    private ArrayList<Movie> filteredListMovies;

    /**
     *
     * @return list of filtered movies
     */
    public ArrayList<Movie> getFilteredListMovies() {
        return filteredListMovies;
    }

    /**
     *
     * @param filteredListMovies list to set for page
     */
    public void setFilteredListMovies(final ArrayList<Movie> filteredListMovies) {
        this.filteredListMovies = filteredListMovies;
    }

    private Movie currentMovie;

    /**
     *
     * @return current movie on page
     */
    public Movie getCurrentMovie() {
        return currentMovie;
    }

    /**
     *
     * @param currentMovie to set for page
     */
    public void setCurrentMovie(final Movie currentMovie) {
        this.currentMovie = currentMovie;
    }

    public SeeDetailsPage() {
        super.getAvailablePages().add("homepage authenticated");
        super.getAvailablePages().add("movies");
        super.getAvailablePages().add("upgrades");
        super.getAvailablePages().add("logout");
        super.getAvailableEvents().add("purchase");
        super.getAvailableEvents().add("watch");
        super.getAvailableEvents().add("like");
        super.getAvailableEvents().add("rate");
        super.setName("see details");
    }

    public void deleteMovie(Movie movieToDelete) {
        for (int i = 0; i < filteredListMovies.size(); i++) {
            if (filteredListMovies.get(i).getName().equals(movieToDelete.getName())) {
                filteredListMovies.remove(i);
                break;
            }
        }
    }
}
