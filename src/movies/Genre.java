package movies;

public class Genre {
    private String type;

    public Genre(String genreType) {
        type = genreType;
    }

    private int numberOfLikes;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public void increaseLikes() {
        this.numberOfLikes += 1;
    }
}
