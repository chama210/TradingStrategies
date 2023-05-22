package com.trading;

// import com.trading.data.PolygonDataProvider;
import com.trading.data.frame.Dataframe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import tech.tablesaw.api.Table;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;


public class Main {
    static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException {
        try {
            loadProperties("secrets.properties");
        } catch (IOException ioe) {
            LOGGER.error("Failed to load secrets.", ioe);
        }

//        if (args.length <= 1) {
//
//        } else {
//            Scanner in = new Scanner(System.in);
//            while (true) {
//                System.out.println("> ");
//                String line = in.nextLine();
//                interpretLine(line);
//            }
//        }

//        PolygonDataProvider pdp = new PolygonDataProvider();
//        Table t = pdp.getDaily("AAPL","2023-05-19");
//        System.out.println(t.printAll());
////
////        System.out.println();
//        System.out.println();
//
//         t = pdp.getHistorical("AAPL","2023-05-01", "2023-05-19");
//        System.out.println(t.printAll());

        Dataframe df = new Dataframe("Test");
        df.addCol("a");
        df.addCol("b");
        df.addCol("c");
        df.addCol("d");
        df.addRow(List.of(1, 2, 3, 4));
        df.addRow(List.of(2, 3, 4, 5));
        System.out.println(df);
    }

    public static void loadProperties(String src) throws IOException {
        Properties props = new Properties();
        props.load(ClassLoader.getSystemResource(src).openStream());
        props.forEach((k, v) -> System.setProperty(String.valueOf(k), String.valueOf(v)));
    }

    public static void interpretLine(String line) {

    }

}