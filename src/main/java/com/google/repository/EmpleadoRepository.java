package com.google.repository;

import com.google.entity.Empleado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository  extends CrudRepository<Empleado,Long> {


}
