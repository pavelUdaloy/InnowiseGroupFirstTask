package by.servlet;

import by.entity.dao.response.UserDAOResponse;
import by.entity.dto.request.CarAdDTORequest;
import by.entity.dto.request.ImageDTORequest;
import by.entity.dto.response.CarAdDTOResponse;
import by.entity.dto.response.ImageDTOResponse;
import by.entity.dto.response.TelephoneDTOResponse;
import by.service.CarAdService;
import by.service.CarAdServiceImpl;
import by.service.ImageService;
import by.service.ImageServiceImpl;
import by.service.TelephoneService;
import by.service.TelephoneServiceImpl;
import by.service.UserService;
import by.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static by.util.TextLabels.ANY_NOT_NUMERAL_SYMBOL;
import static by.util.TextLabels.DEF_NAME;
import static by.util.TextLabels.DOT;
import static by.util.TextLabels.EMPTY;
import static by.util.TextLabels.JSON_FILE;
import static by.util.TextLabels.PROPERTIES_BASE_PATH;
import static by.util.TextLabels.PROPERTIES_MAX_FILE_SIZE;
import static by.util.TextLabels.PROPERTIES_MAX_MEMORY_SIZE;
import static by.util.TextLabels.PROPERTIES_PATH;

@WebServlet(name = "by/servlet/CarAdServlet.java", urlPatterns = "/ads")
public class CarAdServlet extends HttpServlet {

    private Properties property = new Properties();
    private String basePath;
    private ServletFileUpload upload;

    private final CarAdService carAdService = new CarAdServiceImpl();
    private final ImageService imageService = new ImageServiceImpl();
    private final TelephoneService telephoneService = new TelephoneServiceImpl();
    private final UserService userService = new UserServiceImpl();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void init() {
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
        String id = request.getParameter("id");
        if (id != null) {
            try {
                Integer carAdId = Integer.valueOf(id);
                CarAdDTOResponse carAdDTOResponse = carAdService.get(carAdId);
                UserDAOResponse userDAOResponse = userService.get(carAdDTOResponse.getOwnerId());
                List<ImageDTOResponse> imageDTOResponses = imageService.getByOwnerId(carAdDTOResponse.getId());
                carAdDTOResponse.setImageQuantity(imageDTOResponses.size());
                List<String> imagesPaths = new ArrayList<>();
                for (ImageDTOResponse imageDTOResponse : imageDTOResponses) {
                    String imagesPath = property.getProperty(PROPERTIES_BASE_PATH)
                            + imageDTOResponse.getName() + imageDTOResponse.getFileFormat();
                    imagesPaths.add(imagesPath);
                }
                List<TelephoneDTOResponse> telephoneDTOResponses = telephoneService.getByOwnerId(carAdDTOResponse.getOwnerId());
                carAdDTOResponse.setTelephoneList(telephoneDTOResponses);
                GetResponseBody getResponseBody = new GetResponseBody
                        (carAdDTOResponse, userDAOResponse, imageDTOResponses, imagesPaths);
                String jsonString = objectMapper.writeValueAsString(getResponseBody);
                response.setContentType("application/json");
                ServletOutputStream out = response.getOutputStream();
                response.setCharacterEncoding("UTF-8");
                out.print(jsonString);
                out.flush();

//                response.setContentType("multipart/x-mixed-replace;boundary=END");

//                Files.write(Paths.get(DEF_NAME), jsonString.getBytes());
//
//                List<File> files = new ArrayList<>();
//                for (ImageDTOResponse imageDTOResponse : imageDTOResponses) {
//                    File file = new File(property.getProperty(PROPERTIES_BASE_PATH) +
//                            imageDTOResponse.getName() + imageDTOResponse.getFileFormat());
//                    files.add(file);
//                }
//                File file = new File(DEF_NAME);
//                files.add(file);
//
//                ServletOutputStream out = response.getOutputStream();
//                for (File fileForSend : files) {
//                    FileInputStream fis = new FileInputStream(fileForSend);
//                    BufferedInputStream fif = new BufferedInputStream(fis);
//                    int data = 0;
//                    while ((data = fif.read()) != -1) {
//                        out.write(data);
//                    }
//                    fif.close();
//                    out.flush();
//                }
//                out.flush();
//                out.close();
            } catch (NumberFormatException ex) {
                response.sendError(400);
            }
        } else {
            try {
                Integer size = Integer.valueOf(request.getParameter("size"));
                Integer page = Integer.valueOf(request.getParameter("page"));
                List<CarAdDTOResponse> carAdDTOResponses = carAdService.getWithPagination(size, page);
                for (CarAdDTOResponse carAdDTOResponse : carAdDTOResponses) {
                    carAdDTOResponse.setImageQuantity(imageService.getByOwnerId(carAdDTOResponse.getId()).size());
                    carAdDTOResponse.setTelephoneList(telephoneService.getByOwnerId(carAdDTOResponse.getOwnerId()));
                }
                String jsonString = objectMapper.writeValueAsString(carAdDTOResponses);
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                out.print(jsonString);
                out.flush();
            } catch (NumberFormatException ex) {
                response.sendError(400);
            } catch (JsonProcessingException ex) {
                response.sendError(418);
            }
        }
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
        Integer ownerId = (Integer) req.getSession().getAttribute("id");
        if (!ServletFileUpload.isMultipartContent(req)) {
            resp.sendError(400);
        }
        try {
            CarAdDTORequest carAdDTORequest = null;
            List<ImageDTORequest> images = new ArrayList<>();
            List<FileItem> fileItems = upload.parseRequest(req);
            for (int inc = 0, fileItemsSize = fileItems.size(); inc < fileItemsSize; inc++) {
                FileItem fileItem = fileItems.get(inc);
                if (!fileItem.isFormField() && fileItem.getSize() <= upload.getSizeMax()) {
                    String contentType = fileItem.getContentType();
                    if (contentType.equals(JSON_FILE)) {
                        carAdDTORequest = getCarAdDTORequest(ownerId, inc, fileItem);
                    } else {
                        ImageDTORequest image = getImageDTORequest(fileItem);
                        images.add(image);
                    }
                } else throw new FileUploadException();
            }
            if (carAdDTORequest == null) throw new NullPointerException();
            CarAdDTOResponse carAdDTOResponse = carAdService.add(carAdDTORequest);
            for (ImageDTORequest image : images) {
                image.setOwnerId(carAdDTOResponse.getId());
            }
            carAdDTOResponse.setImageQuantity(imageService.addAll(images).size());
            PrintWriter out = resp.getWriter();
            String jsonString = objectMapper.writeValueAsString(carAdDTOResponse);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(jsonString);
            out.flush();
        } catch (FileUploadException ex) {
            resp.sendError(422);
        } catch (JsonProcessingException ex) {
            resp.sendError(418);
        } catch (Exception ex) {
            resp.sendError(400);
        }
    }

    private ImageDTORequest getImageDTORequest(FileItem item) throws Exception {
        String oldName = item.getName();
        String fileType = oldName.substring(oldName.lastIndexOf(DOT));
        Timestamp timestamp = new Timestamp(new Date().getTime());
        String newName = timestamp.toString().replaceAll(ANY_NOT_NUMERAL_SYMBOL, EMPTY);
        item.write(new File(basePath + newName + fileType));
        ImageDTORequest image = new ImageDTORequest();
        image.setName(newName);
        image.setFileFormat(fileType);
        return image;
    }

    private CarAdDTORequest getCarAdDTORequest(Integer ownerId, int inc, FileItem jsonFile) throws Exception {
        CarAdDTORequest carAdDTORequest;
        File fileForRead = new File(DEF_NAME);
        jsonFile.write(fileForRead);
        BufferedReader reader = new BufferedReader(new FileReader(fileForRead));
        carAdDTORequest = objectMapper.readValue(reader, CarAdDTORequest.class);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        timestamp.setNanos(timestamp.getNanos() + 100000000 + inc * 2);
        carAdDTORequest.setCreationDate(timestamp);
        carAdDTORequest.setLastEditDate(timestamp);
        carAdDTORequest.setOwnerId(ownerId);
        return carAdDTORequest;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class GetResponseBody {
        CarAdDTOResponse carAdDTOResponse;
        UserDAOResponse userDAOResponse;
        List<ImageDTOResponse> imageDTOResponses;
        List<String> imagesPath;
    }
}
