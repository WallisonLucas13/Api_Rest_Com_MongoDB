package com.example.Pratics_Nosql.Dtos;

import com.example.Pratics_Nosql.models.Funcionario;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDto {

    public FuncionarioDto(String nome, String setor){
        this.nome = nome;
        this.setor = setor;
    }

    @NotBlank
    private String nome;

    @NotBlank
    private String setor;

    public Funcionario transform(){
        return new Funcionario(this.nome, this.setor);
    }
}
