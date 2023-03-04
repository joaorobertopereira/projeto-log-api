package br.com.contato.api.service;

import br.com.contato.api.exception.ResourceNotFoundException;
import br.com.contato.api.model.Contato;
import br.com.contato.api.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoRepository repository;

    public List<Contato> findAllContato() {
        return repository.findAll();
    }

    public Contato findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contato Nao Encontrado"));
    }

    public Contato save(Contato contato) {
        return repository.save(contato);
    }
    public Contato save(Long id, Contato newContato) {
        Contato contato = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contato Nao Encontrado"));
        contato.setNome(newContato.getNome());
        contato.setEmail(newContato.getEmail());
        return repository.save(contato);
    }

    public Page<Contato> findPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("nome").ascending());
        return repository.findAll(pageRequest);
    }

    public void delete(Long id) {
        Contato contato = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contato Nao Encontrado"));
        repository.delete(contato);
    }

}
