-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2019 at 10:55 AM
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
  `addition_expense_amount` decimal(19,2) DEFAULT NULL,
  `play_amount` decimal(19,2) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `discount_margin` int(11) NOT NULL,
  `discountable_amount` decimal(19,2) DEFAULT NULL,
  `total_amount` decimal(19,2) DEFAULT NULL,
  `game_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`bill_id`, `billing_date`, `addition_expense_amount`, `play_amount`, `customer_name`, `discount_margin`, `discountable_amount`, `total_amount`, `game_id`) VALUES
(11, '2019-01-08 10:13:09', '130.00', '1000.00', 'Kushal Bhandari', 25, '250.00', '880.00', 11);

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
  `futsal_id` int(11) DEFAULT NULL,
  `ground_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`booking_id`, `booking_code`, `booking_date`, `booking_duration`, `booking_status`, `booking_time`, `futsal_id`, `ground_id`, `customer_id`) VALUES
(18, 'BF-2225', '2018-12-27', 2, 'GAME_STARTED', '16:30', 547, 548, 4),
(19, 'BF-4942', '2019-01-02', 2, 'PENDING', '12:30', 547, 548, 5),
(20, 'BF-5870', '2019-01-02', 2, 'PENDING', '16:30', 547, 548, 5),
(21, 'BF-3719', '2019-01-03', 1, 'PENDING', '10:30', 547, 548, 5),
(22, 'BF-4213', '2019-01-03', 1, 'PENDING', '12:30', 547, 548, 5),
(23, 'BF-2297', '2019-01-03', 1, 'CONFIRMED', '17:30', 547, 548, 5);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact_number` varchar(15) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(50) NOT NULL,
  `futsal_id` int(11) DEFAULT NULL,
  `customer_play_count` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `address`, `contact_number`, `email`, `full_name`, `futsal_id`, `customer_play_count`) VALUES
(1, '7ytasdf banesowrkd', '987465', 'basf@gmail.com', 'Ram Thapa', NULL, 0),
(2, '7ytasdf banesowrkd', '987465', 'basf@gmail.com', 'Ram Thapa', 547, 0),
(3, '7ytasdf banesowrkd', '9849581817', 'basf@gmail.com', 'Hari Kishan', 547, 2),
(4, NULL, '9847281712', NULL, 'Ram sharan Mahat', 547, 1),
(5, NULL, '9847564125', NULL, 'Pradip Sapkota', 547, 6),
(6, 'potato', '984958784', 'basf@gmail.com', 'Ravi Krishan', 547, 1),
(7, 'Samakhsi', '9849581818', 'kushlkf@gmail.com', 'Kushal Bhandari', 547, 1);

-- --------------------------------------------------------

--
-- Table structure for table `discount_detail`
--

CREATE TABLE `discount_detail` (
  `discount_detail_id` int(11) NOT NULL,
  `margin` int(11) NOT NULL,
  `time_from` varchar(255) DEFAULT NULL,
  `time_to` varchar(255) DEFAULT NULL,
  `discount_master_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discount_detail`
--

INSERT INTO `discount_detail` (`discount_detail_id`, `margin`, `time_from`, `time_to`, `discount_master_id`) VALUES
(9, 15, '06:00', '12:00', 15),
(10, 25, '13:00', '20:00', 15);

-- --------------------------------------------------------

--
-- Table structure for table `discount_master`
--

CREATE TABLE `discount_master` (
  `discount_master_id` int(11) NOT NULL,
  `discount_name` varchar(255) DEFAULT NULL,
  `insert_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `remarks` varchar(255) DEFAULT NULL,
  `futsal_id` int(11) DEFAULT NULL,
  `ground_id` int(11) DEFAULT NULL,
  `date_from` varchar(255) DEFAULT NULL,
  `date_to` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discount_master`
--

INSERT INTO `discount_master` (`discount_master_id`, `discount_name`, `insert_time`, `remarks`, `futsal_id`, `ground_id`, `date_from`, `date_to`, `status`) VALUES
(15, 'Saturday Discount', '2019-01-06 06:47:16', 'happy new year', 547, 548, '2019-01-06', '2019-01-10', 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `expense`
--

CREATE TABLE `expense` (
  `expense_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `game_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `expense`
--

INSERT INTO `expense` (`expense_id`, `quantity`, `game_id`, `menu_id`, `amount`) VALUES
(5, 5, 10, 1, '100.00'),
(6, 2, 10, 2, '50.00'),
(7, 4, 11, 1, '80.00'),
(8, 2, 11, 4, '50.00');

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
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `game_id` int(11) NOT NULL,
  `entry_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `game_status` varchar(20) DEFAULT NULL,
  `game_type` varchar(20) DEFAULT NULL,
  `play_date` varchar(10) DEFAULT NULL,
  `play_duration` double NOT NULL,
  `play_start_time` varchar(10) DEFAULT NULL,
  `futsal_id` int(11) DEFAULT NULL,
  `ground_id` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`game_id`, `entry_date`, `game_status`, `game_type`, `play_date`, `play_duration`, `play_start_time`, `futsal_id`, `ground_id`, `customer_id`) VALUES
(5, '2018-12-26 09:50:14', 'STARTED', 'DIRECT_ENTRY', '2018-12-26', 1, '16:30', 547, 548, 1),
(6, '2018-12-26 09:52:37', 'STARTED', 'DIRECT_ENTRY', '2018-12-26', 1, '17:30', 547, 548, 2),
(7, '2018-12-27 08:31:26', 'STARTED', 'DIRECT_ENTRY', '2018-12-27', 1.5, '17:30', 547, 548, 3),
(8, '2018-12-27 08:33:34', 'STARTED', 'DIRECT_ENTRY', '2018-12-27', 1.5, '17:30', 547, 548, 3),
(9, '2018-12-27 11:06:32', 'STARTED', 'BOOKED', '2018-12-27', 2, '16:30', 547, 548, 4),
(10, '2019-01-03 05:43:15', 'STARTED', 'DIRECT_ENTRY', '2019-01-03', 1.5, '12:30', 547, 548, 6),
(11, '2019-01-06 09:20:20', 'STARTED', 'DIRECT_ENTRY', '2019-01-06', 2, '17:00', 547, 548, 7);

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
(548, '20:00', 'A', 'http://testurl', '06:00', '500.00', 547, 'ACTIVE'),
(549, '20:00', 'B', 'adsfasd', '10:00', '500.00', 547, 'ACTIVE');

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
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `menu_item` varchar(255) DEFAULT NULL,
  `unit_price` decimal(19,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`menu_id`, `description`, `menu_item`, `unit_price`) VALUES
(1, 'Just a water', 'Mineral Water', '20.00'),
(2, 'Just a juice', 'Real juice', '25.00'),
(3, 'Just a Cigarrate', 'Surya Cigarrate', '15.00'),
(4, 'Juice of suntala', 'Real juice', '25.00');

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
  ADD KEY `FKmhd4f5hyrbnrl7bqc17cschwh` (`game_id`);

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `FKp6hoe2xhtk3syg671kafsijd` (`futsal_id`),
  ADD KEY `FK9gygg2455povas0jvhvyj3cef` (`ground_id`),
  ADD KEY `FKlnnelfsha11xmo2ndjq66fvro` (`customer_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`),
  ADD KEY `FKm8jbkauqw16qi4prck54aad3l` (`futsal_id`);

--
-- Indexes for table `discount_detail`
--
ALTER TABLE `discount_detail`
  ADD PRIMARY KEY (`discount_detail_id`),
  ADD KEY `FKlvxt110j1q2winyp6okxvuspv` (`discount_master_id`);

--
-- Indexes for table `discount_master`
--
ALTER TABLE `discount_master`
  ADD PRIMARY KEY (`discount_master_id`),
  ADD KEY `FKf3klc39rvq5ipin5y9oig118j` (`futsal_id`),
  ADD KEY `FKcn2b3fypnafd1nksegqu71abs` (`ground_id`);

--
-- Indexes for table `expense`
--
ALTER TABLE `expense`
  ADD PRIMARY KEY (`expense_id`),
  ADD KEY `FKqq5ucmdd5yaduskegbk8wnbkp` (`game_id`),
  ADD KEY `FKk3dn010ja4d9p2plb89fi690v` (`menu_id`);

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
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`game_id`),
  ADD KEY `FKep4rk1ihtf6ogginypa0j8k2w` (`customer_id`),
  ADD KEY `FKs5ajykg9m7421knv9buhuk81h` (`futsal_id`),
  ADD KEY `FKo665l8pw800pnpgxgm5ocl01i` (`ground_id`);

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
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`menu_id`);

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
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `booking_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `discount_detail`
--
ALTER TABLE `discount_detail`
  MODIFY `discount_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `discount_master`
--
ALTER TABLE `discount_master`
  MODIFY `discount_master_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `expense`
--
ALTER TABLE `expense`
  MODIFY `expense_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `futsal_operation_jn`
--
ALTER TABLE `futsal_operation_jn`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `game`
--
ALTER TABLE `game`
  MODIFY `game_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `login_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
