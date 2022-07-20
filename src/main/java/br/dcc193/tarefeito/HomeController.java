package br.dcc193.tarefeito;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/layout.html")
    public String layout(){
        return "layout-padrao";
    }
    
}
