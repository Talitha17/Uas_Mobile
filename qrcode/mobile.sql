-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 06, 2022 at 03:14 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mobile`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kode` char(10) NOT NULL,
  `nama_barang` varchar(40) DEFAULT NULL,
  `satuan` int(12) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `expired` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kode`, `nama_barang`, `satuan`, `jumlah`, `expired`) VALUES
('B0001', 'Sabun Mandi Lifeboy', 3000, 13, '2025-07-02'),
('B0002', 'Susu Frisian Flag 500 gram', 25000, 15, '2024-08-03'),
('B0003', 'Sunlight 750 ml', 20000, 6, '2023-03-17'),
('B0004', 'Indomie Mie Aceh ', 2500, 25, '2023-03-13'),
('B0005', 'Susu Dancow 750 gram', 35000, 10, '2026-07-09');

-- --------------------------------------------------------

--
-- Table structure for table `data_barang`
--

CREATE TABLE `data_barang` (
  `kode` varchar(50) NOT NULL,
  `nama_barang` varchar(80) NOT NULL,
  `harga` int(10) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `satuan` varchar(50) NOT NULL,
  `image` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data_barang`
--

INSERT INTO `data_barang` (`kode`, `nama_barang`, `harga`, `jumlah`, `satuan`, `image`) VALUES
('B0001', 'Sabun Mandi Lifeboy', 3000, 10, 'unit', 'B0001.png'),
('B0002', 'Susu Frisian Flag 500 gram', 25000, 13, 'box', 'B0002.png'),
('B0003', 'Sunlight 750 ml', 20000, 14, 'pack', 'B0003.png'),
('B0004', 'Indomie - Mie Aceh', 2500, 25, 'pack', 'B0004.png'),
('B0005', 'Susu Dancow 750 gram', 57000, 10, 'box', 'B0005.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode`);

--
-- Indexes for table `data_barang`
--
ALTER TABLE `data_barang`
  ADD PRIMARY KEY (`kode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
