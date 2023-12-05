package kg.megacom.minitinder.mapper;

import kg.megacom.minitinder.models.Person;
import kg.megacom.minitinder.models.dto.entityDto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PersonMapper extends BaseMapper<Person,PersonDto>{
    PersonMapper MAPPER= Mappers.getMapper(PersonMapper.class);


}
