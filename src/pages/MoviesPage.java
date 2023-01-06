package pages;

public class MoviesPage extends Page {
    public MoviesPage() {
        super.getAvailablePages().add("homepage authenticated");
        super.getAvailablePages().add("see details");
        super.getAvailablePages().add("logout");
        super.getAvailablePages().add("movies");
        super.getAvailableEvents().add("search");
        super.getAvailableEvents().add("filter");
        super.setName("movies");
    }
}
