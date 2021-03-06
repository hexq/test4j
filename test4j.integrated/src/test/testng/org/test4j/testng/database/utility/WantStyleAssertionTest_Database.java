package org.test4j.testng.database.utility;

import org.test4j.database.table.ITable;
import org.test4j.database.table.TddUserTable;
import org.test4j.module.database.IDatabase;
import org.test4j.testng.Test4J;
import org.testng.annotations.Test;

@SuppressWarnings({ "serial", "unchecked" })
@Test(groups = { "test4j", "assertion", "database" })
public class WantStyleAssertionTest_Database extends Test4J implements IDatabase {

    @Test
    public void testDatabase() {
        db.table(ITable.t_tdd_user).clean().insert(2, new TddUserTable() {
            {
                this.put(IColumn.f_id, "1", "2");
                this.put(IColumn.f_first_name, "darui", "jobs");
                this.put(IColumn.f_last_name, "wu", "he");
            }
        });

        db.query("select id,first_name,last_name from tdd_user").reflectionEqMap(toList(new TddUserTable() {
            {
                this.put(IColumn.f_id, 1);
                this.put(IColumn.f_first_name, "darui");
            }
        }, new TddUserTable() {
            {
                this.put(IColumn.f_id, 2);
                this.put(IColumn.f_last_name, "he");
            }
        }));
    }
}
