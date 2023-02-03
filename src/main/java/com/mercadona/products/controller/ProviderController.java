package com.mercadona.products.controller;

import com.mercadona.products.dto.*;
import com.mercadona.products.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/providers")
@AllArgsConstructor
public class ProviderController {

    private final ProviderService providerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetProviderDto> getProviders(){

        return providerService.getProviders();
    }

    @GetMapping(path = "/{providerId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GetProviderDto getProvider(@PathVariable Long providerId){

        return providerService.getProvider(providerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GetProviderDto createProvider(@RequestBody PostProviderDto dto){

        return this.providerService.createProvider(dto);
    }

    @PutMapping(path = "/{providerId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GetProviderDto updateProvider(@PathVariable Long providerId,
                                       @RequestBody PutProviderDto dto){

        if(dto.getId() != null
                && !dto.getId().equals(providerId)) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return this.providerService.updateProvider(dto);
    }

    @DeleteMapping(path =  "/{providerId}")
    public void deleteProvider(@PathVariable Long providerId) {

        this.providerService.deleteProviderById(providerId);
    }

}
