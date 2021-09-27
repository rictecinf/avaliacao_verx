package br.com.verex.avaliacao.controller.usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UsuarioRouter {

    HttpServletResponse handler(HttpServletRequest request, HttpServletResponse response);
}
