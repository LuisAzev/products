package com.mercadona.products.controller;

import com.mercadona.products.custom.exception.InvalidCodeException;
import com.mercadona.products.custom.exception.InvalidNameException;
import com.mercadona.products.dto.*;
import com.mercadona.products.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(path = "/v1/providers")
@AllArgsConstructor
public class ProviderController {

    private static final String PROVIDER_CODE_REGEX = "^\\d{7}$";
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
        nameProviderVerify(dto);
        codeProviderVerify(dto.getCode(), dto);

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

        nameProviderVerify(dto);
        codeProviderVerify(dto.getCode(), dto);

        return this.providerService.updateProvider(dto);
    }

    @DeleteMapping(path =  "/{providerId}")
    public void deleteProvider(@PathVariable Long providerId) {

        this.providerService.deleteProviderById(providerId);
    }

    private void codeProviderVerify(Integer code, @RequestBody PostProviderDto dto) {
        String providerCodeAsText = String.valueOf(code);
        Pattern pattern = Pattern.compile(PROVIDER_CODE_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(String.valueOf(providerCodeAsText));

        if (!matcher.find()){
            throw new InvalidCodeException();
        }
    }

    private void codeProviderVerify(Integer code, @RequestBody PutProviderDto dto) {
        String providerCodeAsText = String.valueOf(code);
        Pattern pattern = Pattern.compile(PROVIDER_CODE_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(String.valueOf(providerCodeAsText));

        if (!matcher.find()){
            throw new InvalidCodeException();
        }
    }

    private static void nameProviderVerify(PostProviderDto dto) {
        if (dto.getName()==null){
            throw new InvalidNameException();
        } else if(dto.getName().isBlank()){
            throw new InvalidNameException();
        }
    }

    private static void nameProviderVerify(PutProviderDto dto) {
        if (dto.getName()==null){
            throw new InvalidNameException();
        } else if(dto.getName().isBlank()){
            throw new InvalidNameException();
        }
    }
}
