package com.chat.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.chat.DAO.MsgDAO;

@Path("/sendmsg")
public class SendMsgResource {

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public void sendNewMsg(@FormParam("msg") String msg, @FormParam("user") String user, @FormParam("ts") String ts){
		System.out.println(user + " : " + msg +" : " + ts);
		try {
			MsgDAO.insertData(user,msg,ts);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
