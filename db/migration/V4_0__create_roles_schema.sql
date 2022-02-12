CREATE TABLE `roles` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

insert  into `roles`(`id`,`name`) values
(1,'ROLE_ADMIN'),
(2,'ROLE_CUSTOMER'),
(3,'ROLE_GOLDEN_CUSTOMER');
