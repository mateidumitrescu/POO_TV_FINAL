package notification;

public class Notification {

    private String notificationType;

    private String movieName;

    /**
     *
     * @return notification type (ex: "action", "thriller")
     */
    public String getNotificationType() {
        return notificationType;
    }

    /**
     *
     * @param notificationType to set (ex: "action", "thriller")
     */
    public void setNotificationType(final String notificationType) {
        this.notificationType = notificationType;
    }

    /**
     *
     * @return movie name for notification
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     *
     * @param movieName to set for notification
     */
    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    public Notification(final String type, final String movie) {
        notificationType = type;
        movieName = movie;
    }
}
