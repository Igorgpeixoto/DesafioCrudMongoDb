package Desafio.Crud.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {

    private String cpf;
    private String nomeCompleto;
    private LocalDate dataNascimento;
}