package com.mkyong.rest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("/hello")
public class HelloWorldService
{

	@GET
	@Path("/events")
	public Response getEvent()
	{
		TomCatManager dataBase = new TomCatManager();
		JSONArray events;
		events = dataBase.getEvents("select * from calendarEvent");
		String output = "";

		for(Object json: events)
		output += json;
 
		return Response.status(200).entity(output).build();
 
	}

	@GET
	@Path("/accounts")
	public Response getAccount()
	{
		TomCatManager dataBase = new TomCatManager();
		JSONArray account;
		account = dataBase.getAccount("select * from account");
		String output = "";

		for(Object json: account)
			output += json;

		return Response.status(200).entity(output).build();

	}

	@GET
	@Path("/events/attending")
	public Response getAttending()
	{
		TomCatManager dataBase = new TomCatManager();
		JSONArray attending;
		attending = dataBase.getAttending("select * from attending");
		String output = "";

		for(Object json: attending)
			output += json;

		return Response.status(200).entity(output).build();

	}

	@GET
	@Path("/contacts")
	public Response getContact()
	{
		TomCatManager dataBase = new TomCatManager();
		JSONArray contact;
		contact = dataBase.getContact("select * from contact");
		String output = "";

		for(Object json: contact)
			output += json;

		return Response.status(200).entity(output).build();

	}

	@GET
	@Path("/friends")
	public Response getFriend()
	{
		TomCatManager dataBase = new TomCatManager();
		JSONArray friend;
		friend = dataBase.getFriend("select * from friend");
		String output = "";

		for(Object json: friend)
			output += json;

		return Response.status(200).entity(output).build();

	}

	@GET
	@Path("/threads/submitted")
	public Response getPost()
	{
		TomCatManager dataBase = new TomCatManager();
		JSONArray post;
		post = dataBase.getPost("select * from post");
		String output = "";

		for(Object json: post)
			output += json;

		return Response.status(200).entity(output).build();

	}

	@GET
	@Path("/threads")
	public Response getThread()
	{
		TomCatManager dataBase = new TomCatManager();
		JSONArray thread;
		thread = dataBase.getThread("select * from thread");
		String output = "";

		for(Object json: thread)
			output += json;

		return Response.status(200).entity(output).build();

	}
}