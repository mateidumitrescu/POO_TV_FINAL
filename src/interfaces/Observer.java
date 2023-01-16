package interfaces;

import movies.Movie;

public interface Observer {

    /**
     *
     * @param movie added/deleted/recommended for notification
     * @param type of notification
     */
    void update(Movie movie, String type);
}
