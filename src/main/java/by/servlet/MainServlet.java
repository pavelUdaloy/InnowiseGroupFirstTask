package by.servlet;

import by.entity.dao.request.TelephoneDAORequest;
import com.google.gson.Gson;
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
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static by.util.TextLabels.*;

@WebServlet(name = "by/servlet/MainServlet.java", urlPatterns = "/test")
public class MainServlet extends HttpServlet {

    private boolean isMultipart;
    private File file;

    @Override
    public void init() throws ServletException {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("AAAAA");
        resp.sendError(401);
//        resp.setContentType("text/html");
//        PrintWriter printWriter = resp.getWriter();
//        printWriter.write("\n Hello123!");
//        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Properties property = new Properties();
        FileInputStream fis = new FileInputStream(PROPERTIES_PATH);
        property.load(fis);

        if (!ServletFileUpload.isMultipartContent(request)) {
            response.sendError(400);
        }
        response.setContentType("text/html");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(Integer.parseInt(property.getProperty(PROPERTIES_MAX_MEMORY_SIZE)));
        String basePath = property.getProperty(PROPERTIES_BASE_PATH);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(Integer.parseInt(property.getProperty(PROPERTIES_MAX_FILE_SIZE)));

        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem item : fileItems) {
                if (!item.isFormField() && item.getSize() <= upload.getSizeMax()) {
                    String contentType = item.getContentType();
                    if (contentType.equals(JSON_FILE)) {
                        Gson gson = new Gson();
                        File file = new File("as.txt");
                        item.write(file);
                        BufferedReader br = new BufferedReader(
                                new FileReader(file));
                        TelephoneDAORequest telephoneDAORequest = gson.fromJson(br, TelephoneDAORequest.class);
                        System.out.println(telephoneDAORequest);
                    } else {
                        file = new File(getNewFileName(basePath, item.getName()));
                        item.write(file);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private String getNewFileName(String savingPath, String oldFileName) {
        String fileType = oldFileName.substring(oldFileName.lastIndexOf(DOT));
        Timestamp timestamp = new Timestamp(new Date().getTime());
        String newName = timestamp.toString().replaceAll(ANY_NOT_NUMERAL_SYMBOL, EMPTY);
        return savingPath + newName + fileType;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
//доабвиь идентификацию по id