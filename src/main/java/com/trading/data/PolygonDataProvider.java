package com.trading.data;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tech.tablesaw.api.Table;

import java.io.IOException;

public class PolygonDataProvider implements StockDataProvider {
    private static final String BASE_URL = "https://api.polygon.io";
    private final String apikey;
    private final OkHttpClient client;
    public PolygonDataProvider() {
        this(System.getProperty("polygon_io.api-key"));
    }
    public PolygonDataProvider(String apikey) {
        this.apikey = apikey;
        client = new OkHttpClient();
    }

    @Override
    public Table get(RequestParameters parameters) throws IOException {
        String url = parameters.addParameter("apikey", apikey).join();
        Request req = new Request.Builder().url(url).build();
        try (Response resp = client.newCall(req).execute()) {
            return Table.read().csv(resp.body().string());
        }
    }
}
