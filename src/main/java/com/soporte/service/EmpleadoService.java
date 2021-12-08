package com.soporte.service;

import com.soporte.Exceptions.EmpleadoInvalidoExcepcion;
import com.soporte.model.Empleado;
import com.soporte.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.web.client.RestTemplate;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

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
        Optional<Empleado> empleado_externs = this.getEmpleados().stream().filter(item -> item.getId().equals(legajo)).findFirst();
        Optional<Empleado> empleado_database = this.getEmpleadosDatabase().stream().filter(item -> item.getId().equals(legajo)).findFirst();
        if(empleado_database.isPresent()) {
            return empleado_database.get();
        } else if(empleado_externs.isPresent()){
            return empleado_externs.get();
        } else {
            throw new EmpleadoInvalidoExcepcion("El empleado no pertenece a la empresa.");
        }
    }

    public void save(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    public Collection<Empleado> getEmpleadosDatabase() {
        Collection<Empleado> empleados = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleados::add);

        return empleados;
    }


    public void deleteById(Integer id) {
        empleadoRepository.deleteById(id);
    }
}
