Layered Systems

即为分层系统，将不同软件间相同的组成结构抽象成一层，

不同软件的相同层具有相似的实现方式，为了减少软件内部的耦合性，提高代码可重复利用性，故将软件分层处理。

此Pos系统利用了三层结构，分别为用户交互层，业务逻辑层，数据库连接层。

业务逻辑层是连接用户交互层与数据库连接层的桥梁，所有“功能”均在业务逻辑层实现，用户交互层只负责调用，数据库连接层只负责把业务逻辑层处理后的数据进行存储，或是读取操作。

![image-20220308141410435](https://gitee.com/cosie/markdown-pic/raw/master/img/202203081414890.png)

* biz为业务逻辑层，PosService接口规定了系统应该实现的功能，PosServiceImp是对PosService的一个实现
* cli为用户交互层，主要用Spring框架的Shell模块实现，当用户输入命令时，会调用业务逻辑层PosServiceImp的具体实现方法，而不是在用户交互层PosCommand实现
* db为数据库连接层 PosDB规定了数据库应实现的功能，PosInMemoryDB是PosDB的一个实现

耦合性下降，cli可由前端人员开发，biz和db由后端人员开发，提高开发效率