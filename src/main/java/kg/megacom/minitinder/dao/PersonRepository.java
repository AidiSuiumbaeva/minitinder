package kg.megacom.minitinder.dao;

import kg.megacom.minitinder.models.Person;
import kg.megacom.minitinder.models.dto.PersonFilteredResponse;
import kg.megacom.minitinder.models.dto.PersonInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

    Person findByFisrtName(String firstName);

    @Query(value = "select p from Person p where p.lastName=:lastName",nativeQuery = false)
    Person findByLastName(String lastName);


    @Query(value = "select from tb_person p where p.firstName=:age",nativeQuery = true)
    List<Person> findByAge(int age);

    @Query("select p.fisrtName as name,p.info as info, p.age as age from Person  p where p.id=:personId")
    PersonInfoResponse getInfoById(Long personId);

    @Query("select p from Person p where p.account.login=:login")
    Person findByLogin(@Param("login") String login);

    @Query("""
            select p.id as id, p.age as age,p.info as info,p.city as city,p.gender as gender,concat(p.fisrtName,' ',p.lastName) as fio from Person p
            where p.age between :ageFrom and :ageTo and p.gender=:gender and p.city=:city
            """)
    PersonFilteredResponse findByParameters(Integer ageFrom, Integer ageTo,
                                            String city, Integer gender);


}
