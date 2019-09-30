-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.19-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 mindmotion 的数据库结构
DROP DATABASE IF EXISTS `mindmotion`;
CREATE DATABASE IF NOT EXISTS `mindmotion` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mindmotion`;

-- 导出  表 mindmotion.ddfmemory 结构
DROP TABLE IF EXISTS `ddfmemory`;
CREATE TABLE IF NOT EXISTS `ddfmemory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `keytype` varchar(16) NOT NULL,
  `caption` varchar(32) NOT NULL,
  `adrspace` varchar(16) NOT NULL,
  `startadr` varchar(16) NOT NULL,
  `endadr` varchar(16) NOT NULL,
  `acctype` varchar(16) NOT NULL,
  `width` varchar(16) DEFAULT NULL,
  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- 正在导出表  mindmotion.ddfmemory 的数据：~12 rows (大约)
/*!40000 ALTER TABLE `ddfmemory` DISABLE KEYS */;
INSERT INTO `ddfmemory` (`id`, `name`, `keytype`, `caption`, `adrspace`, `startadr`, `endadr`, `acctype`, `width`, `crtime`) VALUES
	(1, 'Cortex-M0', 'Memory', 'Periph', 'Memory', '0x40000000', '0x480017FF', 'W', '', '2019-09-26 10:02:38'),
	(2, 'Cortex-M0', 'Memory', 'SystemSFR', 'Memory', '0xE0000000', '0xE00FFFFF', 'W', '', '2019-09-26 10:02:38'),
	(3, 'Cortex-M0', 'Memory', 'ROM', 'Memory', '0x1FFFEC00', '0x1FFFFBFF', 'R', '', '2019-09-26 10:02:38'),
	(4, 'Cortex-M0', 'Memory', 'RemapMem', 'Memory', '0x00000000', '0x00007FFF', 'R', '', '2019-09-26 10:02:38'),
	(5, 'Cortex-M3', 'Memory', 'Periph0', 'Memory', '0x40000000', '0x400233FF', 'W', '', '2019-09-26 10:02:38'),
	(6, 'Cortex-M3', 'Memory', 'Periph-BitBand', 'Memory', '0x42000000', '0x42467FFF', 'W', '', '2019-09-26 10:02:38'),
	(7, 'Cortex-M3', 'Memory', 'SystemSFR', 'Memory', '0xE0000000', '0xE00FFFFF', 'W', '', '2019-09-26 10:02:38'),
	(8, 'Cortex-M3', 'Memory', 'ROM', 'Memory', '0x1FFFE000', '0x1FFFF80F', 'R', '', '2019-09-26 10:02:38'),
	(9, 'Cortex-M3', 'Memory', 'ExtDev', 'Memory', '0x60000000', '0x9FFFFFFF', 'W', '', '2019-09-26 10:02:38'),
	(10, 'Cortex-M3', 'Memory', 'Periph1', 'Memory', '0xA0000000', '0xA0000FFF', 'W', '', '2019-09-26 10:02:38'),
	(11, 'Cortex-M3', 'Memory', 'RemapMem', 'Memory', '0x00000000', '0x0001FFFF', 'R', '', '2019-09-26 10:02:38'),
	(12, 'Cortex-M3', 'Memory', 'RAM-BitBand', 'Memory', '0x22000000', '0x2209FFFF', 'W', '', '2019-09-26 10:02:38');
/*!40000 ALTER TABLE `ddfmemory` ENABLE KEYS */;

-- 导出  表 mindmotion.designcode 结构
DROP TABLE IF EXISTS `designcode`;
CREATE TABLE IF NOT EXISTS `designcode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) NOT NULL,
  `corename` varchar(16) NOT NULL,
  `corever` varchar(16) NOT NULL,
  `minfreq` int(11) NOT NULL,
  `fpu` int(11) NOT NULL,
  `mpu` int(11) NOT NULL,
  `simd` int(11) NOT NULL,
  `jtag` int(11) NOT NULL,
  `swd` int(11) NOT NULL,
  `swotraced0` int(11) NOT NULL,
  `endian` int(11) NOT NULL,
  `thumbsupport` int(11) NOT NULL,
  `armsupport` int(11) NOT NULL,
  `flashbase` int(11) NOT NULL,
  `rambase` int(11) NOT NULL,
  `intvecbase` int(11) NOT NULL,
  `block` int(11) NOT NULL,
  `page` int(11) NOT NULL,
  `trusterange` int(11) NOT NULL,
  `useSfrfilter` int(11) NOT NULL,
  `online` int(11) NOT NULL,
  `aggregate` int(11) NOT NULL,
  `argsdoc` varchar(64) NOT NULL,
  `ddfname` varchar(32) NOT NULL,
  `dmacname` varchar(32) NOT NULL,
  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- 正在导出表  mindmotion.designcode 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `designcode` DISABLE KEYS */;
INSERT INTO `designcode` (`id`, `code`, `corename`, `corever`, `minfreq`, `fpu`, `mpu`, `simd`, `jtag`, `swd`, `swotraced0`, `endian`, `thumbsupport`, `armsupport`, `flashbase`, `rambase`, `intvecbase`, `block`, `page`, `trusterange`, `useSfrfilter`, `online`, `aggregate`, `argsdoc`, `ddfname`, `dmacname`, `crtime`) VALUES
	(1, 'MZ310', 'Cortex-M0', 'r0p0', 72000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0', 'Cortex-M0', '2019-09-26 10:02:38'),
	(2, 'MZ309', 'Cortex-M0', 'r0p0', 72000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0', 'Cortex-M0', '2019-09-26 10:02:38'),
	(3, 'MZ308', 'Cortex-M0', 'r0p0', 96000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0', 'Cortex-M0', '2019-09-26 10:02:38'),
	(4, 'MZ306', 'Cortex-M0', 'r0p0', 72000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0', 'Cortex-M0', '2019-09-26 10:02:38'),
	(5, 'MZ307', 'Cortex-M3', 'r2p1', 144000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M3', 'Cortex-M3', '2019-09-26 10:02:38'),
	(6, 'MZ304', 'Cortex-M3', 'r2p1', 96000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M3', 'Cortex-M3', '2019-09-26 10:02:38');
/*!40000 ALTER TABLE `designcode` ENABLE KEYS */;

-- 导出  表 mindmotion.flashload 结构
DROP TABLE IF EXISTS `flashload`;
CREATE TABLE IF NOT EXISTS `flashload` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `descript` varchar(32) NOT NULL,
  `iarfilename` varchar(256) NOT NULL,
  `keilfilename` varchar(256) NOT NULL,
  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  mindmotion.flashload 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `flashload` DISABLE KEYS */;
/*!40000 ALTER TABLE `flashload` ENABLE KEYS */;

-- 导出  表 mindmotion.packlog 结构
DROP TABLE IF EXISTS `packlog`;
CREATE TABLE IF NOT EXISTS `packlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `familyName` varchar(64) NOT NULL,
  `version` varchar(32) NOT NULL,
  `descript` varchar(2048) NOT NULL,
  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  mindmotion.packlog 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `packlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `packlog` ENABLE KEYS */;

-- 导出  表 mindmotion.part 结构
DROP TABLE IF EXISTS `part`;
CREATE TABLE IF NOT EXISTS `part` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `familyname` varchar(64) NOT NULL,
  `partname` varchar(64) NOT NULL,
  `flashloadname` varchar(32) NOT NULL,
  `tag` varchar(256) NOT NULL,
  `displayname` varchar(256) NOT NULL,
  `freq` int(11) NOT NULL,
  `flashsize` int(11) NOT NULL,
  `ramsize` int(11) NOT NULL,
  `ips` varchar(10240) NOT NULL,
  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  mindmotion.part 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `part` DISABLE KEYS */;
INSERT INTO `part` (`id`, `familyname`, `partname`, `flashloadname`, `tag`, `displayname`, `freq`, `flashsize`, `ramsize`, `ips`, `crtime`) VALUES
	(1, 'MM32F032', 'MM32F032K6U6', 'MM32F032x6', 'MM32F032K6U6', 'MindMotion MM32F032K6U6', 72000000, 32768, 8192, '{}', '2019-09-26 12:32:22');
/*!40000 ALTER TABLE `part` ENABLE KEYS */;

-- 导出  表 mindmotion.partfamily 结构
DROP TABLE IF EXISTS `partfamily`;
CREATE TABLE IF NOT EXISTS `partfamily` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` varchar(16) NOT NULL DEFAULT '-1',
  `designCode` varchar(32) NOT NULL,
  `familyName` varchar(64) NOT NULL,
  `descript` varchar(256) NOT NULL,
  `flashLoadID` int(11) NOT NULL DEFAULT '-1',
  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  mindmotion.partfamily 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `partfamily` DISABLE KEYS */;
INSERT INTO `partfamily` (`id`, `parentId`, `designCode`, `familyName`, `descript`, `flashLoadID`, `crtime`) VALUES
	(1, '-1', 'MZ310', 'MM32F0', 'MM32F0 Descript', -1, '2019-09-26 13:46:14'),
	(2, '1', 'MZ310', 'MM32F032', 'MM32F032 Descript', -1, '2019-09-26 14:13:26');
/*!40000 ALTER TABLE `partfamily` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
