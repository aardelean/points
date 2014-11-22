INSERT INTO `points`.`user`
(`id`,
 `creationDate`,
 `email`,
 `firstName`,
 `lastModifiedDate`,
 `lastName`,
 `password`,
 `phoneNo`,
 `socialProvider`,
 `username`)
VALUES
  (
    100,
    '2007-06-01 17:09:11',
    'test@email.com',
    'firstaname',
    '2007-06-01 17:09:11',
    'lastname',
    'password',
    '0087766554',
    'FACEBOOK',
    'username'
  );

INSERT INTO `points`.`userstatus`
(`id`,
 `lastModified`,
 `pingTime`,
 `type`,
 `userId`)
VALUES
  (
    100,
    '2007-06-01 17:09:11',
    100,
    'AVAILABLE',
    100
  );

INSERT INTO `points`.`strategy`
(`id`,
 `enabled`,
 `strategyType`)
VALUES
  (
    100,
    1,
    'LOCATION'
  );
INSERT INTO `points`.`locationstrategy`
(`locationName`,
 `locationType`,
 `strategyId`)
VALUES
  (
    'MUNICH',
    'CITY',
    100
  );



INSERT INTO `points`.`groups`
(`id`,
 `contactIds`,
 `enabled`,
 `name`,
 `creatorId`,
 `statusId`,
 `strategyId`)
VALUES
  (
    100,
    NULL,
    1,
    'testname',
    100,
    100,
    100
  );
