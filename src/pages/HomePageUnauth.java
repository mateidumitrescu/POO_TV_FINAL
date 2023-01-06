package pages;

public class HomePageUnauth extends Page {
    public HomePageUnauth() {
        super.getAvailablePages().add("login");
        super.getAvailablePages().add("register");
        super.setName("homepage unauthenticated");
    }
}
