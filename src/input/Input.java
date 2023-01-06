package input;

import actions.Action;
import movies.Movie;
import users.User;

import java.util.ArrayList;

public class Input {
    private ArrayList<User> users;

    /**
     *
     * @return users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     *
     * @param users to set
     */
    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    private ArrayList<Movie> movies;

    /**
     *
     * @return array of movies
     */
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    /**
     *
     * @param movies to add
     */
    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    private ArrayList<Action> actions;

    /**
     *
     * @return array of actions
     */
    public ArrayList<Action> getActions() {
        return actions;
    }

    /**
     *
     * @param actions that will be handled
     */
    public void setActions(final ArrayList<Action> actions) {
        this.actions = actions;
    }
}
