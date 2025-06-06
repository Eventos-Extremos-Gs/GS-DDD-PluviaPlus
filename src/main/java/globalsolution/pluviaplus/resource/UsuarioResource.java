package globalsolution.pluviaplus.resource;

import globalsolution.pluviaplus.bo.UsuarioBO;
import globalsolution.pluviaplus.dto.UsuarioDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private final UsuarioBO bo = new UsuarioBO();

    @GET // retorna nome e função de todos os usuários
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNomeEFuncoes() {
        List<UsuarioDto> lista = bo.listarNomeEFuncoes();
        return Response.ok(lista).build();
    }

    @POST // cria um novo usuario no sistema
    public Response create(UsuarioDto dto) {
        bo.create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}") // remove um usuario do sitema pelo ID
    public Response delete(@PathParam("id") int id) {
        bo.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/estatisticas/quantidade") // retorna o total de usuarios cadastrados
    public Response quantidadeTotal() {
        return Response.ok(bo.contagemTotal()).build();
    }

    @GET
    @Path("/estatisticas/tipos") // retorna a quantidade de usuários agrupados por tipo (Admin, Técnico, etc)
    public Response estatisticaPorTipo() {
        return Response.ok(bo.contagemPorTipo()).build();
    }
}


