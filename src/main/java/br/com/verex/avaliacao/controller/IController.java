package br.com.verex.avaliacao.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IController {
    HttpResponse handler(HttpServletRequest request, HttpServletResponse response);
}
