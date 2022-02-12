CREATE TABLE `order_entries` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int unsigned NOT NULL,
  `quantity` int unsigned DEFAULT NULL,
  `order_id` int unsigned NOT NULL,
  `price` double unsigned NOT NULL,
  PRIMARY KEY (`id`,`order_id`,`price`),
  KEY `order_entry_book_fk` (`book_id`),
  KEY `order_entry_order_fk` (`order_id`),
  CONSTRAINT `order_entries_books_fk` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_entries_orders_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4;
