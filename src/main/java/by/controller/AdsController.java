package by.controller;

import by.controller.request.ad.UpdateAdRequest;
import by.controller.response.ad.AddAdResponse;
import by.controller.response.ad.DeleteAdResponse;
import by.controller.response.ad.GetAdResponse;
import by.controller.response.ad.PaginationGetAdResponse;
import by.controller.response.ad.UpdateAdResponse;
import by.entity.dto.CarAdDto;
import by.entity.dto.ImageDto;
import by.exception.CustomFileToJsonException;
import by.exception.CustomRequestException;
import by.exception.JsonParserException;
import by.service.AdService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static by.util.TextLabels.ANY_NOT_NUMERAL_SYMBOL;
import static by.util.TextLabels.DEF_NAME;
import static by.util.TextLabels.DOT;
import static by.util.TextLabels.EMPTY;
import static by.util.TextLabels.JSON_FILE;
import static by.util.TextLabels.PROPERTIES_BASE_PATH;
import static by.util.TextLabels.PROPERTIES_MAX_FILE_SIZE;
import static by.util.TextLabels.PROPERTIES_MAX_MEMORY_SIZE;
import static by.util.TextLabels.property;

@RestController
@RequestMapping(path = "/ads")
public class AdsController implements InitializingBean {

    private String basePath;
    private ServletFileUpload upload;
    private ObjectMapper objectMapper;

    private final AdService adService;

    public AdsController(AdService adService) {
        this.adService = adService;
    }

    @Override
    public void afterPropertiesSet() {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(Integer.parseInt(property.getProperty(PROPERTIES_MAX_MEMORY_SIZE)));
        basePath = property.getProperty(PROPERTIES_BASE_PATH);
        upload = new ServletFileUpload(factory);
        upload.setSizeMax(Integer.parseInt(property.getProperty(PROPERTIES_MAX_FILE_SIZE)));
        objectMapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule()).findAndRegisterModules();
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

    @PostMapping
    @RequestMapping(consumes = {"multipart/form-data"})
    ResponseEntity<AddAdResponse> post(HttpServletRequest request) {
        Integer ownerId = 19; //todo пока нет реализации аутентификации айди хозяина задаю статикой
        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new CustomRequestException();
        }//todo need to delete
        CarAdDto carAdDTO = null;
        List<ImageDto> imageDtos = new ArrayList<>();
        List<FileItem> fileItems;
        try {
            fileItems = upload.parseRequest(request);
        } catch (FileUploadException e) {
            throw new CustomRequestException();
        }
        for (FileItem fileItem : fileItems) {
            if (!fileItem.isFormField()) {
                String contentType = fileItem.getContentType();
                if (contentType.equals(JSON_FILE)) {
                    carAdDTO = getCarAdDtoReqFromFile(ownerId, fileItem);
                } else {
                    ImageDto imageDTO = getAndSaveImageDtoReq(fileItem);
                    imageDtos.add(imageDTO);
                }
            } else throw new CustomRequestException();
        }
        if (carAdDTO == null) {
            throw new CustomRequestException();
        }

        AddAdResponse addAdResponse = adService.add(carAdDTO, imageDtos);
        return new ResponseEntity<>(addAdResponse, HttpStatus.ACCEPTED);
    }

    private ImageDto getAndSaveImageDtoReq(FileItem item) {
        String oldName = item.getName();
        String fileType = oldName.substring(oldName.lastIndexOf(DOT));
        LocalDateTime timestamp = LocalDateTime.now();
        String newName = timestamp.toString().replaceAll(ANY_NOT_NUMERAL_SYMBOL, EMPTY);
        try {
            item.write(new File(basePath + newName + fileType));
        } catch (Exception exception) {
            throw new CustomRequestException();
        }
        ImageDto image = new ImageDto();
        image.setName(newName);
        image.setFileFormat(fileType);
        return image;
    }

    private CarAdDto getCarAdDtoReqFromFile(Integer ownerId, FileItem jsonFile) {
        CarAdDto carAdDTO;
        BufferedReader reader;
        File fileForRead = new File(DEF_NAME);
        try {
            jsonFile.write(fileForRead);
            reader = new BufferedReader(new FileReader(fileForRead));
        } catch (Exception exception) {
            throw new CustomFileToJsonException();
        }
        try {
            carAdDTO = objectMapper.readValue(reader, CarAdDto.class);
        } catch (IOException e) {
            throw new JsonParserException();
        }
        LocalDateTime timestamp = LocalDateTime.now();
        carAdDTO.setCreationDate(timestamp);
        carAdDTO.setLastEditDate(timestamp);
        carAdDTO.setOwnerId(ownerId);
        return carAdDTO;
    }
}
