package org.test4j.module.spring.strategy.register.types;

import java.lang.reflect.Field;
import java.util.Queue;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.test4j.module.spring.strategy.register.RegisterBeanDefinition;
import org.test4j.tools.commons.AnnotationHelper;
import org.test4j.tools.commons.StringHelper;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class AutowiredPropertiesRegister extends PropertiesRegister {

	protected AutowiredPropertiesRegister(Class ownerClazz, RegisterBeanDefinition definitionRegister) {
		super(ownerClazz, definitionRegister);
	}

	@Override
	public void registerProperties(Queue<Class> registedBeanClazz) {
		Set<Field> fields = AnnotationHelper.getFieldsAnnotatedWith(ownerClazz, Autowired.class);
		for (Field field : fields) {
			// Autowired autowired = field.getAnnotation(Autowired.class);
			Qualifier qualifier = field.getAnnotation(Qualifier.class);
			String beanName = null;
			if (qualifier != null) {
				beanName = qualifier.value();
			}
			if (StringHelper.isBlankOrNull(beanName)) {
				beanName = field.getName();
			}
			Class propClazz = field.getType();
			boolean isExclude = this.definitionRegister.isExcludeProperty(beanName, propClazz);
			if (isExclude == false) {
				this.registerBean(beanName, propClazz, registedBeanClazz);
			}
		}
	}

}
