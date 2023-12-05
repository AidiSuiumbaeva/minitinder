package kg.megacom.minitinder.mapper;

import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public interface BaseMapper<ENTITY, DTO> {
    ENTITY toEntity(DTO dto);
    DTO update(DTO dto);
    DTO toDto(ENTITY entity);
    List<ENTITY> toEntities(List<DTO> dtoList);
    List<DTO> toDtos(List<ENTITY> entityList);}
