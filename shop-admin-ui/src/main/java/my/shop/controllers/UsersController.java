package my.shop.controllers;

import my.shop.entity.User;
import my.shop.repo.RoleRepository;
import my.shop.rest.NotFoundException;
import my.shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.regex.Pattern;

@RequestMapping("/user")
@Controller
public class UsersController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public UsersController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

//    @GetMapping("/list")
//    public String userList(Model model,
//                           @RequestParam (name = "page", defaultValue = "1", required = false) Integer page,
//                           @RequestParam (name = "size", defaultValue = "5", required = false) Integer size
//    ) {
//
//        Page<User> userPage = userService.filterByAge(
//                PageRequest.of(page- 1, size)
//        );
//        model.addAttribute("usersPage", userPage);
//        model.addAttribute("prevPageNumber", userPage.hasPrevious() ? userPage.previousPageable().getPageNumber() + 1 : -1);
//        model.addAttribute("nextPageNumber", userPage.hasNext() ? userPage.nextPageable().getPageNumber() + 1 : -1);
//        return "users";
//    }

    @GetMapping("/list")
    public String userList(){
        return "users";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "user_card";
    }

    @GetMapping(path="/update/{id}")
    public String updateById(Model model, @PathVariable(value = "id") Long userId) {
        model.addAttribute("user",  userService.findById(userId)
                .orElseThrow(() -> new NotFoundException()));
        model.addAttribute("roles", roleRepository.findAll());
        return "user_card";
    }

    @GetMapping(path="/remove/{id}")
    public String removeById(@PathVariable(value = "id") Long userId) {
        userService.delete(userId);
        return "redirect:/user/list";
    }

    @PostMapping
    public String saveUser(@Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user_card";
        }
//        if (!user.getPassword().equals(user.getRepeatPassword())) {
//            bindingResult.rejectValue("repeatPassword", "", "пароли не совпадают");
//            return "user";
//        }

        //Password validation
        Pattern kirillPat = Pattern.compile("^.*[А-Яа-я].*$");
        Pattern mainPat = Pattern.compile("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");//Честно стырено с Хабра, но всё понятно
        if (!(mainPat.matcher(user.getPassword()).matches()& !kirillPat.matcher(user.getPassword()).matches())) {
            bindingResult.rejectValue("password", "", "Пароль слишком простой");
            return "user_card";
        }

        userService.save(user);
        return "redirect:/user/list";
    }
}