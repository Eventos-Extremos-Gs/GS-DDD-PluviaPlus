package globalsolution.pluviaplus.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> { //retorna reposta rest padronizada no frontend pra cada excecao

    @Override
    public Response toResponse(Throwable exception) {

        if (exception instanceof RecursoNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ExceptionDetails(e.getMessage(), 404))
                    .build();
        }

        if (exception instanceof RequisicaoInvalidaException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ExceptionDetails(e.getMessage(), 400))
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ExceptionDetails("Erro inesperado no servidor.", 500))
                .build();
    }
}

