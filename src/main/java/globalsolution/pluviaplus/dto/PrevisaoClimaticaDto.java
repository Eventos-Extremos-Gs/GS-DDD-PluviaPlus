package globalsolution.pluviaplus.dto;

import java.time.LocalDate;

public record PrevisaoClimaticaDto(
        int idPrevisao,
        int idLocalizacao,
        LocalDate dataPrevisao,
        double umidadeRelativa,
        double temperaturaCelsius
) {
}
