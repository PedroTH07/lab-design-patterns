package br.com.dio.lab_design_patterns.service;

import br.com.dio.lab_design_patterns.model.Cliente;
import br.com.dio.lab_design_patterns.model.ClienteRepository;
import br.com.dio.lab_design_patterns.model.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final ViaCepService cepService;

    public ClienteService(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository, ViaCepService cepService) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.cepService = cepService;
    }

    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void inserir(Cliente cliente) {
        var cep = cliente.getCep();
        var endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            var novoEndereco = cepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    public void atualizar(Long id, Cliente cliente) {
        var existsCliente = clienteRepository.findById(id);
        if (existsCliente.isEmpty()) return;

        var cep = cliente.getCep();
        var endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            var novoEndereco = cepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
