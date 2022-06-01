package br.dcc193.tarefeito;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TarefaController {

    @RequestMapping("/")
    @ResponseBody //A saida dessa função retorna direto ao cliente
    public String index(){
            return "<h1> Olá mundo! </h1>";
    }
    
}
