package Desafio.Crud.demo.mapper;

import Desafio.Crud.demo.dto.CoordenadorDTO;
import Desafio.Crud.demo.model.CoordenadorModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", uses = {PessoaMapper.class, EstagiarioMapper.class})
public interface CoordenadorMapper {
    CoordenadorDTO toDTO(CoordenadorModel model);
    CoordenadorModel toEntity(CoordenadorDTO dto);
}