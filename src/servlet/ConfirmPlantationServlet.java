package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assets.Plantation;
import assets.ProduitIntermediaire;

public class ConfirmPlantationServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idproduit=req.getParameter("produit");
        String quantite=req.getParameter("quantite");
        String date=req.getParameter("date");

        ProduitIntermediaire produit=new ProduitIntermediaire();
        produit.setId(idproduit);
        Plantation plantation=new Plantation();
        try{
            produit.getProduitIntermediaireById(null);
            plantation.setProduit(produit);
            plantation.setQuantite(quantite);
            plantation.setDate(date);
        }catch(Exception e){

        }
    }
}
