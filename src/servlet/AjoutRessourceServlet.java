package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assets.Fournisseur;
import assets.Ressource;

public class AjoutRessourceServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<Ressource> ressources=Ressource.getAllRessources(null);
            ArrayList<Fournisseur> fournisseurs=Fournisseur.getAllFournisseurs(null);

            String path="";
            RequestDispatcher dispat=req.getRequestDispatcher(path);
            req.setAttribute("ressources", ressources);
            req.setAttribute("fournisseurs", fournisseurs);
            dispat.forward(req, resp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
