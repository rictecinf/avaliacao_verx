package br.com.verex.avaliacao.controller.usuario;

import br.com.verex.avaliacao.controller.IController;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@Log
public class UsuarioController implements IController {

    @Override
    @RequestMapping( "/usuarios")
    public HttpResponse handler(HttpServletRequest request, HttpServletResponse response) {
        log.info("passou aqui" + request.getMethod());
        UsuarioRouterEnum.valueOf(request.getMethod().toUpperCase()).handler(request,response);
        return null;
    }
}
