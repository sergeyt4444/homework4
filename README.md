# homework4

Домашняя работа №4 для NC Java Autumn School 

Открыть сайт можно по адресу:  
http://localhost:7777/input  
или  
http://localhost:7777/query  
Там есть что-то вроде навигации, до всех страничек вроде бы можно добраться оттуда.  

http://localhost:7777/input - форма для записи данных о пользователе, там же можно подгрузить пользователя файлом  
http://localhost:7777/query - поиск данных о пользователе по имени и фамилии.
Чтобы отправить письмо пользователю, нужно его либо ввести (файлом или через форму в http://localhost:7777/input), либо найти (через http://localhost:7777/query). Дальше нужно нажать кнопку "Email this person", заполнить формочку, письмо отправится на почту этого пользователя.  

Формат файла, содержащего пользователя:  

<Фамилия>  
<Имя>  
<Отчество>  
<Возраст>  
<Зарплата>  
<Email>  
<Место работы>  

Каждое поле пишется отдельной строчкой, тут же лежит пример файла с пользователем в example.txt

`spring.datasource.url=jdbc:postgresql://localhost:5432/persondb` (Можно будет поменять в application.properties или создать БД persondb у себя на постгресе)

Запрос для создания БД + добавления пробного пользователя:

```
CREATE TABLE person (
	id serial PRIMARY KEY,
	fname varchar(50) not null,
	lname varchar(50) not null,
	patron varchar(50) not null,
	age integer,
	email varchar(50),
	salary real,
	workplace varchar(50)
);

INSERT INTO person (fname, lname,patron,age,email,salary,workplace)
values
('Vasya', 'Pupkin', 'Olegovich', 22, 'pupkin@mail.ru', 100.1, 'Sberbank');
```

Чтобы потестить работу с почтой, не залезая в application.properties, можно воспользоваться этими логином и паролем к аккаунту в google:  
testmail4444000@gmail.com  
Q!werty123  

Чураков Сергей