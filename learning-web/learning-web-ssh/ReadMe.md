1.报错：
Caused by: java.lang.IncompatibleClassChangeError: class net.sf.cglib.core.DebuggingClassWriter has interface org.objectweb.asm.ClassVisitor as super class
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:760)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
	
	网上有资料说：
	原因是jar包冲突引起的。
spring-core.jar包里已经有了asm
固不用单独导入asm包.
仔细观察发现，我的spring由于昨天修改了版本号，用到了4.2，所有spring用的asm和项目本身引入的asm有冲突。项目本身还通过cglib引入了asm.把spring版本回退即可。
另外尝试下删除asm的单独引用。

