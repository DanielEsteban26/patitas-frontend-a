package pe.edu.cibertec.patitas_frontend_a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.cibertec.patitas_frontend_a.viewmodel.LoginModel;

@Controller
@RequestMapping("/login")
public class LoginController {


    @GetMapping("/inicio")
    public String inicio(Model model) {
        LoginModel loginModel = new LoginModel("00", "", "");
        model.addAttribute("loginModel", loginModel);
        return "inicio";

    }

    @PostMapping("autenticar")
    public String autenticar(@RequestParam("tipoDocumento") String tipoDocumento,
                             @RequestParam("numeroDocumento") String numeroDocumento,
                             @RequestParam("password") String password,
                             Model model) {

        //validar campos de entrada
        if(tipoDocumento == null || tipoDocumento.trim().length() ==0 ||
           numeroDocumento == null || numeroDocumento.trim().length() ==0 ||
           password == null || password.trim().isEmpty()){
            LoginModel loginModel = new LoginModel("01", "RELLENAAAAAAAAAAAAAAA", "Daniel Isidro");
            model.addAttribute("loginModel", loginModel);
            return "inicio";
        }
        LoginModel loginModel = new LoginModel("00", "", "Daniel Isidro");
        model.addAttribute("loginModel", loginModel);
        return "principal";

    }

}
