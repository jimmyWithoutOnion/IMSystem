INSERT INTO `users`(`id`,`name`,`password`,`gender`,`signature`,`email`)
        VALUES (NULL,'Devkit','FD90A979478F2E1792FFFBD46AEC768F8D3E72E98D786A3C584943E9A07D3782','male','湖人总冠军','1111@qq.com');

INSERT INTO `users`(`id`,`name`,`password`,`gender`,`signature`,`email`)
        VALUES (NULL,'Kunpeng','85F5D124DEEC9EAB2B1847B5225E9C57123F4A211AB211F1378D63383CD28','female','东契奇yyds','2222@qq.com');

INSERT INTO `users`(`id`,`name`,`password`,`gender`,`signature`,`email`)
        VALUES (NULL,'Huawei','1C6731BB57CBF24C7DD9CEEAD46956BB578DF042F36E78CAD8791360F2B8','female','hhhhh','3333@qq.com');

INSERT INTO `contacts`(`id`,`name`,`email`)
        VALUES (1,'Devkit','1111@qq.com');

INSERT INTO `contacts`(`id`,`name`,`email`)
        VALUES (2,'Kunpeng','2222@qq.com');

INSERT INTO `contacts`(`id`,`name`,`email`)
        VALUES (3,'Huawei','3333@qq.com');

INSERT INTO `user_contact`(`user_id`,`contact_id`,`name`)
        VALUES (1,2,'小可爱');

INSERT INTO `user_contact`(`user_id`,`contact_id`,`name`)
        VALUES (2,1,'大宝贝');

INSERT INTO `user_contact`(`user_id`,`contact_id`,`name`)
        VALUES (1,3,'c语言');

INSERT INTO `conversations`(`title`,`creator_id`)
        VALUES ('hahhahhaha',1);

INSERT INTO `conversations`(`title`,`creator_id`)
        VALUES ('lalalalalal',1);

INSERT INTO `participants` (`conversation_id`,`user_id`,`type`)
        VALUES (1, 1, 'single');

INSERT INTO `participants` (`conversation_id`,`user_id`,`type`)
        VALUES (1, 2, 'single');

INSERT INTO `participants` (`conversation_id`,`user_id`,`type`)
        VALUES (2, 1, 'single');

INSERT INTO `participants` (`conversation_id`,`user_id`,`type`)
        VALUES (2, 3, 'single');