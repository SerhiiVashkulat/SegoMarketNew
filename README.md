Бізнес вимоги:

Інтернет магазин СегоМаркет.
1.1 Кліеєнт може переглядати товари магазину;

1.2 Кліеєнт повинен мати можливість реєстрації та авторизації;

1.3 Клієнт може змінювати пароль або email;

1.4 Клієнт може додавати та видаляти(усі чи по 1 ) товари з кошику;

1.5 Клієнт може дивитись іформацію кошику(кількість товару, загальну сумму);

1.6 Клієнт може зробити замовлення за адресою ;

1.7 Клієнт  може переглядати статус замовленя;

2.1 Адміністратор магазину повинен додавати Менеджерів магазину;

3.1 Менеджер повинен додавати товари до магазину;

3.2 Менеджер може змінювати статус заказу;

Модель данних

User:
 id, username, password, email, role, active, bucket;

Role: 
 ADMIN, MANAGER, CLIENT;

Bucket:
 id, user, products;

Product:
 id, title, price;

OrderStatus:
 NEW, APPROVED, CANCELED, PAID, CLOSED;

Order:
 id, created, updated, user, details, summ, address, status;

OrderDetails:
 id, order, amount, price, product

Функціональні вимоги

1.1 Перегляд товару

Доступ мають усі користувачі

GetMapping http://localhost:8080/api/v1/products  

1.2 Реєстрація

Доступ мають усі користувачі

PostMapping
{
"name":"user",
"password":"12345678",
"email":"example@mail.ua",
"matchingPassword" : "12345678"
}
Валідація данних

username >= 4 символа, <= 15 символів, дозволені символи A-Za-z\d._-

password >=8 символа, <=100, дозволені символи A-Za-z\d.,:;_\-?!+=/'\\"*\[\]{}()

Успіх

{
"message": " Token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjc5OTIzMDA4LCJleHAiOjI0MTkwODkxMzI1NzI2NDB9.RfM7vcvbR0Il5oZ_9b6q4yDQk0Hv1115c66rqBx6gfg",

"httpStatus": "ACCEPTED"
} Status 200 Ok

Помилка 

"message": "Nickname or Email is busy" Status 226 Iam Used

"message": "Password not valid. Nick name not valid." Status 400 Bad Request


Авторизація 

PostMapping
{
"name":"user1",
"password":"12345678"
}
Валідація данних

Не може бути пустим логін чи пароль

Успіх

{
"message": " Token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjc5OTIzNjI2LCJleHAiOjI0MTkwOTAwMjE3NjExMjB9.tPtrZ1SJB5u0126OaYOw1y8u7qPoS1cCc7gD249kMd0",
"httpStatus": "OK"
} Status 200 OK

Помилка

"message": "Password must not be empty. Name must not be empty" Status  400 Bad Request

"message": "Bad credentials" Status 401 Unauthorized

1.3 Зміна паролю чи email

PutMapping

Token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjgwNTA5MTAyLCJleHAiOjI0MTk5MzMxMDc1MjY1NjB9.8ML_I9RpHv4TBPxQ9QM3xmBYMwp5XEOdwzkQc94laqk
{
"email":"example2@mail.com",
"password":"123456789"
}
Валідація данних

@Email

password >=8 символа, <=100, дозволені символи A-Za-z\d.,:;_\-?!+=/'\\"*\[\]{}()

Role - CLIENT

Email чи password повинні бути передані

Успіх

"massage": "You have changed your profile" Status 200 OK

Помилка

"message": "Email or password not valid" Status 400 Bad Request

"message": "Email must be different to change it" Status 400 Bad Request

"message": "Need credential for update your profile" Status 400 Bad Request

1.4 Додавання чи видалення з кошику

GetMapping

Додовання по ID 

Token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjgwNTA5MTAyLCJleHAiOjI0MTk5MzMxMDc1MjY1NjB9.8ML_I9RpHv4TBPxQ9QM3xmBYMwp5XEOdwzkQc94laqk

http://localhost:8080/api/v1/products/1

Валідація данних

Перевірка id товару

Успіх 

"massage": "Add product to bucket user" Status 200 OK

Помилка 

"message": "Unable to find product" Status 400 Bad Request

Видалення товару по ID

DeleteMapping

Token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjgwNTA5MTAyLCJleHAiOjI0MTk5MzMxMDc1MjY1NjB9.8ML_I9RpHv4TBPxQ9QM3xmBYMwp5XEOdwzkQc94laqk

http://localhost:8080/api/v1/bucket/1

Валідація данних

Перевірка id товару чи присутній у кошику клієнта

Успіх

"massage": "Delete product from bucket { Імя клієнта }" Status 200 OK

Помилка 

"message": "Product not found" Status 400 Bad Request

"message": "Bucket is empty" 400 Bad Request

Видалення усього товару

Token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjgwNTA5MTAyLCJleHAiOjI0MTk5MzMxMDc1MjY1NjB9.8ML_I9RpHv4TBPxQ9QM3xmBYMwp5XEOdwzkQc94laqk

http://localhost:8080/api/v1/bucket/all

Успіх

"massage": "Delete all product from bucket { Імя клієнта }" Status 200 OK

Помилка

"message": "Bucket is empty" Status 400 Bad Request

1.5 Інформація кошику клієнта

http://localhost:8080/api/v1/bucket

GetMapping

Token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjgwNTA5MTAyLCJleHAiOjI0MTk5MzMxMDc1MjY1NjB9.8ML_I9RpHv4TBPxQ9QM3xmBYMwp5XEOdwzkQc94laqk

Успіх

{
"amountProducts": 1,
"sum": 600.0,
"bucketDetails": [
{
"title": "cheese",
"productId": 2,
"price": 300.0,
"amount": 2.0,
"sum": 600.0
}
]
}

Помилка 

"message": "Bucket is empty" Status 400 Bad Request

1.6 Підтвердження замовлення за адресою 

http://localhost:8080/api/v1/bucket

Kyiv

PostMapping

Token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjgwNTA5MTAyLCJleHAiOjI0MTk5MzMxMDc1MjY1NjB9.8ML_I9RpHv4TBPxQ9QM3xmBYMwp5XEOdwzkQc94laqk

Успіх 

"massage": "Your order has been accepted, please wait for a call from the manager" Status 202 Accepted

Помилка

"message": "Bucket is empty" Status 400 Bad Request

1.7 Перевірка статусу замовлення

GetMapping

http://localhost:8080/api/v1/order

http://localhost:8080/api/v1/order/{id}

Token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjgwNTA5MTAyLCJleHAiOjI0MTk5MzMxMDc1MjY1NjB9.8ML_I9RpHv4TBPxQ9QM3xmBYMwp5XEOdwzkQc94laqk

Валідація

Клієнт може дивитися усі свої замовлення чи дивитися по ID

Успіх

{
"id": 1,
"status": "NEW",
"address": "Kyiv",
"updated": "2023-04-03T14:29:18.810721",
"sum": 500.00,
"products": [
{
"product": {
"title": "bear",
"price": 500.0
},
"amount": 1.00
}
]
}

Помилка по ID

"message": "Order not found " Status 400 Bad Request

2.1 Додавання Менеджера до магазину

PostMapping

Token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyTWFuYWdlciIsImlhdCI6MTY4MDUyOTE0NiwiZXhwIjoyNDE5OTYxOTcwODM5MDQwfQ.FkJDAWSGILYrfiRrggLLPsjiY1S8jF9fnJy9WEAhph4

http://localhost:8080/api/v1/users

"name":"userManager",
"password":"12345679",
"email":"example2@mail.ua"

Валідація данних

Тільки Адміністратор може додавати менеджера до магазину, перевірка імя та email чи э такий у базі

@Email

password >=8 символа, <=100, дозволені символи A-Za-z\d.,:;_\-?!+=/'\\"*\[\]{}()

Успіх

"massage": "Add manager userManager to shop " Status 201 Created

Помилка

"message": "Доступ заборонений" Status 400 Bad Request

"message": "Nickname or Email is busy" Status 226 Im Used

"message": "Email or password not valid" Status 400 Bad Request

3.1 Додавання товару до магазину

PostMapping
Token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyIiwiaWF0IjoxNjgwNTI3MzE4LCJleHAiOjI0MTk5NTkzMzgyMzI0ODB9.4TgfV-qN1O8fuSD1YHTdOOsSkpiqzsUJusZFhSgvbzA

http://localhost:8080/api/v1/products

"title":"tea",
"price":"400"

Валідація данних

Товар може додавати тільки Менеджер, перевірка чи є товар вже у магазині 

Успіх

"massage": "Adding product tea to shop." Status 200 OK

Помилка 

"message": "Доступ заборонений"  Status 400 Bad Request

"message": "Product is already adding" Status 400 Bad Request

3.2 Зміна статусу заказу

PutMapping

Token : eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYW5hZ2VyIiwiaWF0IjoxNjgwNTI3MzE4LCJleHAiOjI0MTk5NTkzMzgyMzI0ODB9.4TgfV-qN1O8fuSD1YHTdOOsSkpiqzsUJusZFhSgvbzA

http://localhost:8080/api/v1/order/1

"PAID"

Валідація данних

Тільки менеджер може змінювати статус заказу, перевірка по ID чи є такий заказ

Успіх 

"massage": "Order status number 2 change to PAID" Status 200 OК

Помилка

"message": "Order not found " Status 400 Bad Request

"message": "Доступ заборонений" Status 400 Bad Request
