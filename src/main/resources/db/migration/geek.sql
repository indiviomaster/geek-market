CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');


CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



INSERT INTO `users` VALUES (1,'alex','$2a$10$5KrUXSK06WFSzKEc4RxkX.63EqBhiOERuZrKhVrudy9gq3rrZ5r9u','Alex','GeekBrains','alex@gb.com','123',1),(2,'andy','$2a$10$5KrUXSK06WFSzKEc4RxkX.63EqBhiOERuZrKhVrudy9gq3rrZ5r9u','Alex','GeekBrains','andy@gb.com','1234',1);


CREATE TABLE `users_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_ROLE_idx` (`role_id`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `users_roles` VALUES (1,1),(1,2),(2,2);



CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `categories` VALUES (1,'Телевизоры'),(2,'Ноутбуки');


CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_id` int NOT NULL,
  `vendor_code` varchar(8) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `short_description` varchar(1000) NOT NULL,
  `full_description` varchar(5000) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_CATEGORY_ID` (`category_id`),
  CONSTRAINT `FK_CATEGORY_ID` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;


INSERT INTO `products` VALUES (1,1,'00000001','null','40\" Телевизор Samsung UE40NU7170U','Коротко: Хороший телевизор Samsung 40','LED телевизор Samsung 40',21000.00,'2020-08-30 23:23:11'),(2,1,'00000002','null','48\" Телевизор Samsung UE48NU7170U','Коротко: Хороший телевизор Samsung 48','LED телевизор Samsung 48',32000.00,'2020-08-30 23:23:11'),(3,1,'00000003','null','56\" Телевизор Samsung UE56NU7170U','Коротко: Хороший телевизор Samsung 56','LED телевизор Samsung 56',44000.00,'2020-08-30 23:23:11'),(4,1,'00000004','null','41\" Телевизор Samsung UE41NU7170U','Коротко: Хороший телевизор Samsung 41','LED телевизор Samsung 41',26000.00,'2020-08-30 23:34:23'),(5,1,'00000005','null','42\" Телевизор Samsung UE42NU7170U','Коротко: Хороший телевизор Samsung 42','LED телевизор Samsung 42',32000.00,'2020-08-30 23:34:23'),(6,1,'00000006','null','43\" Телевизор Samsung UE43NU7170U','Коротко: Хороший телевизор Samsung 43','LED телевизор Samsung 43',44000.00,'2020-08-30 23:34:23'),(7,1,'00000007','null','44\" Телевизор Samsung UE44NU7170U','Коротко: Хороший телевизор Samsung 44','LED телевизор Samsung 44',54000.00,'2020-08-30 23:34:23'),(8,1,'00000008','null','45\" Телевизор Samsung UE45NU7170U','Коротко: Хороший телевизор Samsung 45','LED телевизор Samsung 45',64000.00,'2020-08-30 23:34:23'),(9,1,'00000009','null','46\" Телевизор Samsung UE46NU7170U','Коротко: Хороший телевизор Samsung 46','LED телевизор Samsung 46',74000.00,'2020-08-30 23:34:23'),(10,1,'00000010','null','47\" Телевизор Samsung UE47NU7170U','Коротко: Хороший телевизор Samsung 47','LED телевизор Samsung 47',84000.00,'2020-08-30 23:34:23'),(11,1,'00000011','null','48\" Телевизор Samsung UE48NU7170U','Коротко: Хороший телевизор Samsung 48','LED телевизор Samsung 48',94000.00,'2020-08-30 23:34:23'),(12,1,'00000012','null','49\" Телевизор Samsung UE49NU7170U','Коротко: Хороший телевизор Samsung 49','LED телевизор Samsung 49',104000.00,'2020-08-30 23:34:23'),(13,1,'00000013','null','50\" Телевизор Samsung UE50NU7170U','Коротко: Хороший телевизор Samsung 50','LED телевизор Samsung 50',114000.00,'2020-08-30 23:34:23'),(14,1,'00000014','null','51\" Телевизор Samsung UE51NU7170U','Коротко: Хороший телевизор Samsung 51','LED телевизор Samsung 51',124000.00,'2020-08-30 23:34:23');



CREATE TABLE `books` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_ru_0900_ai_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_ru_0900_ai_ci DEFAULT NULL,
  `year` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_ru_0900_ai_ci;

INSERT INTO `books` VALUES (1,'Алмазный меч','фантастика',2019),(2,'Деревянный меч','фантастика',2020),(3,'Том сойер','приключеие',2018);
