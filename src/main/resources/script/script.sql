DROP table if exists `designcode`;
CREATE TABLE `designcode` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`code` VARCHAR(16) NOT NULL,
	`corename` VARCHAR(16) NOT NULL,
	`corever` VARCHAR(16) NOT NULL,
	`minfreq` INT(11) NOT NULL,
	`fpu` INT(11) NOT NULL,
	`mpu` INT(11) NOT NULL,
	`simd` INT(11) NOT NULL,
	`jtag` INT(11) NOT NULL,
	`swd` INT(11) NOT NULL,
	`swotraced0` INT(11) NOT NULL,
	`endian` INT(11) NOT NULL,
	`thumbsupport` INT(11) NOT NULL,
	`armsupport` INT(11) NOT NULL,
	`flashbase` INT(11) NOT NULL,
	`rambase` INT(11) NOT NULL,
	`intvecbase` INT(11) NOT NULL,
	`block` INT(11) NOT NULL,
	`page` INT(11) NOT NULL,
	`trusterange` INT(11) NOT NULL,
	`useSfrfilter` INT(11) NOT NULL,
	`online` INT(11) NOT NULL,
	`aggregate` INT(11) NOT NULL,
	`argsdoc` VARCHAR(64) NOT NULL,
	`ddfname` VARCHAR(32) NOT NULL,
	`dmacname` VARCHAR(32) NOT NULL,
	`crtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id_UNIQUE` (`id`),
	UNIQUE INDEX `code` (`code`)
)
	COLLATE='utf8_general_ci'
	ENGINE=InnoDB
	AUTO_INCREMENT=1
;

INSERT INTO designcode (`code`, `corename`, `corever`, `minfreq`, `fpu`, `mpu`, `simd`, `jtag`, `swd`, `swotraced0`, `endian`, `thumbsupport`, `armsupport`, `flashbase`,
												`rambase`, `intvecbase`, `block`, `page`, `trusterange`, `useSfrfilter`, `online`, `aggregate`, `argsdoc`, `ddfname`, `dmacname`)
Values
	('MZ310', 'Cortex-M0', 'r0p0', 72000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0', 'Cortex-M0'),
	('MZ309', 'Cortex-M0', 'r0p0', 72000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0', 'Cortex-M0'),
	('MZ308', 'Cortex-M0', 'r0p0', 96000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0', 'Cortex-M0'),
	('MZ306', 'Cortex-M0', 'r0p0', 72000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0', 'Cortex-M0'),
	('MZ307', 'Cortex-M3', 'r2p1', 144000000,0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0', 'Cortex-M0'),
	('MZ304', 'Cortex-M3', 'r2p1', 96000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0', 'Cortex-M0');

DROP table if exists `dmac`;
CREATE TABLE `dmac` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(32) NOT NULL,
	`filePath` VARCHAR(256) NOT NULL,
	`crtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id_UNIQUE` (`id`)
)
	COLLATE='utf8_general_ci'
	ENGINE=InnoDB
	AUTO_INCREMENT=1
;

INSERT INTO dmac (`name`, `filePath`)
Values
	('Cortex-M0', 'M0.DMAC'),
	('Cortex-M3', 'M3.DMAC');

DROP table if exists `ddfmemory`;
CREATE TABLE `ddfmemory` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(32) NOT NULL,
	`keytype` VARCHAR(16) NOT NULL,
	`caption` VARCHAR(32) NOT NULL,
	`adrspace` VARCHAR(16) NOT NULL,
	`startadr` bigint(11) NOT NULL,
	`endadr` bigint(11) NOT NULL,
	`acctype` VARCHAR(16) NOT NULL,
	`width` VARCHAR(16) NULL DEFAULT NULL,
	`crtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id_UNIQUE` (`id`)
)
	COLLATE='utf8_general_ci'
	ENGINE=InnoDB
	AUTO_INCREMENT=1
;

INSERT INTO ddfmemory (`name`, `keytype`, `caption`, `adrspace`, `startadr`, `endadr`, `acctype`, `width`)
Values
	('Cortex-M0', 'Memory', 'Periph', 				'Memory', '$40000000', '$480017FF', 'W', ''),
	('Cortex-M0', 'Memory', 'SystemSFR',			'Memory', '$E0000000', '$E00FFFFF', 'W', ''),
	('Cortex-M0', 'Memory', 'ROM', 						'Memory', '$1FFFEC00', '$1FFFFBFF', 'W', ''),
	('Cortex-M0', 'Memory', 'RemapMem', 			'Memory', '$00000000', '$00007FFF',	'W', ''),
	('Cortex-M3', 'Memory', 'Periph'		, 		'Memory', '$40000000', '$400233FF', 'W', ''),
	('Cortex-M3', 'Memory', 'Periph-BitBand',	'Memory', '$42000000', '$42467FFF', 'W', ''),
	('Cortex-M3', 'Memory', 'SystemSFR'		, 	'Memory', '$E0000000', '$E00FFFFF', 'W', ''),
	('Cortex-M3', 'Memory', 'ROM', 						'Memory', '$1FFFE000', '$1FFFF80F',	'W', ''),
	('Cortex-M3', 'Memory', 'ExtDev'		, 		'Memory', '$60000000', '$9FFFFFFF', 'W', ''),
	('Cortex-M3', 'Memory', 'Periph1'		,			'Memory', '$A0000000', '$A0000FFF', 'W', ''),
	('Cortex-M3', 'Memory', 'RemapMem'		, 	'Memory', '$00000000', '$0001FFFF', 'W', ''),
	('Cortex-M3', 'Memory', 'RAM-BitBand'	, 	'Memory', '$22000000', '$2209FFFF', 'W', '');

DROP table if exists `flashload`;
CREATE TABLE `flashload` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(32) NOT NULL,
	`descript` VARCHAR(32) NOT NULL,
	`iarfilename` VARCHAR(256) NOT NULL,
	`keilfilename` VARCHAR(256) NOT NULL,
	`crtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id_UNIQUE` (`id`)
)
	COLLATE='utf8_general_ci'
	ENGINE=InnoDB
	AUTO_INCREMENT=1
;

DROP table if exists `partfamily`;
CREATE TABLE `partfamily` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`parentId` VARCHAR(16) NOT NULL DEFAULT -1,
	`designCode` VARCHAR(32) NOT NULL,
	`familyName` VARCHAR(64) NOT NULL,
	`descript` VARCHAR(256) NOT NULL,
	`flashLoadID` INT(11) NOT NULL DEFAULT -1,
	`crtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id_UNIQUE` (`id`)
)
	COLLATE='utf8_general_ci'
	ENGINE=InnoDB
	AUTO_INCREMENT=1
;

DROP table if exists `part`;
CREATE TABLE `part` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`familyname` VARCHAR(64) NOT NULL,
	`partname` VARCHAR(64) NOT NULL,
	`flashloadname` VARCHAR(32) NOT NULL,
	`tag` VARCHAR(256) NOT NULL,
	`displayname` VARCHAR(256) NOT NULL,
	`freq` INT(11) NOT NULL,
	`flashsize` INT(11) NOT NULL,
	`ramsize` INT(11) NOT NULL,
	`ips`  VARCHAR(10240) NOT NULL,
	`crtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id_UNIQUE` (`id`)
)
	COLLATE='utf8_general_ci'
	ENGINE=InnoDB
	AUTO_INCREMENT=1
;

DROP table if exists `packlog`;
CREATE TABLE `packlog` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`familyName` VARCHAR(64) NOT NULL,
	`version` VARCHAR(32) NOT NULL,
	`descript` VARCHAR(2048) NOT NULL,
	`crtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `id_UNIQUE` (`id`)
)
	COLLATE='utf8_general_ci'
	ENGINE=InnoDB
	AUTO_INCREMENT=1
;