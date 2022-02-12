CREATE TABLE `authors` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `date_of_birth` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

insert  into `authors`(`id`,`first_name`,`last_name`,`date_of_birth`) values 
(1,'Ivo','Andric','1892-10-09'),
(2,'Gijom','Muso','1974-06-06'),
(3,'Kejt','Najton','1985-07-24'),
(4,'Anton de Sent','Egziperi','1990-06-29'),
(5,'Stevan','Sremac','1855-11-23'),
(6,'Lujza','Hej','1926-10-08'),
(7,'Nil Donald','Vols','1943-09-10'),
(8,'Nada','Bucevic','1978-02-09');
