package com.trading.data;

import com.trading.data.frame.Dataframe;

import java.io.IOException;

public interface StockDataProvider {
    public Dataframe getHistorical(String ticker, String from, String to) throws IOException;
    public Dataframe getDaily(String ticker, String date) throws IOException;
}
