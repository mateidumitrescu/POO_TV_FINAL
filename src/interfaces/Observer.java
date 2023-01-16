package interfaces;

import movies.Movie;

public interface Observer {

    void update(Movie movie, String type);
}
