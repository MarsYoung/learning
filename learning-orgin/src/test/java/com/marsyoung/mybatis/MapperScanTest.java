package com.marsyoung.mybatis;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
//import org.mybatis.spring.mapper.AnnotatedMapper;
//import org.mybatis.spring.mapper.MapperInterface;
//import org.mybatis.spring.mapper.MapperSubinterface;
//import org.mybatis.spring.mapper.child.MapperChildInterface;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.marsyoung.annotation.Mapper;
public class MapperScanTest {
	 private AnnotationConfigApplicationContext applicationContext;

	  @Before
	  public void setupContext() {
	    applicationContext = new AnnotationConfigApplicationContext();

	    setupSqlSessionFactory("sqlSessionFactory");

	    // assume support for autowiring fields is added by MapperScannerConfigurer
	    // via
	    // org.springframework.context.annotation.ClassPathBeanDefinitionScanner.includeAnnotationConfig
	  }

	  private void startContext() {
	    applicationContext.refresh();
	    applicationContext.start();

	    // this will throw an exception if the beans cannot be found
	    applicationContext.getBean("sqlSessionFactory");
	  }

	  @After
	  public void assertNoMapperClass() {
	    // concrete classes should always be ignored by MapperScannerPostProcessor
	    assertBeanNotLoaded("mapperClass");

	    // no method interfaces should be ignored too
	    assertBeanNotLoaded("package-info");
	    // assertBeanNotLoaded("annotatedMapperZeroMethods"); // as of 1.1.0 mappers
	    // with no methods are loaded

	    applicationContext.destroy();
	  }

	  @Test
	  public void testInterfaceScan() {
	    applicationContext.register(AppConfigWithPackageScan.class);

	    startContext();

	    // all interfaces with methods should be loaded
	    applicationContext.getBean("mapperInterface");
	    applicationContext.getBean("mapperSubinterface");
	    applicationContext.getBean("mapperChildInterface");
	    applicationContext.getBean("annotatedMapper");
	  }

	  @Test
	  public void testInterfaceScanWithPackageClasses() {
	    applicationContext.register(AppConfigWithPackageClasses.class);

	    startContext();

	    // all interfaces with methods should be loaded
	    applicationContext.getBean("mapperInterface");
	    applicationContext.getBean("mapperSubinterface");
	    applicationContext.getBean("mapperChildInterface");
	    applicationContext.getBean("annotatedMapper");
	  }

	  @Test
	  public void testNameGenerator() {
	    applicationContext.register(AppConfigWithNameGenerator.class);

	    startContext();

	    // only child inferfaces should be loaded and named with its class name
//	    applicationContext.getBean(MapperInterface.class.getName());
//	    applicationContext.getBean(MapperSubinterface.class.getName());
//	    applicationContext.getBean(MapperChildInterface.class.getName());
//	    applicationContext.getBean(AnnotatedMapper.class.getName());
	  }

	  @Test
	  public void testMarkerInterfaceScan() {
	    applicationContext.register(AppConfigWithMarkerInterface.class);

	    startContext();

	    // only child inferfaces should be loaded
	    applicationContext.getBean("mapperSubinterface");
	    applicationContext.getBean("mapperChildInterface");

	    assertBeanNotLoaded("mapperInterface");
	    assertBeanNotLoaded("annotatedMapper");
	  }

	  @Test
	  public void testAnnotationScan() {
	    applicationContext.register(AppConfigWithAnnotation.class);

	    startContext();

	    // only annotated mappers should be loaded
	    applicationContext.getBean("annotatedMapper");
	    applicationContext.getBean("mapperChildInterface");

	    assertBeanNotLoaded("mapperInterface");
	    assertBeanNotLoaded("mapperSubinterface");
	  }

	  @Test
	  public void testMarkerInterfaceAndAnnotationScan() {
	    applicationContext.register(AppConfigWithMarkerInterfaceAndAnnotation.class);

	    startContext();

	    // everything should be loaded but the marker interface
	    applicationContext.getBean("annotatedMapper");
	    applicationContext.getBean("mapperSubinterface");
	    applicationContext.getBean("mapperChildInterface");

	    assertBeanNotLoaded("mapperInterface");
	  }

	  @Test
	  public void testScanWithNameConflict() {
	    GenericBeanDefinition definition = new GenericBeanDefinition();
	    definition.setBeanClass(Object.class);
	    applicationContext.registerBeanDefinition("mapperInterface", definition);

	    applicationContext.register(AppConfigWithPackageScan.class);

	    startContext();

	    assertSame("scanner should not overwite existing bean definition", applicationContext.getBean("mapperInterface").getClass(), Object.class);
	  }

	  private void setupSqlSessionFactory(String name) {
	    GenericBeanDefinition definition = new GenericBeanDefinition();
	    definition.setBeanClass(SqlSessionFactoryBean.class);
	    //definition.getPropertyValues().add("dataSource", new MockDataSource());
	    applicationContext.registerBeanDefinition(name, definition);
	  }

	  private void assertBeanNotLoaded(String name) {
	    try {
	      applicationContext.getBean(name);
	      fail("Spring bean should not be defined for class " + name);
	    } catch (NoSuchBeanDefinitionException nsbde) {
	      // success
	    }
	  }

	  @Test
	  public void testScanWithExplicitSqlSessionFactory() {
	    applicationContext.register(AppConfigWithSqlSessionFactory.class);

	    startContext();

	    // all interfaces with methods should be loaded
	    applicationContext.getBean("mapperInterface");
	    applicationContext.getBean("mapperSubinterface");
	    applicationContext.getBean("mapperChildInterface");
	    applicationContext.getBean("annotatedMapper");
	  }

	  @Test
	  public void testScanWithExplicitSqlSessionTemplate() throws Exception {
	    GenericBeanDefinition definition = new GenericBeanDefinition();
	    definition.setBeanClass(SqlSessionTemplate.class);
	    ConstructorArgumentValues constructorArgs = new ConstructorArgumentValues();
	    constructorArgs.addGenericArgumentValue(new RuntimeBeanReference("sqlSessionFactory"));
	    definition.setConstructorArgumentValues(constructorArgs);
	    applicationContext.registerBeanDefinition("sqlSessionTemplate", definition);

	    applicationContext.register(AppConfigWithSqlSessionTemplate.class);
	    
	    startContext();

	    // all interfaces with methods should be loaded
	    applicationContext.getBean("mapperInterface");
	    applicationContext.getBean("mapperSubinterface");
	    applicationContext.getBean("mapperChildInterface");
	    applicationContext.getBean("annotatedMapper");
	    
	  }

	  @Configuration
	  @MapperScan("org.mybatis.spring.mapper")
	  public static class AppConfigWithPackageScan {
	  }

	  @Configuration
	  @MapperScan(basePackageClasses = Mapper.class)
	  public static class AppConfigWithPackageClasses {
	  }

	  @Configuration
	  @MapperScan(basePackages = "org.mybatis.spring.mapper", markerInterface = Mapper.class)
	  public static class AppConfigWithMarkerInterface {
	  }

	  @Configuration
	  @MapperScan(basePackages = "org.mybatis.spring.mapper", annotationClass = Component.class)
	  public static class AppConfigWithAnnotation {
	  }

	  @Configuration
	  @MapperScan(basePackages = "org.mybatis.spring.mapper", annotationClass = Component.class, markerInterface = Mapper.class)
	  public static class AppConfigWithMarkerInterfaceAndAnnotation {
	  }

	  @Configuration
	  @MapperScan(basePackages = "org.mybatis.spring.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
	  public static class AppConfigWithSqlSessionTemplate {
	  }

	  @Configuration
	  @MapperScan(basePackages = "org.mybatis.spring.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
	  public static class AppConfigWithSqlSessionFactory {
	  }

	  @Configuration
	  @MapperScan(basePackages = "org.mybatis.spring.mapper", nameGenerator = MapperScanTest.BeanNameGenerator.class)
	  public static class AppConfigWithNameGenerator {
	  }

	  public static class BeanNameGenerator implements org.springframework.beans.factory.support.BeanNameGenerator {

	    public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry definitionRegistry) {
	      return beanDefinition.getBeanClassName();
	    }

	  }
	
}
