package globalsolution.pluviaplus.bo;

import globalsolution.pluviaplus.dto.ImpactoDto;
import globalsolution.pluviaplus.exception.RecursoNaoEncontradoException;
import globalsolution.pluviaplus.exception.RequisicaoInvalidaException;
import globalsolution.pluviaplus.models.Impacto;
import globalsolution.pluviaplus.repository.ImpactoRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ImpactoBO {

    private final ImpactoRepository repository = new ImpactoRepository();

    public void create(ImpactoDto dto) {
        validate(dto);
        Impacto impacto = toEntity(dto);
        repository.add(impacto);
    }

    public List<ImpactoDto> getAll() {
        return repository.get().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Map<String, Object> getEstatisticasTotais() {
        return repository.getEstatisticasTotais();
    }

    public ImpactoDto getById(int id) {
        Optional<Impacto> impacto = repository.getById(id);
        return impacto.map(this::toDto)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Impacto com ID " + id + " não encontrado."));
    }

    public void update(int id, ImpactoDto dto) {
        validate(dto);
        Impacto impacto = toEntity(dto);
        impacto.setId_impacto(id);
        repository.update(impacto);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    private void validate(ImpactoDto dto) {
        if (dto.co2EconomizadoKg() < 0 || dto.pessoasBeneficiadas() < 0) {
            throw new RequisicaoInvalidaException("Valores de CO2 e beneficiados devem ser positivos.");
        }
    }

    private Impacto toEntity(ImpactoDto dto) {
        return new Impacto(
                dto.idImpacto(),
                dto.idProducao(),
                dto.co2EconomizadoKg(),
                dto.pessoasBeneficiadas()
        );
    }

    private ImpactoDto toDto(Impacto impacto) {
        return new ImpactoDto(
                impacto.getId_impacto(),
                impacto.getId_producao(),
                impacto.getCo2_economizado_kg(),
                impacto.getPessoas_beneficiadas()
        );
    }


}
