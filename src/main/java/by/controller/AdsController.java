package by.controller;

import by.controller.request.ad.UpdateAdRequest;
import by.controller.response.ad.DeleteAdResponse;
import by.controller.response.ad.GetAdResponse;
import by.controller.response.ad.PaginationGetAdResponse;
import by.controller.response.ad.UpdateAdResponse;
import by.controller.util.ErrorUtils;
import by.exception.ConnectionWithDBLostException;
import by.exception.NullQueryException;
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
    private final ErrorUtils errorUtils;

    public AdsController(AdService adService, ErrorUtils errorUtils) {
        this.adService = adService;
        this.errorUtils = errorUtils;
    }

    @GetMapping("/{id}")
    @ResponseBody
    ResponseEntity get(@PathVariable Integer id) {
        try {
            GetAdResponse getAdResponse = adService.get(id);
            return new ResponseEntity(getAdResponse, HttpStatus.ACCEPTED);
        } catch (ConnectionWithDBLostException | NullQueryException ex) {
            return errorUtils.getErrorResponse(ex);
        }
    }

    @GetMapping(produces = "application/json")
    @ResponseBody
    ResponseEntity get(@RequestParam Integer page, Integer size) {
        try {
            PaginationGetAdResponse getAdResponse = adService.getWithPagination(size, page);
            return new ResponseEntity(getAdResponse, HttpStatus.ACCEPTED);
        } catch (ConnectionWithDBLostException | NullQueryException ex) {
            return errorUtils.getErrorResponse(ex);
        }
    }

    @PutMapping(produces = "application/json")
    @ResponseBody
    ResponseEntity put(@RequestBody UpdateAdRequest updateAdRequest) {
        try {
            UpdateAdResponse update = adService.update(updateAdRequest);
            return new ResponseEntity(update, HttpStatus.ACCEPTED);
        } catch (ConnectionWithDBLostException | NullQueryException ex) {
            return errorUtils.getErrorResponse(ex);
        }
    }

    @DeleteMapping(produces = "application/json")
    @ResponseBody
    ResponseEntity delete(@RequestParam Integer id) {
        try {
            DeleteAdResponse deleteAdResponse = adService.delete(id);
            return new ResponseEntity(deleteAdResponse, HttpStatus.ACCEPTED);
        } catch (ConnectionWithDBLostException | NullQueryException ex) {
            return errorUtils.getErrorResponse(ex);
        }
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
