# SteelEye full-stack developer - Orders filters and listing problem (Part - 1)

The question below is meant to give candidates a sense of the problems we tackle at SteelEye. Please submit solutions in the form of a readme + working code. The problems should take around three hours to complete (2 hrs for the APIs and 1 for the Frontend).

Write an API endpoint that returns a paginated filtered list of Order records and create a frontend for the same.

## Requirements:
* API - Python (use one of the Python framework: FastAPI, Flask or Django)
* Database - Elasticsearch
* Frontend - React.

## Order record Elasticsearch schema:
```
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

## Elasticsearch Database Credentials
```
DATABASE_URL=<INSERT_HERE>
INDEX_NAME=.order
```
Use the above url to connect to the ES instance and query the result.

For eg:

The query should return the orders with `purchaseType=BUY`
```
POST: `{DATABSE_URL}/{INDEX_NAME}/_search`
BODY:
{
    "query": {
        "bool": {
            "filter": [
                {
                    "term": {
                        "purchaseType": "BUY"
                    }
                }
            ]
        }
    }
}
```

## API:
Write an API using Python 

```
GET /listings?type=BUY&search=Amazon&assetClass=Equity&minOrderSubmitted=2011-10-05T14:48:00.000Z&maxOrderSubmitted=2020-12-05T14:48:00.000Z&minPrice=120&maxPrice=2000&minQuantity=10&axQuantity=2000
```


| Parameter           | Description                                                                  |
|---------------------|------------------------------------------------------------------------------|
| `assetClass`        | Asset class of the order.                                                    |
| `maxOrderSubmitted` | The maximum date for `timestamps.orderSubmitted` field.                      |
| `maxPrice`          | The maximum value for `tradeData.price` field.                               |
| `minOrderSubmitted` | The minimum date for `timestamps.orderSubmitted` field.                      |
| `minPrice`          | The minimum value for `tradeData.price` field.                               |
| `search`            | Search on the `instrumentName`, `instrumentId` and `counterparty.name` field |
| `purchaseType`      | The trade is BUY or SELL.                                                    |
| `limit`             | Maximum number of orders to send at once., defaults to 10                    |
| `skip`              | The number of orders to skip, defaults to 0                                  |


The expected response is a paginated JSON orders of listings:

```
{
  header: {
    totalHits: 30,
  },
  results: [
    {
      assetClass: 'Equity',
      countpartyName: 'John Smith',
      dataSourceName: 'SteelEye',
      instrumentId: 'AMZN',
      instrumentName: 'AMAZON NYE',
      orderId: 'ORD-343243',
      orderSubmitted: '2020-10-05T14:48:00.000Z',
      purchaseType: 'BUY',
      tradePrice: 10000,
      tradeQuantity: 100,
    }
    ...
  ]
}
```

All query parameters are optional, all minimum and maximum fields should be inclusive (e.g. minPrice=2&maxPrice=400 should return orders with 2, 3, or 400 trade price).

At a minimum:
- Your API endpoint URL is `/listings`
- Your API should correctly filter any combination of API parameters
