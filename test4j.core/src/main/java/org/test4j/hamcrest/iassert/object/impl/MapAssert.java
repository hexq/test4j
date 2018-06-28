package org.test4j.hamcrest.iassert.object.impl;

import java.util.Map;
import java.util.Map.Entry;

import org.hamcrest.Matcher;
import org.hamcrest.collection.IsMapContaining;
import org.hamcrest.core.AllOf;
import org.test4j.hamcrest.iassert.common.impl.AllAssert;
import org.test4j.hamcrest.iassert.object.intf.IMapAssert;
import org.test4j.hamcrest.matcher.array.MapMatcher;
import org.test4j.hamcrest.matcher.array.MapMatcher.MapMatcherType;

@SuppressWarnings({ "rawtypes" })
public class MapAssert extends AllAssert<Map<?, ?>, IMapAssert> implements IMapAssert {
	
	public MapAssert() {
		super(IMapAssert.class);
		this.valueClaz = Map.class;
	}

	public MapAssert(Map<?, ?> map) {
		super(map, IMapAssert.class);
		this.valueClaz = Map.class;
	}

	public IMapAssert hasKeys(Object key, Object... keys) {
		MapMatcher matcher1 = new MapMatcher(key, MapMatcherType.KEY);
		if (keys == null || keys.length == 0) {
			return assertThat(matcher1);
		}
		int len = keys.length;
		Matcher[] arr = new MapMatcher[len + 1];
		arr[0] = matcher1;
		for (int i = 0; i < len; i++) {
			arr[i+1] = new MapMatcher(keys[i], MapMatcherType.KEY);
		}
		return assertThat(AllOf.allOf(arr));
	}

	public IMapAssert hasValues(Object value, Object... values) {
		MapMatcher matcher1 = new MapMatcher(value, MapMatcherType.VALUE);
		if (values == null || values.length == 0) {
			return assertThat(matcher1);
		}
		/*List<Matcher> list = new ArrayList<Matcher>();
		list.add(matcher1);
		for (Object item : values) {
			list.add(new MapMatcher(item, MapMatcherType.VALUE));
		}*/
		int len = values.length;
		Matcher[] arr = new MapMatcher[len + 1];
		arr[0] = matcher1;
		for (int i = 0; i < len; i++) {
			arr[i+1] = new MapMatcher(values[i], MapMatcherType.VALUE);
		}
		return assertThat(AllOf.allOf(arr));
	}

	public IMapAssert hasEntry(Object key, Object value, Object... objects) {
		Matcher<?> matcher = IsMapContaining.hasEntry(key, value);
		if (objects == null) {
			return assertThat(matcher);
		}
		int size = objects.length;
		int len = size / 2;
		Matcher[] arr = new Matcher[len + 1];
		arr[0] = matcher;
		
		for (int i = 0; i < len; i++) {
			arr[i+1] = IsMapContaining.hasEntry(objects[i * 2], objects[i * 2 + 1]);
		}
		/*List<Matcher> list = new ArrayList<Matcher>();
		list.add(matcher);
		for (int index = 0; index < size / 2; index++) {
			Matcher<?> matcher2 = IsMapContaining.hasEntry(objects[index * 2], objects[index * 2 + 1]);
			list.add(matcher2);
		}*/

		return assertThat(AllOf.allOf(arr));
	}

	public IMapAssert hasEntry(Entry<?, ?> entry, Entry<?, ?>... entries) {
		Matcher<?> matcher = IsMapContaining.hasEntry(entry.getKey(), entry.getValue());
		if (entries == null) {
			return assertThat(matcher);
		}
		int len = entries.length;
		Matcher[] arr = new Matcher[len + 1];
		arr[0] = matcher;
		for (int i = 0; i < len; i++) {
			Map.Entry<?, ?> mapEntry = entries[i];
			arr[i+1] = IsMapContaining.hasEntry(mapEntry.getKey(), mapEntry.getValue());
		}
		/*List<Matcher> list = new ArrayList<Matcher>();
		list.add(matcher);
		for (Map.Entry<?, ?> item : entries) {
			Matcher<?> matcher2 = IsMapContaining.hasEntry(item.getKey(), item.getValue());
			list.add(matcher2);
		}*/
		return assertThat(AllOf.allOf(arr));
	}
}
