package com.desafio.dio.desafiodio.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.dio.desafiodio.models.Cliente;
import com.desafio.dio.desafiodio.models.Endereco;
import com.desafio.dio.desafiodio.repository.ClienteRepository;
import com.desafio.dio.desafiodio.repository.EnderecoRepository;
import com.desafio.dio.desafiodio.services.ClienteService;
import com.desafio.dio.desafiodio.services.ViaCepService;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author emersonjps
 */
@Service
public class ClienteServiceImpl implements ClienteService {

  // Singleton: Injetar os componentes do Spring com @Autowired.
  @Autowired
  private ClienteRepository clienteRepository;
  @Autowired
  private EnderecoRepository enderecoRepository;
  @Autowired
  private ViaCepService viaCepService;

  // Strategy: Implementar os métodos definidos na interface.
  // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

  @Override
  public Iterable<Cliente> buscarTodos() {
    // Buscar todos os Clientes.
    return clienteRepository.findAll();
  }

  @Override
  public Cliente buscarPorId(Long id) {
    // Buscar Cliente por ID.
    Optional<Cliente> cliente = clienteRepository.findById(id);
    return cliente.get();
  }

  @Override
  public void inserir(Cliente cliente) {
    cliente.setEndereco(formatarCep(cliente));
    clienteRepository.save(cliente);
  }

  @Override
  public void atualizar(Long id, Cliente cliente) {
    // Buscar Cliente por ID, caso exista:
    Optional<Cliente> clienteBd = clienteRepository.findById(id);
    if (clienteBd.isPresent()) {
      clienteBd.get().setNome(cliente.getNome());
      clienteBd.get().setEndereco(formatarCep(cliente));
      clienteRepository.save(clienteBd.get());
    }
  }

  @Override
  public void deletar(Long id) {
    // Deletar Cliente por ID.
    clienteRepository.deleteById(id);
  }

  private Endereco formatarCep(Cliente cliente) {
    // Verificar se o Endereco do Cliente já existe (pelo CEP).
    String cep = cliente.getEndereco().getCep();
    Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
      // Caso não exista, integrar com o ViaCEP e persistir o retorno.
      Endereco novoEndereco = viaCepService.consultarCep(cep);
      enderecoRepository.save(novoEndereco);
      return novoEndereco;
    });
    cliente.setEndereco(endereco);
    return cliente.getEndereco();
  }

}