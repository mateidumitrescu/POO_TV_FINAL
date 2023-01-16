package movies;

public class Genre {
    private String type;

    public Genre(final String genreType) {
        type = genreType;
    }

    private int numberOfLikes;

    /**
     *
     * @return type of genre (ex: "action")
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type to set for genre(ex: "action")
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     *
     * @return number of likes of genre
     */
    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    /**
     *
     * @param numberOfLikes to set for genre
     */
    public void setNumberOfLikes(final int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    /**
     * increase number of likes for genre
     */
    public void increaseLikes() {
        this.numberOfLikes += 1;
    }
}
