package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assets.ProduitIntermediaire;

public class PlantationServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<ProduitIntermediaire> produits=ProduitIntermediaire.getAllProduitIntermediaires(null);
            String path="";
            RequestDispatcher dispat=req.getRequestDispatcher(path);
            req.setAttribute("produitsIntermediaires", produits);
            dispat.forward(req, resp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
