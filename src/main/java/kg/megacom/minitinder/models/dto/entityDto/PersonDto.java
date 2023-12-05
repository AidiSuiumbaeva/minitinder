package kg.megacom.minitinder.models.dto.entityDto;

import kg.megacom.minitinder.models.Account;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonDto {
    Long id;
    String fisrtName;
    String city;
    String lastName;
    int age;
    String info;
    Integer gender;
    AccountDto account;
}
