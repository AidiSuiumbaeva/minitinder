package kg.megacom.minitinder.models;

import kg.megacom.minitinder.models.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Entity
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    OrderStatus status;
    String comment;
    @ManyToOne
    @JoinColumn(name = "master_id")
    Person master;
    @ManyToOne
    @JoinColumn(name = "slave_id")
    Person slave;

    Date addDate;

}
