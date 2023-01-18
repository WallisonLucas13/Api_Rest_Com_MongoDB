package com.example.Pratics_Nosql.services;

import com.example.Pratics_Nosql.models.Funcionario;
import com.example.Pratics_Nosql.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService implements ServicesDataImpl<Funcionario>{

    @Autowired
    private FuncionarioRepository repository;

    @Override
    public void save(Funcionario funcionario) {

        List<Funcionario> funcionarios = repository.findAll();

        if(funcionarios.isEmpty()){
            repository.save(funcionario);
            return;
        }

        boolean loop = true;
        int cod = 0;

        while(loop){

            cod = funcionario.gerarCodigo();

            for(int i=0; i<funcionarios.size(); i++){

                if(cod != funcionarios.get(i).getCodigo() && String.valueOf(cod).length() == 6){
                    loop = false;
                    continue;
                }
                else{
                    loop = true;
                    break;
                }
            }

        }
        funcionario.setCodigo(cod);
        repository.save(funcionario);
    }

    @Override
    public Funcionario findById(String id) throws IllegalArgumentException{
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));
    }

    @Override
    public List<Funcionario> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(String id) throws IllegalArgumentException{
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Funcionario findByNome(String nome) {
        return repository.findByNome(nome).orElseThrow(() -> new IllegalArgumentException(""));
    }

    @Override
    public List<Funcionario> findAllBySetor(String setor) {
        return repository.findAllBySetor(setor);
    }

    @Override
    public Funcionario updateByNome(String nome, Funcionario object) {
        Funcionario old = repository.findByNome(nome).orElseThrow(() -> new IllegalArgumentException(""));

        object.setId(old.getId());
        object.setCodigo(old.getCodigo());
        repository.save(object);
        return object;
    }

    @Override
    public Funcionario updateById(String id, Funcionario object) {
        Funcionario old = repository.findById(id).orElseThrow(() -> new IllegalArgumentException(""));

        object.setId(old.getId());
        object.setCodigo(old.getCodigo());
        repository.save(object);
        return object;
    }
}
