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

