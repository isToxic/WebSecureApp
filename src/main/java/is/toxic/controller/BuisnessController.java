package is.toxic.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuisnessController {

    @Value("${index-resource-name}")
    private String indexResourceName;

    @Value("${admin-resource-name}")
    private String adminResourceName;

    @Value("${user-resource-name}")
    private String userResourceName;


    @GetMapping(value = "${index-mapping}")
    public String index() {
        return indexResourceName;
    }

    @GetMapping(value = "${admin-mapping}")
    public String admin() {
        return adminResourceName;
    }

    @GetMapping(value = "${user-mapping}")
    public String user() {
        return userResourceName;
    }


}