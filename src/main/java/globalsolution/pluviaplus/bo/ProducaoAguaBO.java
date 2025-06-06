package globalsolution.pluviaplus.bo;

import globalsolution.pluviaplus.dto.ProducaoAguaDto;
import globalsolution.pluviaplus.exception.RecursoNaoEncontradoException;
import globalsolution.pluviaplus.exception.RequisicaoInvalidaException;
import globalsolution.pluviaplus.models.ProducaoAgua;
import globalsolution.pluviaplus.repository.ProducaoAguaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProducaoAguaBO {

    private final ProducaoAguaRepository repository = new ProducaoAguaRepository();

    public void create(ProducaoAguaDto dto) {
        validate(dto);
        ProducaoAgua producao = toEntity(dto);
        repository.add(producao);
    }

    public List<ProducaoAguaDto> getAll() {
        return repository.get().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ProducaoAguaDto getById(int id) {
        Optional<ProducaoAgua> producao = repository.getById(id);
        return producao.map(this::toDto)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produção com ID " + id + " não encontrada."));
    }

    public void update(int id, ProducaoAguaDto dto) {
        validate(dto);
        ProducaoAgua producao = toEntity(dto);
        producao.setId_producao(id);
        repository.update(producao);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    private void validate(ProducaoAguaDto dto) {
        if (dto.dataProducao() == null || dto.litrosGerados() <= 0) {
            throw new RequisicaoInvalidaException("Data e litros gerados são obrigatórios e válidos.");
        }
    }

    private ProducaoAgua toEntity(ProducaoAguaDto dto) {
        return new ProducaoAgua(
                dto.idProducao(),
                dto.idDispositivo(),
                dto.dataProducao(),
                dto.litrosGerados()
        );
    }

    private ProducaoAguaDto toDto(ProducaoAgua producao) {
        return new ProducaoAguaDto(
                producao.getId_producao(),
                producao.getId_dispositivo(),
                producao.getData_producao(),
                producao.getLitros_gerados()
        );
    }

    public Map<Integer, Double> getVolumeSummaryByDevice() {
        return repository.getVolumeGroupedByDevice();
    }
}
