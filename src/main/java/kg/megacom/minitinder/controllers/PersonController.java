package kg.megacom.minitinder.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kg.megacom.minitinder.models.dto.AccountCreateRequest;
import kg.megacom.minitinder.models.enums.Language;
import kg.megacom.minitinder.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1/person")
@AllArgsConstructor
@Api(tags = "Управление пользователями")
public class PersonController {


    private final PersonService personService;


    /**
     * при регистрации
     * если логин повторяется, то верните текст и код 418
     * если возраст неподходящий то верните текст и код 409
     * */

    @ApiOperation(value = "Регистрация")
    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody AccountCreateRequest request, @RequestParam Language language){
        return new ResponseEntity<>(personService.create(request,language), HttpStatus.OK);
    }

    @ApiOperation(value = "Информация о клиенте")
    @GetMapping("/info/by/id")
    //TODO add headers
    ResponseEntity<?> getInfo(@RequestHeader String token, @RequestParam Language language){
        return new ResponseEntity<>(personService.getInfo(token,language), HttpStatus.OK);
    }

    @ApiIgnore
    @ApiOperation(value = "только для разработчика")
    @GetMapping()
    ResponseEntity<?> findById(@RequestParam Long id){
        try {
            return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
        }catch (Exception e){
            return   new ResponseEntity<>(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }



    @ApiOperation(value = "Фильтр")
    @GetMapping("/filter")
    ResponseEntity<?> filter(@RequestParam(required = false) Integer ageFrom,
                             @RequestParam(required = false) Integer ageTo,
                             @RequestParam(required = false) String city,
                             @RequestParam(required = false) Integer gender){
        try {

            return new ResponseEntity<>(personService.filter(ageFrom,ageTo,city,gender), HttpStatus.OK);
        }catch (Exception e){
            return   new ResponseEntity<>(e.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }
    }







}
