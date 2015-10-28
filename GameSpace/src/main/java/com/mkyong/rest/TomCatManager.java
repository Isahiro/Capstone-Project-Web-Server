package com.mkyong.rest;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This class will serve as the manager between the Java application and
 * the database.  This class creates the connection obtained through Tomcat
 */
public class TomCatManager
{
    private Connection connection;
    private Statement statement;
    private ResultSet results;
    private Context initCtx;

    TomCatManager()
    {
        try
        {
            initCtx = new InitialContext();
            Context envCtx = (Context)initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/CapstoneProject");

            if (ds != null)
            {
                connection = ds.getConnection();
                statement = connection.createStatement();
            }
        }
        catch (NamingException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void getData()
    {
        try
        {
            String query = "select * from account";
            results = statement.executeQuery(query);

            System.out.println("Results obtained:");
            while(results.next() )
            {
                String username = results.getString("accountUsername");
                String fName = results.getString("accountFirstName");
                String lName = results.getString("accountLastName");

                System.out.println(username + " " + fName + " " + lName);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<CalendarEvent> getEvents(String query)
    {
        ArrayList output = new ArrayList<CalendarEvent>();
        try
        {
            results = statement.executeQuery(query);

                    while(results.next() )
                    {
                        CalendarEvent event = new CalendarEvent();
                        event.name = results.getString("eventName");
                        event.dateTime = results.getString("eventDateTime");
                        event.game = results.getString("eventGame");
                        event.location = results.getString("eventLocation");
                        event.description = results.getString("eventDescription");

                        output.add(event);
                    }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return output;
    }
}