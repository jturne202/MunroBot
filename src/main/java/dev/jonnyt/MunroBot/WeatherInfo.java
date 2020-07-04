package dev.jonnyt.MunroBot;

import org.json.JSONObject;

public class WeatherInfo {

    private final double feelsLike;
    private final int chanceOfRain;
    private final int chanceOfSnow;
    private final double gustSpeed;
    private final String gustDirection;
    // todo: private final Date time;

    public WeatherInfo(JSONObject timeSeriesInstance) {
        this.feelsLike = timeSeriesInstance.getDouble("feelsLikeTemp");
        this.chanceOfRain = timeSeriesInstance.getInt("probOfRain");
        this.chanceOfSnow = timeSeriesInstance.getInt("probOfSnow");
        this.gustSpeed = timeSeriesInstance.getDouble("windGustSpeed10m");
        this.gustDirection = convertDirectionToCardinal(timeSeriesInstance.getInt("windDirectionFrom10m"));
        // todo: this.time = timeSeriesInstance.getString("time");
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public int getChanceOfRain() {
        return chanceOfRain;
    }

    public int getChanceOfSnow() {
        return chanceOfSnow;
    }

    public double getGustSpeed() {
        return gustSpeed;
    }

    public String getGustDirection() {
        return gustDirection;
    }

    /*public Date getTime() { todo
        return time;
    }*/

    private String convertDirectionToCardinal(int degrees) {
        return "SW";
    }
}
