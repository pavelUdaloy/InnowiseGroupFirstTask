package by.servlet;

import by.entity.dto.request.CarAdDTORequest;
import by.entity.dto.request.ImageDTORequest;
import by.entity.dto.response.CarAdDTOResponse;
import by.entity.dto.response.ImageDTOResponse;
import by.entity.dto.response.TelephoneDTOResponse;
import by.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static by.util.TextLabels.*;

@WebServlet(name = "by/servlet/CarAdServlet.java", urlPatterns = "/ads")
public class CarAdServlet extends HttpServlet {

    private Properties property;
    private String basePath;
    private ServletFileUpload upload;

    private final CarAdService carAdService = new CarAdServiceImpl();
    private final ImageService imageService = new ImageServiceImpl();
    private final TelephoneService telephoneService = new TelephoneServiceImpl();

    @SneakyThrows
    @Override
    public void init() {
        Properties property = new Properties();
        FileInputStream fis = new FileInputStream(PROPERTIES_PATH);
        property.load(fis);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(Integer.parseInt(property.getProperty(PROPERTIES_MAX_MEMORY_SIZE)));
        basePath = property.getProperty(PROPERTIES_BASE_PATH);
        upload = new ServletFileUpload(factory);
        upload.setSizeMax(Integer.parseInt(property.getProperty(PROPERTIES_MAX_FILE_SIZE)));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AAAAA " + request.getParameter("id") + " " + request.getParameter("for"));
        response.sendError(401);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute("id", 1);
        if (!ServletFileUpload.isMultipartContent(req)) {
            resp.sendError(400);
        }
        resp.setContentType("text/html");
        try {
            CarAdDTORequest carAdDTORequest = null;
            List<ImageDTORequest> images = new ArrayList<>();
            List<FileItem> fileItems = upload.parseRequest(req);
            for (FileItem item : fileItems) {
                if (!item.isFormField() && item.getSize() <= upload.getSizeMax()) {
                    String contentType = item.getContentType();
                    if (contentType.equals(JSON_FILE)) {
                        ObjectMapper mapper = new ObjectMapper();
                        File file = new File(DEF_NAME);
                        item.write(file);
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                        carAdDTORequest = mapper.readValue(bufferedReader, CarAdDTORequest.class);
                        Timestamp timestamp = new Timestamp(new Date().getTime());
                        carAdDTORequest.setCreationDate(timestamp);
                        carAdDTORequest.setLastEditDate(timestamp);
                        carAdDTORequest.setOwnerId((Integer) req.getSession().getAttribute("id"));
                    } else {
                        String oldName = item.getName();
                        String fileType = oldName.substring(oldName.lastIndexOf(DOT));
                        Timestamp timestamp = new Timestamp(new Date().getTime());
                        String newName = timestamp.toString().replaceAll(ANY_NOT_NUMERAL_SYMBOL, EMPTY);
                        item.write(new File(basePath + newName + fileType));
                        ImageDTORequest image = new ImageDTORequest();
                        image.setName(newName);
                        image.setFileFormat(fileType);
                        image.setOwnerId((Integer) req.getSession().getAttribute("id"));
                        images.add(image);
                    }
                }
            }
            List<TelephoneDTOResponse> telephoneDTOResponses =
                    telephoneService.addAll(carAdDTORequest.getTelephones());
            List<ImageDTOResponse> imageDTOResponses = imageService.addAll(images);
            CarAdDTOResponse carAdDTOResponse = carAdService.add(carAdDTORequest);
            System.out.println(telephoneDTOResponses);
            System.out.println(imageDTOResponses);
            System.out.println(carAdDTOResponse);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
