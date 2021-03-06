/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import Entite.CFicheFrais;
import Entite.CFrais;
import Entite.CTypeFrais;
import Tables.CTableFicheFrais;
import Tables.CTableFrais;
import Tables.CTableTypeFrais;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.util.ArrayList;
import javax.swing.JTable;


/**
 *
 * @author admin
 */
public class CMetierFicheFrais {

    public CTableFicheFrais getTableFicheFrais() {
        return tableFicheFrais;
    }

    public void setTableFicheFrais(CTableFicheFrais tableFicheFrais) {
        this.tableFicheFrais = tableFicheFrais;
    }

    public CTableTypeFrais getTableTypeFrais() {
        return tableTypeFrais;
    }

    public void setTableTypeFrais(CTableTypeFrais tableTypeFrais) {
        this.tableTypeFrais = tableTypeFrais;
    }

    public String getNomVisiteur() {
        return nomVisiteur;
    }

    public void setNomVisiteur(String nomVisiteur) {
        this.nomVisiteur = nomVisiteur;
    }

    public String getMatricule() {
        return matricule;
    }
    
    public CTableFrais getTableFrais() {
        return tableFrais;
    }

    public void setTableFrais(CTableFrais tableFrais) {
        this.tableFrais = tableFrais;
    }

    // attributs
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
          CTableFicheFrais tableFicheFrais ;
          CTableTypeFrais tableTypeFrais;
          CTableFrais tableFrais;
          String nomVisiteur;
          String matricule;
          
          
          public CMetierFicheFrais (CTableFicheFrais tableFicheFrais,  CTableTypeFrais tableTypeFrais, CTableFrais tableFrais) {
              this.tableFicheFrais= tableFicheFrais;
              this.tableTypeFrais = tableTypeFrais;
              this.tableFrais = tableFrais;
          }
          
         /* public void valider(String matricule, String mois, JTable table){
              table.this.tableFicheFrais.fetchTravailsMois(matricule, mois);
          }*/
          
          // methode permettant de récupérer la fiche de frais correspondant à un mois et un matricule dans une liste
          public ArrayList<CFicheFrais> valider(String matricule, int mois){
              return this.tableFicheFrais.fetchTravailsMois(matricule, mois);
          }
          
          
          // methode permettant de recupérer la liste des mois où il y a une fiche de frais pour un matricule donné
          public String[] listeMois(String matricule) {
              ArrayList<CFicheFrais> listeFichesFrais = this.tableFicheFrais.fetchFichesFrais(matricule);
              String[] tab = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};
             // ArrayList<String> listedesMois= new ArrayList();
              int nbreMois = listeFichesFrais.size();
              String[] tableauMois = new String[nbreMois];
           
              for (int i = 0; i < nbreMois; i++) {
                  
                    tableauMois[i] = tab[listeFichesFrais.get(i).getMois()-1];
                    // listedesMois.add(tab[listeFichesFrais.get(i).getMois()-1]);   
                     
              }
              
              
              return tableauMois;
          }
          
          
        // permet de lire tous les types de frais
    public ArrayList<CTypeFrais> listeTypeFrais() { 
        return this.tableTypeFrais.lireTypeFrais();
    }
       
          
          // permet de recupérer la liste de tous les noms de frais dans la table type de frais
          public String[] listeNomFrais() {
              ArrayList<CTypeFrais> listedesTypeFrais = listeTypeFrais();
              int nbreFrais = listedesTypeFrais.size();
              String[] tableauFrais = new String[nbreFrais];
              
              for (int i = 0; i < nbreFrais; i++) {
                  tableauFrais[i] = listedesTypeFrais.get(i).getLabel();
              }
              
              return tableauFrais;
          }
          
          
          // permet d'ajouter une fiche de frais
          public void ajouterFicheFrais(CFicheFrais ficheFrais) {
              this.tableFicheFrais.insererFicheFrais(ficheFrais);
          }
          
          // permet de modifier une fiche de frais
          public void modifierFicheFrais(CFicheFrais ficheFrais) {
              this.tableFicheFrais.modifierFicheFrais(ficheFrais);
              int nbFrais = ficheFrais.getListeFrais().size();
              for (int i = 0; i < nbFrais; i++) {
                  this.tableFrais.modifierFrais(ficheFrais.getListeFrais().get(i));
              }
          }
          
        /*  // permet de supprimer une fiche de frais
          public void supprimerFicheFrais (ArrayList<CFrais> listeFrais){
              for (int i = 0; i < listeFrais.size(); i++) {
                 this.tableFrais.supprimerFrais(listeFrais.get(i).getIdFicheFrais(), listeFrais.get(i).getTypeFrais().getCode()); 
              }
              
              this.tableFicheFrais.supprimerFicheFrais(listeFrais.get(0).getIdFicheFrais());
              
          }*/
          
            // permet de supprimer une fiche de frais
          public void supprimerFicheFrais (int idFicheFrais){
             CFicheFrais ficheFrais =  this.tableFicheFrais.lireUneFicheFrais(idFicheFrais);
             ArrayList<CFrais> listeFrais = ficheFrais.getListeFrais();
              for (int i = 0; i < listeFrais.size(); i++) {
                 this.tableFrais.supprimerFrais(listeFrais.get(i).getIdFicheFrais(), listeFrais.get(i).getTypeFrais().getCode()); 
              }
              
              this.tableFicheFrais.supprimerFicheFrais(idFicheFrais);
              
          }

}
