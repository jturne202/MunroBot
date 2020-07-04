package dev.jonnyt.MunroBot;

public class MunroInfo {
    private String title;
    private String description;
    private String distance;
    private String time;
    private String ascent;
    private String[] munros;
    private long munroLat;
    private long munroLong;
    private final String url;

    public MunroInfo(String url) {
        this.url = url;

        // web scraping
        // set remaining attributes from web page
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDistance() {
        return distance;
    }

    public String getTime() {
        return time;
    }

    public String getAscent() {
        return ascent;
    }

    public String getUrl() {
        return url;
    }

    public String[] getMunros() {
        return munros;
    }

    public long getMunroLat() {
        return munroLat;
    }

    public long getMunroLong() {
        return munroLong;
    }
}

