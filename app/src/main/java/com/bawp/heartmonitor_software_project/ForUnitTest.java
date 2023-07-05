package com.bawp.heartmonitor_software_project;

import java.util.ArrayList;
import java.util.List;

public class ForUnitTest {
    List<Userdets> dataList=new ArrayList<>();



    public void add(Userdets data)
    {
        if(dataList.contains(data))
        {
            throw new IllegalArgumentException();
        }
        dataList.add(data);
    }



    public void delete(Userdets datadlt)
    {
        List<Userdets> datist = dataList;
        if(datist.contains(datadlt))
        {
            datist.remove(datadlt);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }



    public void edit(int pos, Userdets userdets)
    {
        dataList.set(pos,userdets);
    }

    /**
     * This returns the list
     * @return
     *      return the list
     */

    public List<Userdets> getData()
    {
        return dataList;
    }

    public List<Userdets> getData(int x)
    {

        return dataList;
    }

    public int countData()
    {
        return dataList.size();
    }
}
