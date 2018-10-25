# MySQL学习笔记

## 安装
见官方安装文档

## 基础知识

### 查询元数据
+ 查询实例所有数据库, information_schema包含服务器相关信息，mysql存储用户名，密码和权限，performance_schemaz收集性能相关信息
    ```show databases;```
+ 查询当前db表信息
    ```show tables from test;```
    
## SQL 
+ DDL(Data Ddefinition Language)  
    创建新表  
    ```create table books(book_id int, title text, status int);```  

    查看表信息  
    ```desc books;```  
    ```show create table books```
+ DML(Data Manipulation Language)  
    添加新数据  
    ``` insert into books values(100, 'Heart of Darkness', 0); ```  


+ DCL(Data Control Language)  

+ TCL(transaction Control Language)   


## 优化

## 分区

## 复制

## 高可用

## 管理

## 安全

+ 安装后初始化密码  
  ```mysqladmin -u root -p flush-privileges password "new_pwd" ```

+ mysql用户表mysql.user  
    添加新用户jack,密码test  
    ```grant usage on *.* to 'jack'@'localhost' identified by 'test';```  
    ```grant select on *.* to 'jack'@'localhost';```  
    ```show grants for `jack`@`localhost`; ```  
    ```grant all on *.* to 'jack'@'localhost';```  

## 参考
[Mysql 5.7 Reference](https://dev.mysql.com/doc/refman/5.6/en/column-count-limit.html)