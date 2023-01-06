package errors;

import application.Application;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import movies.Movie;
import users.User;

import java.util.ArrayList;

public class OutputHandler {

    /**
     *
     * @return output for standard error
     */
    public ObjectNode standardError() {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("error", "Error");
        objectNode.putArray("currentMoviesList");
        objectNode.put("currentUser", (JsonNode) null);
        return objectNode;
    }

    /**
     *
     * @param user current
     * @return current user output
     */
    public ObjectNode getCurrentUser(final User user) {
        ObjectNode currentUser = new ObjectMapper().createObjectNode();
        ObjectNode credentials = new ObjectMapper().createObjectNode();
        credentials.put("name", user.getCredentials().getName());
        credentials.put("password", user.getCredentials().getPassword());
        credentials.put("accountType", user.getCredentials().getAccountType());
        credentials.put("country", user.getCredentials().getCountry());
        credentials.put("balance", user.getCredentials().getBalance());
        currentUser.put("credentials", credentials);

        currentUser.put("tokensCount", user.getTokensCount());
        currentUser.put("numFreePremiumMovies", user.getNumFreePremiumMovies());
        createMovieNodes(currentUser, "purchasedMovies", user.getPurchasedMovies());
        createMovieNodes(currentUser, "watchedMovies", user.getWatchedMovies());
        createMovieNodes(currentUser, "likedMovies", user.getLikedMovies());
        createMovieNodes(currentUser, "ratedMovies", user.getRatedMovies());



        return currentUser;
    }

    /**
     *
     * @param objectNode output
     * @param message for output
     * @param movies array to print
     */
    public void createMovieNodes(final ObjectNode objectNode,
                                 final String message,
                                 final ArrayList<Movie> movies) {

        ArrayNode movieNodes = objectNode.putArray(message);
        if (movies != null && movies.size() > 0) {
            for (Movie movie : movies) {
                ObjectNode node = new ObjectMapper().createObjectNode();
                node.put("name", movie.getName());
                node.put("year", movie.getYear());
                node.put("duration", movie.getDuration());
                ArrayNode nodeGenres = node.putArray("genres");
                for (String genre : movie.getGenres()) {
                    nodeGenres.add(genre);
                }
                ArrayNode nodeActors = node.putArray("actors");
                for (String actor : movie.getActors()) {
                    nodeActors.add(actor);
                }
                ArrayNode nodeCountriesBanned = node.putArray("countriesBanned");
                for (String country : movie.getCountriesBanned()) {
                    nodeCountriesBanned.add(country);
                }
                node.put("numLikes", movie.getNumLikes());
                node.put("rating", movie.getRating());
                node.put("numRatings", movie.getNumRatings());
                movieNodes.add(node);
            }
        }
    }


    /**
     *
     * @param page current page
     * @param user current
     * @return output
     */
    public ObjectNode userOutput(final String page, final User user) {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("error", (JsonNode) null);
        if (page.equals("movies")) {
            createMovieNodes(objectNode, "currentMoviesList", user.getAvailableMovies());
        } else if (page.equals("see details")) {
            createMovieNodes(objectNode, "currentMoviesList", Application.getSeeDetailsPage().getFilteredListMovies());
        } else {
            ArrayList<Movie> nullMovies = new ArrayList<>();
            createMovieNodes(objectNode, "currentMoviesList", nullMovies);
        }

        objectNode.put("currentUser", getCurrentUser(user));

        return objectNode;
    }

    /**
     *
     * @param user current
     * @param prefix "starts with" movie string
     * @return output
     */
    public ObjectNode searchMovies(final User user, final String prefix) {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("error", (JsonNode) null);

        ArrayList<Movie> foundMovies = new ArrayList<>();
        for (Movie movie : user.getAvailableMovies()) {
            if (movie.getName().startsWith(prefix)) {
                foundMovies.add(movie);
            }
        }
        createMovieNodes(objectNode, "currentMoviesList", foundMovies);

        objectNode.put("currentUser", getCurrentUser(user));

        return objectNode;
    }

    /**
     *
     * @param user current
     * @return output
     */
    public ObjectNode filteredMovies(final User user) {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("error", (JsonNode) null);

        createMovieNodes(objectNode, "currentMoviesList",
                Application.getSeeDetailsPage().getFilteredListMovies());

        objectNode.put("currentUser", getCurrentUser(user));

        return objectNode;
    }

    /**
     *
     * @param filteredMovies array
     * @param movieToAdd actual see details movie to print
     * @return output
     */
    public ObjectNode oneMovie(final ArrayList<Movie> filteredMovies,
                               final Movie movieToAdd) {
        ArrayList<Movie> movieList = new ArrayList<>();
        for (Movie movie : filteredMovies) {
            if (movie.getName().equals(movieToAdd.getName())) {
                movieList.add(movie);
                break;
            }
        }
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("error", (JsonNode) null);

        createMovieNodes(objectNode,"currentMoviesList", movieList);
        objectNode.put("currentUser", getCurrentUser(
                Application.getInstance().getCurrentUser()));

        return objectNode;
    }
}
