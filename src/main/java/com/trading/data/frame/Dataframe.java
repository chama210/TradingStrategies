package com.trading.data.frame;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Dataframe  {

    private String name;
    private final LinkedHashMap<String, List<Object>> cols;
    private int rowCount;
    private int colCount;

    public Dataframe() {
        cols = new LinkedHashMap<>();
        this.rowCount = 0;
        this.colCount = 0;
    }

    public Dataframe(String name) {
        this();
        this.name = name;
    }

    public static Dataframe fromCSV(String name, String cont) {
        Objects.requireNonNull(cont);

        String[] lines = cont.split("\n");
        ArrayList<String> colNames = new ArrayList<>(Arrays.asList(lines[0].split(",")));

        ArrayList<ArrayList<Object>> cols = new ArrayList<>();
        for (int i = 0; i < colNames.size(); i++) cols.add(new ArrayList<>());

        for (int i = 1; i < lines.length; i++) {
            String[] row = lines[i].split(",");
            for (int j = 0; j < row.length; j++) cols.get(j).add(row[j]);
        }

        Dataframe df = new Dataframe(name);
        for (int i = 0; i < colNames.size(); i++) df.addCol(colNames.get(i), cols.get(i));

        return df;
    }

    public static Dataframe merge(String name, Dataframe df1, Dataframe df2) {
        if (Objects.isNull(df1)) return df2;
        if (Objects.isNull(df2)) return df1;

        Dataframe newDf = new Dataframe(name);
        for (String col: df1.cols.keySet()) {
            newDf.addCol(col, mergeCol(col, df1, df2, false));
        }
        for (String col: df2.cols.keySet()) {
            newDf.addCol(col, mergeCol(col, df2, df1, true));
        }

        return newDf;
    }

    private static List<Object> mergeCol(String name, Dataframe frame1, Dataframe frame2, boolean fillFront) {
        List<Object> frame1Col = frame1.getCol(name);
        if (frame2.containsCol(name)) {
            frame1Col.addAll(frame2.getCol(name));
        } else {
            fillListNull(frame1Col, frame2.rowCount, fillFront);
        }
        return frame1Col;
    }

    private static <T> void fillListNull(List<T> ls, int amount, boolean fillFront) {
        for (int i = 0; i < amount; i++) ls.add(fillFront ? 0 : ls.size(), null);
    }

    public boolean addRow(List<Object> row) {
        return insertRow(rowCount, row);
    }

    public List<Object> getRow(int idx) {
        List<Object> objs = new LinkedList<>();
        for (String s : cols.keySet()) {
            Object v = cols.get(s).get(idx);
            objs.add(v);
        }
        return objs;
    }

    public boolean insertRow(int pos, List<Object> row) {
        if (pos > rowCount + 1) return false;

        if (row.size() != colCount)
            throw new IllegalArgumentException("Length of entries {%s} do not match number of columns {%s} in the table"
                    .formatted(row.size(), colCount));

        int i = 0;
        for (String col: cols.keySet()) {
            cols.get(col).add(pos, row.get(i++));
        }

        rowCount++;
        return true;
    }

    public boolean removeRow(int pos) {
        if (pos > rowCount) return false;
        for (String col: cols.keySet()) {
            cols.get(col).remove(pos);
        }
        return true;
    }

    public boolean addCol(String name) {
        return addCol(name, null);
    }

    public boolean addCol(String name, List<Object> vals) {
        if (cols.containsKey(name)) return false;

        if (Objects.isNull(vals)) {
            vals = new ArrayList<>();
        } else {
            vals = new ArrayList<>(vals);
        }

        while (vals.size() < rowCount) {
            vals.add(null);
        }

        while(vals.size() > rowCount) {
            for (String col: cols.keySet()) {
                cols.get(col).add(null);
            }
            rowCount++;
        }

        cols.put(name, vals);

        colCount++;
        return true;
    }

    public boolean containsCol(String name) {
        return this.cols.containsKey(name);
    }

    public List<Object> getCol(String name) {
        return new ArrayList<>(cols.get(name));
    }

    public void mapCol(String name, Function<Object, Object> mapper) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(mapper);
        if (!cols.containsKey(name))
            throw new IllegalArgumentException("Column %s does not exist in the table".formatted(name));
        cols.put(name, cols.get(name).stream().map(mapper).toList());
    }

    public Optional<Object> summarizeCol(String name, BinaryOperator<Object> accumulator) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(accumulator);
        if (!cols.containsKey(name))
            throw new IllegalArgumentException("Column %s does not exist in the table".formatted(name));
       return cols.get(name).stream().reduce(accumulator);
    }

    public boolean removeCol(String name) {
        if (!cols.containsKey(name)) return false;
        cols.remove(name);
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return toString(true, false);
    }

    public String toString(boolean full, boolean includeRowNumbers) {
        if (rowCount < 10) full = true;

        // Preprocess to determine table widths.
        int[] widths = new int[colCount];
        Iterator<String> keys = cols.keySet().iterator();
        int i = 0;
        while(keys.hasNext()) {
            String key = keys.next();
            int mx = String.valueOf(key).length();
            for (Object val: cols.get(key)) {
                mx = Math.max(mx, String.valueOf(val).length());
            }
            widths[i++] = mx;
        }

        int twidth = Math.max(this.name.length() + 2, Arrays.stream(widths).sum() + colCount * 3 + 1);

        // Build table string
        StringBuilder sb = new StringBuilder();

        // Make title
        sb.append(pad(this.name, twidth))
                .append('\n')
                .append(horizontalRule(widths))
                .append(toStringHeader(widths))
                .append(horizontalRule(widths));

        if (full) {
            for (int j = 0; j < rowCount; j++) {
                sb.append(toStringRow(j, widths))
                        .append(horizontalRule(widths));
            }
        } else {
            for (int j = 0; j < 5; j++) {
                sb.append(toStringRow(j, widths))
                        .append(horizontalRule(widths));
            }
            sb.append((pad(".", twidth)+"\n").repeat(3));
            for (int j = 5; j > 0; j--) {
                sb.append(toStringRow(rowCount-j, widths))
                        .append(horizontalRule(widths));
            }

        }


        return sb.toString();
    }

    private String toStringHeader(int[] widths) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String col: cols.keySet())
            sb.append("| ")
                    .append(pad(col, widths[i++]))
                    .append(" ");
        sb.append("|\n");

        return sb.toString();
    }

    private String toStringRow(int idx, int[] widths) {
        StringBuilder sb = new StringBuilder();

        Iterator<String> keys = cols.keySet().iterator();
        int j = 0;
        while(keys.hasNext()) {
            Object v = cols.get(keys.next()).get(idx);
            sb.append("| ").append(pad(String.valueOf(v), widths[j++])).append(" ");
        }
        sb.append("|\n");

        return sb.toString();
    }

    private String horizontalRule(int[] widths) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cols.keySet().size(); i++) {
            sb.append("+").append("-".repeat(widths[i]+2)).append("");
        }
        sb.append("+\n");
        return sb.toString();
    }

    private String pad(String s, int w) {
        int l = s.length();
        int a = w - l;
        int b = a / 2;
        return " ".repeat(b) + s + " ".repeat(b + a % 2);
    }

}