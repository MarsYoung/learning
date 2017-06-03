#1.No mapping found for dependency [type=com.opensymphony.xwork2.ObjectFactory, name='default']

Solution:

<filter-name>struts2</filter-name>
<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
	<init-param>  
		<param-name>config</param-name>  
		<param-value>struts-default.xml,struts-plugin.xml,struts2/struts-*.xml</param-value>  
	</init-param>  
</filter>

即,必须添加struts-default.xml(必须),struts-plugin.xml(可选)二个额外的配置文件.