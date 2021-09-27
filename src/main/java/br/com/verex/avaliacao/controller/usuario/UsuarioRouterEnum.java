package br.com.verex.avaliacao.controller.usuario;

import lombok.extern.java.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log
public enum UsuarioRouterEnum implements UsuarioRouter {

    GET{
        @Override
        public HttpServletResponse handler(HttpServletRequest request, HttpServletResponse response) {
            log.info("GET::::");
            return null;
        }
    },
    POST{
        @Override
        public HttpServletResponse handler(HttpServletRequest request, HttpServletResponse response) {
            log.info("dasfsdfsdfsdf");
            return null;
        }

    },
    PUT{
        @Override
        public HttpServletResponse handler(HttpServletRequest request, HttpServletResponse response) {
            return null;
        }
    };


}
