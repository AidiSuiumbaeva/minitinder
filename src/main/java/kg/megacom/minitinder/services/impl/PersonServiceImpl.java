package kg.megacom.minitinder.services.impl;

import kg.megacom.minitinder.dao.PersonRepository;
import kg.megacom.minitinder.mapper.PersonMapper;
import kg.megacom.minitinder.models.dto.AccountCreateRequest;
import kg.megacom.minitinder.models.dto.PersonFilteredResponse;
import kg.megacom.minitinder.models.dto.PersonInfoResponse;
import kg.megacom.minitinder.models.dto.Response;
import kg.megacom.minitinder.models.dto.entityDto.AccountDto;
import kg.megacom.minitinder.models.dto.entityDto.PersonDto;
import kg.megacom.minitinder.models.enums.Language;
import kg.megacom.minitinder.services.AccountService;
import kg.megacom.minitinder.services.PersonService;
import kg.megacom.minitinder.utils.JwtProvider;
import kg.megacom.minitinder.utils.ResourceBundle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final JwtProvider jwtProvider;
    private final AccountService accountService;

    public PersonServiceImpl(PersonRepository repository, JwtProvider jwtProvider, AccountService accountService) {
        this.repository = repository;
        this.jwtProvider = jwtProvider;
        this.accountService = accountService;
    }

    @Override
    public PersonDto save(PersonDto person) {
        return PersonMapper.MAPPER.toDto(repository.save(PersonMapper.MAPPER.toEntity(person)));
    }

    @Override
    public PersonDto findById(Long id) {
      return PersonMapper.MAPPER.toDto(repository.findById(id).orElseThrow(()-> new RuntimeException("По id "+id+" не найден объект")));
    }

    @Override
    public List<PersonDto> findAll() {
       return null;
    }

    @Override
    public PersonDto findByName(String name) {
        return PersonMapper.MAPPER.toDto(repository.findByFisrtName(name));
    }



    @Override
    public Response create(AccountCreateRequest request, Language language) {

            if (request.getAge()<18){
                throw new RuntimeException(ResourceBundle.periodMessages("ageCheck",language));
            }
            AccountDto account=accountService.create(request.getLogin(),language);
            PersonDto person=new PersonDto();
            person.setAge(request.getAge());
            person.setLastName(request.getLastName());
            person.setFisrtName(request.getFirstName());
            person.setInfo(request.getInfo());
            person.setAccount(account);
            person.setGender(request.getGender());
            person.setCity(request.getCity());
            save(person);
            return new Response("Успешно");

    }

    @Override
    public PersonInfoResponse getInfo(String token, Language language) {
        Long personId=jwtProvider.validateToken(token,language);
        return repository.getInfoById(personId);
    }

    @Override
    public PersonDto findByLogin(String login) {
        return PersonMapper.MAPPER.toDto(repository.findByLogin(login));
    }

    @Override
    public PersonFilteredResponse filter(Integer ageFrom, Integer ageTo, String city, Integer gender) {
        /**
         * вывести фио и все остальное по параметрам
         *
         * */

        return repository.findByParameters(ageFrom,ageTo,city,gender);



    }
}
