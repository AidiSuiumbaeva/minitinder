package kg.megacom.minitinder.services.impl;

import kg.megacom.minitinder.dao.AccountRep;
import kg.megacom.minitinder.exceptions.NotUniqueLogin;
import kg.megacom.minitinder.mapper.AccountMapper;
import kg.megacom.minitinder.models.Account;
import kg.megacom.minitinder.models.dto.entityDto.AccountDto;
import kg.megacom.minitinder.models.enums.Language;
import kg.megacom.minitinder.services.AccountService;
import kg.megacom.minitinder.utils.JwtProvider;
import kg.megacom.minitinder.utils.ResourceBundle;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRep rep;
    private final JwtProvider jwtProvider;
    //TODO add mappers
    @Override
    public AccountDto save(AccountDto account) {
        Account result= AccountMapper.MAPPER.toEntity(account);

        result=rep.save(result);

       account=AccountMapper.MAPPER.toDto(result);

        return account;

//        return AccountMapper.MAPPER.toDto(
//                rep.save(AccountMapper.MAPPER.toEntity(account)));
    }

    @Override
    public AccountDto findById(Long id) {
        return null;
    }

    @Override
    public AccountDto create(String login, Language language) {
        AccountDto account=new AccountDto();
        account.setLogin(login);
        account.setPassword(generatePassword());
        try {
            account=save(account);
        }catch (DataIntegrityViolationException e){

            throw new NotUniqueLogin(ResourceBundle.periodMessages("uniqueLogin",language));
        }

        return account;
    }




    private String generatePassword(){
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int PASSWORD_LENGTH = 6;
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }
}
