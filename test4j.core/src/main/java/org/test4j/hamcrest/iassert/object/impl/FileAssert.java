package org.test4j.hamcrest.iassert.object.impl;

import java.io.File;

import org.hamcrest.Matcher;
import org.test4j.hamcrest.iassert.common.impl.BaseAssert;
import org.test4j.hamcrest.iassert.object.intf.IFileAssert;
import org.test4j.hamcrest.matcher.file.FileExistsMatcher;
import org.test4j.hamcrest.matcher.file.FileExistsMatcher.FileExistsMatcherType;
import org.test4j.hamcrest.matcher.file.FileMatchers;

public class FileAssert extends BaseAssert<File, IFileAssert> implements IFileAssert {
	public FileAssert() {
		super(IFileAssert.class);
		this.valueClaz = File.class;
	}

	public FileAssert(File file) {
		super(file, IFileAssert.class);
		this.valueClaz = File.class;
	}

	public IFileAssert isExists() {
		FileExistsMatcher matcher = new FileExistsMatcher((File) this.value, FileExistsMatcherType.ISEXISTS);
		return this.assertThat(matcher);
	}

	public IFileAssert unExists() {
		FileExistsMatcher matcher = new FileExistsMatcher((File) this.value, FileExistsMatcherType.UNEXISTS);
		return this.assertThat(matcher);
	}

	public IFileAssert nameContain(String expected) {
		Matcher<?> matcher = FileMatchers.nameContain(expected);
		return this.assertThat(matcher);
	}

	public IFileAssert nameEq(String expected) {
		Matcher<?> matcher = FileMatchers.nameEq(expected);
		return this.assertThat(matcher);
	}
}
