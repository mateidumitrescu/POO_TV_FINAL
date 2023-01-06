package actions;

public class Sort {
    private String rating;
    private String duration;

    /**
     *
     * @return duration of movie
     */
    public String getDuration() {
        return duration;
    }

    /**
     *
     * @param duration to set
     */
    public void setDuration(final String duration) {
        this.duration = duration;
    }

    /**
     *
     * @return rating
     */
    public String getRating() {
        return rating;
    }

    /**
     *
     * @param rating to set
     */
    public void setRating(final String rating) {
        this.rating = rating;
    }
}
