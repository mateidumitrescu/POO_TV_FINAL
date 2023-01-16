package application;

import actions.Action;
import com.fasterxml.jackson.databind.node.ArrayNode;
import errors.OutputHandler;
import input.Input;
import interfaces.Observer;
import movies.Movie;
import notification.Notification;
import pages.*;
import users.User;

import java.util.ArrayList;
import java.util.Stack;

public final class Application {

    private ArrayList<Action> actions;

    private Stack<Page> pagesStack;

    public Stack<Page> getPagesStack() {
        return pagesStack;
    }

    public void setPagesStack(final Stack<Page> pagesStack) {
        this.pagesStack = pagesStack;
    }

    private static Page currentPage;

    private ArrayList<User> users;

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(final ArrayList<Action> actions) {
        this.actions = actions;
    }

    public static Page getCurrentPage() {
        return currentPage;
    }

    public static void setCurrentPage(final Page currentPage) {
        Application.currentPage = currentPage;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    private ArrayList<Movie> movies;

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    private static Application instance = null;

    private static HomePageUnauth homePageUnauth;
    private static HomePageAuth homePageAuth;
    private static LoginPage loginPage;
    private static LogoutPage logoutPage;
    private static MoviesPage moviesPage;
    private static RegisterPage registerPage;
    private static SeeDetailsPage seeDetailsPage;
    private static UpgradesPage upgradesPage;

    /**
     *
     * @param user to add to list
     */
    public void addUser(final User user) {
        getUsers().add(user);
    }

    private boolean userLoggedIn;

    public void setUserLoggedIn(final boolean userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    public static HomePageUnauth getHomePageUnauth() {
        return homePageUnauth;
    }

    public static HomePageAuth getHomePageAuth() {
        return homePageAuth;
    }

    public static LoginPage getLoginPage() {
        return loginPage;
    }

    public static MoviesPage getMoviesPage() {
        return moviesPage;
    }

    public static RegisterPage getRegisterPage() {
        return registerPage;
    }

    public static SeeDetailsPage getSeeDetailsPage() {
        return seeDetailsPage;
    }

    public static UpgradesPage getUpgradesPage() {
        return upgradesPage;
    }

    /**
     *
     * @param inputData to transfer
     */
    private Application(final Input inputData) {
        setActions(inputData.getActions());
        setMovies(inputData.getMovies());
        setUsers(inputData.getUsers());
        homePageAuth = new HomePageAuth();
        homePageUnauth = new HomePageUnauth();
        loginPage = new LoginPage();
        logoutPage = new LogoutPage();
        moviesPage = new MoviesPage();
        registerPage = new RegisterPage();
        seeDetailsPage = new SeeDetailsPage();
        upgradesPage = new UpgradesPage();
        pagesStack = new Stack<>();
    }

    /**
     *
     * @param inputData -
     * @return instance of application
     *
     */
    public static Application getInstance(final Input inputData) {
        if (instance == null) {
            instance = new Application(inputData);
        }
        return instance;
    }

    /**
     *
     * @return instance of application
     */
    public static Application getInstance() {
        return instance;
    }

    /**
     *
     * @param instanceToSet for application
     */
    public static void setApplication(final Application instanceToSet) {
        Application.instance = instanceToSet;
    }

    /**
     *
     * @param movie which was added or deleted
     * @param type of notification to send to users
     */
    public void notifyUsers(final Movie movie,
                            final String type) {
        for (Observer user : users) {
            user.update(movie, type);
        }
    }

    /**
     *
     * @param movieName to delete from database
     * @return deleted movie
     */
    public Movie deleteMovie(final String movieName) {
        Movie deletedMovie;
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getName().equals(movieName)) {
                deletedMovie = movies.get(i);
                movies.remove(i);
                return deletedMovie;
            }
        }
        return null;
    }

    /**
     *
     * @param output -
     */
    public void startActions(final ArrayNode output) {
        setCurrentPage(homePageUnauth);
        pagesStack.push(homePageUnauth);
        this.setUserLoggedIn(false);

        seeDetailsPage.setFilteredListMovies(this.getMovies());

        // setting available movies for users if they live in certain countries
        for (User user : getUsers()) {
            user.setAvailableMovies(this.getMovies());
        }

        OutputHandler outputHandler = new OutputHandler();

        for (int i = 0; i < getActions().size(); i++) {
            switch (getActions().get(i).getType()) {
                case "change page" -> getActions().get(i).changePage(output, this);
                case "on page" -> getActions().get(i).onPage(output, this);
                case "back" -> {
                    if (currentPage == homePageUnauth
                            || currentPage == homePageAuth
                            || currentPage == logoutPage) {
                        output.add(outputHandler.standardError());
                    } else {
                        getActions().get(i).backPage(output);
                    }
                }
                case "database" -> getActions().get(i).databaseChange(output);
                default -> System.out.println("no command");
            }
            if ((i == getActions().size() - 1)
                    && currentUser != null
                    && currentUser.getCredentials().getAccountType().equals("premium")) {
                output.add(outputHandler.userOutput(currentPage.getName(),
                        currentUser, true));
            }
        }
    }
}
