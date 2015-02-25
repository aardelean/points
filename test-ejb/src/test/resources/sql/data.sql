INSERT INTO `user`
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

INSERT INTO `userstatus`
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

INSERT INTO `strategy`
(`id`,
 `enabled`,
 `strategyType`,
 `status_id`,
 `locationName`,
 `locationType`)
VALUES
  (
    100,
    1,
    'LOCATION',
    100,
    'MUNICH',
    'CITY'
  );






INSERT INTO `groups`
(`id`,
 `enabled`,
 `name`,
 `creatorId`,
 `statusId`)
VALUES
  (
    100,
    1,
    'testname',
    100,
    100);


