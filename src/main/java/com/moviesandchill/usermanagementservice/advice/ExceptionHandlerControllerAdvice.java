package com.moviesandchill.usermanagementservice.advice;

import com.moviesandchill.usermanagementservice.exception.ApiError;
import com.moviesandchill.usermanagementservice.exception.achievement.AchievementNotFoundException;
import com.moviesandchill.usermanagementservice.exception.film.FilmNotFoundException;
import com.moviesandchill.usermanagementservice.exception.globalrole.GlobalRoleNotFoundException;
import com.moviesandchill.usermanagementservice.exception.staff.StaffNotFoundException;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
    @ExceptionHandler({
            AchievementNotFoundException.class,
            FilmNotFoundException.class,
            GlobalRoleNotFoundException.class,
            StaffNotFoundException.class,
            UserNotFoundException.class,
    })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiError handleResourceNotFound(final Exception exception) {
        return new ApiError(exception);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ApiError handleException(final Exception exception) {
        exception.printStackTrace();
        return new ApiError(exception);
    }

}
