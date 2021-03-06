package br.dcc193.tarefeito;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/layout.html")
    public String layout(){
        return "layout-padrao";
    }

    @GetMapping("/erro.html")
    public String erro(){
        int a = 10/0;
        return "layout-padrao";
    }

    @ExceptionHandler
    public ModelAndView trataDivisaoPorZero( ArithmeticException e){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("status",500);
        mv.addObject("error","Deu ruim");
        mv.addObject("message","Divisao por zero");
        return mv;
    }
}
