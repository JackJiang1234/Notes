## GraphQL学习笔记

### 介绍

GraphQL这个名字，Graph + Query Language，就表明了它的设计初衷是想要用类似图的方式表示数据：即不像在REST中，数据被各个API endpoint所分割，而是有关联和层次结构的被组织在一起

### 设计原则

层次性， 以产品为中心,  强类型, 客户端指定查询, 自描述

### 内容

词法，语法， 数据描述语言

#### 查询

指定查询字段

指定查询参数

### 应用场景

### 优点与缺点

查询与返回结果差不多一样结构

然而GraphQL的Application则可能只需要一两个，这相当于把复杂性和heavy lifting交给了server端和cache层，而不是资源有限，并且speed-sensitive的client端。

前后端分工

### 参考

[Specification](<https://graphql.github.io/graphql-spec/June2018/>)

[GraphQL 为何没有火起来](<https://www.zhihu.com/question/38596306/answer/137333431>)

[GraphCN](<http://graphql.cn/learn/>)

[GraphQL 在微服务架构中的实践](https://draveness.me/graphql-microservice)

[GraphiQL Demo](https://graphql.github.io/swapi-graphql/)

[ASP.NET Core&GraphQL视频](https://www.bilibili.com/video/av33252179/)