package com.hackerrank.github.controller;

import java.util.List;


import javax.validation.Valid;

import com.hackerrank.github.mis.FeignClientTest;
import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.repository.RepoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
@Api(value="GitHubEvent", description="Operations to Add Update Delete and view Event inforamtion ")
public class GithubApiRestController {
    
    @Autowired
    EventRepository eventRepository;
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    RepoRepository repoRepository;
    @Autowired
    FeignClientTest feignClientTest;
    @ApiOperation(value = "Add a EVENT")
    @RequestMapping(method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE, value="/events" )
   public Event  eventsCreate(@Valid @RequestBody Event event){
        String s=feignClientTest.greet();
        System.out.println(s);
       return eventRepository.save(event);
        
    }

    @RequestMapping(value = "/events/actors/{id}", 
            method = RequestMethod.GET, 
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Actor getEventActor(@PathVariable("id") Long id) {
        
        return actorRepository.findOne(id);
    }
     @RequestMapping(value = "/url",
            method = RequestMethod.PUT, 
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<?> putActors(@Valid @RequestBody Actor actor) {
        Actor act=actorRepository.findOne(actor.getId());
        act.setLogin(actor.getLogin());
        act.setAvatar_url(actor.getAvatar_url());
         actorRepository.save(act);
         
        return ResponseEntity.ok().build();
    }
    @ApiOperation(value = "View a list of available Events",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
     @RequestMapping(value = "/events", 
            method = RequestMethod.GET, 
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Event> getEvent() {
        return eventRepository.findAll();
    }
    @RequestMapping(value = "/actors", 
            method = RequestMethod.GET, 
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Actor> getActors() {
        
        return actorRepository.findAll();
    }
    
   @RequestMapping(method = RequestMethod.DELETE,  value="/erase" )
   public void  erase(){
       
       eventRepository.deleteAll();
        
    }
    
    
}
