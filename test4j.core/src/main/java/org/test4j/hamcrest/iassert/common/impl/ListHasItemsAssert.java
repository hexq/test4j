package org.test4j.hamcrest.iassert.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsArrayContaining;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.IsCollectionContaining;
import org.test4j.hamcrest.iassert.common.intf.IAssert;
import org.test4j.hamcrest.iassert.common.intf.IListHasItemsAssert;
import org.test4j.hamcrest.matcher.array.ListEveryItemMatcher;
import org.test4j.hamcrest.matcher.modes.ItemsMode;
import org.test4j.hamcrest.matcher.modes.MatchMode;

@SuppressWarnings({ "rawtypes" })
public class ListHasItemsAssert<T, E extends IAssert> extends BaseAssert<T, E> implements IListHasItemsAssert<E> {

	public ListHasItemsAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public ListHasItemsAssert(T value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}

	public E hasItems(Object item) {
		assert valueClaz != null : "the value asserted must not be null";
		if (this.valueClaz == Object[].class) {
			return this.assertThat(IsArrayContaining.hasItemInArray(item));
		} else {
			return this.assertThat(IsCollectionContaining.hasItem(item));
		}
	}

	public E hasItems(Object item, Object... items) {
		return this.hasAllItems(item, items);
	}

	public E hasAllItems(Object item, Object... items) {
		List<Matcher<? super T>> list = this.getHasItemMatchers(item, items);
		Matcher matcher = AllOf.allOf(list);
		return this.assertThat(matcher);
	}

	public E hasAnyItems(Object item, Object... items) {
		List<Matcher<? super T>> list = this.getHasItemMatchers(item, items);
		Matcher matcher = AnyOf.anyOf(list);
		return this.assertThat(matcher);
	}

	private List<Matcher<? super T>> getHasItemMatchers(Object item, Object... items) {
		assert valueClaz != null : "the value asserted must not be null";
		List<Matcher<? super T>> list = new ArrayList<>();

		if (this.valueClaz == Object[].class) { //array
			list.add((Matcher)IsArrayContaining.hasItemInArray(item));
		} else {
			list.add((Matcher)IsCollectionContaining.hasItem(item));
		}
		if (items == null || items.length == 0) {
			return list;
		}
		for (Object it : items) {
			if (this.valueClaz == Object[].class) {
				list.add((Matcher)IsArrayContaining.hasItemInArray(it));
			} else {
				list.add((Matcher)IsCollectionContaining.hasItem(it));
			}
		}
		return list;
	}

	public E match(ItemsMode itemsMode, MatchMode matchMode, Matcher matcher, Matcher... matchers) {
		if (itemsMode == ItemsMode.AllItems && matchMode == MatchMode.MatchAll) {
			return this.allItemsMatchAll(matcher, matchers);
		} else if (itemsMode == ItemsMode.AllItems && matchMode == MatchMode.MatchAny) {
			return this.allItemsMatchAny(matcher, matchers);
		} else if (itemsMode == ItemsMode.AnyItems && matchMode == MatchMode.MatchAll) {
			return this.anyItemsMatchAll(matcher, matchers);
		} else if (itemsMode == ItemsMode.AnyItems && matchMode == MatchMode.MatchAny) {
			return this.anyItemsMatchAny(matcher, matchers);
		} else {
			throw new RuntimeException("the arguments[ItmesMode and MatchMode] of items match API can't be null.");
		}
	}

	private Matcher getAnyItemsMatchAll(Matcher matcher, Matcher... matchers) {
		List<Matcher<? super T>> list = new ArrayList<>();
		list.add(matcher);
		for (Matcher<? super T> m : matchers) {
			list.add(m);
		}
		Matcher allItems = AllOf.allOf(list);
		return new ListEveryItemMatcher(allItems, ItemsMode.AnyItems);
	}

	private List<Matcher<? super T>> getItemsMatchers(ItemsMode itemsMode, Matcher matcher, Matcher... matchers) {
		List<Matcher<? super T>> list = new ArrayList<>();

		ListEveryItemMatcher m1 = new ListEveryItemMatcher(matcher, itemsMode);
		list.add(m1);
		if (matchers == null || matchers.length == 0) {
			return list;
		}
		for (Matcher m : matchers) {
			ListEveryItemMatcher m2 = new ListEveryItemMatcher(m, itemsMode);
			list.add(m2);
		}
		return list;
	}

	public E allItemsMatchAll(Matcher matcher, Matcher... matchers) {
		List<Matcher<? super T>> list = this.getItemsMatchers(ItemsMode.AllItems, matcher, matchers);
		return this.assertThat(AllOf.allOf(list));
	}

	public E allItemsMatchAny(Matcher matcher, Matcher... matchers) {
		List<Matcher<? super T>> list = this.getItemsMatchers(ItemsMode.AllItems, matcher, matchers);
		return this.assertThat(AnyOf.anyOf(list));
	}

	public E anyItemsMatchAll(Matcher matcher, Matcher... matchers) {
		Matcher m = this.getAnyItemsMatchAll(matcher, matchers);
		return this.assertThat(m);
	}

	public E anyItemsMatchAny(Matcher matcher, Matcher... matchers) {
		List<Matcher<? super T>> list = this.getItemsMatchers(ItemsMode.AnyItems, matcher, matchers);
		return this.assertThat(AnyOf.anyOf(list));
	}
}
