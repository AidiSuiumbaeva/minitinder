package kg.megacom.minitinder.models.dto.entityDto;

import kg.megacom.minitinder.models.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    Long id;
    OrderStatus status;
    String comment;

    PersonDto master;

    PersonDto slave;

    Date addDate;
}
