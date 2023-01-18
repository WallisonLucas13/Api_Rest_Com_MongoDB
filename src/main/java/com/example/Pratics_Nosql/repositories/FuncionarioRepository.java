package com.example.Pratics_Nosql.repositories;

import com.example.Pratics_Nosql.models.Funcionario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends MongoRepository<Funcionario, String> {

    public Optional<Funcionario> findByNome(String nome);

    public List<Funcionario> findAllBySetor(String setor);
}
