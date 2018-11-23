-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 23, 2018 at 12:37 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `futsaldb`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `contact_no` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` tinyint(1) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_group_id` int(11) DEFAULT NULL,
  `futsal_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `contact_no`, `email`, `full_name`, `password`, `status`, `user_name`, `user_group_id`, `futsal_id`) VALUES
(16, '9849581817', 'Sagar@gmail.com', 'Sagar Bhandari', 'e1d0e45a6b0595f14fda5f32b208edbfe379b4fdc4b6f97f440507989a09d44cbf65015601055ae538b9527e668b21ea000c1e94fc6810385dc71fdd34d16b8e', 1, 'sagar', 1, 531),
(17, '9849581817', 'surya@gmail.com', 'surya kc', 'e1d0e45a6b0595f14fda5f32b208edbfe379b4fdc4b6f97f440507989a09d44cbf65015601055ae538b9527e668b21ea000c1e94fc6810385dc71fdd34d16b8e', 1, 'surya', 2, 531),
(18, '9849581817', 'haria@gmail.com', 'hari kc', 'e1d0e45a6b0595f14fda5f32b208edbfe379b4fdc4b6f97f440507989a09d44cbf65015601055ae538b9527e668b21ea000c1e94fc6810385dc71fdd34d16b8e', 0, 'hari', 2, 531),
(20, '9849581817', 'nikunj@gmail.com', 'Nikunj Ghimire', 'e1d0e45a6b0595f14fda5f32b208edbfe379b4fdc4b6f97f440507989a09d44cbf65015601055ae538b9527e668b21ea000c1e94fc6810385dc71fdd34d16b8e', 1, 'nikunjey', 1, 536),
(21, '9849581817', 'ramesh@gmail.com', 'Ramesh kc', 'e1d0e45a6b0595f14fda5f32b208edbfe379b4fdc4b6f97f440507989a09d44cbf65015601055ae538b9527e668b21ea000c1e94fc6810385dc71fdd34d16b8e', 1, 'Ramesh', 2, 531),
(22, '9849581817', 'ramey@gmail.com', 'Ram bamjom', 'e1d0e45a6b0595f14fda5f32b208edbfe379b4fdc4b6f97f440507989a09d44cbf65015601055ae538b9527e668b21ea000c1e94fc6810385dc71fdd34d16b8e', 1, 'Ramey', 1, 540),
(23, '9849581817', 'kiran@gmail.com', 'kiran bamjom', 'e1d0e45a6b0595f14fda5f32b208edbfe379b4fdc4b6f97f440507989a09d44cbf65015601055ae538b9527e668b21ea000c1e94fc6810385dc71fdd34d16b8e', 1, 'kiran', 1, 544),
(24, '9849581817', 'Sagar12312@gmail.com', 'Sagar Bhandari', '9c523cc10c0823720a75e607c332bfc043dc56d1d2943afee831c6ddbab012e001a1d173833ca2096e1b2154493921645de96d0670510a4399497f0ea4ab8915', 1, 'rob123', 1, 547);

--
-- Triggers `account`
--
DELIMITER $$
CREATE TRIGGER `account_after_insert` AFTER INSERT ON `account` FOR EACH ROW INSERT INTO account_operation_jn (user_id,username,operation,db_user)
 VALUES (new.id,new.user_name,'INS','test')
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `account_operation_jn`
--

CREATE TABLE `account_operation_jn` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `operation` varchar(255) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `db_user` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account_operation_jn`
--

INSERT INTO `account_operation_jn` (`id`, `user_id`, `username`, `operation`, `date`, `db_user`) VALUES
(1, 3, 'Ram', 'INS', '2018-09-27 08:18:34', 'test'),
(2, 4, 'Johndoe', 'INS', '2018-09-30 16:12:08', 'test'),
(3, 5, 'Sumedon', 'INS', '2018-10-03 11:16:37', 'test'),
(4, 6, 'testuser', 'INS', '2018-10-07 11:53:37', 'test'),
(5, 7, 'testnormal', 'INS', '2018-10-07 11:54:23', 'test'),
(6, 8, 'gaida', 'INS', '2018-10-09 05:16:09', 'test'),
(7, 9, 'surya', 'INS', '2018-10-09 06:00:22', 'test'),
(8, 10, 'sagar', 'INS', '2018-10-09 12:14:42', 'test'),
(9, 11, 'sagar', 'INS', '2018-10-09 12:23:45', 'test'),
(10, 12, 'sagar', 'INS', '2018-10-09 12:32:14', 'test'),
(11, 13, 'sagar', 'INS', '2018-10-09 12:36:30', 'test'),
(12, 14, 'sagar', 'INS', '2018-10-09 12:38:03', 'test'),
(13, 15, 'sagar', 'INS', '2018-10-09 12:43:51', 'test'),
(14, 16, 'sagar', 'INS', '2018-10-09 13:31:55', 'test'),
(15, 17, 'surya', 'INS', '2018-10-09 15:10:29', 'test'),
(16, 18, 'hari', 'INS', '2018-10-11 17:24:13', 'test'),
(17, 19, 'suryaaaaaa', 'INS', '2018-10-12 15:12:09', 'test'),
(18, 20, 'nikunjey', 'INS', '2018-11-02 05:29:20', 'test'),
(19, 21, 'Ramesh', 'INS', '2018-11-02 05:49:48', 'test'),
(20, 22, 'Ramey', 'INS', '2018-11-17 08:17:14', 'test'),
(21, 23, 'kiran', 'INS', '2018-11-18 06:41:26', 'test'),
(22, 24, 'rob123', 'INS', '2018-11-18 06:54:21', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `address_id` int(11) NOT NULL,
  `country` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state_district` varchar(100) DEFAULT NULL,
  `street` varchar(100) DEFAULT NULL,
  `zip_postal_code` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`address_id`, `country`, `city`, `state_district`, `street`, `zip_postal_code`) VALUES
(527, 'Nepal', 'Tokha', 'Kathmandu', 'basindhara', 5144754),
(522, 'Nepal', 'Tokha', 'Kathmandu', 'basindhara', 5144754),
(524, 'Nepal', 'Tokha', 'Kathmandu', 'basindhara', 5144754),
(526, 'Nepal', 'Tokha', 'Kathmandu', 'basindhara', 5144754),
(528, 'Nepal', 'Tokha', 'Kathmandu', 'basindhara', 5144754),
(530, 'Nepal', 'Tokha', 'Kathmandu', 'basindhara', 5144754),
(532, 'Nepal', 'Tokha', 'Kathmandu', 'basindhara', 5144754),
(535, 'Nepal', 'Gongabu', 'Kathmandu', 'gairidhara', 5144754),
(539, 'Nepal', 'Samakhusi', 'Kathmandu', 'greenland', 5144754),
(543, 'Nepal', 'Samakhusi', 'Kathmandu', 'banasthali', 5144754),
(546, 'Nepal', 'Tokha', 'Kathmandu', 'basindhara', 5144754);

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `bill_id` int(11) NOT NULL,
  `billing_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `book_person_name` varchar(255) DEFAULT NULL,
  `play_duration` double NOT NULL,
  `play_start_time` varchar(255) DEFAULT NULL,
  `futsal_id` int(11) DEFAULT NULL,
  `ground_id` int(11) DEFAULT NULL,
  `addition_expense_amount` decimal(19,2) DEFAULT NULL,
  `play_amount` decimal(19,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`bill_id`, `billing_date`, `book_person_name`, `play_duration`, `play_start_time`, `futsal_id`, `ground_id`, `addition_expense_amount`, `play_amount`) VALUES
(1, '2018-11-15 05:12:17', 'John Doe', 2, '2018-11-15 14:30:00', 536, 537, NULL, NULL),
(2, '2018-11-15 05:12:17', 'John Doe', 2, '2018-11-15 16:30:00', 536, 537, NULL, NULL),
(3, '2018-11-15 05:13:32', 'John Doe', 2, '2018-11-15 16:30:00', 536, 537, NULL, NULL),
(4, '2018-11-15 05:26:01', 'John Doe', 2, '2018-11-15 18:30:00', 536, 537, NULL, NULL),
(13, '2018-11-23 10:19:58', 'Jonyy doye', 2, '2018-11-15 20:00:00', 536, 537, '80.00', '900.00'),
(14, '2018-11-23 11:10:07', 'Jonyy doye', 2, '2018-11-15 20:00:00', 536, 537, '80.00', '900.00'),
(15, '2018-11-23 11:16:05', 'Jonyy doye', 2, '2018-11-15 20:00:00', 536, 537, '80.00', '900.00');

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `booking_id` int(11) NOT NULL,
  `booking_code` varchar(50) DEFAULT NULL,
  `booking_date` varchar(10) DEFAULT NULL,
  `booking_duration` double NOT NULL,
  `booking_status` varchar(50) DEFAULT NULL,
  `booking_time` varchar(10) DEFAULT NULL,
  `contact_num` varchar(20) DEFAULT NULL,
  `futsal_id` int(11) DEFAULT NULL,
  `ground_id` int(11) DEFAULT NULL,
  `full_name` varchar(50) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`booking_id`, `booking_code`, `booking_date`, `booking_duration`, `booking_status`, `booking_time`, `contact_num`, `futsal_id`, `ground_id`, `full_name`) VALUES
(9, 'CF-1135', '2018-10-29', 1.5, 'PENDING', '19:30:00', '9849582827', 531, 533, 'Mansubh Bhandari'),
(4, 'CF-75', '2018-10-29', 1.5, 'PENDING', '10:30:00', '9849582827', 531, 533, 'Mansubh Bhandari'),
(8, 'CF-8946', '2018-10-30', 1.5, 'PENDING', '10:30:00', '9849582827', 531, 533, 'Mansubh Bhandari'),
(10, 'CF-7353', '2018-10-31', 1.5, 'CANCELLED', '11:30:00', '9849582827', 531, 533, 'Mansubh Bhandari'),
(11, 'CF-4897', '2018-11-01', 2, 'PENDING', '11:30:00', '9849582827', 531, 533, 'Mansubh Bhandari'),
(12, 'AF-9924', '2018-11-14', 2, 'PENDING', '10:00:00', '9849582827', 536, 537, 'Mansubh Bhandari'),
(13, 'AF-9814', '2018-11-14', 1.5, 'CONFIRMED', '15:00:00', '98495821231', 536, 537, 'Sagar Pandey'),
(14, 'AF-519', '2018-11-14', 1, 'PENDING', '11:00:00', '9849582123', 536, 538, 'Ram Sharma'),
(15, 'AF-9547', '2018-11-14', 2, 'PENDING', '17:00:00', '9849582123', 536, 538, 'Ram Sharma'),
(16, 'NF-7368', '2018-11-18', 2, 'PENDING', '17:00:00', '9849582123', 544, 545, 'Ram Sharma'),
(17, 'BF-8801', '2018-11-19', 1, 'CONFIRMED', '02:00:00', '123123123', 547, 548, 'adf');

-- --------------------------------------------------------

--
-- Table structure for table `discount`
--

CREATE TABLE `discount` (
  `id` int(11) NOT NULL,
  `discount_margin` int(11) NOT NULL,
  `discount_weekdays` tinyblob,
  `futsal_id` int(11) DEFAULT NULL,
  `ground_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discount`
--

INSERT INTO `discount` (`id`, `discount_margin`, `discount_weekdays`, `futsal_id`, `ground_id`) VALUES
(1, 10, 0xaced0005757200025b494dba602676eab2a502000078700000000400000002000000030000000400000005, 536, 537);

-- --------------------------------------------------------

--
-- Table structure for table `expense`
--

CREATE TABLE `expense` (
  `expense_id` int(11) NOT NULL,
  `particular` varchar(255) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `unit_price` double NOT NULL,
  `bill_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `expense`
--

INSERT INTO `expense` (`expense_id`, `particular`, `quantity`, `unit_price`, `bill_id`) VALUES
(1, 'Mineral water', 4, 20, 1),
(2, 'Surya Cigerrate', 5, 15, 1),
(3, 'Mineral water', 4, 20, 2),
(4, 'Surya Cigerrate', 2, 15, 2),
(5, 'Mineral water', 4, 20, 3),
(6, 'Surya Cigerrate', 2, 15, 3),
(7, 'Mineral water', 4, 20, 4),
(8, 'Surya Cigerrate', 2, 15, 4),
(9, 'Mineral water', 4, 20, 5),
(10, 'Wai Wai Noodles', 2, 20, 5),
(11, 'Mineral water', 4, 20, 6),
(12, 'Wai Wai Noodles', 2, 20, 6),
(13, 'Mineral water', 4, 20, 7),
(14, 'Wai Wai Noodles', 2, 20, 7),
(15, 'Mineral water', 4, 20, 8),
(16, 'Wai Wai Noodles', 2, 20, 8),
(17, 'Mineral water', 4, 20, 9),
(18, 'Wai Wai Noodles', 2, 20, 9),
(19, 'Mineral water', 4, 20, 10),
(20, 'Wai Wai Noodles', 2, 20, 10),
(21, 'Mineral water', 4, 20, 11),
(23, 'Mineral water', 4, 20, 12),
(24, 'Wai Wai Noodles', 2, 20, 12),
(25, 'Mineral water', 4, 20, 13),
(26, 'Mineral water', 4, 20, 14),
(27, 'Mineral water', 4, 20, 15);

-- --------------------------------------------------------

--
-- Table structure for table `futsal`
--

CREATE TABLE `futsal` (
  `futsal_id` int(11) NOT NULL,
  `contact_no` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `image_url` varchar(100) DEFAULT NULL,
  `mobile_no` varchar(100) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `futsal_name` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `futsal`
--

INSERT INTO `futsal` (`futsal_id`, `contact_no`, `email`, `image_url`, `mobile_no`, `address_id`, `futsal_name`) VALUES
(1001, 'test', 'test', 'test', 'test', 522, 'test'),
(531, '9849581817', 'chabel_futsal@gmail.com', 'http://testurl', '9845652312', 532, 'chabel Futsal'),
(536, '01-423507', 'abc_futsal@gmail.com', 'http://testurl', '9845652312', 535, 'Abc Futsal'),
(540, '01-423507', 'greenland_futsal@gmail.com', 'http://testurl', '9845652312', 539, 'Greeland Futsal'),
(544, '9849581817', 'kiran@gmail.com', 'test', 'test', 543, 'Nepal Futsal'),
(547, '9849581817', 'Sagar12312@gmail.com', 'test', 'test', 546, 'Basundhara Futsal');

--
-- Triggers `futsal`
--
DELIMITER $$
CREATE TRIGGER `futsal_after_insert` AFTER INSERT ON `futsal` FOR EACH ROW insert INTO futsal_operation_jn(futsal_id,futsal_name,operation) VALUES(
new.futsal_id,new.futsal_name,'INS')
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `futsal_operation_jn`
--

CREATE TABLE `futsal_operation_jn` (
  `id` int(11) NOT NULL,
  `futsal_id` int(11) NOT NULL,
  `futsal_name` varchar(255) NOT NULL,
  `operation` varchar(100) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `futsal_operation_jn`
--

INSERT INTO `futsal_operation_jn` (`id`, `futsal_id`, `futsal_name`, `operation`, `date`) VALUES
(1, 7, 'Gongabu FUtsal', 'INS', '2018-09-30 11:01:37'),
(2, 9, 'Samakhusi Futsal', 'INS', '2018-10-09 04:41:19'),
(3, 11, 'Samakhusi Futsal', 'INS', '2018-10-09 04:45:16'),
(4, 13, 'Samakhusi Futsal', 'INS', '2018-10-09 05:03:18'),
(5, 15, 'Greenland Futsal', 'INS', '2018-10-09 05:16:09'),
(6, -482, 'Greenland Futsal', 'INS', '2018-10-09 12:14:42'),
(7, 519, 'Basundhara Futsal', 'INS', '2018-10-09 12:23:45'),
(8, 521, 'Basundhara Futsal', 'INS', '2018-10-09 12:32:14'),
(9, 1001, 'test', 'INS', '2018-10-09 12:36:04'),
(10, 523, 'Basundhara Futsal', 'INS', '2018-10-09 12:36:30'),
(11, 1001, 'test', 'INS', '2018-10-09 12:37:29'),
(12, 525, 'Basundhara Futsal', 'INS', '2018-10-09 12:38:03'),
(13, 529, 'Basundhara Futsal', 'INS', '2018-10-09 12:43:51'),
(14, 531, 'Basundhara Futsal', 'INS', '2018-10-09 13:31:55'),
(15, 536, 'Basundhara Futsal', 'INS', '2018-11-02 05:29:20'),
(16, 540, 'Greenland Futsal', 'INS', '2018-11-17 08:17:14'),
(17, 544, 'Nepal Futsal', 'INS', '2018-11-18 06:41:26'),
(18, 547, 'Basundhara Futsal', 'INS', '2018-11-18 06:54:21');

-- --------------------------------------------------------

--
-- Table structure for table `ground`
--

CREATE TABLE `ground` (
  `ground_id` int(11) NOT NULL,
  `closing_hour` varchar(100) DEFAULT NULL,
  `ground_name` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `opening_hour` varchar(100) DEFAULT NULL,
  `unit_hour_price` decimal(19,2) DEFAULT NULL,
  `futsal_id` int(11) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ground`
--

INSERT INTO `ground` (`ground_id`, `closing_hour`, `ground_name`, `image`, `opening_hour`, `unit_hour_price`, `futsal_id`, `status`) VALUES
(533, '8:00 pm', 'Alpha', 'http://testurl', '10:00 am', '500.00', 531, 'ACTIVE'),
(537, '20:00:00', 'Alpha', 'http://testurl', '07:00:00', '500.00', 536, 'ACTIVE'),
(538, '22:00:00', 'Beta', 'http://testurl', '08:00:00', '500.00', 536, 'ACTIVE'),
(541, '22:00:00', 'A', 'http://testurl', '08:00:00', '500.00', 540, 'ACTIVE'),
(542, '20:00:00', 'B', 'http://testurl', '06:00:00', '500.00', 540, 'ACTIVE'),
(545, '20:00:00', 'A', 'http://testurl', '06:00:00', '500.00', 544, 'ACTIVE'),
(548, '20:00:00', 'A', 'http://testurl', '06:00:00', '500.00', 547, 'ACTIVE'),
(549, '08:00:00', 'sdfasd', 'adsfasd', '10:00:00', '500.00', 547, 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(550),
(550);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL,
  `login_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `login_token` varchar(255) DEFAULT NULL,
  `login_token_status` tinyint(1) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`login_id`, `login_time`, `login_token`, `login_token_status`, `user_name`, `account_id`) VALUES
(1, NULL, '0af73f8fce4f1d6c528a677dd44def2a', 1, 'Ram', 3),
(2, NULL, 'cd93ae15bc0acb8dd13d8da379d2b4a1', 1, 'Ram', 3),
(3, NULL, '4de7e9ccfc1c3ff40820db781b15b2ea', 1, 'Sumedon', 5),
(4, NULL, 'ccf9c1f1e506dbef16ad50ad3e6be7df', 1, 'Sumedon', 5),
(5, NULL, '95b9fe53f12393d865d7f5600679fcb2', 1, 'testnormal', 7),
(6, NULL, '41c3f64b26212ce0908aa87d3070d619', 1, 'testuser', 6),
(7, NULL, 'aa9f7b7ba90abe290084e784d6e310ea', 1, 'gaida', 8),
(8, NULL, '85992c42bad5c8e5b02006575822a6f3', 1, 'testuser', 6),
(9, NULL, '0d133a840fd0d7a181b60b9d04450a04', 1, 'sagar', 16),
(10, NULL, 'b3827ae0ae884cdb7d136ee5f0424f80', 1, 'surya', 17),
(11, NULL, '551481c606b11957b32827aef34edbf4', 1, 'surya', 17),
(12, NULL, '342da620d30589942e6aa6b545a65f27', 1, 'nikunjey', 20);

-- --------------------------------------------------------

--
-- Table structure for table `user_group`
--

CREATE TABLE `user_group` (
  `user_group_id` int(11) NOT NULL,
  `user_group_name` varchar(100) NOT NULL,
  `user_group_desc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_group`
--

INSERT INTO `user_group` (`user_group_id`, `user_group_name`, `user_group_desc`) VALUES
(1, 'OWNER', 'owner of futsal will be responsible for the user creation and account reports'),
(2, 'EMP', 'employee of futsal will be responsible for the everyday operational task');

-- --------------------------------------------------------

--
-- Table structure for table `user_menu`
--

CREATE TABLE `user_menu` (
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(100) NOT NULL,
  `owner_access_flag` tinyint(4) NOT NULL,
  `emp_access_falg` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_menu`
--

INSERT INTO `user_menu` (`menu_id`, `menu_name`, `owner_access_flag`, `emp_access_falg`) VALUES
(1, 'User Creation', 1, 0),
(2, 'Settings', 1, 1),
(3, 'Profile', 1, 1),
(4, 'Pricing_menu', 1, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKd9n9a5tj7ettp99tm9nkx3xg3` (`futsal_id`),
  ADD KEY `FK3x2mx59ygwck174e5xec8jaa5` (`user_group_id`);

--
-- Indexes for table `account_operation_jn`
--
ALTER TABLE `account_operation_jn`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`address_id`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`bill_id`),
  ADD KEY `FKri33j2e4sat10cvced3ptepno` (`futsal_id`),
  ADD KEY `FKiy2wkkvqypgq1qfhsp9yjhkfy` (`ground_id`);

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `FKp6hoe2xhtk3syg671kafsijd` (`futsal_id`),
  ADD KEY `FK9gygg2455povas0jvhvyj3cef` (`ground_id`);

--
-- Indexes for table `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmx6k9dei4q48xckye9cyg18wh` (`futsal_id`),
  ADD KEY `FK3trhn3a3ubx4ufk1dys6n73cp` (`ground_id`);

--
-- Indexes for table `expense`
--
ALTER TABLE `expense`
  ADD PRIMARY KEY (`expense_id`),
  ADD KEY `FKa6o36sk9smgorqwww3ey59cli` (`bill_id`);

--
-- Indexes for table `futsal`
--
ALTER TABLE `futsal`
  ADD PRIMARY KEY (`futsal_id`),
  ADD KEY `FKd9t041wo1ema4gl58j1uyi5bh` (`address_id`);

--
-- Indexes for table `futsal_operation_jn`
--
ALTER TABLE `futsal_operation_jn`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ground`
--
ALTER TABLE `ground`
  ADD PRIMARY KEY (`ground_id`),
  ADD KEY `FK8b6jk6fmt6dd4astw93ch4866` (`futsal_id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`login_id`),
  ADD KEY `FKk88164dmn15688pqsi8xi6tji` (`account_id`);

--
-- Indexes for table `user_group`
--
ALTER TABLE `user_group`
  ADD PRIMARY KEY (`user_group_id`);

--
-- Indexes for table `user_menu`
--
ALTER TABLE `user_menu`
  ADD PRIMARY KEY (`menu_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `account_operation_jn`
--
ALTER TABLE `account_operation_jn`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `booking_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `discount`
--
ALTER TABLE `discount`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `expense`
--
ALTER TABLE `expense`
  MODIFY `expense_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `futsal_operation_jn`
--
ALTER TABLE `futsal_operation_jn`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `login_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `user_group`
--
ALTER TABLE `user_group`
  MODIFY `user_group_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user_menu`
--
ALTER TABLE `user_menu`
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
