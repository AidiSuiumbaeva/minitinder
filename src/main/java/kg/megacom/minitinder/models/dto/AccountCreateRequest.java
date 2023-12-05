package kg.megacom.minitinder.models.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreateRequest {

    String firstName;
    String lastName;
    String login;
    int age;
    String info;
    String city;
    Integer gender;

}
