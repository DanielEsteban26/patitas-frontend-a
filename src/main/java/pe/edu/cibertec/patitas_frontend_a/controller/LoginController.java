package pe.edu.cibertec.patitas_frontend_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pe.edu.cibertec.patitas_frontend_a.viewmodel.LoginModel;
import pe.edu.cibertec.patitas_frontend_a.viewmodel.LoginRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

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
        if (tipoDocumento == null || tipoDocumento.trim().length() == 0 ||
                numeroDocumento == null || numeroDocumento.trim().length() == 0 ||
                password == null || password.trim().isEmpty()) {
            LoginModel loginModel = new LoginModel("01", "Por favor, complete todos los campos.", "");
            model.addAttribute("loginModel", loginModel);
            return "inicio";
        }

        String url = "http://localhost:8081/autentificacion/login"; //url del backend
        LoginRequest LoginReqDTO = new LoginRequest(tipoDocumento, numeroDocumento, password);
        LoginModel loginModel = restTemplate.postForObject(url, LoginReqDTO , LoginModel.class);

        // Verificar la respuesta del backend
        if (loginModel != null && "00".equals(loginModel.codigo())) {
            model.addAttribute("loginModel", loginModel);
            return "principal";
        } else {
            model.addAttribute("loginModel", new LoginModel("01", "Credenciales incorrectas.", ""));
            return "inicio";
        }
    }
}
