//package by.artem.servlet;
//
//import by.artem.dao.util.JspHelper;
//import by.artem.service.CompetitionCatalogService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//@WebServlet("/competitions")
//public class CompetitionCatalogServlet extends HttpServlet {
//
//    private final CompetitionCatalogService competitionCatalogService = CompetitionCatalogService.getInstance();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        req.setAttribute("competitions", competitionCatalogService.findAll());
//        req.getRequestDispatcher(JspHelper.getPath("competitions")).forward(req, resp);
//
//    }
//}
