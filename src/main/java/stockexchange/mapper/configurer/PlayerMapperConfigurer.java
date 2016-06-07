package stockexchange.mapper.configurer;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;
import stockexchange.mapper.MappingConfigurer;
import stockexchange.model.entity.PlayerEntity;
import stockexchange.model.to.PlayerTo;

@Component
public class PlayerMapperConfigurer implements MappingConfigurer {

    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.classMap(PlayerEntity.class, PlayerTo.class).byDefault().register();
    }
}
