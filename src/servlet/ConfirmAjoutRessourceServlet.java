package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assets.AjoutRessource;
import assets.Fournisseur;
import assets.Ressource;

public class ConfirmAjoutRessourceServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idRessource=req.getParameter("ressource");
        String idFournisseur=req.getParameter("fournisseur");
        String quantite=req.getParameter("quantite");
        String date=req.getParameter("date");
        String prixUnitaire=req.getParameter("prixUnitaire");

        AjoutRessource ajout=new AjoutRessource();
        Ressource ressource=new Ressource();
        ressource.setId(idRessource);
        Fournisseur fournisseur=new Fournisseur();
        fournisseur.setId(idFournisseur);
        try{
            ressource.getRessourceById(null);
            fournisseur.getFournisseurById(null);
            ajout.setRessource(ressource);
            ajout.setFournisseur(fournisseur);
            ajout.setQuantite(quantite);
            ajout.setDate(date);
            ajout.setPrixUnitaire(prixUnitaire);
            ajout.insert(null);
            String path="";
            resp.sendRedirect(path);
        }catch(Exception e){
            e.printStackTrace();
            String pathRetour="";
            resp.sendRedirect(pathRetour);
        }
    }
}
