package Desafio.Crud.demo.repository;
import Desafio.Crud.demo.model.CoordenadorModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface CoordenadorRepository extends MongoRepository<CoordenadorModel, String> {
    Optional<CoordenadorModel> findByMatricula(String matricula);
    Optional<CoordenadorModel> findByCpf(String cpf);
}
