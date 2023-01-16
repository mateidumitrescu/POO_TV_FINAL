package errors;

import application.Application;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import movies.Genre;
import movies.Movie;
import notification.Notification;
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
        createNotificationNodes(
                Application.getInstance().getCurrentUser().getNotifications(),
                currentUser);

        return currentUser;
    }

    public void createNotificationNodes(final ArrayList<Notification> notifications,
                                        final ObjectNode objectNode) {
        ArrayNode notificationsNodes = objectNode.putArray("notifications");
        if (notifications != null) {
            for (Notification notification : notifications) {
                ObjectNode node = new ObjectMapper().createObjectNode();
                node.put("movieName", notification.getMovieName());
                node.put("message", notification.getNotificationType());
                notificationsNodes.add(node);
            }
        }
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
                node.put("year", String.valueOf(movie.getYear()));
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
    public ObjectNode userOutput(final String page, final User user, final boolean lastAction) {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("error", (JsonNode) null);
        if (lastAction) {
            objectNode.put("currentMoviesList", (JsonNode) null);
        } else {
            if (page.equals("movies")) {
                createMovieNodes(objectNode, "currentMoviesList", Application.getSeeDetailsPage().getFilteredListMovies());
            } else if (page.equals("see details")) {
                createMovieNodes(objectNode, "currentMoviesList", Application.getSeeDetailsPage().getFilteredListMovies());
            } else {
                ArrayList<Movie> nullMovies = new ArrayList<>();
                createMovieNodes(objectNode, "currentMoviesList", nullMovies);
            }
        }
        if (lastAction) {
            objectNode.put("currentUser", recommendationOutput(user));
        } else {
            objectNode.put("currentUser", getCurrentUser(user));
        }

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

    /**
     *
     * @param user current user logged in
     * @return node with final output with recommendation for premium user
     */
    public ObjectNode recommendationOutput(final User user) {
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
        boolean foundRec = false;
        for (Genre genre : user.getLikedGenres()) {
            for (Movie movie : user.getSortedMovies()) {
                if (movie.getGenres().contains(genre.getType())) {
                    user.update(movie, "RECOMMENDATION");
                    foundRec = true;
                    break;
                }
                if (foundRec) {
                    break;
                }
            }
        }
        if (!foundRec) {
            user.update(null, "RECOMMENDATION");
        }
        createNotificationNodes(
                Application.getInstance().getCurrentUser().getNotifications(),
                currentUser);

        return currentUser;
    }
}
