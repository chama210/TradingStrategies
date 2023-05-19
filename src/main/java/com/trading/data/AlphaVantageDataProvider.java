//package com.trading.data;
//
//import com.crazzyghost.alphavantage.AlphaVantage;
//import com.crazzyghost.alphavantage.Config;
//import com.crazzyghost.alphavantage.parameters.OutputSize;
//import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
//import tech.tablesaw.api.Table;
//
//public class AlphaVantageDataProvider implements StockDataProvider {
//
//    private Config config;
//
//
//    public AlphaVantageDataProvider() {
//        this(System.getProperty("alpha_vantage.api-key"));
//    }
//    public AlphaVantageDataProvider(String apikey) {
//        config = Config.builder()
//                .key(apikey)
//                .timeOut(15)
//                .build();
//        AlphaVantage.api().init(config);
//    }
//
//    @Override
//    public Table get(RequestOptions options, String ticker) {
//        TimeSeriesResponse resp =  AlphaVantage.api()
//                .timeSeries()
//                .daily()
//                .adjusted()
//                .forSymbol("aapl")
//                .outputSize(OutputSize.FULL)
//                .fetchSync();
//        resp.getStockUnits().forEach(System.out::println);
//        return null;
//    }
//
//    @Override
//    public Table get(RequestOptions options, String... ticker) {
//        return null;
//    }
//}
