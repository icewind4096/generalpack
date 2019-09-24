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
												`rambase`, `intvecbase`, `block`, `page`, `trusterange`, `useSfrfilter`, `online`, `aggregate`, `argsdoc`, 'ddfname')
Values
	('MZ310', 'Cortex-M0', 'r0p0', 72000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0'),
	('MZ309', 'Cortex-M0', 'r0p0', 72000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0'),
	('MZ308', 'Cortex-M0', 'r0p0', 96000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0'),
	('MZ306', 'Cortex-M0', 'r0p0', 72000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0'),
	('MZ307', 'Cortex-M3', 'r2p1', 144000000,0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0'),
	('MZ304', 'Cortex-M3', 'r2p1', 96000000, 0, 0, 0, 0, 0, 0, 0, 1, 0, 134217728, 536870912, 134217728, 1024, 2, 1, 1, 1, 1, '--skip_erase -Don\'t erase blocks that read empty.', 'Cortex-M0');

CREATE TABLE `ddfmemory` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`ddfname` VARCHAR(32) NOT NULL,
	`keytype` VARCHAR(16) NOT NULL,
	`name` VARCHAR(16) NOT NULL,
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

INSERT INTO ddfmemory (`ddfname`, `keytype`, `name`, `adrspace`, `startadr`, `endadr`, `acctype`, `width`)
Values
	('Cortex-M0', 'Memory', 'Periph', 				'Memory', 1073741824, 1207965695,  	'W', ''),
	('Cortex-M0', 'Memory', 'SystemSFR',			'Memory', 3758096384, 3759144959,  	'W', ''),
	('Cortex-M0', 'Memory', 'ROM', 						'Memory', 536869887,	536869887,  	'W', ''),
	('Cortex-M0', 'Memory', 'RemapMem', 			'Memory', 0,   				32767,  			'W', ''),
	('Cortex-M3', 'Memory', 'Periph'		, 		'Memory', 1073741824, 1073886207,  	'W', ''),
	('Cortex-M3', 'Memory', 'Periph-BitBand',	'Memory', 1107296256, 1111916543,  	'W', ''),
	('Cortex-M3', 'Memory', 'SystemSFR'		, 	'Memory', 3758096384,	3759144959,  	'W', ''),
	('Cortex-M3', 'Memory', 'ROM', 						'Memory', 536862720, 	536868879, 		'W', ''),
	('Cortex-M3', 'Memory', 'ExtDev'		, 		'Memory', 1610612736, 2684354559,  	'W', ''),
	('Cortex-M3', 'Memory', 'Periph1'		,			'Memory', 2684354560, 2684358655,  	'W', ''),
	('Cortex-M3', 'Memory', 'RemapMem'		, 	'Memory', 0,					65535, 	 			'W', ''),
	('Cortex-M3', 'Memory', 'RAM-BitBand'	, 	'Memory', 570425344,	571080703, 		'W', '');

CREATE TABLE `flashload` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`designcode` VARCHAR(16) NOT NULL,
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

CREATE TABLE `Subpartfamily` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`familyname` VARCHAR(64) NOT NULL,
	`subFamilyname` VARCHAR(64) NOT NULL,
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