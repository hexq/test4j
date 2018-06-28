package org.test4j.hamcrest.iassert.common.impl;

import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.test4j.hamcrest.iassert.common.intf.IAssert;
import org.test4j.hamcrest.iassert.common.intf.ISizedAssert;
import org.test4j.hamcrest.matcher.array.SizeOrLengthMatcher;
import org.test4j.hamcrest.matcher.array.SizeOrLengthMatcher.SizeOrLengthMatcherType;

@SuppressWarnings("rawtypes")
public class SizeAssert<T, E extends IAssert> extends ReflectionAssert<T, E> implements IAssert<T, E>, ISizedAssert<E> {

	public SizeAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public SizeAssert(T value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}

	// following are size equal matcher
	public E sizeIs(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size, SizeOrLengthMatcherType.EQ);
		return this.assertThat(matcher);
	}

	public E sizeEq(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size, SizeOrLengthMatcherType.EQ);
		return this.assertThat(matcher);
	}

	public E sizeGe(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size, SizeOrLengthMatcherType.GE);
		return this.assertThat(matcher);
	}

	public E sizeGt(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size, SizeOrLengthMatcherType.GT);
		return this.assertThat(matcher);
	}

	public E sizeLe(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size, SizeOrLengthMatcherType.LE);
		return this.assertThat(matcher);
	}

	public E sizeLt(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size, SizeOrLengthMatcherType.LT);
		return this.assertThat(matcher);
	}

	public E sizeBetween(int min, int max) {
		SizeOrLengthMatcher geMatcher = new SizeOrLengthMatcher(min, SizeOrLengthMatcherType.GE);
		SizeOrLengthMatcher leMatcher = new SizeOrLengthMatcher(max, SizeOrLengthMatcherType.LE);
		Matcher matcher = AllOf.allOf(geMatcher, leMatcher);
		return this.assertThat(matcher);
	}

	public E sizeNe(int size) {
		SizeOrLengthMatcher matcher = new SizeOrLengthMatcher(size, SizeOrLengthMatcherType.NE);
		return this.assertThat(matcher);
	}
}
