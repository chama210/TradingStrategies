package com.trading.algorithms;

import com.trading.data.StockDataProvider;
import com.trading.data.frame.Dataframe;

import java.io.IOException;
import java.util.List;

public class CUSUM implements TradingStrategies {

    private final StockDataProvider dataProvider;
    public CUSUM(StockDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }
    @Override
    public TradeOption[] analyze(String ticker, String from, String to) throws IOException {
        Dataframe df = dataProvider.getHistorical(ticker, from, to);
        System.out.println(df);
        List<Object> closesString = df.getCol("close");
        List<Double> closes = closesString.stream().map(val -> Double.parseDouble(String.valueOf(val))).toList();
        Double sum = closes.stream().reduce(Double::sum).orElseGet(() -> 0.0);
        int count = closes.size();
        Double avg = sum / count;

        double threshold = .1 * avg;

        double[] gains = new double[closes.size()];
        double[] losses = new double[closes.size()];
        TradeOption[] trades = new TradeOption[count];
        trades[0] = TradeOption.BUY;
        for (int i = 1; i < closes.size(); i++) {
            double diff = closes.get(i-1) - closes.get(i);
            gains[i] = Math.max(0, diff + gains[i-1]);
            losses[i] = Math.min(0, diff + losses[i-1]);

            trades[i] = TradeOption.HOLD;
            if (gains[i] >= threshold) {
                gains[i] = 0;
                trades[i] = TradeOption.SELL;
            }

            if (losses[i] <= -threshold) {
                losses[i] = 0;
                trades[i] = TradeOption.BUY;
            }
        }

        return trades;
    }

    @Override
    public TradeOption predict(String ticker, String from, String to) {
        return null;
    }
}
