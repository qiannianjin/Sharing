package top.arexstorm.sharing.controller.file;

import com.wf.captcha.Captcha;
import com.wf.captcha.GifCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/image")
public class ImageController {

    @RequestMapping(value = "/captcha", method = {RequestMethod.GET, RequestMethod.POST})
    public void captcha(String codeKey, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (codeKey != null && !codeKey.trim().isEmpty()) {
            response.setContentType("image/gif");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0L);
            Captcha captcha = new GifCaptcha(130, 38, 5);
            ServletContext servletContext = request.getSession().getServletContext();
            servletContext.setAttribute("code_" + codeKey, captcha.text().toLowerCase());
            captcha.out(response.getOutputStream());
        }
    }
}
