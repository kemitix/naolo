package net.kemitix.naolo.core.ui;

import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log
@Path(FeatureResource.PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class FeatureResource {

    public static final String PATH = "ui/features";

    private List<Feature> features;

    public FeatureResource() {
    }

    @Inject
    public FeatureResource(
            final Instance<Feature> features
    ) {
        this.features = features.stream()
                .sorted(Comparator.comparingInt(Feature::getWeight))
                .collect(Collectors.toList());
    }

    @GET
    public List<Feature> features() {
        return features;
    }
}
