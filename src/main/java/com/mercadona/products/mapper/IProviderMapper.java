package com.mercadona.products.mapper;

import com.mercadona.products.dto.GetProviderDto;
import com.mercadona.products.dto.PostProviderDto;
import com.mercadona.products.dto.PutProviderDto;
import com.mercadona.products.entity.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IProviderMapper {

    @Mapping(target = "code", source = "entity.code")
    GetProviderDto entityToDto (Provider entity);

    Provider postProviderDtoToEntity (PostProviderDto dto);

    Provider putDtoToEntity (PutProviderDto dto);
}
