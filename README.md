
# REST Price Service

## Overview

This project is a Spring Boot application that provides a REST service to query product prices.

## Important Tools Used

- **Java 17**
- **Spring Framework**
- **Maven** for dependency management
- **H2 Database** (in-memory)

## Database Structure

The database is configured with a table called `PRICES` that reflects the final price.

```plaintext
| BRAND_ID | START_DATE           | END_DATE             | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|----------------------|----------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14 00:00:00  | 2020-12-31 23:59:59  | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14 15:00:00  | 2020-06-14 18:30:00  | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15 00:00:00  | 2020-06-15 11:00:00  | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15 16:00:00  | 2020-12-31 23:59:59  | 4          | 35455      | 1        | 38.95 | EUR  |
```

### Fields

- **BRAND_ID**: Foreign key representing the chain of the group (1 = ZARA).
- **START_DATE**: Start date and time of the rate.
- **END_DATE**: End date and time of the rate.
- **PRICE_LIST**: Identifier of the applicable price rate.
- **PRODUCT_ID**: Identifier of the product.
- **PRIORITY**: Price application disambiguator; the rate with the highest priority is applied if there are date range overlaps.
- **PRICE**: Final selling price.
- **CURR**: Currency ISO code.

## Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/ErnestoJavaDev/prices.git
   cd prices
   ```

2. **Build and run the application**:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Using the REST Service

The service provides an endpoint to query the price of a product:

### Endpoint

```
GET /shop/search/prices
```

### Query Parameters

- `dateTime`: Date in `YYYY-MM-DDTHH:mm:ss` format (e.g., `2020-06-14T10:00:00`).
- `productId`: Product identifier (e.g., `35455`).
- `brandId`: Brand identifier (e.g., `1`).

### Example Request

```http
GET /shop/search/prices?dateTime=2020-06-14T10:00:00&productId=35455&brandId=1
```

### Example Response

```json
{
    "productId": 35455,
    "brandId": 1,
    "priceList": 1,
    "startDate": "2020-06-14T00:00:00",
    "endDate": "2020-12-31T23:59:59",
    "price": 35.50,
    "currency": "EUR"
}
```

## Tests

 - Unit tests to validate layers individualy. The tests are located in the `src/test/java` directory. Into `/com/shop/pricing` and `/application` or `/infrastructure` or `/domain`. Depends of the layer you want test


 - Integration tests to validate the REST endpoint. The tests are located in the `src/test/java` directory. `/com/shop/pricing/PriceIntegrationTests.java` class

   - **Test 1**: Request at 10:00 on the 14th for product 35455 for brand 1 (ZARA).
   - **Test 2**: Request at 16:00 on the 14th for product 35455 for brand 1 (ZARA).
   - **Test 3**: Request at 21:00 on the 14th for product 35455 for brand 1 (ZARA).
   - **Test 4**: Request at 10:00 on the 15th for product 35455 for brand 1 (ZARA).
   - **Test 5**: Request at 21:00 on the 16th for product 35455 for brand 1 (ZARA).

To run the tests, use the following command:

```bash
mvn test
```

## More Information

For more information about Spring Boot and REST, please refer to the official documentation:

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Data JPA Documentation](https://spring.io/projects/spring-data-jpa)

## Support

If you have any questions or need assistance, please open an issue in the repository.
