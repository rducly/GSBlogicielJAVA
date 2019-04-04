/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import Entite.CDepartement;
import Entite.CRegion;
import Entite.CSecteur;
import Entite.CTravail;
import Entite.CVisiteur;
import Utilitaires.CUtilitaire;
import bdd.CBDD;
import bdd.CParametresStockageBDD;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class CTableVisiteur {
        
     protected CBDD bdd;
     protected CTableTravail tableTravail;

    //il n'y aura pas d heritage de ce constructeur CTablePersonne donc methode final
     //pour chaque visiteur on a une liste de travail et donc on a besoin d'une reference de la table travail pour charger la liste cTabletravail
    public CTableVisiteur(CBDD bdd, CTableTravail tableTravail) {
        this.setBdd(bdd);
        this.tableTravail = tableTravail;
        
    }
    
    public CTableVisiteur() {
    }
    

    /**
     * @return the bdd
     */
    public CBDD getBdd() {
        return bdd;
    }

    /**
     * @param bdd the bdd to set
     */
    public final void setBdd(CBDD bdd) {
        this.bdd = bdd;
    }
    


    public int insererVisiteur(CVisiteur visiteur) {
        Date dateEmbauche = new Date(visiteur.getDateEmbauche().getTimeInMillis());
        
        //On initialise le secteur à null si le visiteur n'est pas responsable d'un secteur
        String secteur = "NULL";
        
        //on change le type de visiteur.getSecteur().getCode() en string au lieu de int car secteur est un string
        if (visiteur.getSecteur()!= null) { secteur = String.valueOf(visiteur.getSecteur().getCode());}
        
        String req = "INSERT INTO `visiteur` (`VIS_MATRICULE_VISITEUR`,`VIS_NOM_VISITEUR`, `VIS_ADRESSE_VISITEUR`, `VIS_CP_VISITEUR`,`VIS_VILLE_VISITEUR`, `VIS_DATEEMBAUCHE_VISITEUR`,`VIS_PRENOM_VISITEUR`, `SEC_CODE_SECTEUR`,`DEP_CODE_DEPARTEMENT`) "
                + "VALUES ('"
                + visiteur.getMatricule() + "', '"
                + visiteur.getNom() + "', '"
                + visiteur.getAdresse() + "', '"
                + visiteur.getCodePostal() + "', '"
                + visiteur.getVille() + "', '"
                + dateEmbauche  + "', '"
                + visiteur.getPrenom() + "', "
                + secteur + ", "
                + visiteur.getDepartement().getCode() + ");";
                
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }

   
    
     public int modifierVisiteur(CVisiteur visiteur) {
        Date dateEmbauche = new Date(visiteur.getDateEmbauche().getTimeInMillis());
        
         String secteur = "NULL";
        //on change le type de visiteur.getSecteur().getCode() en string au lieu de int car secteur est un string
        if (visiteur.getSecteur()!= null) { secteur = String.valueOf(visiteur.getSecteur().getCode());}
        
        String req = "UPDATE `visiteur` SET `VIS_NOM_VISITEUR` = '"
                + visiteur.getNom()
                +"', `VIS_ADRESSE_VISITEUR` = '"
                + visiteur.getAdresse()
                +"', `VIS_CP_VISITEUR` = '"
                + visiteur.getCodePostal()
                +"', `VIS_VILLE_VISITEUR` = '"
                + visiteur.getVille()
                +"', `VIS_DATEEMBAUCHE_VISITEUR` = '"
                +dateEmbauche
                +"', `VIS_PRENOM_VISITEUR` = '"
                + visiteur.getPrenom()
                +"', `SEC_CODE_SECTEUR` = "
                + secteur
                +", `DEP_CODE_DEPARTEMENT` = "
                + visiteur.getDepartement().getCode()
                + " WHERE `visiteur`.`VIS_MATRICULE_VISITEUR` =  "
                + visiteur.getMatricule() + ";";
            
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    }
    
   // On crée une méthode qui permet de convertir le résultat de la requête sql "rs" en un objet de type CVisiteur
    CVisiteur convertir_RS_Visiteur(ResultSet rs) {
        try {
            String matricule =  rs.getString("VIS_MATRICULE_VISITEUR");
            String nom = rs.getString("VIS_NOM_VISITEUR");
            String prenom = rs.getString("VIS_PRENOM_VISITEUR");
            String adresse = rs.getString("VIS_ADRESSE_VISITEUR");
            String codePostal = rs.getString("VIS_CP_VISITEUR");
            String ville = rs.getString("VIS_VILLE_VISITEUR");
            String dateEmbauche = rs.getString("VIS_DATEEMBAUCHE_VISITEUR");
            GregorianCalendar dateEmbaucheGC = CUtilitaire.convertSQLDatetoGregCal(dateEmbauche);
            
            CDepartement departement = new CDepartement(rs.getInt("DEP_CODE_DEPARTEMENT"),rs.getString("DEP_NOM_DEPARTEMENT"),rs.getBoolean("DEP_CHEFVENTE_DEPARTEMENT"));
            
            //on recupere la liste de travails concernant un visiteur
            ArrayList<CTravail> listeTravails = tableTravail.fetchTravails(matricule);
            
            
           
            
            //on test si secteur est nul (pas chef de secteur)
            int secteurCode = rs.getInt("SEC_CODE_SECTEUR");
            CSecteur secteur;
            if(rs.wasNull()){
                secteur = null;
                
            }
            else {
                secteur = new CSecteur(secteurCode, rs.getString("SEC_LIBELLE_SECTEUR"));
            }
            
           // CSecteur secteur = rs.getCSecteur("secteur");


            return new CVisiteur(matricule, nom, prenom, adresse, codePostal, ville, dateEmbaucheGC, secteur, listeTravails , departement);
        } catch (SQLException ex) {
            Logger.getLogger(CVisiteur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

   public Object query() {
        if (bdd.connecter() == true) {
            Object objet = new Object();
            ResultSet rs = bdd.executerRequeteQuery("");
            try {
                while (rs.next()) {
                    //lecture et creation objet par objet de l'entite correspondante
                    //ajout eventuel a une liste
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return objet;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }

    public ArrayList<CVisiteur> lireVisiteurs() {
        if (bdd.connecter() == true) {
            ArrayList<CVisiteur> liste = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT visiteur.*, departement.*, secteur.* FROM `departement` ,`visiteur` LEFT JOIN `secteur` ON visiteur.SEC_CODE_SECTEUR = secteur.SEC_CODE_SECTEUR WHERE visiteur.DEP_CODE_DEPARTEMENT = departement.DEP_CODE_DEPARTEMENT ");
            try {
                while (rs.next()) {
                    CVisiteur visiteur = convertir_RS_Visiteur(rs);
                    liste.add(visiteur);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            
            return liste;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
    
    
        public CVisiteur lireUnVisiteur(String matricule) {
        if (bdd.connecter() == true) {
        //CVisiteur visiteur = new CVisiteur();
        //Le visiteur est initialisé à null au cas où le visiteur n'a pas été trouvé (visiteur = null)
        CVisiteur visiteur = null;
       // visiteur.setMatricule("-1");
        
       ResultSet rs = bdd.executerRequeteQuery("SELECT visiteur.*, departement.*, secteur.* FROM `departement` ,`visiteur` LEFT JOIN `secteur` ON visiteur.SEC_CODE_SECTEUR = secteur.SEC_CODE_SECTEUR WHERE `VIS_MATRICULE_VISITEUR`=" + matricule + " AND visiteur.DEP_CODE_DEPARTEMENT = departement.DEP_CODE_DEPARTEMENT "); 
       //ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `visiteur`,`secteur`, `departement` WHERE `VIS_MATRICULE_VISITEUR`="+matricule+" AND visiteur.SEC_CODE_SECTEUR = secteur.SEC_CODE_SECTEUR AND visiteur.DEP_CODE_DEPARTEMENT = departement.DEP_CODE_DEPARTEMENT ;");
            try {
                //lit chaque ligne u'on a récuperer
                while (rs.next()) {
                    //il enregistre la ligne trouvé dans l'objet visiteur
                    visiteur = convertir_RS_Visiteur(rs);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return visiteur;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
  
        
    public int supprimerVisiteur(CVisiteur visiteur) {
      
        String req = "DELETE FROM visiteur WHERE VIS_MATRICULE_VISITEUR ="+visiteur.getMatricule()+";";
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        return res;
    
    }
   
    public static void main(String[] args) {
        //on crée une instance de la classe CTableVisiteur, l'objet:  
        CTableVisiteur table = new CTableVisiteur();
        /*
    CParametresStockageBDD paramStock = new CParametresStockageBDD("parametresBdd.properties");
    CBDD bdd = new CBDD(paramStock); 
    table.setBdd(bdd);
         */
        table.setBdd(new CBDD(new CParametresStockageBDD("parametresBdd.properties")));
        //table.supprimerTable();
        //table.creerTable();
       // int n = 0;
        CVisiteur pepere = new CVisiteur();
       // CAdresse adresse = new CAdresse();
        //pour initialiser adresse
        pepere.setAdresse("21 rue des LiLas");
        pepere.setCodePostal("35000");
        pepere.setDateEmbauche(CUtilitaire.convertSQLDatetoGregCal("1986-06-23"));
        pepere.setDepartement(new CDepartement(1, "Swiss", true));
        
        //creation liste région et liste travail
        //ArrayList<CRegion> region = new ArrayList();
        CSecteur secteur1 = new CSecteur(1,"Nord");
       // region.add(new CRegion(1,"bretagne", secteur1));
       // region.add(new CRegion(2,"normandie", secteur1));
        //ajout d'une region
        CRegion region1 = new CRegion(1,"bretagne", secteur1);
        ArrayList<CTravail> travail = new ArrayList();
        //on ajoute à la liste de travail un objet (travail) de type CTravail 
        travail.add(new CTravail("Responsable", CUtilitaire.convertSQLDatetoGregCal("2019-03-11"),region1));
        
       
        
        pepere.setListeTravail(travail);
        pepere.setMatricule("1234");
        pepere.setNom("SERRES");
        pepere.setPrenom("Damien");
        pepere.setSecteur(null);
        pepere.setVille("Rennes");
        
        //insertion de l'objet pepere dans ma table visiteur
       // table.insererVisiteur(pepere);
        
        
        
        //test pour la methode modifierVisiteur
       // pepere.setDateEmbauche(CUtilitaire.convertSQLDatetoGregCal("2018-09-01"));
       // table.modifierVisiteur(pepere);
        
       //CVisiteur visteur1 = table.lireUnVisiteur("1234");
       
      ArrayList<CVisiteur> visiteur1 = table.lireVisiteurs();
        System.out.println("hello");
        /*adresse.setIdAdresse("285");
        pepere.setIdAdresse("1");
        pepere.setNom("DAMEDE");
        pepere.setPrenom("Piques");
        pepere.setMail("mail@mail.design");
        pepere.setTelephone("06.06.06.06.06");
        pepere.setDateNaissance(CUtilitaire.convertSQLDatetoGregCal("1986-06-23"));
*/
        //java.sql.Date date = new java.sql.Date(CUtilitaire.convertSQLDatetoGregCal("1986-06-23").getTimeInMillis());
        //System.out.println(date);
        //table.insererPersonne(pepere);
        
        //table.lirePersonnes().afficherPersonne();
        //table.lireUnePersonne("4").afficher();
        
        //table.supprimerPersonne(new CPersonne("5"));
        //table.lirePersonnes().afficherPersonne();
    }

}
