package com.workintech.s19d1.controller;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import com.workintech.s19d1.service.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actor")
@AllArgsConstructor
public class ActorController {
    private final ActorService actorService;
    @GetMapping
    public List<Actor> findAll(){return actorService.findAll();}
    @GetMapping("/{id}")
    public Actor findById(@PathVariable long id){return actorService.findById(id);}

    @PostMapping
    public Actor save(@RequestBody Actor actor){
        Actor savedA = actorService.save(actor);
        return savedA;
    }

    @PutMapping("/{id}")
    public Actor update(@PathVariable long id, @RequestBody Actor actor){
        Actor foundA = actorService.findById(id);
        actor.setMovies(foundA.getMovies());
        actor.setId(foundA.getId());
        actorService.save(actor);
        return actor;
    }

    @DeleteMapping("/{id}")
    public Actor delete(@PathVariable long id){
        Actor foundA = actorService.findById(id);
        actorService.delete(foundA);
        return foundA;
    }
}