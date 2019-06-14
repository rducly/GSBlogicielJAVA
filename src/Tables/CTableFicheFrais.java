/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tables;

import Entite.CDepartement;
import Entite.CSecteur;
import Entite.CTravail;
import Entite.CFicheFrais;
import Entite.CFrais;
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
public class CTableFicheFrais {
     protected CBDD bdd;
     protected CTableFrais tableFrais;
     
     //pour chaque FicheFrais on a une liste de Frais et donc on a besoin d'une reference de la table frais pour charger la liste CTableFicheFrais
    public CTableFicheFrais(CBDD bdd, CTableFrais tableFrais) {
        this.setBdd(bdd);
        this.tableFrais = tableFrais;
        
    }
    
    public CTableFicheFrais() {
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
    


    public int insererFicheFrais(CFicheFrais FicheFrais) {
        
        Date dateDerniereModif = new Date(FicheFrais.getDateDerniereModif().getTimeInMillis());
        
        
        String req = "INSERT INTO `fiche_frais` (`FF_MOIS_FICHE_FRAIS`,`FF_NBHorsClassif_FICHE_FRAIS`, `FF_MontantHorsClassif_FICHE_FRAIS`, `VIS_MATRICULE_VISITEUR`,`FF_ETAPE_FICHE_FRAIS`, `FF_DateDerniereModif_Fiche_Frais`) "
                + "VALUES ('"
                + FicheFrais.getMois() + "', '"
                + FicheFrais.getNbHorsClassif() + "', '"
                + FicheFrais.getMontantHorsClassif() + "', '"
                + FicheFrais.getMatriculeVisiteur() + "', '"
                + FicheFrais.getEtape() + "', '"
                + dateDerniereModif  + "');";
                
        int res = -1;
        if (bdd.connecter() == true) {
            res = bdd.executerRequeteUpdate(req);
            System.out.println("Res = " + res);
            bdd.deconnecter();
        } else {
            System.out.println("Connexion KO");
        }
        
        //lecture id 
       int idFF =idMaxFichesFrais();
       
        
        
        for (int i = 0; i < FicheFrais.getListeFrais().size(); i++) {
            FicheFrais.getListeFrais().get(i).setIdFicheFrais(idFF);
            this.tableFrais.insererFrais(FicheFrais.getListeFrais().get(i));
        }
        
        return res;
    }

   
    
     public int modifierFicheFrais(CFicheFrais FicheFrais) {
         Date dateDerniereModif = new Date(FicheFrais.getDateDerniereModif().getTimeInMillis());
        
        String req = "UPDATE `fiche_frais` SET `FF_MOIS_FICHE_FRAIS`= '"
                + FicheFrais.getMois()
                +"', `FF_NBHorsClassif_FICHE_FRAIS` = '"
                + FicheFrais.getNbHorsClassif()
                +"', `FF_MontantHorsClassif_FICHE_FRAIS` = '"
                + FicheFrais.getMontantHorsClassif()
                +"', `VIS_MATRICULE_VISITEUR` = '"
                + FicheFrais.getMatriculeVisiteur()
                +"', `FF_ETAPE_FICHE_FRAIS` = '"
                + FicheFrais.getEtape()
                +"', `FF_DateDerniereModif_Fiche_Frais` = '"
                + dateDerniereModif
                + "' WHERE `fiche_frais`.`FF_ID_FICHE_FRAIS` =  "
                + FicheFrais.getIdFicheFrais() + ";";
            
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
    
   // On crée une méthode qui permet de convertir le résultat de la requête sql "rs" en un objet de type CFicheFrais
    CFicheFrais convertir_RS_FicheFrais(ResultSet rs) {
      
        try {
            int idFicheFrais =  rs.getInt("FF_ID_FICHE_FRAIS");
            int mois  = rs.getInt("FF_MOIS_FICHE_FRAIS");
            int nbHorsClassif = rs.getInt("FF_NBHorsClassif_FICHE_FRAIS");
            double montantHorsClassif = rs.getFloat("FF_MontantHorsClassif_FICHE_FRAIS");
            String matriculeVisiteur = rs.getString("VIS_MATRICULE_VISITEUR");
            String etape = rs.getString("FF_ETAPE_FICHE_FRAIS");
            String dateDerniereModif = rs.getString("FF_DateDerniereModif_Fiche_Frais");
            GregorianCalendar dateDerniereModifGC = CUtilitaire.convertSQLDatetoGregCal(dateDerniereModif);
        
            //on recupere la liste de frais concernant une FicheFrais
            ArrayList<CFrais> listeFrais = tableFrais.fetchFrais(idFicheFrais);
       
            return new CFicheFrais(idFicheFrais, mois, nbHorsClassif, montantHorsClassif, etape, matriculeVisiteur, dateDerniereModifGC, listeFrais);
        } catch (SQLException ex) {
            Logger.getLogger(CFicheFrais.class.getName()).log(Level.SEVERE, null, ex);
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

   
   //methode qui permet d'avoir la liste de toutes les fiches de frais d'un visiteur (matricule)
    public ArrayList<CFicheFrais> fetchFichesFrais(String matricule) {
        if (bdd.connecter() == true) {
            ArrayList<CFicheFrais> liste = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `fiche_frais`  WHERE fiche_frais.VIS_MATRICULE_VISITEUR="+ matricule +" ;");
            //ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `fiche_frais` ,`inclure`, `type_frais` WHERE fiche_frais.VIS_MATRICULE_VISITEUR="+ matricule +" AND fiche_frais.FF_ID_FICHE_FRAIS=inclure.FF_ID_FICHE_FRAIS AND inclure.TF_CODE_TYPE_FRAIS=type_frais.TF_CODE_TYPE_FRAIS ;");
            try {
                while (rs.next()) {
                   CFicheFrais FicheFrais = convertir_RS_FicheFrais(rs);
                    liste.add(FicheFrais);
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
    
  
      //methode qui permet d'avoir le dernier idFicheFrais enregistré
    public int idMaxFichesFrais() {
        if (bdd.connecter() == true) {
            int idMaxFF=0;
            ResultSet rs1 =  bdd.executerRequeteQuery("SELECT MAX(FF_ID_FICHE_FRAIS) AS maxID FROM `fiche_frais` ;");
            try {
                while (rs1.next()) {
                    idMaxFF = rs1.getInt("maxID");
                
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
           
            
            return idMaxFF;
        } else {
            System.out.println("Connexion KO");
        }
        return 0;
    }
    
    
    
      //methode qui permet d'avoir la liste de toutes les fiches de frais d'un visiteur (matricule)
    public ArrayList<CFicheFrais> fetchTravailsMois(String matricule, int mois) {
        if (bdd.connecter() == true) {
            ArrayList<CFicheFrais> liste = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `fiche_frais` ,`inclure`, `type_frais` WHERE fiche_frais.FF_MOIS_FICHE_FRAIS="+mois+" AND fiche_frais.VIS_MATRICULE_VISITEUR="+matricule+" AND fiche_frais.FF_ID_FICHE_FRAIS=inclure.FF_ID_FICHE_FRAIS AND inclure.TF_CODE_TYPE_FRAIS=type_frais.TF_CODE_TYPE_FRAIS ;");
            try {
                while (rs.next()) {
                   CFicheFrais FicheFrais = convertir_RS_FicheFrais(rs);
                    liste.add(FicheFrais);
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
    
  /* 
    public ArrayList<CFicheFrais> lireLesFicheFrais() {
        if (bdd.connecter() == true) {
            ArrayList<CFicheFrais> liste = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT FicheFrais.*, departement.*, secteur.* FROM `departement` ,`FicheFrais` LEFT JOIN `secteur` ON FicheFrais.SEC_CODE_SECTEUR = secteur.SEC_CODE_SECTEUR WHERE FicheFrais.DEP_CODE_DEPARTEMENT = departement.DEP_CODE_DEPARTEMENT ");
            try {
                while (rs.next()) {
                    CFicheFrais FicheFrais = convertir_RS_FicheFrais(rs);
                    liste.add(FicheFrais);
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
    
        public CFicheFrais lireUneFicheFrais(int idFicheFrais) {
        if (bdd.connecter() == true) {
        CFicheFrais FicheFrais = new CFicheFrais();
        //Le FicheFrais est initialisé à null au cas où le FicheFrais n'a pas été trouvé (FicheFrais = null)
        //CFicheFrais FicheFrais = null;
        FicheFrais.setIdFicheFrais(-1);
        
       ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `fiche_frais` ,`inclure`, `type_frais`  WHERE `fiche_frais`.`FF_ID_FICHE_FRAIS`=" + idFicheFrais + " AND `fiche_frais`.`FF_ID_FICHE_FRAIS` = `inclure`.`FF_ID_FICHE_FRAIS` AND `inclure`.`TF_CODE_TYPE_FRAIS`= `type_frais`.`TF_CODE_TYPE_FRAIS`;"); 
       
            try {
                //lit chaque ligne qu'on a récuperer
                while (rs.next()) {
                    //il enregistre la ligne trouvé dans l'objet FicheFrais
                    FicheFrais = convertir_RS_FicheFrais(rs);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return FicheFrais;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
        
        
        // methode qui permet de lire une fiche frais d'un visiteur (matricule) pour un mois donné
        // dans la BDD il est sauvegardé que les fiches des 12 derniers mois
                public CFicheFrais lireUneFicheFraisMois(String matricule, int mois) {
        if (bdd.connecter() == true) {
        CFicheFrais FicheFrais = new CFicheFrais();
        //Le FicheFrais est initialisé à null au cas où le FicheFrais n'a pas été trouvé (FicheFrais = null)
        //CFicheFrais FicheFrais = null;
        FicheFrais.setIdFicheFrais(-1);
        
       ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `fiche_frais` ,`inclure`, `type_frais` WHERE `fiche_frais`.FF_MOIS_FICHE_FRAIS="+mois+" AND `fiche_frais`.VIS_MATRICULE_VISITEUR="+matricule+" AND `fiche_frais`.`FF_ID_FICHE_FRAIS` = `inclure`.`FF_ID_FICHE_FRAIS` AND `inclure`.`TF_CODE_TYPE_FRAIS`= `type_frais`.`TF_CODE_TYPE_FRAIS` ;"); 
       
            try {
                //lit chaque ligne u'on a récuperer
                while (rs.next()) {
                    //il enregistre la ligne trouvé dans l'objet FicheFrais
                    FicheFrais = convertir_RS_FicheFrais(rs);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return FicheFrais;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
  
        
    public int supprimerFicheFrais(int idFicheFrais) {
      
        String req = "DELETE FROM `fiche_frais` WHERE `FF_ID_FICHE_FRAIS` ="+idFicheFrais+";";
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
