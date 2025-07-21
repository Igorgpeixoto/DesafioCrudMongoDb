package Desafio.Crud.demo.repository;
import Desafio.Crud.demo.model.PessoaModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface PessoaRepository extends MongoRepository<PessoaModel, String> {
    Optional<PessoaModel> findByCpf(String cpf);
}
