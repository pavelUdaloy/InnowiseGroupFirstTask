package by.servlet;

import by.entity.dto.ImageDto;
import by.exception.ConnectionWithDBLostException;
import by.exception.CustomResponseException;
import by.exception.IncorrectRequestParameterException;
import by.exception.JsonParserException;
import by.exception.NullQueryException;
import by.service.ImageService;
import by.service.ImageServiceImpl;
import by.servlet.response.ad.GetImageResponse;
import by.servlet.utils.ErrorUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
import static by.util.TextLabels.PROPERTIES_MAX_FILE_SIZE;
import static by.util.TextLabels.PROPERTIES_MAX_MEMORY_SIZE;
import static by.util.TextLabels.property;

@WebServlet(name = "by/servlet/ImageServlet.java", urlPatterns = "/images/*")
public class ImageServlet extends HttpServlet {

    private final ErrorUtils errorUtils = new ErrorUtils();

    private final ImageService imageService = new ImageServiceImpl();

    private ServletFileUpload upload;

    @SneakyThrows
    @Override
    public void init() {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(Integer.parseInt(property.getProperty(PROPERTIES_MAX_MEMORY_SIZE)));
        upload = new ServletFileUpload(factory);
        upload.setSizeMax(Integer.parseInt(property.getProperty(PROPERTIES_MAX_FILE_SIZE)));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Object o = property.get(PROPERTIES_BASE_PATH);
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
            BufferedImage bufferedImage = ImageIO.read(imageFile);//todo тут ошибка с properties
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
