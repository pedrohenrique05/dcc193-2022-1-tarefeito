package br.dcc193.tarefeito;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String titulo;
    
    public Tarefa(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Tarefa(String titulo) {
        this(null,titulo);
    }

    public Tarefa() {
        this(null,null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Tarefa [id=" + id + ", titulo=" + titulo + "]";
    }
}
