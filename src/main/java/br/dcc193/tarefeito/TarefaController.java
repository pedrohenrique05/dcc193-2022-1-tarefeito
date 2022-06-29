package br.dcc193.tarefeito;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TarefaController {
    @Autowired
    private TarefaRepository rep;

    @RequestMapping("/")
    @ResponseBody //A saida dessa função retorna direto ao cliente
    public String indexOld(){
        Tarefa t = new Tarefa("Criado em "+new Date());
        rep.save(t);
        
        List<Tarefa> tarefas = rep.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Olá mundo!</h1>");
        for(Tarefa tarefa : tarefas){
            sb.append("<li>" + tarefa);
        }
        return sb.toString();
    }

    @RequestMapping({"/tarefa","/tarefa/index.html"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("tarefa-index");
        mv.addObject("mensagem","Ola Mundo");
        return mv;
    }
}