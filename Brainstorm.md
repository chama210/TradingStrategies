# Objective

The objective of this project is to rewrite an existing python implementation of
([cusum](https://algofin.substack.com/p/algorithmic-trading-models-cumulative) as in cumulative sum?) and buy-and-hold trading methodologies in Java.
This implementation will also utilize an api to query fresh trading data.
This implementation is will also allow for the user to issue commands.

A similar strategy to consider is [rubber banding](https://www.quantifiedstrategies.com/rubber-band-trading-strategy/).

These investment strategies will be back tested to determine optimality.
> Backtesting is the general method for seeing how well a strategy or model would have done ex-post.
> Backtesting assesses the viability of a trading strategy by discovering how it would play out using historical data.
> If backtesting works, traders and analysts may have the confidence to employ it going forward.

Possibility of adding heuristics, such as:  
stock has been split -> buy, etc.

Notes:
1. For information on quantitative trading see [this](https://www.investopedia.com/terms/q/quantitative-trading.asp)
2. For information on stock analysis see [this](https://www.investopedia.com/terms/s/stock-analysis.asp)

# Approach

User Stories
1. Users can create query data in regards to a specific stock
   1. Price
   2. Volume
   3. Total Value
   4. etc.
2. users can query an analysis
   1. mean-value
   2. mean-high
   3. etc.
3. User can check whether a stock is ranging or trending
   1. ranging - the stock value is oscilating within a fixed range
   2. trending - the stock value is rising are falling significantly over an
   extended period of time.


Functional Requirements
1. System should support simple caching of information to reduce network load and api calls
2. 

# Tools and Technologies
## Libraries
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
```
String url = "https://something.com/say_hey?name=\"code_monkey\""
JsonReadOptions opt = JsonReadOption.builderFromUrl($url).build();
Table t = new JsonReader().read(opt);
```

## API

There are many apis available for stock/trading data, below is a list of some 
that are under consideration:
1. [Polygon.io](https://polygon.io/docs/stocks/getting-started)
   1. Does not support time series data, but allows request for data
on a stock for a specific day.
2. [Alpha Vantage](https://www.alphavantage.co/documentation/)
   1. Clearly indicates support for adjusted and non-adjusted stock data.
   2. Provides Java APIs
3. [Twelve Data](https://twelvedata.com/docs#reference-data)

Good resources for alternative options and further research are 
[Best Stock APIs and Industry Landscape in 2022](https://patrickalphac.medium.com/stock-api-landscape-5c6e054ee631) and
[RapidApi](https://rapidapi.com/blog/best-stock-api/).

# Notes/ Aside

# Markdown Cheat Sheet

Thanks for visiting [The Markdown Guide](https://www.markdownguide.org)!

This Markdown cheat sheet provides a quick overview of all the Markdown syntax elements. It can’t cover every edge case, so if you need more information about any of these elements, refer to the reference guides for [basic syntax](https://www.markdownguide.org/basic-syntax) and [extended syntax](https://www.markdownguide.org/extended-syntax).

## Basic Syntax

These are the elements outlined in John Gruber’s original design document. All Markdown applications support these elements.

### Heading

# H1
## H2
### H3

### Bold

**bold text**

### Italic

*italicized text*

### Blockquote

> blockquote

### Ordered List

1. First item
2. Second item
3. Third item

### Unordered List

- First item
- Second item
- Third item

### Code

`code`

### Horizontal Rule

---

### Link

[Markdown Guide](https://www.markdownguide.org)

### Image

![alt text](https://www.markdownguide.org/assets/images/tux.png)

## Extended Syntax

These elements extend the basic syntax by adding additional features. Not all Markdown applications support these elements.

### Table

| Syntax | Description |
| ----------- | ----------- |
| Header | Title |
| Paragraph | Text |

### Fenced Code Block

```
{
  "firstName": "John",
  "lastName": "Smith",
  "age": 25
}
```

### Footnote

Here's a sentence with a footnote. [^1]

[^1]: This is the footnote.

### Heading ID

### My Great Heading {#custom-id}

### Definition List

term
: definition

### Strikethrough

~~The world is flat.~~

### Task List

- [x] Write the press release
- [ ] Update the website
- [ ] Contact the media

### Emoji

That is so funny! :joy:

(See also [Copying and Pasting Emoji](https://www.markdownguide.org/extended-syntax/#copying-and-pasting-emoji))

### Highlight

I need to highlight these ==very important words==.

### Subscript

H~2~O

### Superscript

X^2^

