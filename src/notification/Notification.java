package notification;

public class Notification {

    private String notificationType;

    private String movieName;

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Notification(String type, String movie) {
        notificationType = type;
        movieName = movie;
    }
}
