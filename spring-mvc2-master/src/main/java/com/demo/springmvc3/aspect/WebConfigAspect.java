package com.demo.springmvc3.aspect;

import com.demo.springmvc3.model.Product;
import com.demo.springmvc3.service.ProductService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class WebConfigAspect {

    private ProductService productService;

    public WebConfigAspect(ProductService productService) {
        this.productService = productService;
    }

    private static Logger logger = LoggerFactory.getLogger( WebConfigAspect.class);

    @Before( "execution(* com.demo.springmvc3.controller.*.*(..))" )
    public void loggingAspect(JoinPoint joinPoint){
        String arguments  = Arrays.toString(joinPoint.getArgs());

        logger.info( "Method Name : " + joinPoint.getSignature().getName() );
        logger.info( "Call Time : " + LocalDateTime.now() );
        logger.info( "Args with  : " + arguments );
    }


    @Before( "execution(* *.details(..))" )
    public void notFoundAspect(JoinPoint joinPoint){
        Product product = productService.findById( Integer.valueOf( (Integer) joinPoint.getArgs()[0] ) );

        if(product == null){
            throw new EntityNotFoundException( Integer.valueOf( (Integer) joinPoint.getArgs()[0] ) + "Not Founds" );
        }
    }

}
