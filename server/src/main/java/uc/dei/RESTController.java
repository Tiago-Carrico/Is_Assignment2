package uc.dei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api")
public class RESTController {
    
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private PetService petService;

    private Logger logger = LoggerFactory.getLogger(RESTController.class);

    //Request for saving new owner od DB -> POST /api/owner
    
    @PostMapping("/addOwner"/*, consumes = {MediaType.APPLICATION_JSON_VALUE}*/)
    @ResponseBody
    public Mono<Owner> addOwner(@RequestBody Owner owner){
        System.out.println("it shows something here?");
        logger.info("Request: POST | /api/owner");
        return ownerService.saveOwner(owner);
    }

    @GetMapping("/getOwner")
    public Mono<Owner> getOwner(){
        System.out.println("it shows something here?");
        logger.info("Request: GET | /api/owner/1");
        return ownerService.getOwnerById((long) 1);
    }
    
    
}
