package Desafio.Crud.demo.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordenadorDTO extends PessoaDTO {

    private String matricula;
    private String setor;
    private List<EstagiarioDTO> estagiarios;
}