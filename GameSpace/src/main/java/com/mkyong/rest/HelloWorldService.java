package com.mkyong.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/hello")
public class HelloWorldService
{

	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg)
	{
		TomCatManager dataBase = new TomCatManager();
		ArrayList<CalendarEvent> events;
		events = dataBase.getEvents("select * from calendarEvent");
		String output = "";

		for(CalendarEvent ce: events)
		output += ce;
 
		return Response.status(200).entity(output).build();
 
	}
 
}