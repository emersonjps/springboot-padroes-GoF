package com.desafio.dio.desafiodio.repository;

import org.springframework.data.repository.CrudRepository;

import com.desafio.dio.desafiodio.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {}
