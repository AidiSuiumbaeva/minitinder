package kg.megacom.minitinder.services.impl;

import kg.megacom.minitinder.dao.OrderRep;
import kg.megacom.minitinder.mapper.OrderMapper;
import kg.megacom.minitinder.models.Order;
import kg.megacom.minitinder.models.dto.entityDto.OrderDto;
import kg.megacom.minitinder.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRep rep;
    //todo вспомнить внедренние маппера
//    OrderMapper mapper=OrderMapper.MAPPER;

    @Override
    public OrderDto save(OrderDto orderDto) {
//        Order saveFoOrder=OrderMapper.MAPPER.toEntity(orderDto);
//        Order order=rep.save(saveFoOrder);
//        OrderDto result=OrderMapper.MAPPER.toDto(order);
//        return result;
        return OrderMapper.MAPPER.toDto(rep.save(OrderMapper.MAPPER.toEntity(orderDto)));
    }

    @Override
    public OrderDto findById(Long id) {
        return OrderMapper.MAPPER.toDto(rep.findById(id).orElse(null));
    }
}
