/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppe;

import Entite.CDepartement;
import Entite.CFicheFrais;
import Entite.CFrais;
import Entite.CRegion;
import Entite.CSecteur;
import Entite.CTravail;
import Entite.CTypeFrais;
import Entite.CVisiteur;
import Tables.CTableFicheFrais;
import Tables.CTableFrais;
import Tables.CTableRegion;
import Tables.CTableSecteur;
import Tables.CTableTravail;
import Tables.CTableTypeFrais;
import Tables.CTableVisiteur;
import Utilitaires.CUtilitaire;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;


/**
 *
 * @author admin
 */
public class PPE {
      public static void main(String[] args) {
          
          //creation d'un objet pour avoir accès à la base de donnée
          CBDD bdd=new CBDD(new CParametresStockageBDD("parametresBdd.properties"));
         
          CTableTravail tableTravail = new CTableTravail(bdd); 
          
        //on crée une instance de la classe CTableVisiteur, l'objet:  
        CTableVisiteur tableVisiteur = new CTableVisiteur(bdd, tableTravail);
        
        CTableRegion tableRegion = new CTableRegion(bdd); 
        
        CTableSecteur tableSecteur = new CTableSecteur(bdd);
        
        CTableTypeFrais tableTypeFrais = new CTableTypeFrais(bdd);
        
        CTableFrais tableFrais = new CTableFrais(bdd);
        
        CTableFicheFrais tableFicheFrais = new CTableFicheFrais(bdd, tableFrais);
        
        
                  
        CVisiteur visiteur = new CVisiteur();        

        

       
 
        //Test methode insererVisiteur
       
        //on crée un nouvel utilisateur de type CVisiteur(String matricule,
        //String nom, String prenom, String adresse, String codePostal, 
        //String ville, GregorianCalendar dateEmbauche, CSecteur secteur,
        //ArrayList<CTravail> listeTravail, CDepartement departement)
 /*      visiteur.setMatricule("1750");
       visiteur.setNom("FABIEN");
       visiteur.setPrenom("Fabrice");
       visiteur.setAdresse("12 rue du paradis");
       visiteur.setCodePostal("35000");
       visiteur.setVille("Rennes");
       visiteur.setDateEmbauche(CUtilitaire.convertSQLDatetoGregCal("2019-01-05"));
     
         //creation d'objets de type CSecteur
         CSecteur secteur1 = new CSecteur(1,"Nord");
         CSecteur secteur2 = new CSecteur(12,"Nord");
      
       //si le visiteur est responsable d'un secteur:
       visiteur.setSecteur(secteur2);
      
          //on ajoute une liste de travails concernant ce matricule:
         ArrayList<CTravail> listeTravails = new ArrayList(); //creation d'une liste de travails
         //on ajoute à la liste de travail 2 objets de type CTravail 
         listeTravails.add(new CTravail("Responsable", CUtilitaire.convertSQLDatetoGregCal("2019-03-11"),new CRegion(1,"bretagne", secteur1)));
          listeTravails.add(new CTravail("Visiteur", CUtilitaire.convertSQLDatetoGregCal("2019-03-30"),new CRegion(2,"Normandie", secteur2)));
       visiteur.setListeTravail(listeTravails);
       
       visiteur.setDepartement(new CDepartement(1, "Swiss", true));


       //test de la methode insererVisiteur pour inserer l'objet visiteur dans ma table visiteur
        tableVisiteur.insererVisiteur(visiteur);
*/
     
    // int matriculeVisiteur = tableVisiteur.insererVisiteur(visiteur);

  /*        for (int i = 0; i < size(listeTravails); i++) {
              
          }
     foreach(workList as work) {
         $dateID = tableDate.insererDate(work.date);
         $regionID = tableRegion.insertOrUpdateRegion(work.regionNumber);
         tableTravailler.insererTravail($regionID, $dateId, $matriculeID, ...);   
      }
   
     
     */
     
     


 /*
        //test pour la méthode supprimerVisiteurTr
        tableVisiteur.supprimerVisiteur(new CVisiteur("1718"));
 */
 
  /*     //test pour la methode modifierVisiteur
        CVisiteur visiteurAModifier =tableVisiteur.lireUnVisiteur("1718");
        visiteurAModifier.setDateEmbauche(CUtilitaire.convertSQLDatetoGregCal("2018-09-03"));
        visiteurAModifier.setSecteur(null);
        tableVisiteur.modifierVisiteur(visiteurAModifier);
*/
                       
        //la methode lireUnVisiteur renvoie le visiteur d'un matricule donnée qu'on enregistre dans l'objet visiteur1 
        //CVisiteur visiteur1 = tableVisiteur.lireUnVisiteur("1234");
        
        
       
                       
        //la methode lireVisiteurs renvoie une liste de visiteur qu'on enregistre dans l'objet listevisiteur
       // ArrayList<CVisiteur> listevisiteur = tableVisiteur.lireVisiteurs();
       
       
       //on creer un nouveau travail pour un visiteur donné(matricule)
      // CTravail travailNouveau = new CTravail("visiteur",CUtilitaire.convertSQLDatetoGregCal("2018-11-01"), new CRegion(2) );
       //tableTravail.insererTravail(travailNouveau, "1234" );
       
       
        //on modifie le role d'un travail donné
        // CTravail travailAModifier = new CTravail("delegue regional", CUtilitaire.convertSQLDatetoGregCal("2019-03-20"), new CRegion(1));
        // tableTravail.modifierTravail(travailAModifier, "1234");
         
         //on lit un travail donné
         //CTravail travailLu = tableTravail.lireUnTravail(1, "2019-03-20", "1234");
         
         //on supprime un travail donné
         //tableTravail.supprimerTravail(2, "2018-11-01", "1234");
       
         //TESTS DES METHODES CTableRegion
         
         //Inserer une region dans la table region
        // CRegion region1 = new CRegion(3, "Pays de Loire", secteur1);
         //tableRegion.insererRegion(region1);
         
         
         //Modifier une region dans la table region
         // CRegion region2 = new CRegion(3, "Pays de la Loire", secteur1);
         //tableRegion.modifierRegion(region2);
         
         //lire une region dans la table region qu'on enregistre dans l'objet region2Lu
         //CRegion region2Lu = tableRegion.lireUneRegion(2);
         
          //la methode lireRegions renvoie une liste de regions qu'on enregistre dans l'objet listeRegions
          //ArrayList<CRegion> listeRegions = tableRegion.lireRegions();
          
          //on supprime une region
          //tableRegion.supprimerRegion(3);
         
           //TESTS DES METHODES CTableSecteur
         
         //Inserer un secteur dans la table secteur
       // CSecteur secteur3 = new CSecteur(2, "Sud");
       // tableSecteur.insererSecteur(secteur3);
         
         //Modifier un secteur dans la table secteur
        // CSecteur secteurAModif = new CSecteur(2,"Ouest");
        // tableSecteur.modifierSecteur(secteurAModif);
         
         //lire un secteur dans la table secteur qu'on enregistre dans l'objet secteur2Lu
         //CSecteur secteur2Lu = tableSecteur.lireUneSecteur(2);
         
          //la methode lireSecteurs renvoie une liste de secteurs qu'on enregistre dans l'objet listeSecteurs
          //ArrayList<CSecteur> listeSecteurs = tableSecteur.lireSecteurs();
          
          //on supprime un secteur
          //tableSecteur.supprimerSecteur(2);
         
          
         // test inserer , modifier , supprimer type de frais
         // CTypeFrais typeFrais1 = new CTypeFrais("hebergement", 40.8);
          //tableTypeFrais.insererTypeFrais(typeFrais1);
          //CTypeFrais typeFrais2 = new CTypeFrais( "repas", 20.5);
          //tableTypeFrais.insererTypeFrais(typeFrais2);
          
         //CTypeFrais typeFrais2 = new CTypeFrais(1, "hebergement", 45.8);
          //tableTypeFrais.modifierTypeFrais(typeFrais2);
          
          //tableTypeFrais.supprimerTypeFrais(new CTypeFrais(1));
          
          //test lire type frais
         // CTypeFrais typeFraisLu  = tableTypeFrais.lireUnTypeFrais(1);
          
        
        // test inserer , modifier , supprimer frais
           CTypeFrais typeFrais1 = new CTypeFrais(2,"hebergement", 40.8);
          CFrais frais1 = new CFrais(2,50.3,typeFrais1);
          //tableFrais.insererFrais(frais1);
        
        
        
        
        //test inserer , modifier, supprimer ficheFrais
          ArrayList<CFrais> listeFrais = new ArrayList(); //creation d'une liste de frais
          listeFrais.add(frais1);
         
          
        CFicheFrais ficheFrais1 = new CFicheFrais(2,0,0,"Saisie en cours", "1234", CUtilitaire.convertSQLDatetoGregCal("2019-05-27"), listeFrais);
        
        
        //tableFicheFrais.insererFicheFrais(ficheFrais1);
        CFicheFrais ficheFrais2 = new CFicheFrais(2,2,2,50,"Saisie en cours", "1234", CUtilitaire.convertSQLDatetoGregCal("2019-05-27"), listeFrais);
        //tableFicheFrais.modifierFicheFrais(ficheFrais2);
       
        tableFicheFrais.supprimerFicheFrais(ficheFrais2.getIdFicheFrais());
        
        
        System.out.println("hello");
        
        
    }

}
