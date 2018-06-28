package org.test4j.hamcrest.iassert.common.impl;

import java.util.List;

import org.hamcrest.Matcher;
import org.test4j.hamcrest.iassert.common.intf.IAssert;
import org.test4j.hamcrest.iassert.common.intf.IListAssert;
import org.test4j.hamcrest.matcher.modes.ItemsMode;
import org.test4j.hamcrest.matcher.property.MapListPropertyEqaulMatcher;
import org.test4j.hamcrest.matcher.property.PropertyAllItemsMatcher;
import org.test4j.hamcrest.matcher.property.PropertyAnyItemMatcher;
import org.test4j.hamcrest.matcher.property.ReflectionEqualMatcher;
import org.test4j.hamcrest.matcher.property.reflection.EqMode;
import org.test4j.module.ICore.DataMap;
import org.test4j.tools.commons.ListHelper;
import org.test4j.tools.datagen.DataSet;

@SuppressWarnings("rawtypes")
public class ListAssert<T, E extends IAssert> extends SizeAssert<T, E> implements IAssert<T, E>, IListAssert<T, E> {
	
	public ListAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public ListAssert(T value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}

	public E isEqualTo(Object expected, EqMode... modes) {
		ReflectionEqualMatcher matcher = new ReflectionEqualMatcher(expected, modes);
		return this.assertThat(matcher);
	}

	public E eqIgnoreOrder(Object expected) {
		ReflectionEqualMatcher matcher = new ReflectionEqualMatcher(expected, new EqMode[] { EqMode.IGNORE_ORDER });
		return this.assertThat(matcher);
	}

	@SuppressWarnings("unchecked")
	public E reflectionEqMap(List<DataMap> expected, EqMode... modes) {
		List _modes = ListHelper.toList(modes);
		if (_modes.contains(EqMode.IGNORE_DEFAULTS) == false) {
			_modes.add(EqMode.IGNORE_DEFAULTS);
		}
		EqMode[] arrays = (EqMode[]) _modes.toArray(new EqMode[0]);
		MapListPropertyEqaulMatcher matcher = new MapListPropertyEqaulMatcher(expected, arrays);
		return this.assertThat(matcher);
	}

	public E propertyEqMap(int count, DataMap expected, EqMode... modes) {
		List<DataMap> lists = DataSet.parseMapList(count, expected);
		return reflectionEqMap(lists, modes);
	}

	public E reflectionEqMap(int count, DataMap expected, EqMode... modes) {
		return this.propertyEqMap(count, expected, modes);
	}

	public E propertyMatch(ItemsMode itemsMode, String property, Matcher matcher) {
		switch (itemsMode) {
		case AllItems:
			PropertyAllItemsMatcher m1 = new PropertyAllItemsMatcher(property, matcher);
			return this.assertThat(m1);
		case AnyItems:
			PropertyAnyItemMatcher m2 = new PropertyAnyItemMatcher(property, matcher);
			return this.assertThat(m2);
		default:
			throw new RuntimeException("the argument[ItemsMode] of property match API can't be null.");
		}
	}
}
