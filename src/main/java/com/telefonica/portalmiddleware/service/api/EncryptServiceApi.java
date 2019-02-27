package com.telefonica.portalmiddleware.service.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.telefonica.portalmiddleware.utils.Utils;

@Path("/encrypt/{plainText}")
public class EncryptServiceApi {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String encript(@PathParam("plainText") String plainText)throws Exception{
		return Utils.encrypt(plainText);
	}
}
