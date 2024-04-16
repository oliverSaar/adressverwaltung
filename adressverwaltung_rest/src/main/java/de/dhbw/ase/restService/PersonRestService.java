package de.dhbw.ase.restService;

import de.dhbw.ase.model.Person;
import de.dhbw.ase.restHelperService.PersonRestHelperService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class PersonRestService {


    @Inject
    private PersonRestHelperService personRestHelperService;


    @GET
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        try {
            List<Person> persons = personRestHelperService.getAllPersons();
            return Response.ok(persons).build();
        } catch (RuntimeException e) {
            System.out.println("Personen nicht gefunden");
            return Response.serverError().build();
        }


    }

    @GET
    @Path("/person/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") final int id) {
        try {
            Person person = personRestHelperService.getPerson(id);
            return Response.ok(person).build();
        } catch (RuntimeException e) {
            System.out.println("Person mit ID: " + id + " nicht gefunden");
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/person")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPerson(Person person) {


    }

    public void updatePerson(int id, Person person) {
    }

    public void deletePerson(int id) {
    }

    public void followPerson(int id) {
    }

    public void deleteAddress(int personID, int addressID) {

    }

    public void addAddress(int personID, int addressID) {
    }

    public void addPhoneNumber(int personID, int phoneNumberID) {
    }

    public void deletePhoneNumber(int personID, int phoneNumberID) {
    }
}
