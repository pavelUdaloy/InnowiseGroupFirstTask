package by.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

import static by.util.TextLabels.PROPERTIES_BASE_PATH;
import static by.util.TextLabels.PROPERTIES_MAX_FILE_SIZE;
import static by.util.TextLabels.PROPERTIES_MAX_MEMORY_SIZE;
import static by.util.TextLabels.PROPERTIES_PATH;
import static by.util.TextLabels.PROPERTIES_PATH_FOR_HUGE_FILES;

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

//        if (ServletFileUpload.isMultipartContent(request)) {
//        }
        response.setContentType("text/html");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(Integer.parseInt(property.getProperty(PROPERTIES_MAX_MEMORY_SIZE)));
        String basePath = property.getProperty(PROPERTIES_BASE_PATH);
        String pathForHugeFiles = property.getProperty(PROPERTIES_PATH_FOR_HUGE_FILES);
        factory.setRepository(new File(pathForHugeFiles));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(Integer.parseInt(property.getProperty(PROPERTIES_MAX_FILE_SIZE)));

        try {
            List<FileItem> fileItems = upload.parseRequest(request);

            for (FileItem item : fileItems) {
                if (!item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
//                    "text/plain"
//                    "image/png"
//                    "image/jpeg"
                    String contentType = item.getContentType();
                    boolean isInMemory = item.isInMemory();
                    long sizeInBytes = item.getSize();

                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(basePath + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(basePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    item.write(file);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
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
}
//доабвиь идентификацию по id