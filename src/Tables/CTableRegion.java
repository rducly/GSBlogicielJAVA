/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import Entite.CDepartement;
import Entite.CSecteur;
import Entite.CTravail;
import Entite.CRegion;
import Utilitaires.CUtilitaire;
import bdd.CBDD;
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
public class CTableRegion {
        protected CBDD bdd;
     

    
     
    public CTableRegion(CBDD bdd) {
        this.setBdd(bdd); 
    }
    
    public CTableRegion() {
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
    


    public int insererRegion(CRegion region) {
       
        
        String req = "INSERT INTO `region` (`REG_CODE_REGION`,`REG_NOM_REGION`, `SEC_CODE_SECTEUR`) "
                + "VALUES ('"
                + region.getCode() + "', '"
                + region.getNom() + "', "
                + region.getSecteur().getCode() + ");";
               
                
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

   
    
     public int modifierRegion(CRegion region) {
        
        
        String req = "UPDATE `region` SET  `REG_NOM_REGION` = '"
                + region.getNom()
                +"', `SEC_CODE_SECTEUR` = "
                + region.getSecteur().getCode()
                + " WHERE `region`.`REG_CODE_REGION` =  "
                + region.getCode() + ";";
            
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
    
   // On crée une méthode qui permet de convertir le résultat de la requête sql "rs" en un objet de type CRegion
    CRegion convertir_RS_region(ResultSet rs) {
        try {
            int code=  rs.getInt("REG_CODE_REGION");
            String nom = rs.getString("REG_NOM_REGION");
            
            CSecteur secteur = new CSecteur(rs.getInt("SEC_CODE_SECTEUR"), rs.getString("SEC_LIBELLE_SECTEUR"));
           

            return new CRegion(code, nom, secteur);
        } catch (SQLException ex) {
            Logger.getLogger(CRegion.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<CRegion> lireRegions() {
        if (bdd.connecter() == true) {
            ArrayList<CRegion> liste = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `secteur` ,`region` WHERE region.SEC_CODE_SECTEUR = secteur.SEC_CODE_SECTEUR ; ");
            try {
                while (rs.next()) {
                    CRegion region = convertir_RS_region(rs);
                    liste.add(region);
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
    
    
        public CRegion lireUneRegion(int codeRegion) {
        if (bdd.connecter() == true) {
        //CRegion region = new CRegion();
        //Le region est initialisé à null au cas où le region n'a pas été trouvé (region = null)
        CRegion region = null;
       // region.setMatricule("-1");
        
       ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `secteur` ,`region` WHERE region.REG_CODE_REGION="+codeRegion+" AND region.SEC_CODE_SECTEUR = secteur.SEC_CODE_SECTEUR ;"); 
      
            try {
                //lit chaque ligne qu'on a récuperer
                while (rs.next()) {
                    //il enregistre la ligne trouvé dans l'objet region
                    region = convertir_RS_region(rs);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return region;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
  
        
    public int supprimerRegion(int codeRegion) {
      
        String req = "DELETE FROM region WHERE REG_CODE_REGION ="+codeRegion+";";
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
}
