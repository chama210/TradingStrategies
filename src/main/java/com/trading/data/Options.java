package com.trading.data;

public class Options {

    enum Function {}

    enum Interval {
        ONE_MIN("1min"),
        FIVE_MIN("5min"),
        FIFTEEN_MIN("15min"),
        THIRTY_MIN("30min"),
        SIXTY_MIN("60min"),
        DAILY("daily"),
        WEEKLY("weekly"),
        MONTHLY("monthly"),
        QUARTERLY("quarterly"),
        ANNUAL("annual");

        private final String val;

        Interval(String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val;
        }

    }

    enum Outputsize { FULL, COMPACT}

    enum DataType { JSON, CSV }

    enum SeriesType { OPEN, CLOSE, HIGH, LOW }

    enum Order { ASC, DESC }

    enum Market { STOCKS, CRYPTO, FX, OTC, INDICES }

}
