package com.trading.data;

import com.trading.data.frame.Dataframe;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PolygonDataProvider {
    private static final String BASE_URL = "https://api.polygon.io";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final String apikey;
    private final OkHttpClient client;
    public PolygonDataProvider() {
        this(System.getProperty("polygon_io.api-key"));
    }
    public PolygonDataProvider(String apikey) {
        this.apikey = apikey;
        client = new OkHttpClient();
    }

    public Dataframe getHistorical(String ticker, String from, String to) throws IOException {
        LocalDate fromDate = LocalDate.parse(from, formatter);
        LocalDate toDate = LocalDate.parse(to, formatter);

        if (fromDate.isAfter(toDate))
            throw new IllegalArgumentException("To date should be after from date");

        Dataframe historicalData = new Dataframe("No Results");
        while(!fromDate.isAfter(toDate)) {
            historicalData =
                    Dataframe.merge("%s\t%s to %s".formatted(ticker, from, to), historicalData, getDaily(ticker, fromDate.format(formatter)));
            fromDate = fromDate.plusDays(1);
        }

        return historicalData;
    }

    public Dataframe getDaily(String ticker, String date) throws IOException {
        String url = BASE_URL +
                "/v1/open-close/" +
                ticker +
                '/' +
                date +
                '?';

        String params = new RequestParameters()
                .addParameter("apiKey", apikey)
                .join();

        url += params;

        Request req = new Request.Builder()
                .url(url)
                .addHeader("Accept", "text/csv")
                .build();

        try (Response res = client.newCall(req).execute()) {
            if (res.isSuccessful()) {
                String resBody = res.body().string();
                return Dataframe.fromCSV(ticker, resBody);
            } else {
                return null;
            }
        }
    }

}
