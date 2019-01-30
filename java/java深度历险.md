# java深度历险
本文主要关注java主要的语言特性内部原理，学习在虚拟机和指令集的内部世界是如何实现的，了解这些内部原理能帮助我们更好的使用java语言。

#### 一个简单类

```java
public class Hello {
	
	private int m;
	
	public void inc(){
		m += 1;
	}
	
	public static void main(String[] args){
		System.out.println("hello world!");
	}
}
```

#### 编译后class文件解析

magic: 0xCAFEBABE

minor_version:0

major_version:52

constant_pool_count:29

constant_pool : .....

access_flags: ACC_PUBLIC, ACC_SUPER

this_class: org/example/Hello   

super_class: java/lang/Object   默认Object

interface_count:0  

fileds_count:1   

#0 m ACC_PRIVATE 描述符 I

method_count:3

​	#0:  < init >  默认构造函数

 

#### 工具

javap

[classpy](https://github.com/zxh0/classpy.git)