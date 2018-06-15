package org.test4j.hamcrest.iassert.object.impl;

import java.util.Arrays;

import org.hamcrest.Matcher;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.IsNot;
import org.test4j.hamcrest.iassert.common.impl.ComparableAssert;
import org.test4j.hamcrest.iassert.object.intf.IStringAssert;
import org.test4j.hamcrest.matcher.mockito.Matches;
import org.test4j.hamcrest.matcher.string.StringContainMatcher;
import org.test4j.hamcrest.matcher.string.StringContainsInOrder;
import org.test4j.hamcrest.matcher.string.StringEndWithMatcher;
import org.test4j.hamcrest.matcher.string.StringEqualMatcher;
import org.test4j.hamcrest.matcher.string.StringMatcher;
import org.test4j.hamcrest.matcher.string.StringMode;
import org.test4j.hamcrest.matcher.string.StringNotBlankMatcher;
import org.test4j.hamcrest.matcher.string.StringStartWithMatcher;

@SuppressWarnings({ "rawtypes" })
public class StringAssert extends ComparableAssert<String, IStringAssert> implements IStringAssert {
	public StringAssert() {
		super(IStringAssert.class);
		this.valueClaz = String.class;
	}

	public StringAssert(boolean toString) {
		super(IStringAssert.class);
		if (toString) {
			this.value = "toString";
		}
		this.valueClaz = String.class;
	}

	public StringAssert(String str) {
		super(str, IStringAssert.class);
		this.valueClaz = String.class;
	}

	public IStringAssert isEqualTo(String expected, StringMode... modes) {
		StringMatcher matcher = new StringEqualMatcher(expected);
		matcher.setStringModes(modes);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert contains(String expected, StringMode... modes) {
		StringContainMatcher matcher = new StringContainMatcher(new String[] { expected }, modes);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert contains(String[] expecteds, StringMode... modes) {
		StringContainMatcher matcher = new StringContainMatcher(expecteds, modes);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert end(String expected, StringMode... modes) {
		StringEndWithMatcher matcher = new StringEndWithMatcher(expected);
		matcher.setStringModes(modes);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert start(String expected, StringMode... modes) {
		StringStartWithMatcher matcher = new StringStartWithMatcher(expected);
		matcher.setStringModes(modes);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert notContain(String sub, StringMode... modes) {
		StringContainMatcher matcher = new StringContainMatcher(new String[] { sub }, modes);
		Matcher _matcher = IsNot.not(matcher);
		return this.assertThat(_matcher);
	}

	public IStringAssert notContain(String[] subs, StringMode... modes) {
		/*List<Matcher> matchers = new ArrayList<>();
		for (String sub : subs) {
			StringContainMatcher matcher = new StringContainMatcher(new String[] { sub }, modes);
			matchers.add(matcher);
		}

		Matcher _matcher = IsNot.not(AnyOf.anyOf(matchers));
		return this.assertThat(_matcher);*/
		
		Matcher matcher = new StringContainMatcher(subs, modes);
		Matcher _matcher = IsNot.not(AnyOf.anyOf(matcher));
		return this.assertThat(_matcher);
	}

	public IStringAssert eqIgnoreCase(String string) {
		StringMatcher matcher = new StringEqualMatcher(string);
		matcher.setStringModes(StringMode.IgnoreCase);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert eqWithStripSpace(String string) {
		StringMatcher matcher = new StringEqualMatcher(string);
		matcher.setStringModes(StringMode.SameAsSpace);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert eqIgnoreSpace(String string) {
		StringMatcher matcher = new StringEqualMatcher(string);
		matcher.setStringModes(StringMode.IgnoreSpace);
		return this.assertThat("expect equal when ignore all space.", matcher);
	}

	public IStringAssert regular(String regex) {
		Matcher matcher = new Matches(regex);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert notBlank() {
		Matcher matcher = new StringNotBlankMatcher();
		return this.assertThat("expect string is blank", matcher);
	}

	public IStringAssert containsInOrder(String... expecteds) {
		Iterable<String> substrings = Arrays.asList(expecteds);
		StringContainsInOrder matcher = new StringContainsInOrder(substrings);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert containsInOrder(String[] expecteds, StringMode... modes) {
		Iterable<String> substrings = Arrays.asList(expecteds);
		StringContainsInOrder matcher = new StringContainsInOrder(substrings, modes);
		return (IStringAssert) this.assertThat(matcher);
	}

	public IStringAssert eq(String expected, StringMode... modes) {
		return this.isEqualTo(expected, modes);
	}
}
