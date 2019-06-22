package database.helper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperHelper {

    /**
     * Creates a new model mapper instance that is set to the STRICT Matching Strategy.
     * The STRICT is needed because ModelMapper will try to map fields from nester objects to the parent.
     * So if 2 entities have the same field name it will create an error.
     *
     * As an example, lets take CarTracker.
     * CarTracker has a child object, Car. They both have Ids as they are both entities.
     * The CarTracker's ID should be mapped to the CarTrackerDTO and the Car's ID should be mapped to the CarDTO.
     * @return a new ModelMapper instance with the STRICT Matching Strategy.
     */
    public static ModelMapper getModdelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
