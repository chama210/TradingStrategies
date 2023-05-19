
import com.trading.data.PolygonDataProvider;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.ScatterTrace;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException {
        try {
            loadProperties("secrets.properties");
        } catch (IOException ioe) {
            LOGGER.error("Failed to load secrets.", ioe);
        }
        // System.setProperty("java.home", System.getenv("JAVA_HOME"));
        // System.setProperty("user.dir", "/Users/" + System.getenv("USER"));
//
//
//        System.out.println(System.getProperty("alpha_vantage.api-key"));
//        // LOGGER.info("loaded prop: " + System.getProperty("alpha_vantage.api-key"));

        // System.out.println(new String(new URL("http://www.google.com").openStream().readAllBytes()));
        OkHttpClient client = new OkHttpClient();
        System.out.println(client.dns());
        Request req = new Request.Builder().get().url("https://www.google.com").build();
        Response res = client.newCall(req).execute();
        System.out.println(res.body());
        // AlphaVantageDataProvider pdp = new AlphaVantageDataProvider();
        //Table t = pdp.get(null, "aapl");
        //LOGGER.info("\n" + t.printAll());
    }

    public static void loadProperties(String src) throws IOException {
        Properties props = new Properties();
        props.load(ClassLoader.getSystemResource(src).openStream());
        System.setProperties(props);
    }

//    public static void main(String[] args) throws IOException {
//        LOGGER.debug("Starting");
//
//        int[] ids = new int[10];
//        final AtomicInteger id = new AtomicInteger(1);
//        fillArray(ids, id::getAndIncrement);
//
//
//        double[] cost = new double[10];
//        Random r = new Random();
//        fillArray(cost, () -> r.nextDouble(10));
//
//        IntColumn a = IntColumn.create("id", ids);
//        DoubleColumn b = DoubleColumn.create("cost", cost);
//
//
//        Table t = Table.create("temp", a, b);
//        System.out.printf(t.printAll());
//
//        double[] x = {1, 2, 3, 4, 5, 6};
//        double[] y = {0, 1, 6, 14, 25, 39};
//        String[] labels = {"a", "b", "c", "d", "e", "f"};
//
//        ScatterTrace trace = ScatterTrace.builder(x, y)
//                .text(labels)
//                .build();
//
//        Plot.show(new Figure(trace));
//
//    }
//
//    public static void fillArray(int[] arr, Supplier<Integer> s) {
//        for (int i = 0; i < arr.length; i++) arr[i] = s.get();
//    }
//
//    public static void fillArray(double[] arr, Supplier<Double> s) {
//        for (int i = 0; i < arr.length; i++) arr[i] = s.get();
//    }
//
//    public static <T> void fillArray(T[] arr, Supplier<T> s) {
//        for (int i = 0; i < arr.length; i++) arr[i] = s.get();
//    }
}