package br.dcc193.tarefeito;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id; 
    @NotBlank(message = "E preciso um nome!")
    private String nome;
    @PositiveOrZero(message = "É preciso uma senha!")
    private String password;

    
    public Usuario(Long id, String nome, String password) {
        this.id = id;
        this.nome = nome;
        this.password = password;
    }

    public Usuario(){
        this(null,null,null);
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
