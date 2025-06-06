package globalsolution.pluviaplus.resource;

import globalsolution.pluviaplus.bo.PrevisaoClimaticaBO;
import globalsolution.pluviaplus.dto.PrevisaoClimaticaDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/previsao-climatica")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PrevisaoClimaticaResource {

    private final PrevisaoClimaticaBO bo = new PrevisaoClimaticaBO();

    @GET
    @Path("/simulada") // retorna previsões climáticas simuladas
    public List<PrevisaoClimaticaDto> getSimulada() {
        return bo.getSimulada();
    }
}
