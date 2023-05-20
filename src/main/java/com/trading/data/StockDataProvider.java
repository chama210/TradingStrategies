package com.trading.data;

import tech.tablesaw.api.Table;

import java.io.IOException;

public interface StockDataProvider {
    Table get(RequestParameters options) throws IOException;
}
