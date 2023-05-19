package com.trading.data;

import tech.tablesaw.api.Table;

public interface StockDataProvider {
    Table get(RequestOptions options, String ticker);
    Table get(RequestOptions options, String...ticker);
}
