package net.kemitix.naolo.owners;

import net.kemitix.naolo.core.FeatureMeta;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(OwnersMetaResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class OwnersMetaResource {
    public static final String PATH = "owners/meta";
    @Inject FeatureMeta<Owner> meta;
    @GET
    public FeatureMeta<Owner> get() {
        return meta;
    }
}
