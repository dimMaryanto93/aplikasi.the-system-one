-- phpMyAdmin SQL Dump
-- version 3.5.8.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 11, 2013 at 01:23 PM
-- Server version: 5.5.33a-MariaDB
-- PHP Version: 5.5.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `system_one`
--

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE IF NOT EXISTS `login` (
  `id` varchar(25) NOT NULL,
  `pass` varchar(25) DEFAULT NULL,
  `hak` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`,`pass`,`hak`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `pass`, `hak`) VALUES
('dim', 'dim', 0),
('op', 'op', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE IF NOT EXISTS `pelanggan` (
  `id` varchar(5) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `t4lahir` varchar(20) NOT NULL,
  `tgllahir` varchar(15) NOT NULL,
  `kelamin` varchar(15) NOT NULL,
  `pekerjaan` varchar(15) NOT NULL,
  `telp` varchar(15) NOT NULL,
  `tglmasuk` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`id`, `nama`, `alamat`, `t4lahir`, `tgllahir`, `kelamin`, `pekerjaan`, `telp`, `tglmasuk`) VALUES
('PLG01', 'DIMAS MARYANTO', 'JL.BUKIT INDAH', 'BANDUNG', '28-03-1993', 'Laki - Laki', 'MAHASISWA', '0821173456', '11-11-2013'),
('PLG02', 'MUHAMAD HANIF MUHSIN', 'JL.BALE ENDAH', 'BANDUNG', '06-04-1993', 'Laki - Laki', 'MAHASISWA', '0851234566', '09-11-2013'),
('PLG03', 'WILLIAM PASCAL', '', 'JAKARTA', '01-01-1990', 'Laki - Laki', 'MAHASISWA', '0899512345', '11-11-2013'),
('PLG04', 'RIANSYAH PERMANA PUTRA', '', 'BANDUNG', '01-01-1990', 'Laki - Laki', 'DESINER', '0821345644', '11-11-2013');

-- --------------------------------------------------------

--
-- Table structure for table `Product`
--

CREATE TABLE IF NOT EXISTS `Product` (
  `id` varchar(5) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `type` varchar(15) NOT NULL,
  `pabrik` varchar(25) NOT NULL,
  `qty` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `detail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Product`
--

INSERT INTO `Product` (`id`, `nama`, `type`, `pabrik`, `qty`, `harga`, `detail`) VALUES
('as001', 'ASUS P8H61 MLX', 'Motherboard', 'ASUS', 3, 750000, ''),
('GB001', 'GIGABYTE GAZ77', 'Motherboard', 'GIGABYTE', 2, 1560000, ''),
('MS001', 'MSI POWER Z87', 'Motherboard', 'MSI', 4, 2400000, '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
