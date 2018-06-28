package org.test4j.hamcrest.matcher.property;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.test4j.fortest.beans.User;
import org.test4j.testng.Test4J;
import org.testng.annotations.Test;

@SuppressWarnings("unchecked")
@Test(groups = { "test4j", "assertion" })
public class PropertyAllItemsMatcherTest extends Test4J {
    PropertyAllItemsMatcher matcher = new PropertyAllItemsMatcher("first", the.string().regular("\\w+\\d+\\w+"));

    @Test
    public void testMatches_List_AllItemsMatchAll() {
        MatcherAssert.assertThat(users(), matcher);
    }

    @Test
    public void testMatches_Array_AllItemsMatchAll() {
        User[] users = users().toArray(new User[0]);
        MatcherAssert.assertThat(users, matcher);
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testMatches_List_HasItemNotMatch() {
        List<User> users = users();
        users.add(new User("aasdf", "dfddd"));
        MatcherAssert.assertThat(users, matcher);
    }

    public void testMatches_SingleValue_PropMatch() {
        want.object(new User("firs3445tname", "")).propertyMatch("first", the.string().regular("\\w+\\d+\\w+"));
    }

    @Test(expectedExceptions = AssertionError.class)
    public void testMatches_SingleValue_PropNotMatch() {
        want.object(new User("firs dddt name", "")).propertyMatch("first", the.string().regular("\\w+\\d+\\w+"));
    }

    static List<User> usersArr = null;

    private static List<User> users() {
        usersArr = new ArrayList<User>();
        usersArr.add(new User("firs3445tname", "lastname"));
        usersArr.add(new User("ee333ee", "ddddd"));

        return usersArr;
    }
}
