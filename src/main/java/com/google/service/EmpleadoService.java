package com.google.service;

import com.google.entity.Empleado;

import java.util.List;

public interface EmpleadoService {

    void crearEmpleado(Empleado empleado);
    List<Empleado> listarEmpleado();
    Empleado buscarPorId(Long id);
    void eliminarEmpleadoId(Long id);


}
