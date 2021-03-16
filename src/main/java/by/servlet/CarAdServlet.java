package by.servlet;

import by.entity.dao.response.UserDaoResponse;
import by.entity.dto.request.CarAdDtoRequest;
import by.entity.dto.request.ImageDtoRequest;
import by.entity.dto.response.CarAdDtoResponse;
import by.exception.ConnectionWithDBLostException;
import by.exception.IncorrectSQLParametersException;
import by.service.CarAdService;
import by.service.CarAdServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static by.util.TextLabels.AGE;
import static by.util.TextLabels.ANY_NOT_NUMERAL_SYMBOL;
import static by.util.TextLabels.BRAND;
import static by.util.TextLabels.DEF_NAME;
import static by.util.TextLabels.DOT;
import static by.util.TextLabels.EMPTY;
import static by.util.TextLabels.ENGINE_POWER_PARAM;
import static by.util.TextLabels.ENGINE_SIZE_PARAM;
import static by.util.TextLabels.EQUALLY;
import static by.util.TextLabels.ID;
import static by.util.TextLabels.JSON_FILE;
import static by.util.TextLabels.MILEAGE;
import static by.util.TextLabels.MODEL;
import static by.util.TextLabels.PAGE_PARAM;
import static by.util.TextLabels.PROPERTIES_BASE_PATH;
import static by.util.TextLabels.PROPERTIES_MAX_FILE_SIZE;
import static by.util.TextLabels.PROPERTIES_MAX_MEMORY_SIZE;
import static by.util.TextLabels.SIZE_PARAM;
import static by.util.TextLabels.SPLITTER;
import static by.util.TextLabels.UTF8;
import static by.util.TextLabels.property;

@WebServlet(name = "by/servlet/CarAdServlet.java", urlPatterns = "/ads")
public class CarAdServlet extends HttpServlet {

    private String basePath;
    private ServletFileUpload upload;

    private final CarAdService carAdService = new CarAdServiceImpl();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void init() {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(Integer.parseInt(property.getProperty(PROPERTIES_MAX_MEMORY_SIZE)));
        basePath = property.getProperty(PROPERTIES_BASE_PATH);
        upload = new ServletFileUpload(factory);
        upload.setSizeMax(Integer.parseInt(property.getProperty(PROPERTIES_MAX_FILE_SIZE)));
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String carAdIdInString = request.getParameter(ID);
        if (carAdIdInString != null) {
            try {
                Integer carAdId = Integer.valueOf(carAdIdInString);

                GetResponseBody getResponseBody = carAdService.get(carAdId);

                String jsonString = objectMapper.writeValueAsString(getResponseBody);
                response.setContentType(JSON_FILE);
                ServletOutputStream out = response.getOutputStream();
                response.setCharacterEncoding(UTF8);
                out.print(jsonString);
                out.flush();
            } catch (NumberFormatException | IncorrectSQLParametersException | ConnectionWithDBLostException ex) {
                response.sendError(400);
            }
        } else {
            try {
                Integer size = Integer.valueOf(request.getParameter(SIZE_PARAM));
                Integer page = Integer.valueOf(request.getParameter(PAGE_PARAM));

                List<CarAdDtoResponse> withPagination = carAdService.getWithPagination(size, page);

                String jsonString = objectMapper.writeValueAsString(withPagination);
                PrintWriter out = response.getWriter();
                response.setContentType(JSON_FILE);
                response.setCharacterEncoding(UTF8);
                out.print(jsonString);
                out.flush();
            } catch (NumberFormatException ex) {
                response.sendError(400);
            } catch (JsonProcessingException ex) {
                response.sendError(418);
            }
        }
    }

    @SneakyThrows
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        Map<String, String> dataMap = Splitter.on(SPLITTER).trimResults().withKeyValueSeparator
                (Splitter.on(EQUALLY).limit(2).trimResults()).split(br.readLine());
        int id;
        int age;
        String brand;
        String model;
        int engineSize;
        int enginePower;
        int mileage;
        try {
            id = Integer.parseInt(dataMap.get(ID));
            age = Integer.parseInt(dataMap.get(AGE));
            brand = dataMap.get(BRAND);
            model = dataMap.get(MODEL);
            engineSize = Integer.parseInt(dataMap.get(ENGINE_SIZE_PARAM));
            enginePower = Integer.parseInt(dataMap.get(ENGINE_POWER_PARAM));
            mileage = Integer.parseInt(dataMap.get(MILEAGE));
        } catch (NumberFormatException exception) {
            return;
        }

        CarAdDtoResponse update = carAdService.update(id, age, brand, model, engineSize, enginePower, mileage);

        String jsonString = objectMapper.writeValueAsString
                (update);
        resp.setContentType(JSON_FILE);
        ServletOutputStream out = resp.getOutputStream();
        resp.setCharacterEncoding(UTF8);
        out.print(jsonString);
        out.flush();
    }

    @SneakyThrows
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute(ID, 1);
        Integer ownerId = (Integer) req.getSession().getAttribute(ID);
        Integer carAdId = Integer.valueOf(req.getParameter(ID));

        GetResponseBody getResponseBody = carAdService.delete(carAdId, ownerId);

        String jsonString = objectMapper.writeValueAsString(getResponseBody);
        resp.setContentType(JSON_FILE);
        ServletOutputStream out = resp.getOutputStream();
        resp.setCharacterEncoding(UTF8);
        out.print(jsonString);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute(ID, 1);
        Integer ownerId = (Integer) req.getSession().getAttribute(ID);
        if (!ServletFileUpload.isMultipartContent(req)) {
            resp.sendError(400);
        }
        try {
            CarAdDtoRequest carAdDTORequest = null;
            List<ImageDtoRequest> images = new ArrayList<>();
            List<FileItem> fileItems = upload.parseRequest(req);
            for (int inc = 0, fileItemsSize = fileItems.size(); inc < fileItemsSize; inc++) {
                FileItem fileItem = fileItems.get(inc);
                if (!fileItem.isFormField() && fileItem.getSize() <= upload.getSizeMax()) {
                    String contentType = fileItem.getContentType();
                    if (contentType.equals(JSON_FILE)) {
                        carAdDTORequest = getCarAdDTORequest(ownerId, inc, fileItem);
                    } else {
                        ImageDtoRequest image = getImageDTORequest(fileItem);
                        images.add(image);
                    }
                } else throw new FileUploadException();
            }
            if (carAdDTORequest == null) throw new NullPointerException();

            CarAdDtoResponse carAdDTOResponse = carAdService.add(carAdDTORequest, images);

            PrintWriter out = resp.getWriter();
            String jsonString = objectMapper.writeValueAsString(carAdDTOResponse);
            resp.setContentType(JSON_FILE);
            resp.setCharacterEncoding(UTF8);
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

    private ImageDtoRequest getImageDTORequest(FileItem item) throws Exception {
        String oldName = item.getName();
        String fileType = oldName.substring(oldName.lastIndexOf(DOT));
        Timestamp timestamp = new Timestamp(new Date().getTime());
        String newName = timestamp.toString().replaceAll(ANY_NOT_NUMERAL_SYMBOL, EMPTY);
        item.write(new File(basePath + newName + fileType));
        ImageDtoRequest image = new ImageDtoRequest();
        image.setName(newName);
        image.setFileFormat(fileType);
        return image;
    }

    private CarAdDtoRequest getCarAdDTORequest(Integer ownerId, int inc, FileItem jsonFile) throws Exception {
        CarAdDtoRequest carAdDTORequest;
        File fileForRead = new File(DEF_NAME);
        jsonFile.write(fileForRead);
        BufferedReader reader = new BufferedReader(new FileReader(fileForRead));
        carAdDTORequest = objectMapper.readValue(reader, CarAdDtoRequest.class);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        timestamp.setNanos(timestamp.getNanos() + 100000000 + inc * 2);
        carAdDTORequest.setCreationDate(timestamp);
        carAdDTORequest.setLastEditDate(timestamp);
        carAdDTORequest.setOwnerId(ownerId);
        return carAdDTORequest;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class GetResponseBody {
        CarAdDtoResponse carAdDTOResponse;
        UserDaoResponse userDAOResponse;
        List<String> imageIds;
    }//todo annotations
    //todo excps
}
