1，asm 3.3.1 core，疑似这些年没人做更新，使用外代替


2，objenesis version 1.2，使用外部的v2.6版本代替

<===============================
http://objenesis.org/
升级到最新的2.6版本

删除掉android目录，修改了Android想关联的两个类：
SerializingInstantiatorStrategy.java
StdInstantiatorStrategy.java
<===============================


3，hamcrest 1.3-rc2 使用外部hamcrest-core/library 1.3

<===============================
org.hamcrest 1.3
比对的内容：
hamcrest-core:
ext.test4j.hamcrest.core.*.java
ext.test4j.hamcrest.core.*
ext.test4j.hamcrest.internal.*

备注：isEqual中的areEqual做了增强

原来的hamcrest下还包含了：
collection/number/object/xml
经过确认是hamcrest-library中的内容，已经更新并包含，包括了beans目录

AllOf中新增一个方法：allOfArgs
AnyOf中新增一个方法：anyOfArgs
<===============================


4，cglib 2.2.2，最新的版本已经是3.2.6，是否要考虑单独放到外面，这个版本还是比较经典的版本