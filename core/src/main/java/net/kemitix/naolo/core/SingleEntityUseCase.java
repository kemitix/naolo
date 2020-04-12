package net.kemitix.naolo.core;

import net.kemitix.naolo.entities.HasId;

public interface SingleEntityUseCase<
        T extends HasId,
        REQ extends SingleEntityRequest<T>,
        RES extends SingleEntityResponse<T>>
        extends EntityUseCase<T, REQ, RES> {

}
