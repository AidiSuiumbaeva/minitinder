package kg.megacom.minitinder.models;

import kg.megacom.minitinder.models.enums.AccountStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tb_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(unique = true,nullable = false)
    String login;
    @Column(nullable = false)
    String password;
    int count;
    @Enumerated(EnumType.STRING)
    AccountStatus status;
    Date addDate;
    Date updateDate;

    @PrePersist
    protected void onCreate(){
        addDate=new Date();
        updateDate=new Date();
        status=AccountStatus.ACTIVE;
        count=0;
    }

    @PreUpdate
    protected void onUpdate(){
        updateDate=new Date();
    }





}
