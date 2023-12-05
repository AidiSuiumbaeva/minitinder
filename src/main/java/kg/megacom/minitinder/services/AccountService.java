package kg.megacom.minitinder.services;

import kg.megacom.minitinder.models.Account;
import kg.megacom.minitinder.models.dto.AuthRequest;
import kg.megacom.minitinder.models.dto.Response;
import kg.megacom.minitinder.models.dto.entityDto.AccountDto;
import kg.megacom.minitinder.models.enums.Language;

public interface AccountService {
    AccountDto save(AccountDto account);
    AccountDto findById(Long id);

    AccountDto create(String login, Language language);


}
