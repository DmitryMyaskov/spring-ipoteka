# spring-ipoteka
Микросервис на Spring по подаче заявки на предоставление ипотеки. 
База данных H2.
В контроллере есть 3 запроса: 
1. Получение всех заявок:     /customers 
2. Получение заявки по id:     /customer/{id}
3. Создание заявки, передача параметров осуществляется в json формате через Postman:   /spring_war/customer <br>
Валидация контрольной суммы инн и возраста осуществляется через созданые аннотации.<br>
Из условия, что инн есть только у юр. лица, поле faceItem имеет два значения PhisFace и IndivFace.<br>
При изменении на PhisFace, поле itn становиться равным 0.<br>  

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

create table user(<br>
Id long auto_increment,<br>
First_Name varchar(30),<br>
Second_Name varchar(30),<br>
Last_Name varchar(30),<br>
Date_Of_Birth date,<br>
primary key (Id)<br>
);<br>


create table seller(<br>
Id long auto_increment,<br>
User_Id long,<br>
Face_Item varchar(30),<br>
ITN long,<br>
primary key (Id),<br>
foreign key (User_Id) references user(Id)<br>
);<br>

create table customer(<br>
Id long auto_increment,<br>
User_Id long,<br>
Credit_Amount varchar(30),<br>
Credit_Term int,<br>
Mortgage_Object varchar(30),<br>
Total_Purchase_Value int,<br>
Seller_Id long,<br>
primary key (Id),<br>
foreign key (Seller_Id) references seller(Id),<br>
foreign key (User_Id) references user(Id)<br>
);<br>
