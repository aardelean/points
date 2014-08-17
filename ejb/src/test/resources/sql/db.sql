CREATE  TABLE `points`.`user` (
  `id` INT NOT NULL ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NULL ,
  `lastname` VARCHAR(45) NULL ,
  `firstname` VARCHAR(45) NULL ,
  `phoneNo` VARCHAR(45) NULL ,
  `lastUpdatedDate` DATETIME NOT NULL ,
  `creationDate` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `points`.`visibilityGroup` (
  `id` INT NOT NULL ,
  `serializedUserIds` TEXT NOT NULL ,
  `userId` INT NOT NULL ,
  `status` ENUM('VISIBLE','INVISIBLE','IGNORE') NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `userId` (`userId` ASC) );

CREATE  TABLE `points`.`userRelation` (
  `userId` INT NOT NULL ,
  `provider` VARCHAR (45) NOT NULL ,
  `serializedUserIds` TEXT NOT NULL ,
  `socialIdentifier` VARCHAR(60) NULL ,
  `lastUpdatedDate` DATETIME NOT NULL ,
  PRIMARY KEY (`userId`, `provider`) ,
  INDEX `userId` (`userId` ASC) );
