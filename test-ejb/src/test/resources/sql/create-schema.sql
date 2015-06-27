SET FOREIGN_KEY_CHECKS=0;
CREATE TABLE `contactCollection` (
  `id` bigint(20) NOT NULL,
  `contactIds` mediumblob,
  `source` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_g4h4hck1gdod7ih1l7dpqmtr6` (`user_id`),
  CONSTRAINT `FK_g4h4hck1gdod7ih1l7dpqmtr6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  

CREATE TABLE `group_strategy` (
  `group_id` bigint(20) NOT NULL,
  `strategy_id` bigint(20) NOT NULL,
  KEY `FK_kw51irtffyupfv6vxjlpsvnk3` (`strategy_id`),
  KEY `FK_n0sst4afsrqc3mu71l6oqx48v` (`group_id`),
  CONSTRAINT `FK_kw51irtffyupfv6vxjlpsvnk3` FOREIGN KEY (`strategy_id`) REFERENCES `strategy` (`id`),
  CONSTRAINT `FK_n0sst4afsrqc3mu71l6oqx48v` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  

CREATE TABLE `group_user` (
  `group_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  KEY `FK_ns02np32pqhrbm8cwpifjerp9` (`user_id`),
  KEY `FK_dx4jv6mpv63ufnjl3a7pec1vo` (`group_id`),
  CONSTRAINT `FK_dx4jv6mpv63ufnjl3a7pec1vo` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  CONSTRAINT `FK_ns02np32pqhrbm8cwpifjerp9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  

CREATE TABLE `groupmessage` (
  `type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `issuer_id` bigint(20) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k6xwpg1fk6nb1882m6rprrp26` (`issuer_id`),
  KEY `FK_r52pqe3tatkn4qi44atfw3nx5` (`groupId`),
  CONSTRAINT `FK_k6xwpg1fk6nb1882m6rprrp26` FOREIGN KEY (`issuer_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_r52pqe3tatkn4qi44atfw3nx5` FOREIGN KEY (`groupId`) REFERENCES `groups` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  

CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `creatorId` bigint(20) DEFAULT NULL,
  `statusId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9pn6xmauj25gb7ta8hjjbtmlb` (`creatorId`),
  KEY `FK_1262g80reyrhut3mfy0ldf9xo` (`statusId`),
  CONSTRAINT `FK_1262g80reyrhut3mfy0ldf9xo` FOREIGN KEY (`statusId`) REFERENCES `userstatus` (`id`),
  CONSTRAINT `FK_9pn6xmauj25gb7ta8hjjbtmlb` FOREIGN KEY (`creatorId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  

CREATE TABLE `point` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  

CREATE TABLE `strategy` (
  `strategyType` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) NOT NULL,
  `endTime` datetime DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `timeStrategyType` varchar(255) DEFAULT NULL,
  `locationName` varchar(255) DEFAULT NULL,
  `locationType` varchar(255) DEFAULT NULL,
  `status_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lel23d43e54tx5sn1tulia7q7` (`status_id`),
  CONSTRAINT `FK_lel23d43e54tx5sn1tulia7q7` FOREIGN KEY (`status_id`) REFERENCES `userstatus` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creationDate` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastModifiedDate` datetime DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phoneNo` varchar(255) DEFAULT NULL,
  `socialProvider` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  

CREATE TABLE `usermessage` (
  `type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `issuer_id` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mpoqw7p4wfm1gqaf6ywp7xlkl` (`issuer_id`),
  KEY `FK_id4yajjm0o0f1ejm8egc7d6x4` (`userId`),
  CONSTRAINT `FK_id4yajjm0o0f1ejm8egc7d6x4` FOREIGN KEY (`userId`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_mpoqw7p4wfm1gqaf6ywp7xlkl` FOREIGN KEY (`issuer_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


  

CREATE TABLE `userStatus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastModified` datetime DEFAULT NULL,
  `pingTime` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4x8kuyxn9aqi07ik4nbs9v7r4` (`userId`),
  CONSTRAINT `FK_4x8kuyxn9aqi07ik4nbs9v7r4` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS=1;
