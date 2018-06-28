package org.test4j.hamcrest.iassert.common.impl;

import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsIn;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsAnything;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;
import org.hamcrest.object.HasToString;
import org.test4j.hamcrest.iassert.common.intf.IAssert;
import org.test4j.hamcrest.iassert.common.intf.IBaseAssert;
import org.test4j.hamcrest.iassert.object.impl.StringAssert;
import org.test4j.hamcrest.iassert.object.intf.IStringAssert;
import org.test4j.hamcrest.matcher.clazz.ClassAssignFromMatcher;
import org.test4j.tools.commons.ListHelper;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseAssert<T, E extends IAssert> extends Assert<T, E> implements IAssert<T, E>, IBaseAssert<T, E> {
	public BaseAssert() {
		super();
	}

	public BaseAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public BaseAssert(T value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}

	public E isEqualTo(T expected) {
		Matcher matcher = IsEqual.equalTo(expected);
		return this.assertThat(matcher);
	}

	/**
	 * 做了修改，见注释
	 */
	public E eq(T expected) {
		return isEqualTo(expected);
//		Matcher matcher = IsEqual.equalTo(expected);
//		return this.assertThat(matcher);
	}

	public E isEqualTo(String message, T expected) {
		Matcher matcher = IsEqual.equalTo(expected);
		return this.assertThat(message, matcher);
	}

	public E notEqualTo(T expected) {
		Matcher matcher = IsNot.not(IsEqual.equalTo(expected));
		return this.assertThat(matcher);
	}

	public E clazIs(Class expected) {
		Matcher matcher = Is.isA(expected);
		return this.assertThat(matcher);
	}

	public E clazIsSubFrom(Class claz) {
		ClassAssignFromMatcher matcher = new ClassAssignFromMatcher(claz);
		return this.assertThat(matcher);
	}

	public E not(E matcher) {
		Matcher<T> _matcher = IsNot.not(matcher);
		return this.assertThat(_matcher);
	}

	/**
	 * 做了修改，见注释
	 */
	public E all(E matcher, E... matchers) {
//		List<Matcher> list = ListHelper.toList(matchers);
		List<Matcher<? super T>> list = ListHelper.toList(matchers);
		list.add(matcher);
		Matcher _matcher = AllOf.allOf(list);
		return this.assertThat(_matcher);
	}

	/**
	 * 做了修改，见注释
	 */
	public E any(E matcher, E... matchers) {
//		List<Matcher> list = ListHelper.toList(matchers);
		List<Matcher<? super T>> list = ListHelper.toList(matchers);
		list.add(matcher);
		Matcher _matcher = AnyOf.anyOf(list);
		return this.assertThat(_matcher);
	}

	public E in(T... values) {
		Matcher<T> matcher = IsIn.isOneOf(values);
		return this.assertThat(matcher);
	}

	public E notIn(T... values) {
		Matcher _matcher = IsNot.not(IsIn.isOneOf(values));
		return this.assertThat(_matcher);
	}

	public E same(T value) {
		Matcher _matcher = IsSame.sameInstance(value);
		return this.assertThat(_matcher);
	}

	public E any() {
		Matcher _matcher = IsAnything.anything();
		return this.assertThat(_matcher);
	}

	public E isNull() {
		Matcher _matcher = IsNull.nullValue();
		return this.assertThat(_matcher);
	}

	public E isNull(String message) {
		Matcher _matcher = IsNull.nullValue();
		return this.assertThat(message, _matcher);
	}

	public E notNull() {
		Matcher _matcher = IsNull.notNullValue();
		return this.assertThat(_matcher);
	}

	public E notNull(String message) {
		Matcher _matcher = IsNull.notNullValue();
		return this.assertThat(message, _matcher);
	}

	public E eqToString(String expected) {
		Matcher matcher = HasToString.hasToString(expected);
		return this.assertThat(matcher);
	}

	public E eqToString(IStringAssert matcher) {
		Matcher _matcher = HasToString.hasToString(matcher);
		return this.assertThat(_matcher);
	}

	public IStringAssert toStringAssert() {
		IStringAssert matcher = null;
		if (this.type == AssertType.AssertStyle) {
			matcher = new StringAssert(String.valueOf(this.value));
		} else {
			matcher = new StringAssert(true);
		}
		return (IStringAssert) matcher;
	}

	/**
	 * 做了修改，见注释
	 */
	public E notAny(Matcher matcher, Matcher... matchers) {
//		List<Matcher> ms = new ArrayList<>();
//		ms.add(matcher);
//		for (Matcher m : matchers) {
//			ms.add(m);
//		}
//		Matcher _matcher = AnyOf.notAny(AnyOf.anyOf(ms));
//		return this.assertThat(_matcher);
		
		Matcher _matcher = IsNot.not(AnyOf.anyOf(matchers));
		return this.assertThat(_matcher);
	}

	/**
	 * 做了修改，见注释
	 */
	public E notAll(Matcher matcher, Matcher... matchers) {
//		List<Matcher> ms = new ArrayList<Matcher>();
//		ms.add(matcher);
//		for (Matcher m : matchers) {
//			ms.add(m);
//		}
//		Matcher _matcher = AllOf.notAll(ms);
//		return this.assertThat(_matcher);
		
		Matcher _matcher = IsNot.not(AllOf.allOf(matchers));
		return this.assertThat(_matcher);
	}
}
