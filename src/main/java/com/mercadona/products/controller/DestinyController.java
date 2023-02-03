package com.mercadona.products.controller;

import com.mercadona.products.dto.*;
import com.mercadona.products.service.DestinyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/destinies")
@AllArgsConstructor
public class DestinyController {

    private final DestinyService destinyService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GetDestinyDto> getDestinies(){

        return destinyService.getDestinies();
    }

    @GetMapping(path = "/{destinyId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GetDestinyDto getDestiny(@PathVariable Long destinyId){

        return destinyService.getDestiny(destinyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GetDestinyDto createDestiny(@RequestBody PostDestinyDto dto){

        return this.destinyService.createDestiny(dto);
    }

    @PutMapping(path = "/{destinyId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GetDestinyDto updateDestiny(@PathVariable Long destinyId,
                                         @RequestBody PutDestinyDto dto){

        if(dto.getId() != null
                && !dto.getId().equals(destinyId)) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return this.destinyService.updateDestiny(dto);
    }

    @DeleteMapping(path =  "/{destinyId}")
    public void deleteDestiny(@PathVariable Long destinyId) {

        this.destinyService.deleteDestinyById(destinyId);
    }
}
