/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import me_me.DBConnection;

/**
 *
 * @author jokip
 */

@WebServlet("/addmeme")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50,location="C:\\Users\\jokip\\Documents\\NetBeansProjects\\MemeG\\web\\images")
public class PostDB extends HttpServlet {

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
    private static final long serialVersionUID = 1L;
    private static final String SAVE_DIR = "images";
    private static final String UPLOAD_DIR = "images";

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, FileNotFoundException {
        //String savePath = "C:\\Users\\jokip\\Documents\\NetBeansProjects\\MemeG\\web" + File.separator + SAVE_DIR;
        OutputStream out = null;
        InputStream fileContent = null;
        final PrintWriter writer = response.getWriter();
        //final String path = "images";
        final Part filePart = request.getPart("file");
        final String fileName = getFilename(filePart);

        out = new FileOutputStream(new File("C:\\Users\\jokip\\Documents\\NetBeansProjects\\MemeG\\web\\images\\"+ File.separator+fileName));
        fileContent = filePart.getInputStream();

        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = fileContent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        writer.println("worked");

//        Part file = request.getPart("file");
//        String filename = getFilename(file);
//        InputStream filecontent = file.getInputStream();
//        // ... Do your file saving job here.
//        ;
//        response.setContentType("text/plain");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write("File " + filename + " successfully uploaded");
//        File fileSaveDir = new File(savePath);
//        Part part = request.getPart("file");
//        String fileName = extractFileName(part);
//        part.write(savePath + File.separator + fileName);

        int status;
        index indexx = new index();
        //PrintWriter out = response.getWriter();

        try {

            Connection con = DBConnection.DatabaseConnect();

            PreparedStatement st;
            st = con.prepareStatement("insert into meme(Pic_Vid, Caption, Category) values(?, ?, ?)");

            //String filePath = savePath + File.separator + fileName;
            String filePath = fileName;
            st.setString(1, filePath);
            st.setString(2, request.getParameter("title"));
            st.setString(3, request.getParameter("category"));

            // System.out.println("st");
            //System.out.println(st);
            st.execute();

            // Close all the connections 
            st.close();
            con.close();

            //indexx.processRequest(request, response);
            response.sendRedirect("indexx.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String extractFileName(Part part)
            throws FileNotFoundException {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);

            }
        }
        return "";
    }
}
