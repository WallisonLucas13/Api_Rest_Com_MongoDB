package com.example.Pratics_Nosql.services;

import java.util.List;

public interface ServicesDataImpl<T> {

    public void save(T object);

    public T findById(String id);

    public List<T> findAll();

    public void deleteById(String id);

    public void deleteAll();

    public T findByNome(String nome);

    public List<T> findAllBySetor(String setor);

    public T updateByNome(String nome, T object);

    public T updateById(String id, T object);
}
