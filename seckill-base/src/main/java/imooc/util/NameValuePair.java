/*
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDFS Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDFS Home Page http://www.csource.org/ for more detail.
*/

package imooc.util;

/**
* @author yan
*/
public class NameValuePair
{
    protected String name;
    protected String value;

    public NameValuePair()
    {
    }

    public NameValuePair(String name)
    {
        this.name = name;
    }

    public NameValuePair(String name, String value)
    {
        this.name = name;
        this.value = value;
    }

    public String getName()
    {
        return this.name;
    }

    public String getValue()
    {
        return this.value;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
