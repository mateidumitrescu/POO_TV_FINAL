package pages;

public class LogoutPage extends Page {
    public LogoutPage() {
        super.getAvailablePages().add("homepage unauthenticated");
        super.setName("logout");
    }
}
