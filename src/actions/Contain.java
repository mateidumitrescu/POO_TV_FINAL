package actions;

import java.util.ArrayList;

public class Contain {
    private ArrayList<String> actors;

    private ArrayList<String> genre;

    /**
     *
     * @return list of genres
     */
    public ArrayList<String> getGenre() {
        return genre;
    }

    /**
     *
     * @return list of actors
     */
    public ArrayList<String> getActors() {
        return actors;
    }

    /**
     *
     * @param actors list to set
     */
    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }
}
