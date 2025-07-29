package Desafio.Crud.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "coordenadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CoordenadorModel extends PessoaModel {

    @Indexed(unique = true)
    private String matricula;
    private String setor;
    private List<EstagiarioModel> estagiarios;
}