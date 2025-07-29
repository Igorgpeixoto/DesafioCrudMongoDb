package Desafio.Crud.demo.mapper;

import Desafio.Crud.demo.dto.PessoaDTO;
import Desafio.Crud.demo.model.PessoaModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "nomeCompleto", target = "nomeCompleto")
    @Mapping(source = "dataNascimento", target = "dataNascimento")
    PessoaModel toEntity(PessoaDTO dto);

    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "nomeCompleto", target = "nomeCompleto")
    @Mapping(source = "dataNascimento", target = "dataNascimento")
    PessoaDTO toDTO(PessoaModel model);
}