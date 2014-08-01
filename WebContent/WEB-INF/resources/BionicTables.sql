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

CREATE DATABASE IF NOT EXISTS adonin CHARACTER SET = utf8 COLLATE = utf8_bin;
USE adonin;

-- --------------------------------------------------------

--
-- Структура таблицы `Messages`
--

CREATE TABLE IF NOT EXISTS `Messages` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Message id',
  `sender_id` int(11) NOT NULL COMMENT 'Sender user id',
  `receiver_id` int(11) NOT NULL COMMENT 'Receiver user id',
  `text` varchar(1000) CHARACTER SET latin1 NOT NULL COMMENT 'Text of message',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Read/unread',
  `timestamp` datetime NOT NULL COMMENT 'Send timestamp',
  PRIMARY KEY (`message_id`),
  KEY `sender_id` (`sender_id`),
  KEY `receiver_id` (`receiver_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Структура таблицы `Relations`
--

CREATE TABLE IF NOT EXISTS `Relations` (
  `relation_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Relation id',
  `from_id` int(11) NOT NULL COMMENT 'user id "from"-relation',
  `to_id` int(11) NOT NULL COMMENT 'user id "to"-relation',
  PRIMARY KEY (`relation_id`),
  KEY `from_id` (`from_id`),
  KEY `to_id` (`to_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users relations ' AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Структура таблицы `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user id',
  `username` varchar(30) COLLATE utf8_bin NOT NULL COMMENT 'login',
  `password` varchar(30) COLLATE utf8_bin NOT NULL COMMENT 'password',
  `email` varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'email',
  `type_id` int(11) NOT NULL DEFAULT '1' COMMENT 'user type',
  `firstname` varchar(30) COLLATE utf8_bin NOT NULL COMMENT 'first name',
  `lastname` varchar(30) COLLATE utf8_bin NOT NULL COMMENT 'last name',
  `activity` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'user activity',
  `info` varchar(1000) COLLATE utf8_bin NOT NULL COMMENT 'user info',
  `photo` varchar(256) COLLATE utf8_bin NOT NULL COMMENT 'user photo',
  PRIMARY KEY (`user_id`),
  KEY `fk_UserTypes` (`type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users' AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Структура таблицы `UserTypes`
--

CREATE TABLE IF NOT EXISTS `UserTypes` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user type id',
  `type` varchar(30) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Types of users' AUTO_INCREMENT=3 ;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `Users`
--
ALTER TABLE `Users`
  ADD CONSTRAINT `fk_UserTypes` FOREIGN KEY (`type_id`) REFERENCES `UserTypes` (`type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
