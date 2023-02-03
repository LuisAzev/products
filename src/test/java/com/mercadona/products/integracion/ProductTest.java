package com.mercadona.products.integracion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadona.products.config.PersistenceConfig;
import com.mercadona.products.dto.PostProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {
        PersistenceConfig.class
})
@Transactional
public class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getProductsOk()
            throws Exception {

        mockMvc.perform(get("/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", equalTo(1)))
                .andExpect(jsonPath("$.[0].name", equalTo("Galletas")));
    }

    @Test
    void getProductsByEanOk()
                throws Exception{

        mockMvc.perform(get("/v1/products/findByEan?completeEanCode=8437008456121"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Galletas")));
    }

    @Test
    void getProductsByEanKo()
            throws Exception{

        mockMvc.perform(get("/v1/products/findByEan?completeEanCode=8437008456123"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createProductOk()
            throws Exception {

        PostProductDto postProductDto = new PostProductDto();
        postProductDto.setName("Sobaos");
        postProductDto.setCode(55555);
        postProductDto.setDestinyId(1L);
        postProductDto.setProviderId(1L);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postProductDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Sobaos")))
                .andExpect(jsonPath("$.code", equalTo(55555)));
    }
}
