package com.mercadona.products.service;

import com.mercadona.products.custom.exception.EntityNotFoundException;
import com.mercadona.products.dto.*;
import com.mercadona.products.entity.Destiny;
import com.mercadona.products.entity.Product;
import com.mercadona.products.entity.Provider;
import com.mercadona.products.mapper.IProviderMapper;
import com.mercadona.products.repository.IProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProviderService {

    private final IProviderRepository providerRepository;

    private final IProviderMapper providerMapper;

    @Transactional(readOnly = true)
    public List<GetProviderDto> getProviders(){

        return providerRepository.findAll()
                .stream()
                .map(providerMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<GetProviderDto> getProvider(Long providerId){

        return providerRepository.findById(providerId)
                .map(providerMapper::entityToDto);
    }

    @Transactional
    public GetProviderDto createProvider(PostProviderDto dto){
        Provider entity = providerMapper.postProviderDtoToEntity(dto);

        return providerMapper.entityToDto(providerRepository.save(entity));
    }

    @Transactional
    public GetProviderDto updateProvider(PutProviderDto dto) {

        providerRepository.findById( dto.getId() )
                .orElseThrow(() -> new EntityNotFoundException());

        Provider entity = providerMapper.putDtoToEntity(dto);

        return providerMapper.entityToDto( providerRepository.save(entity) );
    }

    @Transactional
    public void deleteProviderById(Long providerId){
        providerRepository.findById(providerId)
                .orElseThrow(() -> new EntityNotFoundException());

        providerRepository.deleteById(providerId);
    }

}
