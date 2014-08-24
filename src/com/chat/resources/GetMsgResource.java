package com.chat.resources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.chat.DAO.MsgDAO;

@Path("/getmsg")
public class GetMsgResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Object getNewMsg(@QueryParam("lastTime") String lastTime){
		JSONObject jo = null;
		try {
			jo = new JSONObject(MsgDAO.getAllData(lastTime));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jo.toJSONString();
	}
}
