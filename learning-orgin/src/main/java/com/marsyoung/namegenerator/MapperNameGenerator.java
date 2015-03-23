package com.marsyoung.namegenerator;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

public class MapperNameGenerator implements BeanNameGenerator{

	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		return definition.getFactoryBeanName()+"_"+definition.getBeanClassName();
	}
	
}
