package globalsolution.pluviaplus.resource;

import globalsolution.pluviaplus.bo.DispositivoBO;
import globalsolution.pluviaplus.dto.DispositivoDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/dispositivos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DispositivoResource {

    private final DispositivoBO bo = new DispositivoBO();

    @POST // registra um novo dispositivo
    public Response create(DispositivoDto dto) {
        bo.registrar(dto);
        return Response.status(Response.Status.CREATED).build();
    }


    @GET // lista todos os dispositivos cadastrados
    public List<DispositivoDto>getAll() {
        return bo.getAll();
    }

    @GET
    @Path("/{id}") // retorna detalhes de um único dispositivo
    public DispositivoDto getByID(@PathParam("id") int id) {
        return bo.getById(id);
    }

    @GET
    @Path("/resumo") // faz um resumo dos dispositivos, retornando os modelos e as quantidades, alem da localizacao dos dispositivos
    public Response gerarResumo() {
        return Response.ok(bo.gerarResumoGeral()).build();
    }


}
