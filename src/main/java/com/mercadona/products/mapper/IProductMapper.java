package com.mercadona.products.mapper;

import com.mercadona.products.dto.GetProductByEanDto;
import com.mercadona.products.dto.GetProductDto;
import com.mercadona.products.dto.PostProductDto;
import com.mercadona.products.dto.PutProductDto;
import com.mercadona.products.entity.Destiny;
import com.mercadona.products.entity.Product;
import com.mercadona.products.entity.Provider;
import com.mercadona.products.utility.EanCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IProductMapper {

    @Mapping(target = "codProvider", source = "provider.id")
    @Mapping(target = "codDestiny", source = "destiny.id")
    GetProductDto entityToDto (Product entity);

    @Mapping(target = "codProvider", source = "entity.provider.name")
    @Mapping(target = "codDestiny", source = "entity.destiny.name")
    @Mapping(target = "eanCode", source = "eanCode.completeEanCode")
    GetProductByEanDto entityToDto (Product entity,
                                    EanCode eanCode);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "code", source = "dto.code")
    @Mapping(target = "provider", source = "provider")
    @Mapping(target = "destiny", source = "destiny")
    Product postProductDtoToEntity (Provider provider,
                                    PostProductDto dto,
                                    Destiny destiny);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "code", source = "dto.code")
    @Mapping(target = "provider", source = "provider")
    @Mapping(target = "destiny", source = "destiny")
    Product putDtoToEntity (Provider provider,
                            PutProductDto dto,
                            Destiny destiny);
}
