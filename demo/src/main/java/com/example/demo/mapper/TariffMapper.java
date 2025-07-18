package com.example.demo.mapper;

import com.example.demo.dto.CreateTariffDto;
import com.example.demo.dto.TariffDto;
import com.example.demo.entity.TariffEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TariffMapper {


    TariffDto tariffEntityToTariffDto(TariffEntity tariffEntity);
/*
    @Mappings({
            @Mapping(target = "bankid", source = "bankid"),
            @Mapping(target = "surname", source = "surname"),
    })

 */
    TariffEntity createTariffDtoToTariffEntity(CreateTariffDto createTariffDto);
}
