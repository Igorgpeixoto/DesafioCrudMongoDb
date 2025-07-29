package Desafio.Crud.demo.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstagiarioDTO extends PessoaDTO {

    private String matricula;
    private LocalDate dataEntrada;
    private String setor;
}