package com.soporte.service;

import com.soporte.Exceptions.ClienteInvalidoExcepcion;
import com.soporte.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    public Collection<Cliente> getClientsExterns(){
        String url = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/clientes-psa/1.0.0/m/api/clientes";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cliente[]> response =
                restTemplate.getForEntity(
                        url,
                        Cliente[].class);
        Cliente[] employees = response.getBody();

        return Arrays.stream(employees).collect(Collectors.toList());
    }

    public Cliente findById(Integer idClient) throws ClienteInvalidoExcepcion{
        Optional<Cliente> cliente_externs = this.getClientsExterns().stream().filter(item -> item.getId().equals(idClient)).findFirst();

        if(cliente_externs.isPresent()){
            return cliente_externs.get();
        } else {
            throw new ClienteInvalidoExcepcion("El cliente no pertenece a la empresa.");
        }
    }
}
