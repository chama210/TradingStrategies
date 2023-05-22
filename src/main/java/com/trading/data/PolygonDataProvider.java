//package com.trading.data;
//
//import com.mitchellbosecke.pebble.loader.Loader;
//import joinery.DataFrame;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import tech.tablesaw.api.ColumnType;
//import tech.tablesaw.api.Table;
//import tech.tablesaw.io.csv.CsvReadOptions;
//import tech.tablesaw.io.csv.CsvReader;
//import tech.tablesaw.joining.DataFrameJoiner;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Objects;
//
//import static tech.tablesaw.api.ColumnType.*;
//
//public class PolygonDataProvider implements StockDataProvider {
//    private static final String BASE_URL = "https://api.polygon.io";
//
//    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    private final String apikey;
//    private final OkHttpClient client;
//    public PolygonDataProvider() {
//        this(System.getProperty("polygon_io.api-key"));
//    }
//    public PolygonDataProvider(String apikey) {
//        this.apikey = apikey;
//        client = new OkHttpClient();
//    }
//
//    @Override
//    public Table get(RequestParameters parameters) throws IOException {
//        String url = parameters.addParameter("apikey", apikey).join();
//        Request req = new Request.Builder().url(url).build();
//        try (Response resp = client.newCall(req).execute()) {
//            return Table.read().csv(resp.body().string());
//        }
//    }
//
//    public Table getHistorical(String ticker, String from, String to) throws IOException {
//        LocalDate fromDate = LocalDate.parse(from, formatter);
//        LocalDate toDate = LocalDate.parse(to, formatter);
//
//        if (fromDate.isAfter(toDate))
//            throw new IllegalArgumentException("To date should be after from date");
//
//        Table historicalData = null; //
//        while(!fromDate.isAfter(toDate)) {
//            Table singlePoint = getDaily(ticker, fromDate.format(formatter));
//            if (Objects.isNull(historicalData)) {
//                historicalData = singlePoint;
//                continue;
//            }
//            historicalData.append(singlePoint);
//            // System.out.println(singlePoint.printAll());
//            fromDate = fromDate.plusDays(1);
//        }
//
//        if (Objects.isNull(historicalData)) historicalData = Table.create("%s\t%s to %s".formatted(ticker, from, to));
//        historicalData.setName("%s\t%s to %s".formatted(ticker, from, to));
//        return historicalData;
//    }
//
//    public Table getDaily(String ticker, String date) throws IOException {
//        String url = BASE_URL +
//                "/v1/open-close/" +
//                ticker +
//                '/' +
//                date +
//                '?';
//
//        String params = new RequestParameters()
//                .addParameter("apiKey", apikey)
//                .join();
//
//        url += params;
//
//        Request req = new Request.Builder()
//                .url(url)
//                .addHeader("Accept", "text/csv")
//                .build();
//
//        try (Response res = client.newCall(req).execute()) {
//            String resBody = res.body().string();
//           return Table.read().csv(resBody, ticker);
//        }
//    }
//
//}
