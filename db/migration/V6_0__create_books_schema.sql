CREATE TABLE `books` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `image_path` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `genre_id` int unsigned NOT NULL,
  `title` varchar(255) NOT NULL,
  `author_id` int unsigned NOT NULL,
  `price` double unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `book_author_fk` (`author_id`),
  KEY `book_genre_fk` (`genre_id`),
  CONSTRAINT `books_authors_fk` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `books_genres_fk` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4;

insert  into `books`(`id`,`image_path`,`genre_id`,`title`,`author_id`,`price`) values
(1,'znakovi-pored-puta',2,'Znakovi pored puta',1,10),
(2,'na-drini-cuprija',1,'Na Drini cuprija',1,12),
(4,'zato-sto-te-volim',2,'Zato sto te volim',2,4),
(5,'kako-bih-bez-tebe',2,'Kako bih bez tebe',2,4),
(6,'uspavana-lepotica',3,'Uspavana lepotica',3,3),
(7,'mali-princ',3,'Mali princ',4,6),
(8,'pop-cira-pop-spira',3,'Pop Cira i pop Spira',5,5),
(9,'verujte-zivotu',1,'Verujte zivotu',6,7),
(10,'kako-da-izlecite',4,'Kako da izlecite svoj zivot',6,11),
(11,'razgovori-s-bogom-1',1,'Razgovori s Bogom 1',7,12),
(12,'razgovori-s-bogom-2',1,'Razgovori s Bogom 2',7,9),
(13,'razgovori-s-bogom-3',1,'Razgovori s Bogom 3',7,14),
(14,'safari-duha',3,'Safari duha',8,22),
(15,'svesno-majcinstvo',1,'Svesno majcinstvo',8,15),
(16,'zona-zamfirova',2,'Zona Zamfirova',5,8),
(17,'zov-andjela',1,'Zov andjela',1,8);
