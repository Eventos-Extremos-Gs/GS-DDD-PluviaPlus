package globalsolution.pluviaplus.bo;

import globalsolution.pluviaplus.dto.RelatorioDto;
import globalsolution.pluviaplus.exception.RecursoNaoEncontradoException;
import globalsolution.pluviaplus.exception.RequisicaoInvalidaException;
import globalsolution.pluviaplus.models.Relatorio;
import globalsolution.pluviaplus.repository.RelatorioRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RelatorioBO {

    private final RelatorioRepository repository = new RelatorioRepository();

    public void create(RelatorioDto dto) {
        validate(dto);
        Relatorio relatorio = toEntity(dto);
        repository.add(relatorio);
    }

    public List<RelatorioDto> getAll() {
        return repository.get().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public RelatorioDto getById(int id) {
        Optional<Relatorio> relatorio = repository.getById(id);
        return relatorio.map(this::toDto)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Relatório com ID " + id + " não encontrado."));
    }

    public void update(int id, RelatorioDto dto) {
        validate(dto);
        Relatorio relatorio = toEntity(dto);
        relatorio.setId_relatorio(id);
        repository.update(relatorio);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    private void validate(RelatorioDto dto) {
        if (dto.tipo() == null || dto.dataGeracao() == null) {
            throw new RequisicaoInvalidaException("Tipo e data de geração são obrigatórios.");
        }
    }

    private Relatorio toEntity(RelatorioDto dto) {
        return new Relatorio(
                dto.idRelatorio(),
                dto.tipo(),
                dto.dataGeracao(),
                dto.idUsuario()
        );
    }

    private RelatorioDto toDto(Relatorio relatorio) {
        return new RelatorioDto(
                relatorio.getId_relatorio(),
                relatorio.getTipo(),
                relatorio.getData_geracao(),
                relatorio.getId_usuario()
        );
    }
}

