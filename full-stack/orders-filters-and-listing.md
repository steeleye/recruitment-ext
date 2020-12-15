# SteelEye full-stak developer - Orders filters and listing problem

The question below is meant to give candidates a sense of the problems we tackle at SteelEye. Please submit solutions in the form of a readme + working code. The problems should take around three hours to complete (2 hrs for the APIs and 1 for the Frontend).

Write an API endpoint that returns a paginated filtered list of Order records adn create a frontend for the same.

## Requirements:
* API - Python
* Database - Elasticsearch
* Frontend - React / Angular or any framework of your choice.

## Order record schema:
```
{
  &id: { type: String, unique: True, required: True },
  &model: { type: String, required: True }, // For order records value is 'Order'
  orderId: { type: String, unique: True, required: True },
  assetClass: { type: String, required: True, enum: [ 'Equity', 'Debt Instruments', 'Foreign Exchange' ] },
  instrumentName: { type: String, required: True },
  instrumentId: { type: String, required: True },

  purchaseType: { type: String, required: True, enum: [ 'BUY', 'SELL' ] },
  countparty: {
    name: { type: String, required: True },
  },

  dataSource: {
    name: { type: String, required: True },
  },

  tradeData: {
    quantity: { type: String, required: True },
    price: { type: Number, required: True },
    trader: { type: String, required: True },
  },

  timestamps: {
    orderSubmitted: { type: ISODateString, required: True },
  }
}
```

## Database 
```
DATABSE_URL=https://elastic:iHOgynJY6G4HsttZ3CIyjtvz@8f7b2aacdcb14f5ba2464e685e175e69.eu-west-2.aws.cloud.es.io:9243
API_KEY=QXR1N1pIWUJhbkM3Q1R6alZuWEg6YVc0SDVhRFpTZFNvMjdfWDBTN05IQQ==
INDEX_NAME=.order
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

## Frontend

Create a simple web page using Angular / React or any other framework of choice for the the above filters listing. You can use any library for date input and price range input if needed.

### Filters:

| Filter                 | Key                                       |  UI Type                                          |
|------------------------|-------------------------------------------|---------------------------------------------------|
| Type                   | `type`                                    |  Single select Dropdown with `type` enums.        |
| Search                 | `search`                                  |  Input text.                                      |
| Asset Class            | `assetClass`                              |  Single select Dropdown with `assetClass` enums.  |
| Order Submitted Range  | `minOrderSubmitted`, `maxOrderSubmitted`  |  Date Range                                       |
| Price range            | `minPrice`, `maxPrice`                    |  min and max number input fields.                 |

### Table:

Simple table with pagination.

| Column                 | Map to key        |
|------------------------|-------------------|
| Order ID               | `orderId`         |
| Asset Class            | `assetClass`      |
| Order Submitted        | `orderSubmitted`  |
| Instrument Name        | `instrumentName`  |
| Instrument ID          | `instrumentId`    |
| Purchase Type          | `purchaseType`    |
| Trade Quantity         | `tradeQuantity`   |
| Trade Price            | `tradePrice`      |
