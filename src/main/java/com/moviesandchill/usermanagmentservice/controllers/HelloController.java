package com.moviesandchill.usermanagmentservice.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moviesandchill.usermanagmentservice.services.MovieService;

@RestController
public class HelloController {
    private MovieService movieService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot! This is user-management-service";
    }

    @RequestMapping({"/movie/list", "/movie"})
    public String listMovies(Model model){
        model.addAttribute("movies");
        try{movieService.listAll();}
        catch (Exception e) {
            e.printStackTrace();
        }
        return "movie/list";
    }

//    @RequestMapping("/movie/show/{id}")
//    public String getMovie(@PathVariable String id, Model model){
//        model.addAttribute("movie", movieService.getById(Long.valueOf(id)));
//        return "movie/show";
//    }
}
