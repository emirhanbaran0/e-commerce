# e-commerce project ![](spring.PNG)
## Introduction 
An Ecommerce Website Project designed to buy products by users and sell products by the owner.Member of the site can make shopping with open an account and login the account.The backend is designed as a **Monolithic Architecture** with various nuances as discussed below.

## ⚙ Technologies and Dependencies Used
* [Maven](https://maven.apache.org/) used as a dependency management tool.
* [Spring Boot](https://spring.io/projects/spring-boot) used to build hassle free web applications and writing REST APIs.
* [Spring Security](https://spring.io/projects/spring-security) used for Authentication and Authorizations.
* [Spring data JPA (Hibernate)](https://hibernate.org/) Used to reduce the time of writing hardcoded sql queries and instead allows to write much more readable and scalable code 
* [Log4j](https://logging.apache.org/log4j/2.x/) used for logging into the database and a file.
* [PostgreSQL](https://www.postgresql.org/) used as a Java persistence store
* [Project Lombok](https://projectlombok.org/) Reduces the time  of writing java boiler plate code.
* [ModelMapper](https://modelmapper.org/) used for one class to another.
* [Swagger](https://swagger.io/) Used for easy way of applying great documentation.


## Backend Design 
### Entities 
Actors/Entities are inspired by the real world entities that can use the applications  
1. **Base Entity** having attributes:
* creation_date, update_date
2. **User** having attributes:
* unique primary key user_id, username, password, budget, email, role Authorization--> (USER/ADMIN),user_info(foreign key), orderBaskets (foreign key), orders (foreign key), user_favorite_products (foreign key) 
3. **User Detail** having attributes:
* unique primary key user_id, user (foreign key), name, surname,phone_number
4. **Product** having attributes:
* Unique primary key product_id, product_name, product_description, product_price,product_imageURL,product_quantity,category (foreign key), order_baskets (foreign key), order (foreign key), user_favorite_products (foreign key) 
5. **Category** having attributes:
* unique primary key category_id, category_name, category_imageURL, products (foreign key)
6. **Card** having attributes:
* unique primary key card_id,card_number, card_owner_name, card_owner_surname, card_expired_date, card_CVC_code, card_budget
7. **Order** having attributes:
* unique primary key order_id, products(foreign key --> products which are in current order),order_status(Paid,Processing,Canceled,Done) user (foreign key), address, total_price, delivery (foreign key)
8. **Order Basket** having attributes:
* unique primary key order_basket_id,total_price,quantity, products(foreign key --> products which are in order basket), user (foreign key) 
9. **Delivery** having attributes:
* unique primary key delivery_id, order (foreign key), delivery_status(Processing,Canceled,Delivered), delivery_code 

### Relationships Between Entities and ER diagram
An additional SQL table created to map the N:M mapping between the **orders** and the **order_basket** called **order_basket_products**. The  table ,is for user products which are in an order basket.

An additional SQL table created to map the N:M mapping between the **users** and the **products** called **user_favorite**. The  table is for user favorite products.

An additional SQL table created to map the N:M mapping between the **orders** and the **products** called **order_products**. The  table ,is for user products which are in an order.




ER Diagram:
![](ER.PNG)

## 📡 API
### 📌 Admin
- `GET api/admin/reportOfOrdersDailyByStatus/{date}/{status}` -> Returns report of daily orders, order by status.
- `GET api/admin/reportOfOrdersMonthlyByStatus/{date}/{status}` -> Returns report of monthly orders, order by status.
- `GET api/admin/reportOfOrdersYearlyByStatus/{date}/{status}` -> Returns report of yearly orders, order by status.
- `GET api/admin/HowManyOrderHasBeenProceedYearlyAndMonthly` -> Returns how many orders has beein proceed yearly and monthly.

### 📌 User
- `GET api/user/{userId}` -> Returns all user info.
- `POST api/user/register` -> User registiration.
- `DELETE api/user/{userId}` ->User can delete his/her account.
- `PUT api/user/{userId}/addToFavoriteList/{productId}` -> Products added to the user favorite list.
- `PUT api/user/{userId}/addBudget` -> User add money to his/her account.
- `PUT api/user/{userId}` ->  User update his/her account info.
- `PUT api/user/{userId}/userUpdatePassword` ->User update his/her password.

### 📌 Product
- `GET api/products` -> Returns all products.
- `GET api/products/orderByProductPriceAsc` -> Returns all products order by product price in ascending order.
- `GET api/products/categoryProducts/{categoryId}` ->Returns products in the desired category.
- `GET api/products/{productId}` ->Returns a product with details.
- `POST api/products/save` -> Save product by admin.
- `PUT api/products/update/{productId}` -> Update product info by given id.
- `DELETE api/products/delete/{productId}` ->Delete product by given id.

### 📌 Category
- `GET api/categories` ->Returns all categories.
- `GET api/categories/{name}` ->Returns category by given name.
- `GET api/categories/contains/{name}` -> Returns category which contains given word.
- `POST api/categories/create` -> Create a category.
- `PUT api/categories/update/{categoryId}` -> Update category by given id.
- `DELETE api/categories/delete/{categoryId}` -> Delete category by given id.

### 📌 Order
- `GET api/orders/user/{userId}/allOrders` -> Returns all orders of the user by given user id.
- `GET api/orders/user/{userId}/allOrdersOrderByDate` -> Returns all orders of the user by given user id, order by date.
- `GET api/orders/user/{userId}/allOrdersOrderByStatus` -> Returns all orders of the user by given user id, order by order status.
- `PUT api/orders/orderToDelivery/{orderId}/{userId}/` -> Converts an order to a delivery.
- `PUT api/orders/{orderId}/{userId}/cancelOrder` -> Cancel the user order by given user id and order id.

### 📌 OrderBasket
- `GET api/orderBasket/{userId}/{orderBasketId}` -> Returns all products of the user in the order basket by given user id and order basket id.
- `PUT api/orderBasket/addProduct/{userId}/{orderBasketId}/{productId}/{quantity}` ->Add product to order basket.
- `PUT api/orderBasket/removeProduct/{userId}/{orderBasketId}/{productId}/` -> Remove product to order basket.
- `POST api/orderBasket/{userId}/{orderBasketId}` -> Convert an order basket to a delivery.

### ☎️ İletişim

[Github Account](https://github.com/emirhanvaran0)<br>
[LinkedIn Account](https://www.linkedin.com/in/emirhanabaran0/)<br>
[Mail](emir.baran255@gmail.com)


## Author and Developed by
Emirhan Baran
