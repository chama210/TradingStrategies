package com.trading.data;
//
//import io.polygon.kotlin.sdk.rest.AggregateDTO;
//import io.polygon.kotlin.sdk.rest.AggregatesDTO;
//import io.polygon.kotlin.sdk.rest.PolygonRestApiCallback;
//import io.polygon.kotlin.sdk.rest.PolygonRestClient;
//import io.polygon.kotlin.sdk.rest.stocks.PreviousCloseDTO;
//import org.jetbrains.annotations.NotNull;
//import tech.tablesaw.api.DoubleColumn;
//import tech.tablesaw.api.StringColumn;
//import tech.tablesaw.api.Table;
//
//import java.time.LocalDate;
//import java.util.List;
//
public class PolygonDataProvider {} // implements StockDataProvider {}
//
//    PolygonRestClient polygonRestClient;
//
//    public PolygonDataProvider() {
//        this(System.getProperty("polygon_io.api-key"));
//    }
//
//    PolygonDataProvider(String apikey) {
//        polygonRestClient = new PolygonRestClient(apikey);
//    }
//    @Override
//    public Table get(RequestOptions options, String ticker) {
//        PreviousCloseDTO previousCloseDTO = polygonRestClient.getStocksClient().getPreviousCloseBlocking(ticker, false);
//        List<AggregateDTO> aggregateDTOs = previousCloseDTO.getResults();
//
//        Table t = Table.create("General Info");
//        StringColumn tickerCol = StringColumn.create("Ticker");
//        DoubleColumn highCol =  DoubleColumn.create("High");
//        DoubleColumn lowCol =  DoubleColumn.create("Low");
//        DoubleColumn closeCol =  DoubleColumn.create("Close");
//
//        for (AggregateDTO aggregateDTO: aggregateDTOs) {
//            tickerCol.append(aggregateDTO.getTicker());
//            highCol.append(aggregateDTO.getHigh());
//            lowCol.append(aggregateDTO.getLow());
//            closeCol.append(aggregateDTO.getClose());
//        }
//
//        t.addColumns(tickerCol, highCol, lowCol, closeCol);
//        return t;
//    }
//
//    @Override
//    public Table get(RequestOptions options, String... ticker) {
//        return null;
//    }
//}
