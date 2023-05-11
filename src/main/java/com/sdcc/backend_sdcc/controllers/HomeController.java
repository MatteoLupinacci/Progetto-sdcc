package com.sdcc.backend_sdcc.controllers;

import com.sdcc.backend_sdcc.entities.Documento;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("")
public class HomeController {

    @GetMapping(value = "")
    public String mostraDocumenti(){
        return "SpringBoot-sdcc is running";
    }
}
