package br.com.contato.api.controller;

import br.com.contato.api.dto.ContatoDto;
import br.com.contato.api.logger.Logger;
import br.com.contato.api.model.Contato;
import br.com.contato.api.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contato")
public class ContatoController {

    @Autowired
    ContatoService service;

    @GetMapping
    public ResponseEntity<List<Contato>> findAllContato() {
        return ResponseEntity.ok(service.findAllContato());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> findById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping()
    public ResponseEntity<URI> criarContato(@Valid @RequestBody Contato contato) {
        Contato obj = service.save(contato);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        Logger.info("API-CONTATO", "Contato Criado com sucesso", String.format("ID Contato %s", obj.getId()));
        return ResponseEntity.ok(uri);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizarContato(@Valid @PathVariable("id") Long id, @Valid @RequestBody Contato contato) {
        Contato obj = service.save(id, contato);
        Logger.info("API-CONTATO", "Contato Atualizado com sucesso", String.format("ID Contato %s", id));
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarContato(@Valid @PathVariable("id") Long id) {
        service.delete(id);
        Logger.info("API-CONTATO", "Contato deletado com sucesso", String.format("ID Contato %s", id));
        return ResponseEntity.ok("Contato Deletado com Sucesso");
    }
    @GetMapping("/page")
    public ResponseEntity<Page<ContatoDto>> findPage(@Valid @RequestParam(value="page", defaultValue="0") Integer page,
                                                     @Valid @RequestParam(value="size", defaultValue="24") Integer size) {
        Page<Contato> list = service.findPage(page,size);
        Page<ContatoDto> listDto = list.map(ContatoDto::new);
        return ResponseEntity.ok().body(listDto);
    }
}
