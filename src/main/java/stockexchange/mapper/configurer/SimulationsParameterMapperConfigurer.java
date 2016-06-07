package stockexchange.mapper.configurer;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;
import stockexchange.mapper.MappingConfigurer;
import stockexchange.model.entity.SimulationsParameterEntity;
import stockexchange.model.to.SimulationsParameterTo;

@Component
public class SimulationsParameterMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(SimulationsParameterEntity.class, SimulationsParameterTo.class).byDefault().register();
    }
}
