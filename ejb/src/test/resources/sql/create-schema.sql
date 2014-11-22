    create table GroupMessage (
        type varchar(31) not null,
        id bigint not null auto_increment,
        content varchar(255),
        location varchar(255),
        time datetime,
        issuer_id bigint,
        groupId bigint,
        primary key (id)
    );

    create table UserMessage (
        type varchar(31) not null,
        id bigint not null auto_increment,
        content varchar(255),
        location varchar(255),
        time datetime,
        issuer_id bigint,
        userId bigint,
        primary key (id)
    );

    create table friend (
        id bigint not null auto_increment,
        friendsIds varchar(255),
        provider varchar(255),
        userId bigint,
        primary key (id)
    );

    create table groups (
        id bigint not null auto_increment,
        contactIds longtext,
        enabled bit,
        name varchar(255),
        creatorId bigint,
        statusId bigint,
        strategyId bigint,
        primary key (id)
    );

    create table locationStrategy (
        locationName varchar(255),
        locationType varchar(255),
        strategyId bigint not null,
        primary key (strategyId)
    );

    create table point (
        id bigint not null auto_increment,
        latitude varchar(255),
        longitude varchar(255),
        time datetime,
        primary key (id)
    );

    create table strategy (
        id bigint not null auto_increment,
        enabled bit not null,
        strategyType varchar(255),
        primary key (id)
    );

    create table timeStrategy (
        enabled bit not null,
        endTime datetime,
        startTime datetime,
        timeStrategyType varchar(255),
        strategyId bigint not null,
        primary key (strategyId)
    );

    create table user (
        id bigint not null auto_increment,
        creationDate datetime,
        email varchar(255),
        firstName varchar(255),
        lastModifiedDate datetime,
        lastName varchar(255),
        password varchar(255),
        phoneNo varchar(255),
        socialProvider varchar(255),
        username varchar(255),
        primary key (id)
    );

    create table userStatus (
        id bigint not null auto_increment,
        lastModified datetime,
        pingTime integer,
        type varchar(255),
        userId bigint,
        primary key (id)
    );

    alter table GroupMessage 
        add constraint FK_qcgoqiikpt3otjuyg2bic9tfw 
        foreign key (issuer_id) 
        references user (id);

    alter table GroupMessage 
        add constraint FK_50sqnje4tu9qw9lm9d6qs3nx5 
        foreign key (groupId) 
        references groups (id);

    alter table UserMessage 
        add constraint FK_s08cwilcdu1pt6saotxdtg97u 
        foreign key (issuer_id) 
        references user (id);

    alter table UserMessage 
        add constraint FK_qf1partsbcmvs2i2hmr1qxqpw 
        foreign key (userId) 
        references user (id);

    alter table friend 
        add constraint FK_5w93st3rg8803m8r8d973temq 
        foreign key (userId) 
        references user (id);

    alter table groups 
        add constraint FK_9pn6xmauj25gb7ta8hjjbtmlb 
        foreign key (creatorId) 
        references user (id);

    alter table groups 
        add constraint FK_1262g80reyrhut3mfy0ldf9xo 
        foreign key (statusId) 
        references userStatus (id);

    alter table groups 
        add constraint FK_3bied2ytdljd5cmiolnccf861 
        foreign key (strategyId) 
        references strategy (id);

    alter table locationStrategy 
        add constraint FK_2ippg53xyc3llko4il1sebuuv 
        foreign key (strategyId) 
        references strategy (id);

    alter table timeStrategy 
        add constraint FK_oglhnxf0jx8dl3gkgvcir9wi2 
        foreign key (strategyId) 
        references strategy (id);

    alter table userStatus 
        add constraint FK_4x8kuyxn9aqi07ik4nbs9v7r4 
        foreign key (userId) 
        references user (id);
