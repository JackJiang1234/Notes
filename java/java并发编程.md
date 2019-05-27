## Java并发编程

### 核心问题

分工指如何高效拆分任务并分配给线程

同步指线程之间如何协作

互斥保证同一时刻只允许一个线程访问共享资源

#### 信号量

#### 管程

#### 可见性

指一个线程的修改结果是否为另一个线程可见

缓存导致了可见性问题

#### 原子性

线程切换造成了原子性问题

#### 有序性

编译器优化可以会调整程序执行顺序，造成有序性问题

#### Java内存模型

解决可见性和有序性问题

内存模型规范了JVM如何提供按需禁用缓存和编译优化方法，包括volatile, synchronized, final以及Happens-Before规则.

happen-before指前面一个的操作结果对后续操作可见

#### Volatile

禁用缓存，JDK1.5后增加happen-before规则

#### Happen-before规则

1. 程序的顺序性规则
2. 对一个volatile变量的写操作，happen-before于后续对这个volatile变量的读操作
3. 传递性 a happen-before b and b happen-before c ,那么 a happen-before c
4. 锁的解锁happen-before于后续对这个锁的加锁
5. 线程start规则主线程A启动子线程B后，子线程B能够看到主线程在启动子线程前的操作
6. 线程join规则主线程A调用线程B的join并成功返回，线程B的中的任意操作happen-before于join操作返回后。

#### 互斥锁解决原子性问题

