1.其中一种实现方式参考：
http://iblike.iteye.com/blog/1010242
通过MarsYoungApplication继承JAX-RS的Application接口，来管理WebServices.

2.另外一种是通过spring容器来管理。
实现参考自：
http://www.mkyong.com/webservices/jax-rs/resteasy-spring-integration-example/

比较不爽的是，每个service都需要指定，1中需要在MarsYoungApplication中手动注入：singletons.add(new RestDemo()); 2中需要在web.xml中配置。

一个一个配，会很麻烦的。。。。

所以想用：
	<context-param> 
		<param-name>resteasy.scan</param-name> 
		<param-value>true</param-value> 
	</context-param>
但是这个是个坑，配置了也找不到对应的service.


通过外部的tomcat启动，最终可以访问了。需要修改WebProjectSettings为 /。

突然出现了这个：通过RunOnServer启动的时候：
 Project facet Java version 1.8 is not supported.
 查找之后原来是eclipse中修改了默认的jre,换成了jdk8，而原有的server在配置的时候是用的默认的，删除了就好。
 

通过tomcat plugin启动，报错：
java.util.concurrent.ExecutionException: org.apache.catalina.LifecycleException: Failed to start component [StandardEngine[Tomcat].StandardHost[localhost].StandardContext[]]
	at java.util.concurrent.FutureTask.report(FutureTask.java:122)
	at java.util.concurrent.FutureTask.get(FutureTask.java:192)
	at org.apache.catalina.core.ContainerBase.startInternal(ContainerBase.java:1123)
	at org.apache.catalina.core.StandardHost.startInternal(StandardHost.java:800)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:150)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1559)
	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1549)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
	
	
解决：
看到异常栈中有这个提示：
Caused by: java.lang.LinkageError: loader constraint violation: loader (instance of org/apache/catalina/loader/WebappClassLoader) previously initiated loading for a different type with name "javax/servlet/ServletContext"

试着把servlet的dependency去掉试试。

果然，去掉之后没有问题了！！！！！

4.问题是项目报错，需要手动设置build path，引入tomcat运行环境。


5.配置jetty.
http://www.eclipse.org/jetty/documentation/current/jetty-maven-plugin.html#configuring-jetty-container
http://mvnrepository.com/artifact/org.eclipse.jetty/jetty-maven-plugin

启动后请求正常，但是会报错：
[ERROR] failed to execute
javax.ws.rs.NotFoundException: Could not find resource for full path: http://localhost:8081/favicon.ico


