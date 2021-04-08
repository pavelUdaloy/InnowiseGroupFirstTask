package by.controller;

import by.controller.request.ad.UpdateAdRequest;
import by.controller.response.ad.DeleteAdResponse;
import by.controller.response.ad.GetAdResponse;
import by.controller.response.ad.PaginationGetAdResponse;
import by.controller.response.ad.UpdateAdResponse;
import by.service.AdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/ads")
public class AdsController {

    private final AdService adService;

    public AdsController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    ResponseEntity<GetAdResponse> get(@PathVariable Integer id) {
        GetAdResponse getAdResponse = adService.get(id);
        return new ResponseEntity<>(getAdResponse, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    @ResponseBody
    ResponseEntity<PaginationGetAdResponse> get(@RequestParam Integer page, Integer size) {
        PaginationGetAdResponse getAdResponse = adService.getWithPagination(size, page);
        return new ResponseEntity<>(getAdResponse, HttpStatus.OK);
    }

    @PutMapping(produces = "application/json")
    @ResponseBody
    ResponseEntity<UpdateAdResponse> put(@RequestBody UpdateAdRequest updateAdRequest) {
        UpdateAdResponse update = adService.update(updateAdRequest);
        return new ResponseEntity<>(update, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(produces = "application/json")
    @ResponseBody
    ResponseEntity<DeleteAdResponse> delete(@RequestParam Integer id) {
        DeleteAdResponse deleteAdResponse = adService.delete(id);
        return new ResponseEntity<>(deleteAdResponse, HttpStatus.ACCEPTED);
    }


    //todo тут я короче научился один файл только принимать, а мне надо много(.json + .jpg/.jpeg/.png)    еще не до конца- завтра поговорим

//    @SneakyThrows
//    @PostMapping
//    @RequestMapping
//    ResponseEntity post(@RequestParam MultipartFile file) {
//        byte[] bytes = file.getBytes();
//        LocalDateTime timestamp = LocalDateTime.now();
//        String newName = timestamp.toString().replaceAll(ANY_NOT_NUMERAL_SYMBOL, EMPTY);
//        String oldName = file.getOriginalFilename();
//        String fileType = oldName.substring(oldName.lastIndexOf(DOT));
//        Path path = Paths.get(property.getProperty(PROPERTIES_BASE_PATH) + newName + fileType);
//        Files.write(path, bytes);
//        System.out.println(file);
//        return new ResponseEntity(newName, HttpStatus.ACCEPTED);
//    }
}
