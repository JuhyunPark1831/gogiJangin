package com.project.gogiJangin.common.exception;

import com.project.gogiJangin.common.response.CustomResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    protected Object handleCustomException(CustomException ex, HttpServletRequest request) {

        if (request.getRequestURI().contains("/api")) {
            return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(CustomResponseEntity.error(ex.getErrorCode().getMessage(), ex.getErrorCode()));
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    protected Object handleException(
            Exception ex,
            HttpServletRequest request
    ) {

        if (request.getRequestURI().contains("/api")) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(CustomResponseEntity.error(ErrorCode.SERVER_ERROR));
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");

        return modelAndView;
    }
}
