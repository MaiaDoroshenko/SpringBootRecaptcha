package com.google.service;

import com.google.entity.Empleado;
import com.google.repository.EmpleadoRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Data
public class EmpleadoServiceImpl implements EmpleadoService{
    private final EmpleadoRepository empleadoRepository;
    @Override
    public void crearEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);

    }

    @Override
    public List<Empleado> listarEmpleado() {
        return (List<Empleado>) empleadoRepository.findAll();
    }

    @Override
    public Empleado buscarPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarEmpleadoId(Long id) {
        empleadoRepository.deleteById(id);

    }
}
