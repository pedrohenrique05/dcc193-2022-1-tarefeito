package br.dcc193.tarefeito.aspect;

//import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
//* br.dcc193.tarefeito.TarefaController.index()) dessa forma estou criando um LogAspect
//para uma classe especifica!!

// colocando '* *..*.*Controller.*(..)' estou criando um LogAspect para qualquer metodo que tenha
// no nome a palavra 'Controller' com qualquer parametro!!!

// LOG de AFTER e BEFORE!!!!!!!!
@Component
@Aspect
public class LogAspect {

    @Around("execution(* *..*.*Controller.*(..))")
    private Object antesLog(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("O metodo vai ser chamado: "+jp.getSignature());
        Object retorno;
        try{
            retorno = jp.proceed();
        } catch(Exception e){
            System.out.println("O metodo disparou: "+jp.getSignature());
            throw e;
        } finally{
            System.out.println("O metodo terminou: "+jp.getSignature());
        }
        return retorno;
    }
    /*
    @Before("execution(* *..*.*Controller.*(..))")
    private void antesLog(JoinPoint jp){
        System.out.println("O metodo vai ser chamado: "+jp.getSignature());
    }

    @After("execution(* *..*.*Controller.*(..))")
    private void depoisLog(JoinPoint jp){
        System.out.println("O metodo terminou: "+jp.getSignature());
    }

     */
}
