create table Client (idClient identity NOT NULL primary key auto_increment, FIO varchar);

create table Account(idAccount long IDENTITY(10114444321,1) primary key, idClient int,
foreign key(idClient) references Client(idClient) );

create table Card (idCard long IDENTITY(4276380011110000,1) primary key , balance double, idAccount long(100),
foreign key (idAccount) references Account(idAccount));

INSERT INTO Client (FIO) VALUES ('Никонов Николай Петрович');
INSERT INTO Client (FIO) VALUES ('Быков Илларион Мэлорович');
INSERT INTO Client (FIO) VALUES ('Щербакова Лия Александровна');
INSERT INTO Client (FIO) VALUES ('Некрасова Юланта Тимофеевна');
INSERT INTO Client (FIO) VALUES ('Зуева Романа Вадимовна');

INSERT INTO Account (idClient) VALUES (1);
INSERT INTO Account (idClient) VALUES (1);
INSERT INTO Account (idClient) VALUES (2);
INSERT INTO Account (idClient) VALUES (3);
INSERT INTO Account (idClient) VALUES (4);
INSERT INTO Account (idClient) VALUES (5);
INSERT INTO Account (idClient) VALUES (5);
INSERT INTO Account (idClient) VALUES (5);

INSERT INTO Card (balance,idAccount) VALUES (0,10114444321);
INSERT INTO Card (balance,idAccount) VALUES (0,10114444321);
INSERT INTO Card (balance,idAccount) VALUES (0,10114444321);
INSERT INTO Card (balance,idAccount) VALUES (0,10114444322);
INSERT INTO Card (balance,idAccount) VALUES (0,10114444323);
INSERT INTO Card (balance,idAccount) VALUES (0,10114444324);
INSERT INTO Card (balance,idAccount) VALUES (0,10114444325);
INSERT INTO Card (balance,idAccount) VALUES (0,10114444325);
INSERT INTO Card (balance,idAccount) VALUES (0,10114444325);
INSERT INTO Card (balance,idAccount) VALUES (0,10114444326);
INSERT INTO Card (balance,idAccount) VALUES (0,10114444327);
INSERT INTO Card (balance,idAccount) VALUES (0,10114444327);
INSERT INTO Card (balance,idAccount) VALUES (0,10114444328);




