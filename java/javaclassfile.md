## Java Class 文件格式学习笔记

[TOC]

### 注意事项

​	Class文件是一组以8位字节为基础单位的二进制流，各个数据项目严格按照顺序紧凑地排列在Class文件之中，中间没有添加任何分隔符。当需要占用8位字节以上的空间的数据项时，则会按照高位在前的方式分割成若干个8位字节进行存储。

### 总体结构

```java
ClassFile {
    u4             magic;
    u2             minor_version;
    u2             major_version;
    u2             constant_pool_count;
    cp_info        constant_pool[constant_pool_count-1];
    u2             access_flags;
    u2             this_class;
    u2             super_class;
    u2             interfaces_count;
    u2             interfaces[interfaces_count];
    u2             fields_count;
    field_info     fields[fields_count];
    u2             methods_count;
    method_info    methods[methods_count];
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
```

| *FieldType* term | Type        | Interpretation                                               |
| ---------------- | ----------- | ------------------------------------------------------------ |
| `B`              | `byte`      | signed byte                                                  |
| `C`              | `char`      | Unicode character code point in the Basic Multilingual Plane, encoded with UTF-16 |
| `D`              | `double`    | double-precision floating-point value                        |
| `F`              | `float`     | single-precision floating-point value                        |
| `I`              | `int`       | integer                                                      |
| `J`              | `long`      | long integer                                                 |
| `L` *ClassName*  | `reference` | an instance of class *ClassName*                             |
| `S`              | `short`     | signed short                                                 |
| `Z`              | `boolean`   | `true` or `false`                                            |
| `[`              | `reference` | one array dimension                                          |

class 文件没有做什么优化，只是忠实把java类的信息记录下来，以便后续java虚拟机进一步处理

 描述符的作用是描述字段的数据类型，方法的参数列表(包括数量，类型以及顺序)和返回值

 

### 参考

​	[j2se8](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html)

​	[jvmgo](https://github.com/zxh0/jvm.go)

​	[opcode](http://homepages.inf.ed.ac.uk/kwxm/JVM/codeByNm.html#codes_A)

​	[Java Programming/Byte Code](https://en.wikibooks.org/wiki/Java_Programming/Byte_Code)

