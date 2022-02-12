CREATE TABLE `genres` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

insert  into `genres`(`id`,`name`) values
(1,'Popular psihology'),
(2,'Romane'),
(3,'Kids'),
(4,'Action');
