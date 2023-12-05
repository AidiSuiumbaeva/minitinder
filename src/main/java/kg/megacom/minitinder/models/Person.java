package kg.megacom.minitinder.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tb_person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String fisrtName;
    String lastName;
    int age;
    String city;
    String info;
    Integer gender;

    @OneToOne
    @JoinColumn(name = "account_id")
    Account account;



}
