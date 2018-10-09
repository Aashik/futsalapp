-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 09, 2018 at 05:13 PM
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
(3, '9849581817', 'bhandariram@gmail.com', 'Ram Bhandari', '50c9c33f8cbadf17c88219b23b7133c64834ef37f9cbbab0b770f4e73af1518f99e3b9f089cea7e4744c95811b9270bebddd85e525246eb084f7c7c117531d2c', 1, 'Ram', NULL, NULL),
(4, '9849581817', 'bhandarijohn@gmail.com', 'Ram Bhandari', '50c9c33f8cbadf17c88219b23b7133c64834ef37f9cbbab0b770f4e73af1518f99e3b9f089cea7e4744c95811b9270bebddd85e525246eb084f7c7c117531d2c', 1, 'Johndoe', NULL, NULL),
(5, '9849581817', 'donsume@gmail.com', 'sume Don', 'd6e1ba9bcde4b70f2bc269e76c2a2bc322143d3703b1b790f3421deb9cef4f2d70ef9df1d8948462d98df972d468008670177cc2adcead3f2de384ea83fffb38', 1, 'Sumedon', NULL, NULL),
(6, '9849581817', 'test@gmail.com', 'Test Doe', 'e7b1c9017b8df8e359decce402f4af9320c1a2f16c8c87004c213659895366c0d01375659792a572d0b59186a9e25967512f60c8b05479db600c6d3cbd1741b8', 1, 'testuser', 1, NULL),
(7, '9849581817', 'normal@gmail.com', 'Test Doe', 'e7b1c9017b8df8e359decce402f4af9320c1a2f16c8c87004c213659895366c0d01375659792a572d0b59186a9e25967512f60c8b05479db600c6d3cbd1741b8', 1, 'testnormal', 2, NULL),
(16, '9849581817', 'Sagar@gmail.com', 'Sagar Bhandari', 'e1d0e45a6b0595f14fda5f32b208edbfe379b4fdc4b6f97f440507989a09d44cbf65015601055ae538b9527e668b21ea000c1e94fc6810385dc71fdd34d16b8e', 1, 'sagar', 1, 531),
(17, '9849581817', 'surya@gmail.com', 'surya kc', 'e1d0e45a6b0595f14fda5f32b208edbfe379b4fdc4b6f97f440507989a09d44cbf65015601055ae538b9527e668b21ea000c1e94fc6810385dc71fdd34d16b8e', 0, 'surya', 2, 531);

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
(15, 17, 'surya', 'INS', '2018-10-09 15:10:29', 'test');

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
(532, 'Nepal', 'Tokha', 'Kathmandu', 'basindhara', 5144754);

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
(531, '9849581817', 'chabel_futsal@gmail.com', 'http://testurl', '9845652312', 532, 'chabel Futsal');

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
(14, 531, 'Basundhara Futsal', 'INS', '2018-10-09 13:31:55');

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
(533, '8:00 pm', 'Alpha', 'http://testurl', '10:00 am', '500.00', 531, 'ACTIVE');

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
(534),
(534);

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
(10, NULL, 'b3827ae0ae884cdb7d136ee5f0424f80', 1, 'surya', 17);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `account_operation_jn`
--
ALTER TABLE `account_operation_jn`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `futsal_operation_jn`
--
ALTER TABLE `futsal_operation_jn`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `login_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

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
