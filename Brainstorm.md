# Objective

The objective of this project is to rewrite an existing python implementation of
q-sum and buy-and-hold trading methodologies in Java.
This implementation will also utilize an api to query fresh trading data.
This implementation is will also allow for the user to issue commands.

# Approach

User Stories
1. Users can create query data in regards to a specific stock price
   1.
2. users can query an analysis

# Tools and Technologies
In place of numpy, pandas, and matplotlib we will use
[TableSaw](https://github.com/jtablesaw/tablesaw).
> Tablesaw is a dataframe and visualization library that supports loading,
> cleaning, transforming, filtering, and summarizing data. If you work
> with data in Java, it may save you time and effort.
> Tablesaw also supports descriptive statistics and can be used to prepare data for working with machine learning libraries like Smile, Tribuo, H20.ai, DL4J.

[Getting started guide](https://jtablesaw.github.io/tablesaw/gettingstarted)  
[User Guide](https://jtablesaw.github.io/tablesaw/userguide/toc)  
[Java Docs](https://www.javadoc.io/doc/tech.tablesaw/tablesaw-core/latest/overview-summary.html)  

We can retrieve data from a web api using TableSaw as shown below.
<pre>
JsonReadOptions opt = JsonReadOption.builderFromUrl(""https://wizard-world-api.herokuapp.com/Spells"").build();
Table t = new JsonReader().read(opt);
</pre>

# Notes/ Aside
