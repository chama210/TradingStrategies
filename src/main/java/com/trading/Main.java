package com.trading;

import com.trading.algorithms.CUSUM;
import com.trading.algorithms.TradingStrategies;
import com.trading.data.PolygonDataProvider;
import com.trading.data.frame.Dataframe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class Main {
    static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    static {
        try {
            loadProperties("secrets.properties");
        } catch (IOException ioe) {
            LOGGER.error("Failed to load secrets.", ioe);
        }
    }
    static final PolygonDataProvider POLYGON_DATA_PROVIDER = new PolygonDataProvider();
    static final CUSUM CUSUM = new CUSUM(POLYGON_DATA_PROVIDER);
    public static void main(String[] args) {
        if (args.length > 1) {
            //
        } else {
            Scanner in = new Scanner(System.in);
            while (true) {
                System.out.print("> ");
                String line = in.nextLine();
                try {
                    interpretLine(line);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void loadProperties(String src) throws IOException {
        Properties props = new Properties();
        props.load(ClassLoader.getSystemResource(src).openStream());
        props.forEach((k, v) -> System.setProperty(String.valueOf(k), String.valueOf(v)));
    }

    public static void interpretLine(String line) throws NoSuchMethodException, IOException {
        String[] args = line.toLowerCase().split(" ");
        if (args.length < 1) return;

        String func = args[0];
        Hashtable<String, String> argsMap = new Hashtable<>();
        for (int i = 1; i < args.length; i++) {
            String[] kv = args[i].replaceFirst("--", "").split("=");
            if (kv.length >= 2) {
                argsMap.put(kv[0], kv[1]);
            } else {}
        }

        switch(func) {
            case "cusum" -> {
                String ticker = Objects.requireNonNull(argsMap.get("ticker"));
                String from = Objects.requireNonNull(argsMap.get("from"));
                String to = Objects.requireNonNull(argsMap.get("to"));
                Dataframe df = POLYGON_DATA_PROVIDER.getHistorical(ticker, from, to);
                TradingStrategies.TradeOption[] trades = Main.CUSUM.analyze(ticker, from, to);
                df.addCol("trades", Arrays.asList(trades));
                System.out.println(Arrays.toString(trades));
                System.out.println(df);
            }
            default -> throw new NoSuchMethodException("%s is not an implemented trading strategy.".formatted(func));
        }
    }

}
// cusum --ticker=aapl --from=2023-01-01 --to=2023-03-01
// cusum --ticker=aapl --from=2023-03-01 --to=2023-01-01