package com.mvc.jump;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class StaticFileManager {
    public void sendFile(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        File resourceFile = new File(servletContext.getRealPath("/") + req.getServletPath());
        if (!resourceFile.exists() || resourceFile.isDirectory()) {
            System.out.println("File doesn't exist: " + servletContext.getRealPath("/") + req.getServletPath());
            return;
        }

        OutputStream outputStream = resp.getOutputStream();
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(resourceFile));
            byte[] buf = new byte[4096];
            while(true) {
                int len = inputStream.read(buf);
                if (len == -1) {
                    break;
                }
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
