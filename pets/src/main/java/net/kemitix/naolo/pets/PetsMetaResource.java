package net.kemitix.naolo.pets;

import net.kemitix.naolo.core.FeatureMeta;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(PetsMetaResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PetsMetaResource {

    public static final String PATH = "pets/meta";

    @Inject FeatureMeta<Pet> meta;

    @GET
    public FeatureMeta<Pet> get() {
        return meta;
    }

}
