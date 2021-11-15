package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.service.AdministradorService;

@Path("/adm")
public class AdministradorResource {
	
	private AdministradorService administrador = new AdministradorService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Administrador> buscarAdm() throws SQLException {
		return administrador.listar();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Administrador adm, @Context UriInfo uriInfo) throws SQLException{
		administrador.inserir(adm);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(Integer.toString(adm.getMatricula()));
		return Response.created(builder.build()).build();
	}
	
//	@PUT
//	@Path("/{id}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response atualizar(Administrador adm, @PathParam("id") int id) throws SQLException {
//		adm.setMatricula(id);
//		administrador.atualizar(adm);
//		return Response.ok().build();
//	}
	
	
	
}	
