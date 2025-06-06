package globalsolution.pluviaplus.dto;

import globalsolution.pluviaplus.models.TipoRelatorio;

import java.time.LocalDate;

public record RelatorioDto(
        int idRelatorio,
        TipoRelatorio tipo,
        LocalDate dataGeracao,
        int idUsuario
) {
}
