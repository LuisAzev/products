package com.mercadona.products.service;

import com.mercadona.products.custom.exception.EntityNotFoundException;
import com.mercadona.products.dto.*;
import com.mercadona.products.entity.Destiny;
import com.mercadona.products.entity.Provider;
import com.mercadona.products.mapper.IDestinyMapper;
import com.mercadona.products.repository.IDestinyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DestinyService {

    private final IDestinyRepository destinyRepository;

    private final IDestinyMapper destinyMapper;

    @Transactional(readOnly = true)
    public List<GetDestinyDto> getDestinies(){

        return destinyRepository.findAll()
                .stream()
                .map(destinyMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<GetDestinyDto> getDestiny(Long destinyId){

        return destinyRepository.findById(destinyId)
                .map(destinyMapper::entityToDto);
    }

    @Transactional
    public GetDestinyDto createDestiny(PostDestinyDto dto){
        Destiny entity = destinyMapper.postDestinyDtoToEntity(dto);

        return destinyMapper.entityToDto(destinyRepository.save(entity));
    }

    @Transactional
    public GetDestinyDto updateDestiny(PutDestinyDto dto) {

        destinyRepository.findById( dto.getId() )
                .orElseThrow(() -> new EntityNotFoundException());

        Destiny entity = destinyMapper.putDtoToEntity(dto);

        return destinyMapper.entityToDto( destinyRepository.save(entity) );
    }

    @Transactional
    public void deleteDestinyById(Long destinyId){
        destinyRepository.findById(destinyId)
                .orElseThrow(() -> new EntityNotFoundException());

        destinyRepository.deleteById(destinyId);
    }
}
