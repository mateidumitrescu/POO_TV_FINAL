package pages;

public class RegisterPage extends Page {
    public RegisterPage() {
        super.getAvailableEvents().add("register");
        super.setName("register");
    }
}
