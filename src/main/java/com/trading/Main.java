package com.trading;

import com.trading.data.PolygonDataProvider;
import com.trading.data.frame.Dataframe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.events.DTD;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

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

        PolygonDataProvider pdp = new PolygonDataProvider();
        Dataframe t = pdp.getDaily("AAPL","2023-05-19");
        System.out.println(t);
        System.out.println();
        System.out.println();

        t = pdp.getHistorical("AAPL","2023-05-01", "2023-05-19");
        System.out.println(t);

//        Dataframe df = new Dataframe("Test");
//        df.addCol("a", List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
//        df.addCol("b", List.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
//        df.addCol("c", List.of("", "null", "null", "null",
//                "null", "null", "null", "null", "null", "null", "null", "null", "null", "null", "haldjfajdljflkajdlfadh"));
//        System.out.println(df.summarizeCol("a", (a, b) -> (Integer)a + (Integer)b));
//
//        System.out.println(df.toString(false, true));
    }

    public static void loadProperties(String src) throws IOException {
        Properties props = new Properties();
        props.load(ClassLoader.getSystemResource(src).openStream());
        props.forEach((k, v) -> System.setProperty(String.valueOf(k), String.valueOf(v)));
    }

    public static void interpretLine(String line) {

    }

}