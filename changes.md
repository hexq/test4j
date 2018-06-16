
###3.0.1###
- 熟悉JMockit的过程中发现一个很不错的关于JMockit的中文网站：http://jmockit.cn/，值得一看
- 经过JMockit和Test4J的对比测试发现，Test4J的new Expectations{}的record和replay阶段都是record阶段，
  初步分析是Test4j中实现的new Expectations{}的内部类在执行完后没有完成阶段的切换，而阶段切换是在
  ActiveInvocations中的endInvocations（）方法实现的。最终分析，该方法的调用是在StartUp中的initialize（）方法中切入的。
  通过ExpectationsTransformer类的方法进行切入，分析代码发现了这么一句：BASE_CLASSES 
  = "mockit/Expectations mockit/Verifications mockit/VerificationsInOrder mockit/FullVerifications"
  很明显Test4J实现的Expectations并不在这行代码中，因此修改内容为：BASE_CLASSES 
  = "mockit/Expectations mockit/Verifications mockit/VerificationsInOrder mockit/FullVerifications org/test4j/module/jmockit/IMockit$Expectations"
  经过测试问题解决，但这需要修改jmockit的源代码，并重新打包，后续需要综合考虑解决。


###3.0.0(2018.6.15)###
- 版本升级到V3.0.0，支持JDK版本升级到JDK 1.8+
- test4j.nodep的jmockit依赖升级到v1.40，并从代码中剥离，去除启动需要配置test4j.nodep的-javaagent JVM参数配置
- 目前jmockit官方只提供了v1.39的maven仓库包，由于该jar包的基础依赖都比较新，建议自己手动打包（比如：版本升级V1.40）
- test4j.nodep的Hamcrest(1.3)/ASM(3.3.1)/CGLIB(2.2.2)/Objenesis(2.6)都从代码中做了剥离，改写并做了升级
- test4j.core由于依赖的test4j.nodep大改，对相关的代码做了相应的修改，比如：org.test4j.hamcrest.*包下实现的重写
- 重点整理了test4j.nodep, test4j.core, test4j.junit，包括单元测试代码。其他的模块只解决了编译问题，未做重点跟进测试
- 基于之前的jtester.tutorial新建test4j.tutorial分支，用来做新手入门演示代码
- 其他的代码改动和完善等，存在的问题：new Expectations并不能达到mock的效果，暂时还不能应用到生产环境
- 2.0.7之前代码做的比较好的，就是没有多余的注释代码。自己改动是对部分代码做了注释，后期建议移除


###2.0.7###
- TestScenario toString改进
- 初始化测试类异常打印
- Mix对象不再强制继承Steps接口
- fix：使用Test4JSuite时，根据 @TestFinder查找测试类的bug； 根据@Group查找测试类的bug。

###2.0.6###
- 场景测试增加BeforeScenario， AfterScenario关键字。<br> 
	BeforeScenario中定义的所有方法在每个场景之前执行。<br>
	AfterScenario中定义的所有方法在每个场景之后执行。
- 针对SIndex vm设置，增加简化写法si，方便执行单条测试。
- 增加场景测试编号
- fix SpringBeanFrromFactory初始化的nullpointer异常。

###2.0.5###
- jmockit升级到1.5版本
- Steps类中如果声明了SpringBean，框架会自动进行注入处理
- 支持@Autowired和@Resource的bean注入方式
- remove declared @Test from org.test4j.testng.Test4J