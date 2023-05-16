
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.traces.ScatterTrace;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        LOGGER.debug("Starting");
//        JsonReadOptions opt = JsonReadOptions.builderFromUrl("https://wizard-world-api.herokuapp.com/Spells")
//                .minimizeColumnSizes()
//                .build();
//        Table t = new JsonReader().read(opt);
//        System.out.printf(t.print());

        int[] ids = new int[10];
        final AtomicInteger id = new AtomicInteger(1);
        fillArray(ids, id::getAndIncrement);


        double[] cost = new double[10];
        Random r = new Random();
        fillArray(cost, () -> r.nextDouble(10));

        IntColumn a = IntColumn.create("id", ids);
        DoubleColumn b = DoubleColumn.create("cost", cost);


        Table t = Table.create("temp", a, b);
        System.out.printf(t.printAll());

        double[] x = {1, 2, 3, 4, 5, 6};
        double[] y = {0, 1, 6, 14, 25, 39};
        String[] labels = {"a", "b", "c", "d", "e", "f"};

        ScatterTrace trace = ScatterTrace.builder(x, y)
                .text(labels)
                .build();

        Plot.show(new Figure(trace));

    }

    public static void fillArray(int[] arr, Supplier<Integer> s) {
        for (int i = 0; i < arr.length; i++) arr[i] = s.get();
    }

    public static void fillArray(double[] arr, Supplier<Double> s) {
        for (int i = 0; i < arr.length; i++) arr[i] = s.get();
    }

    public static <T> void fillArray(T[] arr, Supplier<T> s) {
        for (int i = 0; i < arr.length; i++) arr[i] = s.get();
    }
}