package me.edurevsky.controleescola.exceptions;

import me.edurevsky.controleescola.exceptions.appexceptions.NotImplementedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import me.edurevsky.controleescola.exceptions.models.StandardErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Exception e, WebRequest request) {
        log(e, request);
        ModelAndView mv = new ModelAndView("/error");
        mv.addObject("exception", new StandardErrorMessage(e));
        return mv;
    }

    @ExceptionHandler(value = NotImplementedException.class)
    public ModelAndView handleNotImplementedException(Exception e, WebRequest request) {
        log(e, request);
        return new ModelAndView("/error");
    }

    private void log(Exception e, WebRequest request) {
        logger.info(e);
        logger.info(request);
    }
}
