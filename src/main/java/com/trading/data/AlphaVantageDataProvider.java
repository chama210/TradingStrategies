package com.trading.data;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tech.tablesaw.api.Table;

import java.io.IOException;

public class AlphaVantageDataProvider implements StockDataProvider {

    private static final String BASE_URL = "https://www.alphavantage.co/query?";
    private final String apikey;
    private final OkHttpClient client;
    public AlphaVantageDataProvider() {
        this(System.getProperty("alpha_vantage.api-key"));
    }
    public AlphaVantageDataProvider(String apikey) {
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
