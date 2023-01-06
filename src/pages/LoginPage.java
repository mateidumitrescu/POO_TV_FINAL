package pages;


public class LoginPage extends Page {
    public LoginPage() {
        super.getAvailableEvents().add("login");
        super.setName("login");
    }
}
