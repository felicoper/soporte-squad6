package com.soporte.service;

import com.soporte.Exceptions.EmpleadoInvalidoExcepcion;
import com.soporte.model.Empleado;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;

@Service
public class EmpleadoService {

    public Collection<Empleado> getEmpleados(){
        String url = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Empleado[]> response =
                restTemplate.getForEntity(
                        url,
                        Empleado[].class);
        Empleado[] employees = response.getBody();

        return Arrays.stream(employees).collect(Collectors.toList());
    }

    public Empleado findById(Integer legajo) throws EmpleadoInvalidoExcepcion{
        if (legajo < 0) {
            throw new EmpleadoInvalidoExcepcion("El legajo no puede ser negativo");
        }
        
        Optional<Empleado> empleado_externs = this.getEmpleados().stream().filter(item -> item.getId().equals(legajo)).findFirst();

        if(empleado_externs.isPresent()){
            return empleado_externs.get();
        } else {
            throw new EmpleadoInvalidoExcepcion("El empleado no pertenece a la empresa.");
        }
    }
}
