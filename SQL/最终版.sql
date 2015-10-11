CREATE TABLE Users (

user_ID CHAR(32) NOT NULL,

user_Name VARCHAR2(20) NULL,

user_Pass VARCHAR2(20) NULL,

user_Money NUMBER(7,2) NULL,

user_Phone VARCHAR2(15) NULL,

user_EMail VARCHAR2(20) NULL,

user_State INT NULL,-- 用户状态 1001051028

PRIMARY KEY (user_ID) 

);



COMMENT ON COLUMN Users.user_State IS '状态用来限制是否可以借阅';



CREATE TABLE Books (

bk_ID CHAR(32) NOT NULL,-- 书ID

bk_Name VARCHAR2(50) NULL,--书名

bk_Author VARCHAR2(30) NULL,--作者

bk_Price NUMBER(7,2) NULL,

bk_Count INT NULL,

bk_State int,

bt_ID CHAR(32) NULL,

PRIMARY KEY (bk_ID) 

);



CREATE TABLE BookType (

bt_ID CHAR(32) NOT NULL,

bt_Name VARCHAR2(20) NULL,--类型名

bt_RID CHAR(32) NULL, --类型关联ID

bt_Time int,--借阅时间 周

PRIMARY KEY (bt_ID) 

);



CREATE TABLE Borrow (--借阅记录

br_ID CHAR(32) NOT NULL,--借阅批次ID

user_ID CHAR(32) NULL,--借阅者ID

bt_date DATE NULL,--借阅时间

PRIMARY KEY (br_ID) 

);



CREATE TABLE BorrowInfo (--借阅详情

bri_ID CHAR(32) NOT NULL,--借阅详情ID

br_ID CHAR(32) NULL,--借阅批次ID

bk_ID CHAR(32) NULL,--借阅的书籍ID

bri_State int,--借阅状态，没有还为1，已还为0
PRIMARY KEY (bri_ID) 

);



CREATE TABLE SendBack (--还书记录

sdb_ID CHAR(32) NOT NULL,

user_ID CHAR(32) NULL,

sdb_date DATE NULL,

PRIMARY KEY (sdb_ID) 

);



CREATE TABLE SendBackInfo (

stbi_ID CHAR(32) NOT NULL,

sdb_ID CHAR(32) NULL,

bk_ID CHAR(32) NULL,

PRIMARY KEY (stbi_ID) 

);



CREATE TABLE BookComments (--书评

bc_ID CHAR(32) NOT NULL,--书评ID

user_ID CHAR(32) NULL,--评论人

bk_ID CHAR(32) NULL,--被评论书

bc_Comment varchar2(2000) NULL,--评论内容

bc_Date DATE NULL,--评论时间

PRIMARY KEY (bc_ID) 

);



CREATE TABLE lostBooks (

lb_ID CHAR(32) NOT NULL,

bk_ID CHAR(32) NULL,

user_ID CHAR(32) NULL,

lb_Date DATE NULL,

PRIMARY KEY (lb_ID) 

);



CREATE TABLE admins (

ad_ID CHAR(32) NOT NULL,

ad_Name VARCHAR2(30) NULL,

ad_Pass VARCHAR2(30) NULL,

ad_state INT NULL,

PRIMARY KEY (ad_ID) 

);



CREATE TABLE BooksAddInfo (

bka_ID CHAR(32) NOT NULL,

ad_ID CHAR(32) NULL,

bk_ID CHAR(32) NULL,

bka_Date DATE NULL,

PRIMARY KEY (bka_ID) 

);



CREATE TABLE BooksDownInfo (

bkd_ID CHAR(32) NOT NULL,

ad_ID CHAR(32) NULL,

bk_ID CHAR(32) NULL,

bkd_Date DATE NULL,

PRIMARY KEY (bkd_ID) 

);





ALTER TABLE Books ADD CONSTRAINT fk_books_Btype FOREIGN KEY (bt_ID) REFERENCES BookType (bt_ID);

ALTER TABLE BorrowInfo ADD CONSTRAINT fk_bri_br FOREIGN KEY (br_ID) REFERENCES Borrow (br_ID);

ALTER TABLE BorrowInfo ADD CONSTRAINT fk_bri_bk FOREIGN KEY (bk_ID) REFERENCES Books (bk_ID);

ALTER TABLE Borrow ADD CONSTRAINT fk_br_us FOREIGN KEY (user_ID) REFERENCES Users (user_ID);

ALTER TABLE SendBack ADD CONSTRAINT fk_sdb_us FOREIGN KEY (user_ID) REFERENCES Users (user_ID);

ALTER TABLE SendBackInfo ADD CONSTRAINT fk_stbi_stb FOREIGN KEY (sdb_ID) REFERENCES SendBack (sdb_ID);

ALTER TABLE SendBackInfo ADD CONSTRAINT fk_stbi_bk FOREIGN KEY (bk_ID) REFERENCES Books (bk_ID);

ALTER TABLE BookComments ADD CONSTRAINT fk_bc_us FOREIGN KEY (user_ID) REFERENCES Users (user_ID);

ALTER TABLE BookComments ADD CONSTRAINT fk_bc_bk FOREIGN KEY (bk_ID) REFERENCES Books (bk_ID);

ALTER TABLE lostBooks ADD CONSTRAINT fk_lb_bk FOREIGN KEY (bk_ID) REFERENCES Books (bk_ID);

ALTER TABLE lostBooks ADD CONSTRAINT fk_lb_us FOREIGN KEY (user_ID) REFERENCES Users (user_ID);

ALTER TABLE BooksAddInfo ADD CONSTRAINT fk_bka_bk FOREIGN KEY (bk_ID) REFERENCES Books (bk_ID);

ALTER TABLE BooksAddInfo ADD CONSTRAINT fk_bka_ad FOREIGN KEY (ad_ID) REFERENCES admins (ad_ID);

ALTER TABLE BooksDownInfo ADD CONSTRAINT fk_bkd_ad FOREIGN KEY (ad_ID) REFERENCES admins (ad_ID);

ALTER TABLE BooksDownInfo ADD CONSTRAINT fk_bkd_bk FOREIGN KEY (bk_ID) REFERENCES Books (bk_ID);


insert into BOOKTYPE values('1','专业类','0',2);
insert into BOOKTYPE values('2','文学类','0',3);
insert into BOOKTYPE values('3','杂志类','0',1);
insert into BOOKTYPE values('4','计算机','1',5);
insert into BOOKTYPE values('5','科普杂志','3',20);
insert into BOOKTYPE values('6','中篇小说','2',10);
insert into BOOKTYPE values('7','短篇小说','3',6);
insert into BOOKTYPE values('8','长篇小说','3',2);
insert into BOOKTYPE values('9','数学','1',2);

select * from books;
insert into BOOKS values('1','疯狂java讲义','李刚',95,10,1,'4');
insert into BOOKS values('2','Java学习笔记','林信良',68,20,1,'1');
insert into BOOKS values('3','老人与海','ErnestHemingway',29,15,1,'2');
insert into BOOKS values('4','Ojos de perro azul','加西亚·马尔克斯',35,5,1,'2');
insert into BOOKS values('5','蛙','莫言',32,15,1,'2');
insert into BOOKS values('6','丰乳肥臀','莫言',46,15,1,'2');
insert into BOOKS values('7','红高粱家族','莫言',32,15,1,'2');
insert into BOOKS values('8','纸牌屋1','迈克尔·道布斯',30,10,1,'2');
insert into BOOKS values('9','纸牌屋2','迈克尔·道布斯',30,10,1,'2');
insert into BOOKS values('10','纸牌屋3','迈克尔·道布斯',30,10,1,'2');
insert into BOOKS values('11','了不起的盖茨比','斯科特·菲茨杰拉德',30,10,1,'2');
insert into BOOKS values('12','阿弥陀佛么么哒','大冰',38,30,1,'2');
insert into BOOKS values('13','戒了吧！拖延症','辰格',33.3,40,1,'2');
insert into BOOKS values('14','盗墓笔记1七星鲁王宫','南派三叔',32.8,30,1,'2');
insert into BOOKS values('15','盗墓笔记2秦岭神树','南派三叔',32.8,30,1,'2');
insert into BOOKS values('16','盗墓笔记3云顶天宫','南派三叔',32.8,30,1,'2');
insert into BOOKS values('17','盗墓笔记4蛇沼鬼城','南派三叔',32.8,30,1,'2');
insert into BOOKS values('18','盗墓笔记5谜海归巢','南派三叔',32.8,30,1,'2');
insert into BOOKS values('19','盗墓笔记6阴山古楼','南派三叔',32.8,30,1,'2');
insert into BOOKS values('20','盗墓笔记7邛笼石影','南派三叔',32.8,30,1,'2');
insert into BOOKS values('21','盗墓笔记8大结局(上)','南派三叔',32.8,30,1,'2');
insert into BOOKS values('22','盗墓笔记8大结局(下)','南派三叔',32.8,30,1,'2');
insert into BOOKS values('23','全职高手1放逐斗神','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('24','全职高手2千机初芒','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('25','全职高手3副本风云','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('26','全职高手4满城追杀','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('27','全职高手5王牌对决','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('28','全职高手6神之领域','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('29','全职高手7毁人不倦','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('30','全职高手8赏金猎人','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('31','全职高手9草根战队','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('32','全职高手10四会联盟','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('33','全职高手11门票战争','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('34','全职高手12步步惊心','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('35','全职高手13圣诞之战','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('36','全职高手14线下风云','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('37','全职高手15决战嘉世','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('38','全职高手16第十赛季','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('39','全职高手17新的征程','蝴蝶蓝',25,10,1,'2');
insert into BOOKS values('40','Java编程思想','歪果仁',78,10,1,'4');
insert into BOOKS values('41','数学分析（1）','郭林等',25,10,1,'9');
insert into BOOKS values('42','数学分析（2）','郭林等',25,10,1,'9');
insert into BOOKS values('43','数学分析（3）','郭林等',25,10,1,'9');
insert into BOOKS values('44','大自然探索2008_1','四川科学技术出版社',8,10,1,'5');
insert into BOOKS values('45','大自然探索2008_2','四川科学技术出版社',8,10,1,'5');
insert into BOOKS values('46','大自然探索2008_3','四川科学技术出版社',8,10,1,'5');
insert into BOOKS values('47','科学世界2009_3','中国科学院',16,10,1,'5');
insert into BOOKS values('48','科学世界2009_4','中国科学院',16,10,1,'5');
insert into BOOKS values('49','科学世界2009_5','中国科学院',16,10,1,'5');
