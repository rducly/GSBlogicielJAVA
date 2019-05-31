/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import Entite.CFrais;
import Entite.CSecteur;
import Entite.CTravail;
import Entite.CTypeFrais;
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
public class CTableFrais {
     protected CBDD bdd;
     

    
     
    public CTableFrais(CBDD bdd) {
        this.setBdd(bdd); 
    }
    
    public CTableFrais() {
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
    


    public int insererFrais(CFrais frais) {
       
         String req = "INSERT INTO `inclure` (`TF_CODE_TYPE_FRAIS`, `INC_QTE_INCLURE`, `INC_MONTANT_INCLURE`) "
       // String req = "INSERT INTO `inclure` (`FF_ID_FICHE_FRAIS`,`TF_CODE_TYPE_FRAIS`, `INC_QTE_INCLURE`, `INC_MONTANT_INCLURE`) "
                + "VALUES ("
                //+ frais.getIdFicheFrais() + ", "
                + frais.getTypeFrais().getCode() + ", "
                + frais.getQuantite() + ", "
                + frais.getMontant() + ");";
               
                
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

   
    
     public int modifierFrais(CFrais frais) {
        
        
        String req = "UPDATE `inclure` SET  `INC_QTE_INCLURE` = '"
                + frais.getQuantite()
                +"', `INC_MONTANT_INCLURE` = "
                + frais.getMontant()
                + " WHERE `inclure`.`FF_ID_FICHE_FRAIS` =  "
                + frais.getIdFicheFrais() + " AND `inclure`.`TF_CODE_TYPE_FRAIS` = "
                + frais.getTypeFrais().getCode()
                +";";
            
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
    
   // On crée une méthode qui permet de convertir le résultat de la requête sql "rs" en un objet de type CFrais
    CFrais convertir_RS_Frais(ResultSet rs) {
        try {
            int idFicheFrais = rs.getInt("FF_ID_FICHE_FRAIS");
            int quantite=  rs.getInt("INC_QTE_INCLURE");
            double montant = rs.getDouble("INC_MONTANT_INCLURE");
            
            CTypeFrais typeFrais = new CTypeFrais(rs.getInt("TF_CODE_TYPE_FRAIS"), rs.getString("TF_LIBELLE_TYPE_FRAIS"), rs.getFloat("TF_FORFAIT_TYPE_FRAIS"));
           
            return new CFrais(idFicheFrais, quantite, montant, typeFrais);
        } catch (SQLException ex) {
            Logger.getLogger(CFrais.class.getName()).log(Level.SEVERE, null, ex);
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
   
   
   //methode qui permet d'avoir la liste de tous les frais d'un mois donné et un visiteur donné (idFicheFrais)
    public ArrayList<CFrais> fetchFrais(int idFicheFrais) {
        if (bdd.connecter() == true) {
            ArrayList<CFrais> liste = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `inclure`, `type_frais` WHERE `inclure`.`FF_ID_FICHE_FRAIS`= "+idFicheFrais+" AND `inclure`.`TF_CODE_TYPE_FRAIS`=`type_frais`.`TF_CODE_TYPE_FRAIS` ;");
            try {
                while (rs.next()) {
                    CFrais frais = convertir_RS_Frais(rs);
                    liste.add(frais);
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

 /*   public ArrayList<CFrais> lireFrais() {
        if (bdd.connecter() == true) {
            ArrayList<CFrais> liste = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `secteur` ,`Frais` WHERE Frais.SEC_CODE_SECTEUR = secteur.SEC_CODE_SECTEUR ; ");
            try {
                while (rs.next()) {
                    CFrais Frais = convertir_RS_Frais(rs);
                    liste.add(Frais);
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
    */
    
        public CFrais lireUnFrais(int idFicheFrais, int codeTypeFrais) {
        if (bdd.connecter() == true) {
        
        //Le Frais est initialisé à null au cas où le Frais n'a pas été trouvé (Frais = null)
        CFrais frais = null;
       
        
       ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `inclure`, `type_frais` WHERE `inclure`.`FF_ID_FICHE_FRAIS`= "+idFicheFrais+" AND `inclure`.`TF_CODE_TYPE_FRAIS`="+codeTypeFrais+" AND `inclure`.`TF_CODE_TYPE_FRAIS`=`type_frais`.`TF_CODE_TYPE_FRAIS` ;"); 
      
            try {
                //lit chaque ligne qu'on a récuperer
                while (rs.next()) {
                    //il enregistre la ligne trouvé dans l'objet Frais
                    frais = convertir_RS_Frais(rs);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return frais;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
  
        
    public int supprimerFrais(int idFicheFrais, int codeTypeFrais) {
      
        String req = "DELETE FROM inclure WHERE `FF_ID_FICHE_FRAIS`= "+idFicheFrais+" AND `TF_CODE_TYPE_FRAIS`="+codeTypeFrais+";";
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
