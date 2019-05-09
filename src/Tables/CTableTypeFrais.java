/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import Entite.CDepartement;
import Entite.CSecteur;
import Entite.CTravail;
import Entite.CTypeFrais;
import Entite.CVisiteur;
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
public class CTableTypeFrais {
         protected CBDD bdd;
     protected CTableFicheFrais tableTravail;
     
     //pour chaque visiteur on a une liste de travail et donc on a besoin d'une reference de la table travail pour charger la liste CTableFicheFrais
    public CTableTypeFrais(CBDD bdd) {
        this.setBdd(bdd);
        
    }
    
    public CTableTypeFrais() {
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
    


    public int insererTypeFrais(CTypeFrais typeFrais) {
  
        
        String req = "INSERT INTO `type_frais` (`TF_CODE_TYPE_FRAIS`, `TF_LIBELLE_TYPE_FRAIS`, `TF_FORFAIT_TYPE_FRAIS`) "
                + "VALUES ('"
                + typeFrais.getCode() + "', '"
                + typeFrais.getLabel() + "', "
                + typeFrais.getForfait() + ");";
                
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

   
    
     public int modifierTypeFrais(CTypeFrais typeFrais) {
        
        String req = "UPDATE `type_frais` SET  `TF_LIBELLE_TYPE_FRAIS` = '"
                + typeFrais.getLabel()
                +"', `TF_FORFAIT_TYPE_FRAIS` = "
                + typeFrais.getForfait()
                + " WHERE `TF_CODE_TYPE_FRAIS` =  "
                + typeFrais.getCode() + ";";
            
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
    
   // On crée une méthode qui permet de convertir le résultat de la requête sql "rs" en un objet de type CTypeFrais
    CTypeFrais convertir_RS_TypeFrais(ResultSet rs) {
        try {
            int code =  rs.getInt("TF_CODE_TYPE_FRAIS");
            String label = rs.getString("TF_LIBELLE_TYPE_FRAIS");
            double forfait = rs.getDouble("TF_FORFAIT_TYPE_FRAIS");
        

            return new CTypeFrais(code, label, forfait);
        } catch (SQLException ex) {
            Logger.getLogger(CTypeFrais.class.getName()).log(Level.SEVERE, null, ex);
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
   
// permet de lire tous les types de frais
    public ArrayList<CTypeFrais> lireTypeFrais() {
        if (bdd.connecter() == true) {
            ArrayList<CTypeFrais> liste = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `type_frais`");
            try {
                while (rs.next()) {
                    CTypeFrais typeFrais = convertir_RS_TypeFrais(rs);
                    liste.add(typeFrais);
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
    
    
        public CTypeFrais lireUnTypeFrais(int code) {
        if (bdd.connecter() == true) {
        //Le typeFrais est initialisé à null au cas où le typeFrais n'a pas été trouvé (typeFrais = null)
        CTypeFrais typeFrais = null;
       // visiteur.setMatricule("-1");
        
       ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `type_frais` WHERE TF_CODE_TYPE_FRAIS ="+code+" ;"); 
            try {
                //lit chaque ligne qu'on a récuperé
                while (rs.next()) {
                    //il enregistre la ligne trouvé dans l'objet typeFrais
                    typeFrais = convertir_RS_TypeFrais(rs);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return typeFrais;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
  
        
    public int supprimerTypeFrais(CTypeFrais typeFrais) {
      
        String req = "DELETE FROM `type_frais` WHERE TF_CODE_TYPE_FRAIS ="+typeFrais.getCode()+";";
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
