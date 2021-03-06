package com.mkyong.rest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

    public JSONArray getEvents(String query)
    {
        JSONArray output = new JSONArray();
        try
        {
            results = statement.executeQuery(query);

                    while(results.next() )
                    {
                        JSONObject event = new JSONObject();
                        event.put("name", results.getString("eventName") );
                        event.put("dateTime", results.getString("eventDateTime"));
                        event.put("game", results.getString("eventGame"));
                        event.put("location", results.getString("eventLocation") );
                        event.put("description", results.getString("eventDescription") );

                        output.add(event);
                    }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return output;
    }

    public JSONArray getAccount(String query)
    {
        JSONArray output = new JSONArray();
        try
        {
            results = statement.executeQuery(query);

            while(results.next() )
            {
                JSONObject account = new JSONObject();
                account.put("Username", results.getString("accountUsername") );
                account.put("Password", results.getString("accountPassword") );
                account.put("Email", results.getString("accountEmail") );
                account.put("FirstName", results.getString("accountFirstName") );
                account.put("LastName", results.getString("accountLastName") );
                account.put("Birthday", results.getString("accountBirthday") );
                account.put("Privacy", results.getString("accountPrivacy") );


                output.add(account);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return output;
    }

    public JSONArray getAttending(String query)
    {
        JSONArray output = new JSONArray();
        try
        {
            results = statement.executeQuery(query);

            while(results.next() )
            {
                JSONObject attending = new JSONObject();
                attending.put("Event", results.getString("FK_eventID") );
                attending.put("Account", results.getString("FK_accountID") );

                output.add(attending);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return output;
    }

    public JSONArray getContact(String query)
    {
        JSONArray output = new JSONArray();
        try
        {
            results = statement.executeQuery(query);

            while(results.next() )
            {
                JSONObject contact = new JSONObject();
                contact.put("AccountID", results.getString("FK_accountID") );
                contact.put("CharacterName", results.getString("contactCharacter_AccountName") );
                contact.put("GameService", results.getString("contactGame_ServiceName") );
                contact.put("ServerName", results.getString("contactServerName") );

                output.add(contact);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return output;
    }

    public JSONArray getFriend(String query)
    {
        JSONArray output = new JSONArray();
        try
        {
            results = statement.executeQuery(query);

            while(results.next() )
            {
                JSONObject friend = new JSONObject();
                friend.put("FriendID1", results.getString("FK_accountID1") );
                friend.put("FriendID2", results.getString("FK_accountID2") );
                friend.put("ApprovedFriend", results.getString("friendIsApproved") );

                output.add(friend);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return output;
    }

    public JSONArray getPost(String query)
    {
        JSONArray output = new JSONArray();
        try
        {
            results = statement.executeQuery(query);

            while(results.next() )
            {
                JSONObject post = new JSONObject();
                post.put("Thread", results.getString("FK_threadID") );
                post.put("UserAccount", results.getString("FK_accountID") );
                post.put("Submitted", results.getString("postContent") );

                output.add(post);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return output;
    }

    public JSONArray getThread(String query)
    {
        JSONArray output = new JSONArray();
        try
        {
            results = statement.executeQuery(query);

            while(results.next() )
            {
                JSONObject thread = new JSONObject();
                thread.put("ThreadName", results.getString("threadName") );
                thread.put("ThreadGame", results.getString("threadGame") );

                output.add(thread);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return output;
    }
}