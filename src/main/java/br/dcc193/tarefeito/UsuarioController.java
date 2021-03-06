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
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository rep;

    

    @RequestMapping({ "/", "/index.html" })
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("usuario-index");
        mv.addObject("mensagem", "Ola Mundo");
        return mv;
    }

    @GetMapping("/novo.html")
    public ModelAndView novaGET() {
        ModelAndView mv = new ModelAndView("usuario-new");
        Usuario t = new Usuario("Criado em " + new Date());
        mv.addObject("usuario", t);
        return mv;
    }

    @PostMapping("/novo.html")
    public ModelAndView novaPOST(@Valid Usuario t, BindingResult binding) {
        ModelAndView mv = new ModelAndView("usuario-new");
        if (binding.hasErrors()) {
            mv.setViewName("usuario-new.html");
            mv.addObject("usuario", t);
            return mv;
        }
        rep.save(t);
        mv.addObject("usuario", t);
        mv.setViewName("redirect:./listar.html");
        return mv;
    }

    @GetMapping(path = "/listar.html")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("usuario-list");
        List<Usuario> tl = rep.findAll();
        mv.addObject("usuarios", tl);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarGET(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("usuario-edit");

        Optional<Usuario> Usuarioop = rep.findById(id);
        if (Usuarioop.isPresent()) {
            Usuario t = Usuarioop.get();
            mv.setViewName("usuario-edit");
            mv.addObject("usuario", t);
            return mv;
        }
        mv.setViewName("redirect:../listar.html");
        return mv;
    }

    @PostMapping("/editar/{id}")
    public ModelAndView editarPOST(@Valid Usuario t, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("terefa-edit.html");
            mv.addObject("usuario", t);
            return mv;
        }
        rep.save(t);
        mv.setViewName("redirect:../listar.html");
        return mv;

    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:../listar.html");

        Optional<Usuario> Usuarioop = rep.findById(id);

        if (Usuarioop.isPresent()) {
            Usuario t = Usuarioop.get();
            rep.delete(t);
        }
        return mv;
    }

}