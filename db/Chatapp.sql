CREATE SCHEMA IF NOT EXISTS `Chatapp`;
USE `Chatapp` ;

DROP TABLE IF EXISTS `Chatapp`.`chatrooms`;
CREATE TABLE `chatrooms`(
	`id` varchar(36) NOT NULL,
    `name` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`(
	`username` varchar(50) NOT NULL,
    `password` varchar(72) NOT NULL,
    `chatroom` varchar(36) NOT NULL,
    PRIMARY KEY (`username`),
    CONSTRAINT `user_chatroom`
		FOREIGN KEY (`chatroom`)
        REFERENCES `Chatapp`.`chatrooms` (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages`(
	`id` INT NOT NULL AUTO_INCREMENT,
    `content` text NOT NULL,
    `from` varchar(50) NOT NULL,
    `to` varchar(50) NOT NULL,
    `chatroom` varchar(50) NOT NULL,
    `date` DATETIME NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `from`
		FOREIGN KEY (`from`)
        REFERENCES `Chatapp`.`users` (`username`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	CONSTRAINT `to`
		FOREIGN KEY (`to`)
        REFERENCES `Chatapp`.`users` (`username`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT `message_chatroom`
		FOREIGN KEY (`chatroom`)
        REFERENCES `Chatapp`.`chatrooms` (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
