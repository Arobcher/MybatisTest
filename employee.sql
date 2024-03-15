create table employee
(
    id       int auto_increment comment '员工编号'
        primary key,
    name     varchar(5)  null comment '员工名称',
    age      int         null comment '员工年龄',
    position varchar(10) null comment '员工职位'
);