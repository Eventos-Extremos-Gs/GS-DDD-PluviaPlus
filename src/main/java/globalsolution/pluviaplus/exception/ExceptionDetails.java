package globalsolution.pluviaplus.exception;

import java.time.LocalDateTime;

public class ExceptionDetails { //serve para estrutura as respostas de erro (mensagem, status, timestamp)
    private String message;
    private int status;
    private LocalDateTime timestamp;

    public ExceptionDetails(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

