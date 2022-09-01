package br.com.contato.api.dto;

import br.com.contato.api.model.Contato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContatoDto {

    @NotEmpty
    @Size( min = 5, max = 100)
    private String nome;

    @Email
    private String email;

    public ContatoDto(Contato obj) {
        nome = obj.getNome();
        email = obj.getEmail();
    }
}
