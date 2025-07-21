package Desafio.Crud.demo.repository;
import Desafio.Crud.demo.model.EstagiarioModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface EstagiarioRepository extends MongoRepository<EstagiarioModel, String> {
    Optional<EstagiarioModel> findByMatricula(String matricula);
    Optional<EstagiarioModel> findByCpf(String cpf);
}
