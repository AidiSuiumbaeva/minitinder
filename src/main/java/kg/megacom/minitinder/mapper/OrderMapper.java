package kg.megacom.minitinder.mapper;

import kg.megacom.minitinder.models.Order;
import kg.megacom.minitinder.models.dto.entityDto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface OrderMapper extends BaseMapper<Order, OrderDto>{
    OrderMapper MAPPER= Mappers.getMapper(OrderMapper.class);

}
