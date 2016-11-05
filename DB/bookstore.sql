-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Време на генериране: 
-- Версия на сървъра: 5.6.14
-- Версия на PHP: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- БД: `bookstore`
--

-- --------------------------------------------------------

--
-- Структура на таблица `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `catalog_number` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `author` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `name_of_publishing` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `foreign_literature` tinyint(1) NOT NULL,
  `numbers_of_book` int(11) DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=22 ;

--
-- Схема на данните от таблица `book`
--

INSERT INTO `book` (`book_id`, `catalog_number`, `title`, `author`, `price`, `name_of_publishing`, `foreign_literature`, `numbers_of_book`, `status`) VALUES
(1, 'ID235711', 'Thinking in Java', 'Bruce Eckel', 55.2, 'Pearson Education', 1, 10, 1),
(3, 'ID123456', 'Introduction to Programming with C#', 'Svetlin Nakov', 15.2, 'faber', 0, 12, 1),
(4, 'ID233456', 'Programming for .NET Framework', 'Svetlin Nakov', 13.56, '&#1060;&#1102;&#1090;', 1, 35, 1),
(5, 'ID1234562', '&#1051;&#1072;&#1074;&#1080;&#1085;&#1072;', 'Blaga Dimitrova', 12, 'Vacon', 1, 27, 1),
(8, 'ID123456789', 'tom sawyer and huckleberry finn', 'Mark Twain', 23.54, 'American publish', 1, 65, 1);

-- --------------------------------------------------------

--
-- Структура на таблица `enum_values`
--

CREATE TABLE IF NOT EXISTS `enum_values` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `sub_type` varchar(50) NOT NULL,
  `key_id` int(11) NOT NULL,
  `comment` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Схема на данните от таблица `enum_values`
--

INSERT INTO `enum_values` (`id`, `type`, `sub_type`, `key_id`, `comment`) VALUES
(1, 'seller', '', 0, 'user with rights like seller'),
(2, 'administrator', '', 0, 'user with rights like administrator');

-- --------------------------------------------------------

--
-- Структура на таблица `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `user_enum_type_id` int(1) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_users_to_enum_values` (`user_enum_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Схема на данните от таблица `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `last_name`, `user_enum_type_id`, `password`) VALUES
(1, 'plamen', 'terziev', 2, 'admin'),
(2, 'nedko', 'nikolov', 2, 'admin'),
(3, 'martin', 'spasov', 2, 'admin'),
(4, 'Test', 'Testov', 1, 'user');

--
-- Ограничения за дъмпнати таблици
--

--
-- Ограничения за таблица `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_users_to_enum_values` FOREIGN KEY (`user_enum_type_id`) REFERENCES `enum_values` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
