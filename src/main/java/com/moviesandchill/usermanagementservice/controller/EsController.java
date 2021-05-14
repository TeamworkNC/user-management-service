package com.moviesandchill.usermanagementservice.controller;

import com.moviesandchill.usermanagementservice.dto.user.UserDto;
import com.moviesandchill.usermanagementservice.exception.user.UserNotFoundException;
import com.moviesandchill.usermanagementservice.service.EsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(
        path = "/es",
        produces = "application/json"
)
public class EsController {

    private EsService esService;

    public EsController(EsService esService) {
        this.esService = esService;
    }

    @PutMapping("/reload")
    public void loadUsersToSearch() throws Exception {
        esService.loadIndexUsers();
    }

    @PostMapping("/search")
    public List<UserDto> search(@RequestParam("search") String search) throws IOException, UserNotFoundException {
        return esService.search(search);
    }
}
