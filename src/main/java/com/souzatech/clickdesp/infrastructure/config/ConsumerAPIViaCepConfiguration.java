package com.souzatech.clickdesp.infrastructure.config;

import com.souzatech.clickdesp.domain.dto.request.ConsultarEndereco;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerAPIViaCepConfiguration {
    public ConsultarEndereco consumer(String cep){
        String fullUrl = String.format("https://viacep.com.br/ws/%s/json/", cep);
        RestTemplate restTemplate = new RestTemplate();
        var consultaEndereco = restTemplate.getForObject(fullUrl, ConsultarEndereco.class);
        return consultaEndereco;
    }
}
