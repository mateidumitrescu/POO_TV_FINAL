package pages;

public class HomePageAuth extends Page {
    public HomePageAuth() {
        super.getAvailablePages().add("logout");
        super.getAvailablePages().add("upgrades");
        super.getAvailablePages().add("movies");
        super.setName("homepage authenticated");
    }
}
