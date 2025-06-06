package globalsolution.pluviaplus.resource;

import globalsolution.pluviaplus.bo.ProducaoAguaBO;
import globalsolution.pluviaplus.dto.ProducaoAguaDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Path("/producaoagua")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProducaoAguaResource {

    private final ProducaoAguaBO bo = new ProducaoAguaBO();

    @POST // registra um volume novo de produção de água
    public Response create(ProducaoAguaDto dto) {
        bo.create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET // retorna todos os registros de produção de água
    public List<ProducaoAguaDto> getAll() {
        return bo.getAll();
    }

    @GET
    @Path("/estatisticas/volume-por-dispositivo") // retorna volume total de água agrupado por dispositivo
    public Response getVolumeSummaryByDevice() {
        return Response.ok(bo.getVolumeSummaryByDevice()).build();
    }
}
