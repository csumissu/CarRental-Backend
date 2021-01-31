## CarRental-Backend

This is a simple rental car booking API service for customers.

### Users

There will be two types of users, one is the customer, and the other is the system administrator.
The customers use this system to rent and return cars, and the system administrators could manage cars. 

### Features & APIs

#### Customer
- Login ```POST /login```
- Logout ```POST /logout```
- Search all available cars ```GET /cars```
- View the details of a certain car ````GET /cars/{id}````
- Create an order by booking a car ```POST /orders```
- Close an order by returning a car ```POST /orders/{id}/close```
- Search orders ```GET /orders```
- View the details of a certain order ```GET /orders/{id}```

#### Admin (Nice to have)
- Login ```POST /login```
- Logout ```POST /logout```
- Search all cars ```GET /cars```
- Add a new car ```POST /cars```


### The Common Response Format

```
{
    "code": integer,
    "message": "the description of response",
    "data": object
}
```