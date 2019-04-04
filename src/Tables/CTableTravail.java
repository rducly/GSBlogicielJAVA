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
public class CTableTravail {
     protected CBDD bdd;

    
    public CTableTravail(CBDD bdd) {
        this.setBdd(bdd);
    }
    
    public CTableTravail() {
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
    
    

  
    public int insererTravail(CTravail travail, String matriculeVisiteur) {
       Date date = new Date(travail.getDate().getTimeInMillis());
        String req = "INSERT INTO `travailler` (`REG_CODE_REGION`, `JJMMAA_DATE`, `VIS_MATRICULE_VISITEUR`, `TRA_ROLE_TRAVAILLER`) "
                + "VALUES ('"
                + travail.getRegion().getCode() + "', '"
                + date + "', '"
                + matriculeVisiteur + "', '"
                + travail.getRole() + "');";
                
             
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

    public int modifierTravail(CTravail travail, String matriculeVisiteur) {
        Date date = new Date(travail.getDate().getTimeInMillis());
        
   
        
        String req = "UPDATE `travailler` SET `REG_CODE_REGION` = '"
                + travail.getRegion().getCode()
                +"', `JJMMAA_DATE` = '"
                + date
                +"', `TRA_ROLE_TRAVAILLER` = '"
                + travail.getRole()
                + "' WHERE `travailler`.`VIS_MATRICULE_VISITEUR` =  '"
                + matriculeVisiteur
                + "' AND `travailler`.`REG_CODE_REGION` = '"
                + travail.getRegion().getCode()
                + "' AND `travailler`.`JJMMAA_DATE` = '"
                + date
                + "' ;";
                
            
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
    
    
    CTravail convertir_RS_Travail(ResultSet rs) {
        try {
            int codeRegion = rs.getInt("REG_CODE_REGION");
            String matriculeVisiteur = rs.getString("VIS_MATRICULE_VISITEUR");
            String roleTravailler = rs.getString("TRA_ROLE_TRAVAILLER");
            String nomRegion = rs.getString("REG_NOM_REGION");
            int codeSecteur = rs.getInt("SEC_CODE_SECTEUR");
            String nomSecteur = rs.getString("SEC_LIBELLE_SECTEUR");
            String date = rs.getString("JJMMAA_DATE");

            GregorianCalendar dateGC = CUtilitaire.convertSQLDatetoGregCal(date);
            

            return new CTravail(roleTravailler, dateGC, new CRegion(codeRegion, nomRegion, new CSecteur(codeSecteur,nomSecteur)));
        } catch (SQLException ex) {
            Logger.getLogger(CTravail.class.getName()).log(Level.SEVERE, null, ex);
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
    
//methode qui permet d'avoir la liste de tous les travails d'un visiteur (matricule)
    public ArrayList<CTravail> fetchTravails(String matricule) {
        if (bdd.connecter() == true) {
            ArrayList<CTravail> liste = new ArrayList();
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `travailler`, `region`, `secteur` WHERE travailler.VIS_MATRICULE_VISITEUR="+matricule+" AND travailler.REG_CODE_REGION=region.REG_CODE_REGION AND secteur.SEC_CODE_SECTEUR=region.SEC_CODE_SECTEUR ;");
            try {
                while (rs.next()) {
                    CTravail travail = convertir_RS_Travail(rs);
                    liste.add(travail);
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

   
        public CTravail lireUnTravail(int codeRegion, String date, String matriculeVisiteur) {
        if (bdd.connecter() == true) {
        
        //Le travail est initialisé à null au cas où le travail n'a pas été trouvé (travail = null)
        CTravail travail = null;
        
            ResultSet rs = bdd.executerRequeteQuery("SELECT * FROM `travailler`, `region`, `secteur` WHERE `travailler`.`REG_CODE_REGION`="+codeRegion+" AND `travailler`.`JJMMAA_DATE`='"+date+"' AND `travailler`.`VIS_MATRICULE_VISITEUR`='"+matriculeVisiteur+"' AND travailler.REG_CODE_REGION=region.REG_CODE_REGION AND secteur.SEC_CODE_SECTEUR=region.SEC_CODE_SECTEUR ;");
            try {
                //lit chaque ligne u'on a récuperer
                while (rs.next()) {
                    //il enregistre la ligne trouvé dans l'objet personne
                    travail = convertir_RS_Travail(rs);
                }
            } catch (SQLException ex) {
            }
            bdd.deconnecter();
            return travail;
        } else {
            System.out.println("Connexion KO");
        }
        return null;
    }
       
    public int supprimerTravail(int codeRegion, String date, String matriculeVisiteur) {
      
        String req = "DELETE FROM `travailler` WHERE `REG_CODE_REGION`="+codeRegion+" AND `JJMMAA_DATE`='"+date+"' AND `VIS_MATRICULE_VISITEUR`='"+matriculeVisiteur+"' ;";
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
        CTableTravail table = new CTableTravail();
        /*
    CParametresStockageBDD paramStock = new CParametresStockageBDD("parametresBdd.properties");
    CBDD bdd = new CBDD(paramStock); 
    table.setBdd(bdd);
         */
        table.setBdd(new CBDD(new CParametresStockageBDD("parametresBdd.properties")));
        //table.supprimerTable();
        //table.creerTable();
       // int n = 0;
        CTravail pepere = new CTravail();
       // CAdresse adresse = new CAdresse();
        //pour initialiser adresse
        pepere.setDate(CUtilitaire.convertSQLDatetoGregCal("2019-03-20"));
        CSecteur secteur1 = new CSecteur(1,"Nord");
         CRegion region1 = new CRegion(1,"bretagne", secteur1);
        pepere.setRegion(region1);
        pepere.setRole("visiteur");
        
        //creation d'un objet de type CVisiteur qui est associé à ce travail
       // CVisiteur visiteur = new CVisiteur();
        //visiteur.setMatricule("1234");

        
        //insertion de l'objet pepere dans ma table travail
      // table.insererTravail(pepere,visiteur);
ArrayList<CTravail> listTravail = table.fetchTravails("1234");
        System.out.println("liste");

}
}    
    
