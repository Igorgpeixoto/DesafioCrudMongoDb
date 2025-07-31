package Desafio.Crud.demo.mapper;

import Desafio.Crud.demo.dto.PessoaDTO;
import Desafio.Crud.demo.model.PessoaModel;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PessoaMapper {
    PessoaModel toEntity(PessoaDTO dto);
    PessoaDTO toDTO(PessoaModel model);
}
