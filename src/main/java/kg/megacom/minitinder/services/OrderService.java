package kg.megacom.minitinder.services;

import kg.megacom.minitinder.models.dto.entityDto.OrderDto;

public interface OrderService {
    OrderDto save(OrderDto orderDto);
    OrderDto findById(Long id);
}
