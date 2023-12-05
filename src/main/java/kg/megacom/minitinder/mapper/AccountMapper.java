package kg.megacom.minitinder.mapper;

import kg.megacom.minitinder.models.Account;
import kg.megacom.minitinder.models.dto.entityDto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper extends BaseMapper<Account,AccountDto>{
    AccountMapper MAPPER= Mappers.getMapper(AccountMapper.class);

}
