package org.test4j.json.decoder.single;

import java.util.HashMap;

import org.junit.Test;
import org.test4j.json.JSON;
import org.test4j.json.helper.JSONFeature;
import org.test4j.json.helper.JSONMap;
import org.test4j.junit.Test4J;
import org.test4j.junit.annotations.DataFrom;

public class IntegerDecoderTest extends Test4J {

    @Test
    public void testDecodeSimpleValue() {
        JSONMap json = new JSONMap() {
            private static final long serialVersionUID = 1L;
            {
                this.putJSON(JSONFeature.ValueFlag, 3434);
            }
        };
        IntegerDecoder decoder = new IntegerDecoder();
        Integer d = decoder.decode(json, Integer.class, new HashMap<String, Object>());
        want.number(d).isEqualTo(3434);
    }

    @Test
    @DataFrom("simple_value")
    public void testSimpleValue(String json, int expected) {
        Integer actual = JSON.toObject(json, Integer.class);
        want.number(actual).isEqualTo(expected);
    }

    public static Object[][] simple_value() {
        return new Object[][] { { "34234", 34234 },// <br>
                { "\"34234\"", 34234 } /** <br> */
        };
    }
}
