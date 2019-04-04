/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppe;

import Entite.CDepartement;
import Entite.CRegion;
import Entite.CSecteur;
import Entite.CTravail;
import Entite.CVisiteur;
import Tables.CTableRegion;
import Tables.CTableTravail;
import Tables.CTableVisiteur;
import Utilitaires.CUtilitaire;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.util.ArrayList;

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
        
                  
        CVisiteur visiteur = new CVisiteur();        

        

       
 /*
        //Test methode insererVisiteur
       
        //on crée un nouvel utilisateur de type CVisiteur(String matricule,
        //String nom, String prenom, String adresse, String codePostal, 
        //String ville, GregorianCalendar dateEmbauche, CSecteur secteur,
        //ArrayList<CTravail> listeTravail, CDepartement departement)
       visiteur.setMatricule("1718");
       visiteur.setNom("TOM");
       visiteur.setPrenom("Olivier");
       visiteur.setAdresse("12 rue du paradis");
       visiteur.setCodePostal("35000");
       visiteur.setVille("Rennes");
       visiteur.setDateEmbauche(CUtilitaire.convertSQLDatetoGregCal("2019-01-01"));
       
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


 /*
        //test pour la méthode supprimerVisiteur
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
         
         
         
        System.out.println("hello");
        
        

        
        
    }

}
