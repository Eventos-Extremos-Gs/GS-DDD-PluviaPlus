package globalsolution.pluviaplus.resource;

import globalsolution.pluviaplus.bo.ImpactoBO;
import globalsolution.pluviaplus.dto.ImpactoDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Map;

@Path("/impactos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ImpactoResource {

    private final ImpactoBO bo = new ImpactoBO();

    @GET // retorna todos os registros de impacto
    public List<ImpactoDto> getAll() {
        return bo.getAll();
    }

    @GET
    @Path("/estatisticas/totais") // retorna agregação por região ou total geral
    public Map<String, Object> getEstatisticasTotais() {
        return bo.getEstatisticasTotais();
    }
}
