package com.example.Pratics_Nosql.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Random;

@Setter
@Getter
@Document
@NoArgsConstructor
public class Funcionario {
    public Funcionario(String nome, String setor){
        this.nome = nome;
        this.setor = setor;
        this.codigo = gerarCodigo();
    }

    @Id
    private String id;

    private String nome;

    private String setor;

    private int codigo;

    public int gerarCodigo(){

        StringBuilder cod = new StringBuilder();

        cod = new StringBuilder("");
        int size = 6;

        for (int i = 0; i < size; i++) {
            cod.append(new Random().nextInt(10));
        }

        return Integer.parseInt(String.valueOf(cod));
    }
}
