package Desafio.Crud.demo.mapper;
import Desafio.Crud.demo.dto.EstagiarioDTO;
import Desafio.Crud.demo.model.EstagiarioModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PessoaMapper.class)
public interface EstagiarioMapper {
    EstagiarioDTO toDTO(EstagiarioModel model);
    EstagiarioModel toEntity(EstagiarioDTO dto);
}

