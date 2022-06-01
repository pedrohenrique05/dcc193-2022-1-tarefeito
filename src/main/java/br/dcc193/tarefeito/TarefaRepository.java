package br.dcc193.tarefeito;

import org.springframework.data.jpa.repository.JpaRepository;

public  interface TarefaRepository extends JpaRepository<Tarefa, Long>{
    
}
