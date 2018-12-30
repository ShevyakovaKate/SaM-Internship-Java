package com.samsolutions.error;

import com.samsolutions.error.exception.LoginExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class ExceptionHandlingController {

    private static final String DEFAULT_ERROR_PAGE = "error/default";
    private static final String ERROR_DTO_ATTRIBUTE = "errorDTO";
    private static final String LOGIN_EXIST_ERROR_MESSAGE = "message.error.login.exist";
    private static final String INTERNAL_ERROR_MESSAGE = "error.message.internal";

    @Autowired
    protected MessageSource messageSource;

    @ExceptionHandler(BindException.class)
    public ModelAndView handleMethodArgumentNotValidException(BindException ex, Locale locale, ModelAndView modelAndView) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.value());
        errorDTO =  processFieldErrors(fieldErrors, errorDTO, locale);
        return modelAndView.addObject(ERROR_DTO_ATTRIBUTE, errorDTO);
    }

    @ExceptionHandler(LoginExistsException.class)
    public ModelAndView handleLoginExistsException(Locale locale) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                messageSource.getMessage(LOGIN_EXIST_ERROR_MESSAGE, new Object[]{}, locale));
        return new ModelAndView(DEFAULT_ERROR_PAGE).addObject(ERROR_DTO_ATTRIBUTE, errorDTO);
    }


    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, Locale locale) {
        ex.printStackTrace();
        String message = messageSource.getMessage(INTERNAL_ERROR_MESSAGE, new Object[]{}, locale);
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),message);
        ModelAndView modelAndView = new ModelAndView(DEFAULT_ERROR_PAGE);
        modelAndView.addObject(ERROR_DTO_ATTRIBUTE, errorDTO);
        return modelAndView;
    }


    private ErrorDTO processFieldErrors(List<FieldError> fieldErrors, ErrorDTO error, Locale locale) {
        fieldErrors.stream().map(fieldError -> new FieldValidationErrorDTO(fieldError.getField(),
                messageSource.getMessage(fieldError.getDefaultMessage(),
                        fieldError.getArguments(), locale)))
                .forEach(error::addFieldError);

        return error;
    }


}
