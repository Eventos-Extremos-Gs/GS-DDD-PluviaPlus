package globalsolution.pluviaplus.exception;

public class RecursoNaoEncontradoException extends RuntimeException { // usado quando nao encontra um dado
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
