package uc.dei;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import uc.dei.templates.Owner;
import uc.dei.templates.Pet;

//TODO just declare the functions and do Imports, actual code is in ReactiveClientServImpl
//Focus mostly on general stuff like getting all owners, getting them by id, and adding them
public interface ReactiveClientServ {




    //For testing and such, for definite functions make them above
    public void postOwner(Owner owner);
}
