package kg.megacom.minitinder.services;

import kg.megacom.minitinder.models.dto.AccountCreateRequest;
import kg.megacom.minitinder.models.dto.PersonFilteredResponse;
import kg.megacom.minitinder.models.dto.PersonInfoResponse;
import kg.megacom.minitinder.models.dto.Response;
import kg.megacom.minitinder.models.dto.entityDto.PersonDto;
import kg.megacom.minitinder.models.enums.Language;

import java.util.List;

public interface PersonService {
    PersonDto save(PersonDto person);
    PersonDto findById(Long id);

    List<PersonDto> findAll();

    PersonDto findByName(String name);

    Response create(AccountCreateRequest request, Language language);

    PersonInfoResponse getInfo(String token,Language language);

    PersonDto findByLogin(String login);

    PersonFilteredResponse filter(Integer ageFrom, Integer ageTo, String city, Integer gender);
}
