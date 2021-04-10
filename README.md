# Кинотеатр

## Оглавление

- О проекте
- Структура проекта
- Для разработчиков
- Автор

### О проекте

Консольное приложение "Кинотеатр". 

При старте приложения можно зарегистрироваться или залогиниться. Представлено три уровня пользователя: админ, менеджер, пользователь.

Простой пользователь может:
- просматривать мероприятия (фильмы) 
- покупать и возвращать билеты
- просматривать купленные билеты
- сохранять билеты в файл

Менеджер может:
- просматривать мероприятия (фильмы) 
- редактировать мероприятия (фильмы)
- покупать и возвращать билеты определенного пользователя
- сохранять билеты в файл;

Админ может:
- просматривать мероприятия (фильмы) 
- просматривать пользователей
- удалять и изменять пользователей 
- удалять и изменять мероприятия;

### Структура проекта
- Java 15
- Maven 
- mysql-connector-java 8.0.23
- log4j 1.2.17

### Для разработчиков
Откройте проект в IDE.

Добавьте JAVA SDK 15 в Project Structure.

Установите MySQL, если не установлен.

В src/main/java/org/cinema/service/dbservice/DBConnection укажите свой логин/пароль для MySQLWorkbench, чтобы установить соединение.

Скачать [базу](https://drive.google.com/file/d/1D6ucqAJfd4t2G2trSacLjVfGu9Bj2RZt/view?usp=sharing) и сделать импорт в MySQL.

Запустите проект.

логин/пароль администратор: admin/admin

логин/пароль менеджер: manager/manager

### Автор

[Kazak Vadim](https://github.com/1kazakvadim)
