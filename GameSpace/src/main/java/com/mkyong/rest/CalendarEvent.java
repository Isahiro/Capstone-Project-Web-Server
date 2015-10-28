package com.mkyong.rest;

import sun.util.calendar.LocalGregorianCalendar;

/**
 * Created by student on 10/28/2015.
 */
public class CalendarEvent
{
    public String name;
    public String dateTime;
    public String game;
    public String location;
    public String description;

    public CalendarEvent() {}

    @Override
    public String toString()
    {
        return name + " " + dateTime + " " + game + " " + location + " " + description;
    }
}
