package pages;

public class UpgradesPage extends Page {
    public UpgradesPage() {
        super.getAvailablePages().add("homepage authenticated");
        super.getAvailablePages().add("movies");
        super.getAvailablePages().add("logout");
        super.getAvailableEvents().add("buy premium account");
        super.getAvailableEvents().add("buy tokens");
        super.setName("upgrades");
    }
}
