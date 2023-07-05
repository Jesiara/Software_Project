package com.bawp.heartmonitor_software_project;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

//import userDefinedClass.Userdets;

public class UnitTest {

    /**
     * testing addData method
     */
//    private Userdets DList()
//    {
//        Userdets DataList=new Userdets();
//        DataList.add(mockdata());
//        return DataList;
//    }
    private Userdets mockdata() {
        return new Userdets("05/07/2023","11:14","100","120","70","Tired");
    }

    @Test
    public void testAddData() {
        Date date = Calendar.getInstance().getTime();

        ForUnitTest dataList = new ForUnitTest();
        Userdets data1 = new Userdets("05/07/2023","11:14","100","120","70","Tired");
        dataList.add(data1);
        assertEquals(1, dataList.getData().size());

        Userdets data2 = new Userdets("06/07/2023","11:24","101","110","80","Tired");
        dataList.add(data2);
        assertEquals(2, dataList.getData().size());

        assertTrue(dataList.getData().contains(data1));
        assertTrue(dataList.getData().contains(data2));
    }

    /**
     * testing deleteData method
     */


    @Test
    public void testDeleteData() {
        Date date = Calendar.getInstance().getTime();

        ForUnitTest dataList = new ForUnitTest();
        Userdets data1 = new Userdets("05/07/2023","11:14","100","120","70","Tired");
        dataList.add(data1);
        assertEquals(1, dataList.getData().size());

        Userdets data2 = new Userdets("06/07/2023","11:24","101","110","80","Tired");
        dataList.add(data2);
        assertEquals(2, dataList.getData().size());

        assertTrue(dataList.getData().contains(data1));
        assertTrue(dataList.getData().contains(data2));

        dataList.delete(data1);
        assertEquals(1, dataList.getData().size());
        assertFalse(dataList.getData().contains(data1));

        dataList.delete(data2);
        assertEquals(0, dataList.getData().size());
        assertFalse(dataList.getData().contains(data2));
    }

    /**
     * testing addData method for exceptions
     */
    @Test
    public void testAddException() {
        Date date = Calendar.getInstance().getTime();

        ForUnitTest dataList = new ForUnitTest();
        Userdets data1 = new Userdets("06/07/2023","11:24","101","110","80","Tired");
        dataList.add(data1);

        assertThrows(IllegalArgumentException.class, () -> dataList.add(data1));
    }

    /**
     * testing deleteData method for exceptions
     */
    @Test
    public void testDeleteException() {
        Date date = Calendar.getInstance().getTime();

        ForUnitTest dataList = new ForUnitTest();
        Userdets data1 = new Userdets("05/07/2023","11:14","100","120","70","Tired");
        dataList.add(data1);

        dataList.delete(data1);

        assertThrows(IllegalArgumentException.class, () -> dataList.delete(data1));
    }

    @Test
    public void testEdit()
    {
        ForUnitTest dataList = new ForUnitTest();
        Userdets data1 = new Userdets("05/07/2023","11:14","100","120","70","Tired");
        dataList.add(data1);
        assertTrue(dataList.getData().contains(data1));
        Userdets data2 = new Userdets("06/07/2023","11:24","101","110","80","Tired");

        dataList.edit(1,data2);
        assertFalse(dataList.getData().contains(data1));
        assertTrue(dataList.getData().contains(data2));
    }


}

