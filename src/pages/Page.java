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

    /**
     *
     * @param availablePages to set
     */
    public void setAvailablePages(final ArrayList<String> availablePages) {
        this.availablePages = availablePages;
    }

    /**
     *
     * @param availableEvents to set
     */
    public void setAvailableEvents(final ArrayList<String> availableEvents) {
        this.availableEvents = availableEvents;
    }

    /**
     *
     * @return name of page
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name to set for page
     */
    public void setName(final String name) {
        this.name = name;
    }
}
