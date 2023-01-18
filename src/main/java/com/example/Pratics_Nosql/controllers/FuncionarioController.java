package com.example.Pratics_Nosql.controllers;

import com.example.Pratics_Nosql.Dtos.FuncionarioDto;
import com.example.Pratics_Nosql.models.Funcionario;
import com.example.Pratics_Nosql.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class FuncionarioController{

    @Autowired
    private FuncionarioService service;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody FuncionarioDto object) {
        service.save(object.transform());
        return ResponseEntity.status(HttpStatus.OK).body("Funcionario Criado!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable("id") String id) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/Todos")
    public ResponseEntity<List<Funcionario>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String id) {

        try {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Funcionario Apagado!");
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID Incorreto!");
        }
    }

    @DeleteMapping("/Todos")
    public ResponseEntity<String> deleteAll(){
        service.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Funcionários Apagados com Sucesso!");
    }

    @GetMapping("/ByNome/{nome}")
    public ResponseEntity<Funcionario> findByNome(@PathVariable("nome") String nome) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findByNome(nome));
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/BySetor/{Setor}")
    public ResponseEntity<List<Funcionario>> findAllBySetor(@PathVariable("Setor") String setor) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllBySetor(setor));
    }

    @PutMapping("/ByNome/{nome}")
    public ResponseEntity<FuncionarioDto> updateByNome(@PathVariable("nome") String nome, @RequestBody FuncionarioDto dto){

        try{
            Funcionario funcionario = service.updateByNome(nome, dto.transform());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new FuncionarioDto(funcionario.getNome(), funcionario.getSetor()));
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping("/ById/{id}")
    public ResponseEntity<FuncionarioDto> updateById(@PathVariable("id") String id, @RequestBody FuncionarioDto dto){

        try{
            Funcionario funcionario = service.updateById(id, dto.transform());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new FuncionarioDto(funcionario.getNome(), funcionario.getSetor()));
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
