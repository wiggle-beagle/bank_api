create table Client (idClient identity NOT NULL primary key auto_increment, FIO varchar);

create table Account(idAccount long IDENTITY(10114444321,1) primary key, idClient int,
                     foreign key(idClient) references Client(idClient) );

create table Card (idCard long IDENTITY(4276380011110000,1) primary key , balance double, idAccount long(100),
                   foreign key (idAccount) references Account(idAccount));

INSERT INTO Client (FIO) VALUES ('Никонов Николай Петрович');
INSERT INTO Client (FIO) VALUES ('Быков Илларион Мэлорович');

INSERT INTO Account (idClient) VALUES (1);
INSERT INTO Account (idClient) VALUES (1);
INSERT INTO Account (idClient) VALUES (2);
INSERT INTO Account (idClient) VALUES (2);


INSERT INTO Card (balance,idAccount) VALUES (150,10114444321);
INSERT INTO Card (balance,idAccount) VALUES (3000,10114444321);
INSERT INTO Card (balance,idAccount) VALUES (50000,10114444321);
INSERT INTO Card (balance,idAccount) VALUES (240000,10114444322);
INSERT INTO Card (balance,idAccount) VALUES (980,10114444323);
INSERT INTO Card (balance,idAccount) VALUES (54000,10114444324);





