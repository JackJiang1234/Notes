# 良好代码实践
编写可维护高质量的代码有利于公司后期项目的维护和沟通，但编写高质量代码很难制定统一的标准，也难以给出具体可度量的指标，本文结合大家日常代码开发中最常见的问题和公司项目情况，提出一些行业推荐的良好实践建议，希望帮助大家编写更优秀的代码

## 前言
本文主要从代码命名，方法编写和重构的角度，结合具体的实例来给予说明，很多建议来自网上和已出版软件工程书籍，具体来源将在文末参考给出

## 命名
命名要准确完全表达所代表的事务,自解释和恰如其份,用最短的长度表达最大的信息量

### 变量命名

#### 示例
|变量用途           |          good                       |          avoid         | 
------------------ | ------------------------------------| -----------------------| 
|收入金额/总额      |      revenueAmount/revenueTotal     |         amount, number |
|当前日期           |      currentDate, todaysDate        |         d, date, cd    |
|每页行数           |      pageSize, linesPerPage         |         size, lines    |
|缓存分钟数         |      cachingInMinutes               |         cm,time        |     

#### 表示计算结果
有些常用的表示计算结果的变量如总额，平均值，最大值等，建议结合问题域名词再加上相应的限定词,如revenueTotal, revenueAverage, revenueMax等

#### 变量命名使用对仗词
有些场景可能需要用一些对仗词作为变量前后缀，使用符合英语习惯的对仗词可提高可读性，下面是一些常用的对仗词，
begin/end, 
start/finish
first/last,
locked/unlocked
min/max
next/previous
old/new
opened/closed
visible/invisable
source/destination
up/down
request/reponse 
active/inactive
enable/disable

#### 循环变量
```C#
foreach(var order in Orders) 
{
    
}
foreach(var invoice in Invoices)
{
}

foreach(var wo in workOrders)
{
}
```
#### bool变量
 一些典型的bool变量名done, error, found, succes or ok， completed, 不推荐在bool变量前加is 如isdone，iserror导致可读性还略差，人们一般习惯正向思维，尽量使用肯定的bool布量名, 反向导致较难阅读理解，如`if not notdone`

### 方法命名

### 类命名

## 可维护代码

### 
