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

    @GetMapping("/getOwner")
    public Mono<Owner> getOwner(){
        System.out.println("it shows something here?");
        logger.info("Request: GET | /api/owner/1");
        return ownerService.getOwnerById((long) 1);
    }

    // GET /api/owner/{id} -> returns the owner that corresponds with the given ID
    @GetMapping("/owner/{id}")
    public Mono<Owner> getOwner(@PathVariable("id") Long id) {
        logger.info("Request type: GET\nRequest URI: /api/owner/" + id + "]");
        return ownerService.getOwnerById(id);
    }

    @GetMapping("/owner")
    public Flux<Owner> getAllOwners(){
        logger.info("Request type: GET\nRequest URI: /api/owner");
        return ownerService.getAllOwners();
    }


    //Test
    @PostMapping("/owner"/*, consumes = {MediaType.APPLICATION_JSON_VALUE}*/)
    public Mono<Owner> addOwner(@RequestBody Owner owner){
        System.out.println("it shows something here?");
        logger.info("Request: POST | /api/owner");
        return ownerService.saveOwner(owner);
    }
    
    
}
