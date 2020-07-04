package dev.jonnyt.MunroBot;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Objects;

public class TweetBuilder {
    OkHttpClient client;
    String clientId = System.getenv("client-id");
    String clientSecret = System.getenv("client-secret");

    public TweetBuilder() throws Exception {
        client = new OkHttpClient();
        // TODO: pick walk via web scraping
        String url = "from above";
        MunroInfo munroInfo = new MunroInfo(url);

        JSONArray filteredTimeSeries = filterWeather(getTimeSeries(munroInfo.getMunroLat(), munroInfo.getMunroLong()));
        WeatherInfo weatherAt9 = new WeatherInfo(filteredTimeSeries.getJSONObject(0));
        WeatherInfo weatherAt12 = new WeatherInfo(filteredTimeSeries.getJSONObject(1));
        WeatherInfo weatherAt3 = new WeatherInfo(filteredTimeSeries.getJSONObject(2));

        // create tweet from MunroInfo and 3 Weather Infos

    }

    private double[] getMunroLatAndLong (String munroName) throws Exception {
        Request request = new Request.Builder()
                .url(String.format("https://munroapi.herokuapp.com/munros/name/%s", munroName))
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = Objects.requireNonNull(response.body()).string();
            JSONArray munroResponse = new JSONArray(responseBody);
            return new double[] {
                    Double.parseDouble(munroResponse.getJSONObject(0).getString("latlng_lat")),
                    Double.parseDouble(munroResponse.getJSONObject(0).getString("latlng_lng"))
            };
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Could not retrieve munro latitude and longitude");
        }
    }

    private JSONArray getTimeSeries (long latitude, long longitude) throws Exception {
        // TODO remove below when uploading to github AND hosting
        clientId = "5a810218-1f1c-4d2b-8acb-8d4ad760aa51";
        clientSecret = "P7xU7vG2wP0nT4tP0wF5wO7wA4fR5qO8pC1nM5uW0gG4uS6oR7";

        Request request = new Request.Builder()
                .url(String.format(
                        "https://api-metoffice.apiconnect.ibmcloud.com/metoffice/production/v0/forecasts/point/three-hourly?includeLocationName=true&latitude=%s&longitude=%s",
                        latitude,
                        longitude))
                .get()
                .addHeader("x-ibm-client-id", clientId)
                .addHeader("x-ibm-client-secret", clientSecret)
                .addHeader("accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            org.json.JSONObject munroWeatherResponse = new org.json.JSONObject(Objects.requireNonNull(response.body()).string());
            return munroWeatherResponse.getJSONArray("features").getJSONObject(0).getJSONObject("properties").getJSONArray("timeSeries");
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Error: Could not retrieve weather time series.");
        }
    }

    private JSONArray filterWeather (JSONArray timeSeries) {
        // TODO: filter timeSeries to only keep 9am 12am and 3pm for next day.
        return new JSONArray();
    }
}

