package com.trading.algorithms;

import java.io.IOException;

public interface TradingStrategies {

    public TradeOption[] analyze(String ticker, String from, String to) throws IOException;

    public TradeOption predict(String ticker, String from, String to);

    enum TradeOption {
        BUY, SELL, HOLD;
    }
}
