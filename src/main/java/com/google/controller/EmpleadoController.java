package com.google.controller;

import com.google.dto.EmpleadoDTO;
import com.google.entity.Empleado;
import com.google.service.EmpleadoService;
import com.google.service.RecaptrchaService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@Data
public class EmpleadoController {
    private final EmpleadoService empleadoService;
    private final RecaptrchaService recaptrchaService;

    @GetMapping(path = {"/", "/all"})
    public String listar(Model model) {
        List<Empleado> listaEmpleados = empleadoService.listarEmpleado();
        model.addAttribute("empleados", listaEmpleados);
        return "index";
    }

    @GetMapping("/crear/form")
    public String crearForm(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "form";

    }

    @PostMapping("/creado/process")
    public String creado(@ModelAttribute("empleadoDTO") EmpleadoDTO empleadoDTO,  @RequestParam(name="g-recaptcha-response") String captcha, Model model) {

        boolean captchaValid = recaptrchaService.validarRecaptcha(captcha);

        if (!captchaValid) {
            model.addAttribute("message", "Captcha Invalido");
            return "error";
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateOfBirth = LocalDate.parse(empleadoDTO.getDateOfBirth(), formatter);

            Empleado empleado = Empleado.builder()
                    .name(empleadoDTO.getName())
                    .LastName(empleadoDTO.getLastName())
                    .dateOfBirth(empleadoDTO.getDateOfBirth())
                    .build();
            empleadoService.crearEmpleado(empleado);
            return "redirect:/all";
        } catch (Exception e) {
            System.err.println("Error al crear empleado: " + e.getMessage());
            model.addAttribute("error", "Error al crear empleado: " + e.getMessage());
            return "form";
        }

    }
}
