## Javacc 学习笔记

### 语法文件组成格式



```
options

PARSER_BEGIN (解析器类名)

任意Java代码

PARSER_END(解析器类名)

(production)* 

<EOF>
```

生成以下文件

xxParse.java   解析器

 xxTokenManager.java  字符扫描和词法解析

 xxConstans.java    用到的常量定义，定义token字符串

