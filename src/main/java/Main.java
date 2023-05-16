import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.json.JsonReadOptions;
import tech.tablesaw.io.json.JsonReader;

import java.io.IOException;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        LOGGER.debug("Starting");
        JsonReadOptions opt = JsonReadOptions.builderFromUrl("https://wizard-world-api.herokuapp.com/Spells")
                .minimizeColumnSizes()
                .build();
        Table t = new JsonReader().read(opt);
        System.out.printf(t.print());

    }
}