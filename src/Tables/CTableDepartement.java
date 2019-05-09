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
public class CTableDepartement {
      protected CBDD bdd;

    
    public CTableDepartement(CBDD bdd) {
        this.setBdd(bdd);
    }
    
    public CTableDepartement() {
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
    
    

  
    public int insererDepartement(CDepartement departement) {
      
        String req = "INSERT INTO `departement` (`DEP_CODE_DEPARTEMENT`, `DEP_NOM_DEPARTEMENT`, `DEP_CHEFVENTE_DEPARTEMENT`) "
                + "VALUES ("
                + departement.getCode() + ", '"
                + departement.getNom() + "', "
                + departement.getChefVente() + ");";
                
             
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

    public int modifierDepartement(CDepartement departement) {
        
        String req = "UPDATE `departement` SET `DEP_NOM_DEPARTEMENT = '"
                + departement.getNom()
                +"', `DEP_CHEFVENTE_DEPARTEMENT` = '"
                + departement.getChefVente()
                + "' WHERE `DEP_CODE_DEPARTEMENT` = "
                + departement.getCode()
                + " ;";
                
            
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
    
    
    CDepartement convertir_RS_Departement(ResultSet rs) {
        try {
            int code = rs.getInt("DEP_CODE_DEPARETEMENT");
            String nom = rs.getString("DEP_NOM_DEPARTEMENT");
            boolean chefVente = rs.getBoolean("DEP_CHEFVENTE_DEPARTEMENT");
            

            return new CDepartement(code, nom, chefVente);
        } catch (SQLException ex) {
            Logger.getLogger(CDepartement.class.getName()).log(Level.SEVERE, null, ex);
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
    

    public ArrayList<CDepartement> lireDepartements() {
        if (bdd.connecter() == true) {
            ArrayList<CDepartement> liste = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `departement` ; ");
            try {
                while (rs.next()) {
                   CDepartement departement = convertir_RS_Departement(rs);
                    liste.add(departement);
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
   
        public CDepartement lireUnDepartement(int code) {
        if (bdd.connecter() == true) {
        
        //Le departement est initialisé à null au cas où le departement n'a pas été trouvé (departement = null)
        CDepartement departement = null;
        
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `departement` WHERE `departement`.`DEP_CODE_DEPARTEMENT`="+code+";");
            try {
                //lit chaque ligne qu'on a récuperé
                while (rs.next()) {
                    //il enregistre la ligne trouvé dans l'objet departement
                    departement = convertir_RS_Departement(rs);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return departement;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
       
        //Méthode qui permet de supprimer un département
    public int supprimerDepartement(int code) {
      
        String req = "DELETE FROM `departement` WHERE `departement`.`DEP_CODE_DEPARTEMENT`="+code+";";
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
