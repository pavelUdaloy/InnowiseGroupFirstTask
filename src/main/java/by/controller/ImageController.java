package by.controller;

import by.controller.util.ErrorUtils;
import by.entity.dto.ImageDto;
import by.exception.ConnectionWithDBLostException;
import by.exception.CustomFileToJsonException;
import by.exception.IncorrectRequestParameterException;
import by.exception.NullQueryException;
import by.service.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.RandomAccessFile;

import static by.util.TextLabels.PROPERTIES_BASE_PATH;
import static by.util.TextLabels.READ;
import static by.util.TextLabels.property;

@Controller
@RequestMapping(path = "/images")
public class ImageController {

    private final ErrorUtils errorUtils;
    private final ImageService imageService;

    public ImageController(ErrorUtils errorUtils, ImageService imageService) {
        this.errorUtils = errorUtils;
        this.imageService = imageService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    ResponseEntity get(@PathVariable Integer id) {
        try {
            ImageDto imageDto = imageService.get(id).getImageDto();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity(getImageBytes(imageDto), headers, HttpStatus.ACCEPTED);
        } catch (ConnectionWithDBLostException | NullQueryException ex) {
            return errorUtils.getErrorResponse(ex);
        } catch (NumberFormatException ex) {
            return errorUtils.getErrorResponse(new IncorrectRequestParameterException());
        } catch (IOException e) {
            return errorUtils.getErrorResponse(new CustomFileToJsonException());
        }
    }

    private byte[] getImageBytes(ImageDto imageDto) throws IOException {
        RandomAccessFile f = new RandomAccessFile(property.getProperty(PROPERTIES_BASE_PATH)
                + imageDto.getName() + imageDto.getFileFormat(), READ);
        byte[] b = new byte[(int) f.length()];
        f.readFully(b);
        return b;
    }
}
