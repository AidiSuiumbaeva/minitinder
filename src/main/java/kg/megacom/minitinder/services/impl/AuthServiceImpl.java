package kg.megacom.minitinder.services.impl;

import kg.megacom.minitinder.exceptions.NotFoundEntityEx;
import kg.megacom.minitinder.exceptions.NotUniqueLogin;
import kg.megacom.minitinder.mapper.AccountMapper;
import kg.megacom.minitinder.models.Account;
import kg.megacom.minitinder.models.Person;
import kg.megacom.minitinder.models.dto.AuthRequest;
import kg.megacom.minitinder.models.dto.Response;
import kg.megacom.minitinder.models.dto.entityDto.AccountDto;
import kg.megacom.minitinder.models.dto.entityDto.PersonDto;
import kg.megacom.minitinder.models.enums.AccountStatus;
import kg.megacom.minitinder.models.enums.Language;
import kg.megacom.minitinder.services.AccountService;
import kg.megacom.minitinder.services.AuthService;
import kg.megacom.minitinder.services.PersonService;
import kg.megacom.minitinder.utils.JwtProvider;
import kg.megacom.minitinder.utils.ResourceBundle;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private final AccountService accountService;
    private final PersonService personService;

    private final JwtProvider jwtProvider;

    public AuthServiceImpl(AccountService accountService, PersonService personService, JwtProvider jwtProvider) {
        this.accountService = accountService;
        this.personService = personService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Response auth(AuthRequest request, Language language) {
        /**
         * Найти по логину пользователя
         * если не нашел ошибка с 404 кодом и текстом
         *
         * если нашел то смотрим на статус
         * если заблокирован смотрим update date если прошло полчаса идем далее,
         * если нет то ошибку с кодом 418 что заблокирован
         *
         * если активен сравниваем пароль
         * если пароль верный то вернем ответ успешно и айди Person
         * вернем и обнулим count и сохраним
         * если пароль неверный смотрим количество count если больше 3
         * блокируем и ответ 418 попробуйте через полчаса
         * если не больше 3 то добавляем count++ и
         * сохраняем возвращаем ответ неверный пароль попробуйте снова*/

        PersonDto person=personService.findByLogin(request.getLogin());
        AccountDto account=person.getAccount();
        if (person==null){
            throw new NotFoundEntityEx(
                    ResourceBundle.periodMessages("personNotFound",language));
        }

        switch (account.getStatus()){
            case ACTIVE -> {}
            case BLOCKED -> checkDate(account.getUpdateDate(),language);
        }

        if (!account.getPassword().equals(request.getPassword())){
            if (account.getCount()>=3){
                account.setStatus(AccountStatus.BLOCKED);
            }else {
                account.setCount(account.getCount()+1);
            }
            accountService.save(account);
            throw new NotUniqueLogin(ResourceBundle.periodMessages("passwordErr",language));
        }

        String token=jwtProvider.generateAccessToken(person.getId());

        return new Response(token);
    }

    private void checkDate(Date updateDate,Language language) {
        /**
         * update date = 18:23
         * от текущего времени отнять полчаса
         * сравнить полученное с данным
         *
         * от текущего времени отнимаем данное
         * проверяем полученное больше 30 или нет
         * */

        Date date=new Date();
        Long result=date.getTime()-updateDate.getTime();

        if (result<1800000){
            throw new NotUniqueLogin(ResourceBundle.periodMessages(
                    "accountBlocked",language));
        }






    }
}
