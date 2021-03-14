package by.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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

@WebServlet(urlPatterns = "/")
public class MainServlet extends HttpServlet {

    private boolean isMultipart;
    private File file;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("AAAAA");
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("\n Hello123!");
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Properties property = new Properties();
        FileInputStream fis = new FileInputStream
                ("C:\\Users\\user\\IdeaProjects\\SimpleWeb\\src\\main\\resources\\properties.properties");
        property.load(fis);

//        if (ServletFileUpload.isMultipartContent(request)) {
//        }
        response.setContentType("text/html");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(Integer.parseInt(property.getProperty("maxMemSize")));
        String basePath = property.getProperty("basePath");
        String pathForHugeFiles = property.getProperty("pathForHugeFiles");
        factory.setRepository(new File(pathForHugeFiles));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(Integer.parseInt(property.getProperty("maxFileSize")));

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
}
//доабвиь идентификацию по id