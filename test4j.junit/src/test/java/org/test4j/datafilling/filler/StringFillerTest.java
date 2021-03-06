package org.test4j.datafilling.filler;

import org.junit.Assert;
import org.junit.Test;
import org.test4j.datafilling.Filler;
import org.test4j.datafilling.annotations.StringValuePojo;
import org.test4j.datafilling.utils.FillDataTestConstants;

public class StringFillerTest {

	@Test
	public void testGetFilling() throws Exception {
		StringValuePojo pojo = Filler.filling(StringValuePojo.class);
		String twentyLengthString = pojo.getTwentyLengthString();
		Assert.assertNotNull("The twentyLengthString cannot be null!", twentyLengthString);
		Assert.assertTrue("The twenty length string must have a length of "
				+ FillDataTestConstants.STR_ANNOTATION_TWENTY_LENGTH + "! but it did have a length of "
				+ twentyLengthString.length(),
				twentyLengthString.length() == FillDataTestConstants.STR_ANNOTATION_TWENTY_LENGTH);

		String preciseValueString = pojo.getPreciseValueString();
		Assert.assertNotNull("The precise value string cannot be null!", preciseValueString);
		Assert.assertEquals("The expected and actual String values don't match",
				FillDataTestConstants.STR_ANNOTATION_PRECISE_VALUE, preciseValueString);
	}
}
