package by.servlet;

import by.entity.dto.request.CarAdDTORequest;
import by.entity.dto.request.TelephoneDTORequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        PrintWriter out = response.getWriter();
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
////            CarAdDTORequest carAdDTORequest = mapper.readValue(request.getReader(), CarAdDTORequest.class);
//            CarAdDTORequest carAdDTORequest = new CarAdDTORequest();
//            carAdDTORequest.setAge(12);
//            carAdDTORequest.setMileage(121212);
//            TelephoneDTORequest telephoneDTORequest = new TelephoneDTORequest();
//            telephoneDTORequest.setNumber("1212121");
//            TelephoneDTORequest telephoneDTORequest2 = new TelephoneDTORequest();
//            telephoneDTORequest2.setNumber("1212121");
//            ArrayList<TelephoneDTORequest> arrayList = new ArrayList<>();
//            arrayList.add(telephoneDTORequest);
//            arrayList.add(telephoneDTORequest2);
//            carAdDTORequest.setTelephones(arrayList);
//            String s = mapper.writeValueAsString(carAdDTORequest);
//            System.out.println(s);
//            System.out.println(carAdDTORequest);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        out.close();
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