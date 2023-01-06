package pages;

import java.util.ArrayList;

public abstract class Page {

    Page() {
        availablePages = new ArrayList<>();
        availableEvents = new ArrayList<>();
    }

    /**
     *
     * @return available pages for page
     */
    public ArrayList<String> getAvailablePages() {
        return availablePages;
    }

    private ArrayList<String> availablePages;

    private ArrayList<String> availableEvents;

    /**
     *
     * @return available events for page
     */
    public ArrayList<String> getAvailableEvents() {
        return availableEvents;
    }

    private String name;

    public void setAvailablePages(ArrayList<String> availablePages) {
        this.availablePages = availablePages;
    }

    public void setAvailableEvents(ArrayList<String> availableEvents) {
        this.availableEvents = availableEvents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
