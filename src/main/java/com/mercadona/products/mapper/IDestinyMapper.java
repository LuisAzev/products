package com.mercadona.products.mapper;

import com.mercadona.products.dto.GetDestinyDto;
import com.mercadona.products.dto.PostDestinyDto;
import com.mercadona.products.dto.PutDestinyDto;
import com.mercadona.products.entity.Destiny;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IDestinyMapper {

    @Mapping(target = "code", source = "entity.code")
    GetDestinyDto entityToDto (Destiny entity);

    Destiny postDestinyDtoToEntity (PostDestinyDto dto);

    Destiny putDtoToEntity (PutDestinyDto dto);
}
