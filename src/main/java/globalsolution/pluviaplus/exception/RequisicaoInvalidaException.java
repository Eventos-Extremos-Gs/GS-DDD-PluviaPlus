package globalsolution.pluviaplus.exception;

public class RequisicaoInvalidaException extends RuntimeException { //serve para erros de entrada, com campos faltando, erro de formatos e etc.
    public RequisicaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
