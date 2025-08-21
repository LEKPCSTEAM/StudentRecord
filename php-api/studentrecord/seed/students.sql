-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 21, 2025 at 05:54 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `allstudents`
--

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `regno` varchar(64) NOT NULL,
  `fullname` varchar(128) NOT NULL,
  `gender` varchar(16) NOT NULL,
  `program` varchar(64) NOT NULL,
  `college` varchar(128) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`regno`, `fullname`, `gender`, `program`, `college`, `date`) VALUES
('650001/2023', 'สมชาย ใจดี...', 'male', 'วิศวกรรมคอมพิวเตอร์', 'คณะวิศวกรรมศาสตร์', '2023-06-10'),
('650002/2023', 'สมหญิง สายชล', 'female', 'วิทยาการคอมพิวเตอร์', 'คณะวิทยาศาสตร์', '2023-06-11'),
('650003/2023', 'กิตติพงษ์ พัฒนาการ', 'male', 'บริหารธุรกิจ', 'คณะบริหารธุรกิจ', '2023-06-12'),
('650004/2023', 'พิมพ์ชนก สุนทร', 'female', 'บัญชี', 'คณะพาณิชยศาสตร์และการบัญชี', '2023-06-13'),
('650005/2023', 'อนันต์ ศรีสุข', 'male', 'กฎหมาย', 'คณะนิติศาสตร์', '2025-08-13'),
('650006/2023', 'ชลธิชา ประเสริฐ', 'female', 'พยาบาลศาสตร์', 'คณะพยาบาลศาสตร์', '2023-06-15'),
('650007/2023', 'วรพงษ์ สายทอง', 'male', 'สาธารณสุข', 'คณะสาธารณสุขศาสตร์', '2023-06-16'),
('650008/2023', 'ศศิธร พิทักษ์', 'female', 'ครุศาสตร์', 'คณะครุศาสตร์', '2023-06-17'),
('650009/2023', 'ธนพล วัฒนกุล', 'male', 'เศรษฐศาสตร์', 'คณะเศรษฐศาสตร์', '2023-06-18'),
('650010/2023', 'นฤมล สายรุ้ง', 'female', 'ศิลปศาสตร์', 'คณะศิลปศาสตร์', '2023-06-19');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`regno`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
