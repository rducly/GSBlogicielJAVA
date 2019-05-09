/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import Entite.CSecteur;
import Entite.CSecteur;
import bdd.CBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class CTableSecteur {
       protected CBDD bdd;
     

    
     
    public CTableSecteur(CBDD bdd) {
        this.setBdd(bdd); 
    }
    
    public CTableSecteur() {
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
    


    public int insererSecteur(CSecteur Secteur) {
       
        
        String req = "INSERT INTO `Secteur` (`SEC_CODE_SECTEUR`, `SEC_LIBELLE_SECTEUR` ) "
                + "VALUES ("
                + Secteur.getCode() + ", '"
                + Secteur.getLabel() + "');";
                
                
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

   
    
     public int modifierSecteur(CSecteur secteur) {
        
        
        String req = "UPDATE `Secteur` SET   `SEC_LIBELLE_SECTEUR` = '"
                + secteur.getLabel()
                + "' WHERE `SEC_CODE_SECTEUR` =  "
                + secteur.getCode() + ";";
            
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
    
   // On crée une méthode qui permet de convertir le résultat de la requête sql "rs" en un objet de type CSecteur
    CSecteur convertir_RS_secteur(ResultSet rs) {
        try {
            int code=  rs.getInt("SEC_CODE_SECTEUR");
            String label = rs.getString("SEC_LIBELLE_SECTEUR");
            
            return new CSecteur(code, label);
            
        } catch (SQLException ex) {
            Logger.getLogger(CSecteur.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<CSecteur> lireSecteurs() {
        if (bdd.connecter() == true) {
            ArrayList<CSecteur> liste = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `secteur` ; ");
            try {
                while (rs.next()) {
                    CSecteur secteur = convertir_RS_secteur(rs);
                    liste.add(secteur);
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
    
    
        public CSecteur lireUnSecteur(int codeSecteur) {
        if (bdd.connecter() == true) {
        //CSecteur secteur = new CSecteur();
        //Le secteur est initialisé à null au cas où le secteur n'a pas été trouvé (secteur = null)
        CSecteur secteur = null;
       // Secteur.setMatricule("-1");
        
       ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `secteur` WHERE SEC_CODE_SECTEUR ="+codeSecteur+" ;"); 
      
            try {
                //lit chaque ligne qu'on a récuperer
                while (rs.next()) {
                    //il enregistre la ligne trouvé dans l'objet Secteur
                    secteur = convertir_RS_secteur(rs);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return secteur;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
  
        
    public int supprimerSecteur(int codeSecteur) {
      
        String req = "DELETE FROM `secteur` WHERE SEC_CODE_SECTEUR ="+ codeSecteur +";";
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
