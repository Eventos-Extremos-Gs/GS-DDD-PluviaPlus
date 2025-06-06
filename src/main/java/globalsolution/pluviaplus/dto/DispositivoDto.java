package globalsolution.pluviaplus.dto;

public record DispositivoDto(
        int idDispositivo,
        String nome,
        String modelo,
        int idLocalizacao
) {
}