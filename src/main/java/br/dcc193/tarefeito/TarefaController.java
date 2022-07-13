package br.dcc193.tarefeito;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaRepository rep;

    @RequestMapping("/")
    @ResponseBody // A saida dessa função retorna direto ao cliente
    public String indexOld() {
        Tarefa t = new Tarefa("Criado em " + new Date());
        rep.save(t);

        List<Tarefa> tarefas = rep.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Olá mundo!</h1>");
        for (Tarefa tarefa : tarefas) {
            sb.append("<li>" + tarefa);
        }
        return sb.toString();
    }

    @RequestMapping({ "/", "/index.html" })
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("tarefa-index");
        mv.addObject("mensagem", "Ola Mundo");
        //int a = 1/0;
        return mv;
    }

    @GetMapping("/nova.html")
    public ModelAndView novaGET() {
        ModelAndView mv = new ModelAndView("tarefa-new");
        Tarefa t = new Tarefa("Criado em " + new Date());
        mv.addObject("tarefa", t);
        return mv;
    }

    @PostMapping("/nova.html")
    public ModelAndView novaPOST(@Valid Tarefa t, BindingResult binding) {
        ModelAndView mv = new ModelAndView("tarefa-new");
        if (binding.hasErrors()) {
            mv.setViewName("tarefa-new.html");
            mv.addObject("tarefa", t);
            return mv;
        }
        rep.save(t);
        mv.addObject("tarefa", t);
        mv.setViewName("redirect:./listar.html");
        return mv;
    }

    @GetMapping(path = "/listar.html")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("tarefa-list");
        List<Tarefa> tl = rep.findAll();
        mv.addObject("tarefas", tl);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarGET(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("tarefa-edit");

        Optional<Tarefa> tarefaop = rep.findById(id);
        if (tarefaop.isPresent()) {
            Tarefa t = tarefaop.get();
            mv.setViewName("tarefa-edit");
            mv.addObject("tarefa", t);
            return mv;
        }
        mv.setViewName("redirect:../listar.html");
        return mv;
    }

    @PostMapping("/editar/{id}")
    public ModelAndView editarPOST(@Valid Tarefa t, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("terefa-edit.html");
            mv.addObject("tarefa", t);
            return mv;
        }
        rep.save(t);
        mv.setViewName("redirect:../listar.html");
        return mv;

    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:../listar.html");

        Optional<Tarefa> tarefaop = rep.findById(id);

        if (tarefaop.isPresent()) {
            Tarefa t = tarefaop.get();
            rep.delete(t);
        }
        return mv;
    }

}