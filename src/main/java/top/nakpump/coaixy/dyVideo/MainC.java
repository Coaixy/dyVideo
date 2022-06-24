package top.nakpump.coaixy.dyVideo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class MainC {
    @RequestMapping("get")
    @ResponseBody
    public String returnResult(String url) throws IOException {
        Parse p = new Parse(url);
        return p.getTrueUrl();
    }
}
