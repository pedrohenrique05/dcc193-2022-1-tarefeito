package br.dcc193.tarefeito.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ErrorAspect {
    @AfterThrowing(value="execution(* *..*.*Controller.*(..))", throwing = "e")
    public void divisaoPorZero(ArithmeticException e){
        System.err.println("===+===+===+===");
        System.err.println(e.toString());
        System.err.println(e.getStackTrace());
        System.err.println("===+===+===+===");
    }
}
