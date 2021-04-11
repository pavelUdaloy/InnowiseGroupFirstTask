package by.controller;

import by.controller.response.ad.AddAdResponse;
import by.controller.response.ad.DeleteAdResponse;
import by.controller.response.ad.GetAdResponse;
import by.controller.response.ad.PaginationGetAdResponse;
import by.controller.response.ad.UpdateAdResponse;
import by.entity.dto.CarAdDto;
import by.exception.CustomRequestException;
import by.service.AdService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/ads", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdsController {

    private final ObjectMapper objectMapper;
    private final AdService adService;

    public AdsController(AdService adService, ObjectMapper objectMapper) {
        this.adService = adService;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/{id}")
    GetAdResponse get(@PathVariable Integer id) {
        return adService.get(id);
    }

    @GetMapping
    PaginationGetAdResponse get(@RequestParam Integer page, Integer size) {
        return adService.getWithPagination(size, page);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    UpdateAdResponse put(@Valid @RequestBody CarAdDto carAdDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new CustomRequestException();
        }
        return adService.update(carAdDto);
    }

    @DeleteMapping
    DeleteAdResponse delete(@RequestParam Integer id) {
        return adService.delete(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public AddAdResponse post(@RequestParam("conf") String conf,
                              @RequestParam(value = "files", required = false) List<MultipartFile> files) {
        Integer ownerId = 19;//todo пока нет реализации аутентификации айди хозяина задаю статикой
        CarAdDto carAdDto;
        try {
            carAdDto = objectMapper.readValue(conf, CarAdDto.class);
        } catch (JsonProcessingException e) {
            throw new CustomRequestException();
        }
        carAdDto.setOwnerId(ownerId);
        return adService.add(carAdDto, files);
    }
}