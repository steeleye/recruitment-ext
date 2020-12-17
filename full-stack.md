# SteelEye full-stack developer

# Table of Contents

1.  [Introduction](#orgc53fb8c)
    1.  [Constraints](#orgcbd2871)
    2.  [Database](#org47ddfe9)
2.  [Test](#orge92764b)
    1.  [Listing orders](#orgfc532bb)
    2.  [Single orders](#orgbec2c43)
    3.  [Orders search](#orgcc3b0e8)
    4.  [Advanced search](#org9970bfa)
    5.  [Bonus points](#org6ef8227)


<a id="orgc53fb8c"></a>

## Introduction

In this exercise, you'll be building an API with endpoints serving
contents from an ElasticSearch database provided for you. You will
build a React front-end on top of this API.


<a id="orgcbd2871"></a>

### Constraints

You are expected to write the API in Python with one of the following
frameworks:

-   FastAPI
-   Flask
-   Django

The front-end must be written with React and be a single-page
application.

Finally you must connect to the ElasticSearch database and
query it dynamically in your API code.


<a id="org47ddfe9"></a>

### Database

You have access to a database of orders. An order is a financial
transaction.

1.  Connection

    The database is available at the url `<INSERT HERE>`.

    The Orders are stored in the index `.order`.

2.  Schema

    The Elasticsearch schema for an Order is as follows:

```json
        {
          "properties": {
            "&id": {
              "type": "keyword"
            },
            "&model": {
              "type": "keyword"
            },
            "assetClass": {
              "type": "keyword"
            },
            "counterparty": {
              "properties": {
                "name": {
                  "type": "keyword"
                }
              }
            },
            "dataSource": {
              "properties": {
                "name": {
                  "type": "keyword"
                }
              }
            },
            "instrumentId": {
              "type": "keyword"
            },
            "instrumentName": {
              "type": "keyword"
            },
            "orderId": {
              "type": "keyword"
            },
            "purchaseType": {
              "type": "keyword"
            },
            "timestamps": {
              "properties": {
                "orderSubmitted": {
                  "type": "date"
                }
              }
            },
            "tradeData": {
              "properties": {
                "quantity": {
                  "type": "integer"
                },
                "price": {
                  "type": "integer"
                },
                "trader": {
                  "type": "keyword"
                }
              }
            }
          }
        }
```

<a id="orge92764b"></a>

## Test


<a id="orgfc532bb"></a>

### Listing orders

As a first exercise, you will be writing an API to list all orders
in the ElasticSearch database and display them all on a web-page.

1.  API

    Write an API endpoint to list orders stored in the elasticsearch
    database. An Order from the API is a subset of the Order fields
    stored in the ElasticSearch database.

    Resource: `/orders`

    Method: `GET`

    Schema for a single order:

```json
        {
          "assetClass": { "type": "string" },
          "instrumentId": { "type": "string" },
          "orderId": { "type": "string" },
          "orderSubmitted": {
            "type": "string",
            "format": "date-time"
          },
          "purchaseType": {
            "type": "string",
            "enum": ["BUY", "SELL"]
          },
          "tradePrice": { "type": "number" },
          "tradeQuantity": { "type": "number" }
        }

    Example response to `GET /orders`:

        [
          {
            "assetClass": "Equity",
            "instrumentId": "AAPL",
            "orderId": "BUYI:FOOBAR123456",
            "...": "more..."
          },
          { ... }
        ]
```

2.  Front-end

    The front-end will list all orders returned by the endpoint, as
    depicted in the mock-up below.

    ![List all orders](full-stack/orders-list.jpg)

    You are free to apply any theme you'd like.


<a id="orgbec2c43"></a>

### Single orders

We want to be able to single out an order and see more details about
this order. To this end, you will need to write an API endpoint to
retrieve data about a specific order, and a front-end view of a single
order.

1.  API

    Resource: `/orders/{order_id}`

    Method: `GET`

    Schema:

```json
        {
          "assetClass": { "type": "string" },
          "instrumentId": { "type": "string" },
          "orderId": { "type": "string" },
          "orderSubmitted": {
            "type": "string",
            "format": "date-time"
          },
          "purchaseType": {
            "type": "string",
            "enum": ["BUY", "SELL"]
          },
          "tradePrice": { "type": "number" },
          "tradeQuantity": { "type": "number" },
          "counterpartyName": { "type": "string" },
          "dataSourceName": { "type": "string" },
        }
```

    We're adding `counterpartyName` and `dataSourceName` to the resource.

2.  Front-end

    We want to be able to click on one of the orders in the orders list you
    previously built. Doing this will change the view to a detail page for
    the selected order.

    Additionally, we want to be able to access the detailed view of an
    order directly through the browser URL so we can share links to orders.

    Below is a mockup of the single-order page.

    ![One order](full-stack/order-view.png)


<a id="orgcc3b0e8"></a>

### Orders search

Displaying all orders all the time is inefficient. So we'd like to
instead limit the number of fetched orders and offer a search feature
for the list of orders.

1.  API

    You will need to modify your endpoint to list orders and limit its
    size to 20 orders only.

    The endpoint will need to support an optional `search` query parameter
    that will search for matching orders through the following fields:

    -   `instrumentName`
    -   `instrumentId`
    -   `counterparty.name`

2.  Front-end

    Add a search field atop the list of orders. The user will enter their
    search text in this field.

    A button next to the search field will run the search.

    The user can alternatively press `ENTER` to execute the search when
    the search field is focused.

    ![Orders search](full-stack/orders-search.jpg)


<a id="org9970bfa"></a>

### Advanced search

Our users now want to be able to narrow down on specific orders from
most of their fields. You'll need to support a set of criteria in the
API endpoint to list orders, and an advanced search form to match in
the front-end.

1.  API

    Modify your `/orders` API endpoint to support the following new
    optional query parameters:

    <table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


    <colgroup>
    <col  class="org-left" />

    <col  class="org-left" />
    </colgroup>
    <thead>
    <tr>
    <th scope="col" class="org-left">Parameter</th>
    <th scope="col" class="org-left">Description</th>
    </tr>
    </thead>

    <tbody>
    <tr>
    <td class="org-left"><code>assetClass</code></td>
    <td class="org-left">Asset class of the order.</td>
    </tr>


    <tr>
    <td class="org-left"><code>maxOrderSubmitted</code></td>
    <td class="org-left">The maximum date for <code>timestamps.orderSubmitted</code> field.</td>
    </tr>


    <tr>
    <td class="org-left"><code>maxPrice</code></td>
    <td class="org-left">The maximum value for <code>tradeData.price</code> field.</td>
    </tr>


    <tr>
    <td class="org-left"><code>minOrderSubmitted</code></td>
    <td class="org-left">The minimum date for <code>timestamps.orderSubmitted</code> field.</td>
    </tr>


    <tr>
    <td class="org-left"><code>minPrice</code></td>
    <td class="org-left">The minimum value for <code>tradeData.price</code> field.</td>
    </tr>


    <tr>
    <td class="org-left"><code>purchaseType</code></td>
    <td class="org-left">The trade is BUY or SELL.</td>
    </tr>
    </tbody>
    </table>

    All maximum and minimum fields are inclusive (e.g. minPrice=2&maxPrice=10 will
    return 2 <= tradePrice <= 10).

    The output schema remains unchanged.

2.  Front-end

    Turn the search field into a full advanced search form that will allow the user
    to fill in the new query parameter for the back-end.

    See the mock-up below for reference:

    ![Advanced orders search](full-stack/orders-search-advanced.jpg)


<a id="org6ef8227"></a>

### Bonus points

Implement a pagination on the list of orders (both API and front-end).
