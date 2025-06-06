package globalsolution.pluviaplus.resource;

import globalsolution.pluviaplus.bo.RelatorioBO;
import globalsolution.pluviaplus.dto.RelatorioDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/relatorios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RelatorioResource {

    private final RelatorioBO bo = new RelatorioBO();

    @POST // cria novo relatório
    public Response create(RelatorioDto dto) {
        bo.create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET // lista todos os relatórios
    public List<RelatorioDto> getAll() {
        return bo.getAll();
    }

    @DELETE
    @Path("/{id}") // remove relatório pelo ID
    public Response delete(@PathParam("id") int id) {
        bo.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
