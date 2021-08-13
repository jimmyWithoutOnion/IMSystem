INSERT INTO `users`(`id`,`name`,`password`,`gender`,`signature`,`email`)
        VALUES (NULL,'黑化肥','123456','male','湖人总冠军','1111@qq.com');

INSERT INTO `users`(`id`,`name`,`password`,`gender`,`signature`,`email`)
        VALUES (NULL,'会挥发','123456','female','东契奇yyds','2222@qq.com');

INSERT INTO `users`(`id`,`name`,`password`,`gender`,`signature`,`email`)
        VALUES (NULL,'氮磷钾','123456','female','hhhhh','3333@qq.com');

INSERT INTO `contacts`(`id`,`name`,`email`)
        VALUES (1,'黑化肥','1111@qq.com');

INSERT INTO `contacts`(`id`,`name`,`email`)
        VALUES (2,'会挥发','2222@qq.com');

INSERT INTO `contacts`(`id`,`name`,`email`)
        VALUES (3,'氮磷钾','3333@qq.com');

INSERT INTO `user_contact`(`user_id`,`contact_id`,`name`)
        VALUES (1,2,'小可爱');

INSERT INTO `user_contact`(`user_id`,`contact_id`,`name`)
        VALUES (2,1,'大宝贝');

INSERT INTO `user_contact`(`user_id`,`contact_id`,`name`)
        VALUES (1,3,'c语言');

