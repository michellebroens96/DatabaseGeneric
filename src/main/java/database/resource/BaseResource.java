package database.resource;

import database.helper.ModelMapperHelper;
import org.modelmapper.TypeToken;

import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

/**
 * Class to be used on the other resource to allow easy returning of objects and errors.
 */
public abstract class BaseResource {

    /**
     * Creates a response for the given error.
     *
     * @param exception
     *         - the exception wanting to be shown.
     *
     * @return a build response with the given error.
     */
    protected Response createErrorResponse(Exception exception) {
        return Response.serverError().entity(exception.getMessage()).build();
    }

    /**
     * Creates a response for the given list of entities.
     * Maps the entities to the given DTO Type.
     *
     * @param entities
     *         - the entities intended to be mapped.
     * @param dtoType
     *         - the class of the DTO wanting to be given back.
     * @param <EntityType>
     *         - the type of the given entities.
     * @param <DTOType>
     *         - the type of the DTO being returned.
     *
     * @return a build response containing the entities.
     */
    protected <EntityType, DTOType> Response createListResponse(Collection<EntityType> entities,
                                                                Class<DTOType> dtoType) {
        try {
            Type listType = new TypeToken<List<DTOType>>() {}.getType();
            List<DTOType> body = ModelMapperHelper.getModdelMapper().map(entities, listType);

            return Response.ok().entity(body).build();
        }
        catch(Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    /**
     * Creates a response for the given entity.
     * Maps the entity using ModelMapper.
     *
     * @param entity
     *         - the entity intended to be mapped.
     * @param dtoType
     *         - the type intended to be mapped to.
     * @param <EntityType>
     *         - the type of the entity before mapping.
     * @param <DTOType>
     *         - the type of the entity after mapping.
     *
     * @return a build response containing the mapped entity.
     */
    protected <EntityType, DTOType> Response createSingleResponse(EntityType entity, Class<DTOType> dtoType) {
        try {
            DTOType entityDto = ModelMapperHelper.getModdelMapper().map(entity, dtoType);

            return Response.ok().entity(entityDto).build();
        }
        catch(Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
