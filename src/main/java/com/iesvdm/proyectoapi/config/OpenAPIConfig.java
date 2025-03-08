package com.iesvdm.proyectoapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Classroom API",
        version = "1.0",
        description = "API para gestionar un Classroom privado en IES Vega de Mijas"
))
public class OpenAPIConfig {
}

/*
*
*   Genera documentación automática de la API usando Springdoc OpenAPI
*   que es una librería para generar documentción automática de APIs en aplicaciones Spring Boot.
*   Se implementará correctamente para entrega de proyecto
* */