package is.toxic.webApp.controller;

import is.toxic.entity.AppUser;
import is.toxic.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {
    private final UserDetailsServiceImpl userService;

    @Value("${login-resource-name}")
    private String loginResourceName;

    @Value("${logout-resource-name}")
    private String logoutResourceName;

    @Value("${forbidden-resource-name}")
    private String forbiddenResourceName;

    @Value("${registration-resource-name}")
    private String regResource;

    @Value("${index-resource-name}")
    private String indexResource;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new AppUser());
        return regResource;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userForm") @Valid AppUser userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return regResource;
        }
        if (!userForm.getEncrytedPassword().equals(userForm.getConfirmPassword())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return regResource;
        }
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return regResource;
        }
        return indexResource;
    }

    @RequestMapping(value = "${login-mapping}")
    public String loginPage(Model model) {
        return loginResourceName;
    }

    @RequestMapping(value = "${success-logout-mapping}")
    public String logoutSuccessfulPage(Model model) {
        return logoutResourceName;
    }

    @RequestMapping(value = "${forbidden-mapping}")
    public String accessDenied(Model model) {
        return forbiddenResourceName;
    }
}
