# spring-ipoteka
Микросервис на Spring по подаче заявки на предоставление ипотеки. 
База данных H2.
В контроллере есть 3 запроса: 
1. Получение всех заявок http://localhost:8080/spring_war/customers 
2. Получение заявки по id http://localhost:8080/spring_war/customer/{id}
3. Создание заявки, передача параметров осуществляется в json формате через Postman  http://localhost:8080/spring_war/customer 
Валидация контрольной суммы инн и возраста осуществляется через созданые аннотации.
Из условия, что инн есть только у юр. лица, поле faceItem имеет два значения PhisFace и IndivFace. При изменении на PhisFace, поле itn становиться равным 0.  

Входные данные 

Корректные:

{"sellerId":{"userId":{"dateOfBirth":"1955-12-16",
"firstName":"Danil",
"lastName":"Alekseevich",
"secondName":"Korshov"},
"itn":500100732259,
"faceItem":"IndivFace"},
"userId":{"dateOfBirth":"1994-12-16",
"firstName":"Dima",
"lastName":"Alekseevich",
"secondName":"Myaskov"},
"creditAmount":10000,
"creditTerm":30,
"mortgageObject":"flat",
"totalPurchaseValue":4000}

Неправильный инн:

{"sellerId":{"userId":{"dateOfBirth":"1955-12-16",
"firstName":"Danil",
"lastName":"Alekseevich",
"secondName":"Korshov"},
"itn":500100732255,
"faceItem":"IndivFace"},     
"userId":{"dateOfBirth":"1994-12-16",
"firstName":"Dima",
"lastName":"Alekseevich",
"secondName":"Myaskov"},
"creditAmount":10000,
"creditTerm":30,
"mortgageObject":"flat",
"totalPurchaseValue":4000}

3 Таблицы:

create table user(
Id long auto_increment,
First_Name varchar(30),
Second_Name varchar(30),
Last_Name varchar(30),
Date_Of_Birth date,
primary key (Id)
);


create table seller(
Id long auto_increment,
User_Id long,
Face_Item varchar(30),
ITN long,

primary key (Id),
foreign key (User_Id) references user(Id)
);

create table customer(
Id long auto_increment,
User_Id long,
Credit_Amount varchar(30),
Credit_Term int,
Mortgage_Object varchar(30),
Total_Purchase_Value int,
Seller_Id long,
primary key (Id),
foreign key (Seller_Id) references seller(Id),
foreign key (User_Id) references user(Id)
);
