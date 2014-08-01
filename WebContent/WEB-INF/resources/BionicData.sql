-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Фев 25 2014 г., 10:20
-- Версия сервера: 5.5.35
-- Версия PHP: 5.3.10-1ubuntu3.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `adonin`
--

USE adonin;

--
-- Дамп данных таблицы `Messages`
--

INSERT INTO `Messages` (`message_id`, `sender_id`, `receiver_id`, `text`, `status`, `timestamp`) VALUES
(1, 1, 2, '1 to 2', 0, '2014-01-19 03:14:07'),
(2, 2, 1, '2 to 1', 0, '2014-01-19 03:15:07'),
(3, 3, 1, '3 to 1', 0, '2014-01-19 04:15:07'),
(4, 1, 3, '3 to 2', 0, '2014-01-19 04:16:07');

--
-- Дамп данных таблицы `Relations`
--

INSERT INTO `Relations` (`relation_id`, `from_id`, `to_id`) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 1, 3),
(4, 3, 1);

--
-- Дамп данных таблицы `UserTypes`
--

INSERT INTO `UserTypes` (`type_id`, `type`) VALUES
(1, 'User'),
(2, 'Administrator');

--
-- Дамп данных таблицы `Users`
--

INSERT INTO `Users` (`user_id`, `username`, `password`, `email`, `type_id`, `firstname`, `lastname`, `activity`, `info`, `photo`) VALUES
(1, 'ivanov', 'ivanov', 'ivanov@bionic.ua', 2, 'Иван', 'Иванов', 0, 'I am Ivanov', ''),
(2, 'petrov', 'petrov', 'petrov@bionic.ua', 1, 'Петр', 'Петров', 0, 'I am Petrov', ''),
(3, 'sidorov', 'sidorov', 'sidorov@bionic.ua', 1, 'Сидор', 'Сидоров', 0, 'I am Sidorov', '');



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
