package by.servlet;

import by.entity.dto.ImageDto;
import by.exception.ConnectionWithDBLostException;
import by.exception.CustomResponseException;
import by.exception.IncorrectRequestParameterException;
import by.exception.JsonParserException;
import by.exception.NullQueryException;
import by.service.ImageService;
import by.service.ImageServiceImpl;
import by.servlet.responseentity.GetImageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static by.util.TextLabels.IMAGE_FILE;
import static by.util.TextLabels.PROPERTIES_BASE_PATH;
import static by.util.TextLabels.property;

@WebServlet(name = "by/servlet/ImageServlet.java", urlPatterns = "/images/*")
public class ImageServlet extends HttpServlet {

    private final ErrorUtils errorUtils = new ErrorUtils();

    private final ImageService imageService = new ImageServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String pathInfo = request.getPathInfo().substring(1);
            if (pathInfo.isEmpty()) {
                throw new IncorrectRequestParameterException();
            }
            Integer imageId = Integer.valueOf(pathInfo);

            GetImageResponse getImageResponse = imageService.get(imageId);
            ImageDto imageDto = getImageResponse.getImageDto();

            response.setContentType(IMAGE_FILE);
            ServletOutputStream outputStream = response.getOutputStream();

            File imageFile = new File(property.getProperty(PROPERTIES_BASE_PATH)
                    + imageDto.getName() + imageDto.getFileFormat());
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            ImageIO.write(bufferedImage, imageDto.getFileFormat().substring(1), outputStream);
            outputStream.close();
        } catch (IncorrectRequestParameterException | ConnectionWithDBLostException | NullQueryException ex) {
            errorUtils.sendErrorJson(response, ex);
        } catch (NumberFormatException ex) {
            errorUtils.sendErrorJson(response, new IncorrectRequestParameterException());
        } catch (JsonProcessingException ex) {
            errorUtils.sendErrorJson(response, new JsonParserException());
        } catch (IOException ex) {
            errorUtils.sendErrorJson(response, new CustomResponseException());
        }
    }
}
