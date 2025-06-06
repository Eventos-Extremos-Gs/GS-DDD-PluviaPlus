package globalsolution.pluviaplus.dto;

public record ImpactoDto(
        int idImpacto,
        int idProducao,
        double co2EconomizadoKg,
        int pessoasBeneficiadas
) {
}

