package by.servlet;

import by.entity.dto.CarAdDto;
import by.entity.dto.ImageDto;
import by.exception.ConnectionWithDBLostException;
import by.exception.CustomFileToJsonException;
import by.exception.CustomRequestException;
import by.exception.CustomResponseException;
import by.exception.IncorrectRequestParameterException;
import by.exception.JsonParserException;
import by.exception.NullQueryException;
import by.service.AdService;
import by.service.AdServiceImpl;
import by.servlet.responseentity.AddAdResponse;
import by.servlet.responseentity.DeleteAdResponse;
import by.servlet.responseentity.GetAdResponse;
import by.servlet.responseentity.PaginationGetAdResponse;
import by.servlet.responseentity.UpdateAdResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;
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
public class AdsServlet extends HttpServlet {

    private String basePath;
    private ServletFileUpload upload;

    private final AdService adService = new AdServiceImpl();

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ErrorUtils errorUtils = new ErrorUtils();

    @SneakyThrows
    @Override
    public void init() {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(Integer.parseInt(property.getProperty(PROPERTIES_MAX_MEMORY_SIZE)));
        basePath = property.getProperty(PROPERTIES_BASE_PATH);
        upload = new ServletFileUpload(factory);
        upload.setSizeMax(Integer.parseInt(property.getProperty(PROPERTIES_MAX_FILE_SIZE)));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String carAdIdInString = request.getParameter(ID);
            if (carAdIdInString != null) {
                Integer carAdId = Integer.valueOf(carAdIdInString);

                GetAdResponse getAdResponse = adService.get(carAdId);

                String jsonString = objectMapper.writeValueAsString(getAdResponse);
                response.setContentType(JSON_FILE);
                ServletOutputStream out = response.getOutputStream();
                response.setCharacterEncoding(UTF8);
                out.print(jsonString);
                out.flush();
            } else {
                Integer size = Integer.valueOf(request.getParameter(SIZE_PARAM));
                Integer page = Integer.valueOf(request.getParameter(PAGE_PARAM));

                PaginationGetAdResponse getAdResponse = adService.getWithPagination(size, page);

                String jsonString = objectMapper.writeValueAsString(getAdResponse);
                PrintWriter out = response.getWriter();
                response.setContentType(JSON_FILE);
                response.setCharacterEncoding(UTF8);
                out.print(jsonString);
                out.flush();
            }
        } catch (ConnectionWithDBLostException | NullQueryException ex) {
            errorUtils.sendErrorJson(response, ex);
        } catch (NumberFormatException ex) {
            errorUtils.sendErrorJson(response, new IncorrectRequestParameterException());
        } catch (JsonProcessingException ex) {
            errorUtils.sendErrorJson(response, new JsonParserException());
        } catch (IOException ex) {
            errorUtils.sendErrorJson(response, new CustomResponseException());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        try {
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
            id = Integer.parseInt(dataMap.get(ID));
            age = Integer.parseInt(dataMap.get(AGE));
            brand = dataMap.get(BRAND);
            model = dataMap.get(MODEL);
            engineSize = Integer.parseInt(dataMap.get(ENGINE_SIZE_PARAM));
            enginePower = Integer.parseInt(dataMap.get(ENGINE_POWER_PARAM));
            mileage = Integer.parseInt(dataMap.get(MILEAGE));

            UpdateAdResponse update = adService.update(id, age, brand, model, engineSize, enginePower, mileage);

            String jsonString = objectMapper.writeValueAsString(update);
            resp.setContentType(JSON_FILE);
            ServletOutputStream out = resp.getOutputStream();
            resp.setCharacterEncoding(UTF8);
            out.print(jsonString);
            out.flush();
        } catch (ConnectionWithDBLostException | NullQueryException ex) {
            errorUtils.sendErrorJson(resp, ex);
        } catch (NumberFormatException exception) {
            errorUtils.sendErrorJson(resp, new IncorrectRequestParameterException());
        } catch (JsonProcessingException ex) {
            errorUtils.sendErrorJson(resp, new JsonParserException());
        } catch (IOException ex) {
            errorUtils.sendErrorJson(resp, new CustomResponseException());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer carAdId = Integer.valueOf(req.getParameter(ID));

            DeleteAdResponse deleteAdResponse = adService.delete(carAdId);

            String jsonString = objectMapper.writeValueAsString(deleteAdResponse);
            resp.setContentType(JSON_FILE);
            ServletOutputStream out = resp.getOutputStream();
            resp.setCharacterEncoding(UTF8);
            out.print(jsonString);
            out.flush();
        } catch (ConnectionWithDBLostException | NullQueryException ex) {
            errorUtils.sendErrorJson(resp, ex);
        } catch (NumberFormatException exception) {
            errorUtils.sendErrorJson(resp, new IncorrectRequestParameterException());
        } catch (JsonProcessingException ex) {
            errorUtils.sendErrorJson(resp, new JsonParserException());
        } catch (IOException ex) {
            errorUtils.sendErrorJson(resp, new CustomResponseException());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer ownerId = (Integer) req.getSession().getAttribute(ID);
            if (!ServletFileUpload.isMultipartContent(req)) {
                throw new FileUploadException();
            }
            CarAdDto carAdDTO = null;
            List<ImageDto> imageDtos = new ArrayList<>();
            List<FileItem> fileItems = upload.parseRequest(req);
            for (int inc = 0, fileItemsSize = fileItems.size(); inc < fileItemsSize; inc++) {
                FileItem fileItem = fileItems.get(inc);
                if (!fileItem.isFormField() && fileItem.getSize() <= upload.getSizeMax()) {
                    String contentType = fileItem.getContentType();
                    if (contentType.equals(JSON_FILE)) {
                        carAdDTO = getCarAdDTORequest(ownerId, inc, fileItem);
                    } else {
                        ImageDto imageDTO = getImageDTORequest(fileItem);
                        imageDtos.add(imageDTO);
                    }
                } else throw new CustomRequestException();
            }
            if (carAdDTO == null) throw new NullPointerException();

            AddAdResponse addAdResponse = adService.add(carAdDTO, imageDtos);

            PrintWriter out = resp.getWriter();
            String jsonString = objectMapper.writeValueAsString(addAdResponse);
            resp.setContentType(JSON_FILE);
            resp.setCharacterEncoding(UTF8);
            out.print(jsonString);
            out.flush();
        } catch (CustomFileToJsonException | CustomRequestException | ConnectionWithDBLostException | NullQueryException | JsonParserException ex) {
            errorUtils.sendErrorJson(resp, ex);
        } catch (NumberFormatException exception) {
            errorUtils.sendErrorJson(resp, new IncorrectRequestParameterException());
        } catch (JsonProcessingException ex) {
            errorUtils.sendErrorJson(resp, new JsonParserException());
        } catch (IOException ex) {
            errorUtils.sendErrorJson(resp, new CustomResponseException());
        } catch (FileUploadException e) {
            errorUtils.sendErrorJson(resp, new CustomRequestException());
        }
    }

    private ImageDto getImageDTORequest(FileItem item) throws CustomFileToJsonException {
        String oldName = item.getName();
        String fileType = oldName.substring(oldName.lastIndexOf(DOT));
        Timestamp timestamp = new Timestamp(new Date().getTime());
        String newName = timestamp.toString().replaceAll(ANY_NOT_NUMERAL_SYMBOL, EMPTY);
        try {
            item.write(new File(basePath + newName + fileType));
        } catch (Exception exception) {
            throw new CustomFileToJsonException();
        }
        ImageDto image = new ImageDto();
        image.setName(newName);
        image.setFileFormat(fileType);
        return image;
    }

    private CarAdDto getCarAdDTORequest(Integer ownerId, int inc, FileItem jsonFile) throws JsonParserException, CustomFileToJsonException {
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
        Timestamp timestamp = new Timestamp(new Date().getTime());
        timestamp.setNanos(timestamp.getNanos() + (100000000 + inc * 2));
        carAdDTO.setCreationDate(timestamp);
        carAdDTO.setLastEditDate(timestamp);
        carAdDTO.setOwnerId(ownerId);
        return carAdDTO;
    }
}
