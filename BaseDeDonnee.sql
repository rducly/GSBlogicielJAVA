-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 09 mai 2019 à 09:13
-- Version du serveur :  5.7.21
-- Version de PHP :  5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `ppegsb`
--
CREATE DATABASE IF NOT EXISTS `ppegsb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `ppegsb`;

-- --------------------------------------------------------

--
-- Structure de la table `activite_compl`
--

DROP TABLE IF EXISTS `activite_compl`;
CREATE TABLE IF NOT EXISTS `activite_compl` (
  `AC_NUM_ACTIVITE_COMPL` smallint(6) NOT NULL,
  `AC_DATE_ACTIVITE_COMPL` datetime DEFAULT NULL,
  `AC_LIEU_ACTIVITE_COMPL` varchar(50) DEFAULT NULL,
  `AC_THEME_ACTIVITE_COMPL` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`AC_NUM_ACTIVITE_COMPL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `composant`
--

DROP TABLE IF EXISTS `composant`;
CREATE TABLE IF NOT EXISTS `composant` (
  `CMP_CODE_COMPOSANT` smallint(6) NOT NULL,
  `CMP_LIBELLE_COMPOSANT` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CMP_CODE_COMPOSANT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `constituer`
--

DROP TABLE IF EXISTS `constituer`;
CREATE TABLE IF NOT EXISTS `constituer` (
  `MED_DEPOTLEGAL_MEDICAMENT` int(11) NOT NULL,
  `CMP_CODE_COMPOSANT` smallint(6) NOT NULL,
  `CST_QTE_CONSTITUER` float DEFAULT NULL,
  `CST_UNITE_CONSTITUER` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`MED_DEPOTLEGAL_MEDICAMENT`,`CMP_CODE_COMPOSANT`),
  KEY `FK_CONSTITUER_CMP_CODE_COMPOSANT` (`CMP_CODE_COMPOSANT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `date`
--

DROP TABLE IF EXISTS `date`;
CREATE TABLE IF NOT EXISTS `date` (
  `JJMMAA_DATE` date NOT NULL,
  PRIMARY KEY (`JJMMAA_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `date`
--

INSERT INTO `date` (`JJMMAA_DATE`) VALUES
('2018-11-01'),
('2019-03-20');

-- --------------------------------------------------------

--
-- Structure de la table `departement`
--

DROP TABLE IF EXISTS `departement`;
CREATE TABLE IF NOT EXISTS `departement` (
  `DEP_CODE_DEPARTEMENT` tinyint(4) NOT NULL,
  `DEP_NOM_DEPARTEMENT` varchar(50) DEFAULT NULL,
  `DEP_CHEFVENTE_DEPARTEMENT` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`DEP_CODE_DEPARTEMENT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `departement`
--

INSERT INTO `departement` (`DEP_CODE_DEPARTEMENT`, `DEP_NOM_DEPARTEMENT`, `DEP_CHEFVENTE_DEPARTEMENT`) VALUES
(1, 'Swiss', 1),
(35, 'illeEtVilaine', 1);

-- --------------------------------------------------------

--
-- Structure de la table `dosage`
--

DROP TABLE IF EXISTS `dosage`;
CREATE TABLE IF NOT EXISTS `dosage` (
  `DOS_CODE_DOSAGE` tinyint(4) NOT NULL,
  `DOS_QUANTITE_DOSAGE` float DEFAULT NULL,
  `DOS_UNITE_DOSAGE` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`DOS_CODE_DOSAGE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `famille`
--

DROP TABLE IF EXISTS `famille`;
CREATE TABLE IF NOT EXISTS `famille` (
  `FAM_CODE_FAMILLE` smallint(6) NOT NULL,
  `FAM_LIBELLE_FAMILLE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`FAM_CODE_FAMILLE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fiche_frais`
--

DROP TABLE IF EXISTS `fiche_frais`;
CREATE TABLE IF NOT EXISTS `fiche_frais` (
  `FF_ID_FICHE_FRAIS` int(11) NOT NULL AUTO_INCREMENT,
  `FF_MOIS_FICHE_FRAIS` tinyint(2) NOT NULL,
  `FF_NBHorsClassif_FICHE_FRAIS` tinyint(2) DEFAULT NULL,
  `FF_MontantHorsClassif_FICHE_FRAIS` float DEFAULT NULL,
  `VIS_MATRICULE_VISITEUR` char(4) NOT NULL,
  `FF_ETAPE_FICHE_FRAIS` varchar(20) DEFAULT NULL,
  `FF_DateDerniereModif_Fiche_Frais` date DEFAULT NULL,
  PRIMARY KEY (`FF_ID_FICHE_FRAIS`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `formuler`
--

DROP TABLE IF EXISTS `formuler`;
CREATE TABLE IF NOT EXISTS `formuler` (
  `MED_DEPOTLEGAL_MEDICAMENT` int(11) NOT NULL,
  `PRE_CODE_PRESENTATION` tinyint(4) NOT NULL,
  PRIMARY KEY (`MED_DEPOTLEGAL_MEDICAMENT`,`PRE_CODE_PRESENTATION`),
  KEY `FK_FORMULER_PRE_CODE_PRESENTATION` (`PRE_CODE_PRESENTATION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `inclure`
--

DROP TABLE IF EXISTS `inclure`;
CREATE TABLE IF NOT EXISTS `inclure` (
  `FF_ID_FICHE_FRAIS` int(11) NOT NULL,
  `TF_CODE_TYPE_FRAIS` tinyint(2) NOT NULL,
  `INC_QTE_INCLURE` tinyint(4) DEFAULT NULL,
  `INC_MONTANT_INCLURE` float DEFAULT NULL,
  PRIMARY KEY (`FF_ID_FICHE_FRAIS`,`TF_CODE_TYPE_FRAIS`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `interagir`
--

DROP TABLE IF EXISTS `interagir`;
CREATE TABLE IF NOT EXISTS `interagir` (
  `MED_DEPOTLEGAL_MEDICAMENT` int(11) NOT NULL,
  `MED_DEPOTLEGAL_MEDICAMENT_INTERAGIR` int(11) DEFAULT NULL,
  PRIMARY KEY (`MED_DEPOTLEGAL_MEDICAMENT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `inviter`
--

DROP TABLE IF EXISTS `inviter`;
CREATE TABLE IF NOT EXISTS `inviter` (
  `AC_NUM_ACTIVITE_COMPL` smallint(6) NOT NULL,
  `PRA_NUM_PRATICIEN` smallint(5) NOT NULL,
  `SPECIALISTEON_INVITER` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`AC_NUM_ACTIVITE_COMPL`,`PRA_NUM_PRATICIEN`),
  KEY `FK_INVITER_PRA_NUM_PRATICIEN` (`PRA_NUM_PRATICIEN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `medicament`
--

DROP TABLE IF EXISTS `medicament`;
CREATE TABLE IF NOT EXISTS `medicament` (
  `MED_DEPOTLEGAL_MEDICAMENT` int(11) NOT NULL,
  `MED_NOMCOMMERCIAL_MEDICAMENT` varchar(50) DEFAULT NULL,
  `MED_COMPOSITION_MEDICAMENT` text,
  `MED_EFFETS_MEDICAMENT` text,
  `MED_CONTREINDIC_MEDICAMENT` text,
  `MED_PRIXECHANTILLON_MEDICAMENT` float DEFAULT NULL,
  `FAM_CODE_FAMILLE` smallint(6) NOT NULL,
  PRIMARY KEY (`MED_DEPOTLEGAL_MEDICAMENT`),
  KEY `FK_MEDICAMENT_FAM_CODE_FAMILLE` (`FAM_CODE_FAMILLE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `offrir`
--

DROP TABLE IF EXISTS `offrir`;
CREATE TABLE IF NOT EXISTS `offrir` (
  `RAP_NUM_RAPPORT_VISITE` smallint(6) NOT NULL,
  `MED_DEPOTLEGAL_MEDICAMENT` int(11) NOT NULL,
  `OFF_QTE_OFFRIR` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`RAP_NUM_RAPPORT_VISITE`,`MED_DEPOTLEGAL_MEDICAMENT`),
  KEY `FK_OFFRIR_MED_DEPOTLEGAL_MEDICAMENT` (`MED_DEPOTLEGAL_MEDICAMENT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `posseder`
--

DROP TABLE IF EXISTS `posseder`;
CREATE TABLE IF NOT EXISTS `posseder` (
  `PRA_NUM_PRATICIEN` smallint(5) NOT NULL,
  `SPE_CODE_SPECIALITE` smallint(6) NOT NULL,
  `POS_DIPLOME_POSSEDER` varchar(80) DEFAULT NULL,
  `POS_COEFPRESCRIPTION_POSSEDER` float DEFAULT NULL,
  PRIMARY KEY (`PRA_NUM_PRATICIEN`,`SPE_CODE_SPECIALITE`),
  KEY `FK_POSSEDER_SPE_CODE_SPECIALITE` (`SPE_CODE_SPECIALITE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `praticien`
--

DROP TABLE IF EXISTS `praticien`;
CREATE TABLE IF NOT EXISTS `praticien` (
  `PRA_NUM_PRATICIEN` smallint(5) NOT NULL,
  `PRA_NOM_PRATICIEN` varchar(25) DEFAULT NULL,
  `PRA_PRENOM_PRATICIEN` varchar(30) DEFAULT NULL,
  `PRA_ADRESSE_PRATICIEN` varchar(50) DEFAULT NULL,
  `PRA_CP_PRATICIEN` varchar(5) DEFAULT NULL,
  `PRA_VILLE_PRATICIEN` varchar(45) DEFAULT NULL,
  `PRA_COEFNOTORIETE_PRATICIEN` float DEFAULT NULL,
  `TYP_CODE_TYPE_PRATICIEN` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`PRA_NUM_PRATICIEN`),
  KEY `FK_PRATICIEN_TYP_CODE_TYPE_PRATICIEN` (`TYP_CODE_TYPE_PRATICIEN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `prescrire`
--

DROP TABLE IF EXISTS `prescrire`;
CREATE TABLE IF NOT EXISTS `prescrire` (
  `MED_DEPOTLEGAL_MEDICAMENT` int(11) NOT NULL,
  `TIN_CODE_TYPE_INDIVIDU` tinyint(4) NOT NULL,
  `DOS_CODE_DOSAGE` tinyint(4) NOT NULL,
  `PRE_POSOLOGIE_PRESCRIRE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MED_DEPOTLEGAL_MEDICAMENT`,`TIN_CODE_TYPE_INDIVIDU`,`DOS_CODE_DOSAGE`),
  KEY `FK_PRESCRIRE_TIN_CODE_TYPE_INDIVIDU` (`TIN_CODE_TYPE_INDIVIDU`),
  KEY `FK_PRESCRIRE_DOS_CODE_DOSAGE` (`DOS_CODE_DOSAGE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `presentation`
--

DROP TABLE IF EXISTS `presentation`;
CREATE TABLE IF NOT EXISTS `presentation` (
  `PRE_CODE_PRESENTATION` tinyint(4) NOT NULL,
  `PRE_LIBELLE_PRESENTATION` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PRE_CODE_PRESENTATION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `rapport_visite`
--

DROP TABLE IF EXISTS `rapport_visite`;
CREATE TABLE IF NOT EXISTS `rapport_visite` (
  `RAP_NUM_RAPPORT_VISITE` smallint(6) NOT NULL,
  `RAP_DATE_RAPPORT_VISITE` date DEFAULT NULL,
  `RAP_BILAN_RAPPORT_VISITE` text,
  `RAP_MOTIF_RAPPORT_VISITE` varchar(50) DEFAULT NULL,
  `VIS_MATRICULE_VISITEUR` char(4) NOT NULL,
  `PRA_NUM_PRATICIEN` smallint(5) NOT NULL,
  PRIMARY KEY (`RAP_NUM_RAPPORT_VISITE`),
  KEY `FK_RAPPORT_VISITE_VIS_MATRICULE_VISITEUR` (`VIS_MATRICULE_VISITEUR`),
  KEY `FK_RAPPORT_VISITE_PRA_NUM_PRATICIEN` (`PRA_NUM_PRATICIEN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `realiser`
--

DROP TABLE IF EXISTS `realiser`;
CREATE TABLE IF NOT EXISTS `realiser` (
  `VIS_MATRICULE_VISITEUR` char(4) NOT NULL,
  `AC_NUM_ACTIVITE_COMPL` smallint(6) NOT NULL,
  PRIMARY KEY (`VIS_MATRICULE_VISITEUR`,`AC_NUM_ACTIVITE_COMPL`),
  KEY `FK_REALISER_AC_NUM_ACTIVITE_COMPL` (`AC_NUM_ACTIVITE_COMPL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `region`
--

DROP TABLE IF EXISTS `region`;
CREATE TABLE IF NOT EXISTS `region` (
  `REG_CODE_REGION` int(11) NOT NULL AUTO_INCREMENT,
  `REG_NOM_REGION` varchar(50) DEFAULT NULL,
  `SEC_CODE_SECTEUR` tinyint(4) NOT NULL,
  PRIMARY KEY (`REG_CODE_REGION`),
  KEY `FK_REGION_SEC_CODE_SECTEUR` (`SEC_CODE_SECTEUR`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `region`
--

INSERT INTO `region` (`REG_CODE_REGION`, `REG_NOM_REGION`, `SEC_CODE_SECTEUR`) VALUES
(1, 'bretagne', 1),
(2, 'Normandie', 12);

-- --------------------------------------------------------

--
-- Structure de la table `secteur`
--

DROP TABLE IF EXISTS `secteur`;
CREATE TABLE IF NOT EXISTS `secteur` (
  `SEC_CODE_SECTEUR` tinyint(4) NOT NULL,
  `SEC_LIBELLE_SECTEUR` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SEC_CODE_SECTEUR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `secteur`
--

INSERT INTO `secteur` (`SEC_CODE_SECTEUR`, `SEC_LIBELLE_SECTEUR`) VALUES
(1, 'Nord'),
(12, 'Nord');

-- --------------------------------------------------------

--
-- Structure de la table `specialite`
--

DROP TABLE IF EXISTS `specialite`;
CREATE TABLE IF NOT EXISTS `specialite` (
  `SPE_CODE_SPECIALITE` smallint(6) NOT NULL,
  `SPE_LIBELLE_SPECIALITE` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SPE_CODE_SPECIALITE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `travailler`
--

DROP TABLE IF EXISTS `travailler`;
CREATE TABLE IF NOT EXISTS `travailler` (
  `REG_CODE_REGION` int(11) NOT NULL AUTO_INCREMENT,
  `JJMMAA_DATE` date NOT NULL,
  `VIS_MATRICULE_VISITEUR` char(4) NOT NULL,
  `TRA_ROLE_TRAVAILLER` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`REG_CODE_REGION`,`JJMMAA_DATE`,`VIS_MATRICULE_VISITEUR`),
  KEY `FK_TRAVAILLER_JJMMAA_DATE` (`JJMMAA_DATE`),
  KEY `FK_TRAVAILLER_VIS_MATRICULE_VISITEUR` (`VIS_MATRICULE_VISITEUR`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `travailler`
--

INSERT INTO `travailler` (`REG_CODE_REGION`, `JJMMAA_DATE`, `VIS_MATRICULE_VISITEUR`, `TRA_ROLE_TRAVAILLER`) VALUES
(1, '2019-03-20', '1234', 'delegue regional');

-- --------------------------------------------------------

--
-- Structure de la table `type_frais`
--

DROP TABLE IF EXISTS `type_frais`;
CREATE TABLE IF NOT EXISTS `type_frais` (
  `TF_CODE_TYPE_FRAIS` tinyint(4) NOT NULL,
  `TF_LIBELLE_TYPE_FRAIS` varchar(30) DEFAULT NULL,
  `TF_FORFAIT_TYPE_FRAIS` float DEFAULT NULL,
  PRIMARY KEY (`TF_CODE_TYPE_FRAIS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `type_individu`
--

DROP TABLE IF EXISTS `type_individu`;
CREATE TABLE IF NOT EXISTS `type_individu` (
  `TIN_CODE_TYPE_INDIVIDU` tinyint(4) NOT NULL,
  `TIN_LIBELLE_TYPE_INDIVIDU` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`TIN_CODE_TYPE_INDIVIDU`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `type_praticien`
--

DROP TABLE IF EXISTS `type_praticien`;
CREATE TABLE IF NOT EXISTS `type_praticien` (
  `TYP_CODE_TYPE_PRATICIEN` tinyint(4) NOT NULL,
  `TYP_LIBELLE_TYPE_PRATICIEN` varchar(20) DEFAULT NULL,
  `TYP_LIEU_TYPE_PRATICIEN` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`TYP_CODE_TYPE_PRATICIEN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `visiteur`
--

DROP TABLE IF EXISTS `visiteur`;
CREATE TABLE IF NOT EXISTS `visiteur` (
  `VIS_MATRICULE_VISITEUR` char(4) NOT NULL,
  `VIS_NOM_VISITEUR` varchar(25) DEFAULT NULL,
  `VIS_ADRESSE_VISITEUR` varchar(50) DEFAULT NULL,
  `VIS_CP_VISITEUR` varchar(5) DEFAULT NULL,
  `VIS_VILLE_VISITEUR` varchar(45) DEFAULT NULL,
  `VIS_DATEEMBAUCHE_VISITEUR` date DEFAULT NULL,
  `VIS_PRENOM_VISITEUR` varchar(30) DEFAULT NULL,
  `SEC_CODE_SECTEUR` tinyint(4) DEFAULT NULL,
  `DEP_CODE_DEPARTEMENT` tinyint(4) NOT NULL,
  PRIMARY KEY (`VIS_MATRICULE_VISITEUR`),
  KEY `FK_VISITEUR_SEC_CODE_SECTEUR` (`SEC_CODE_SECTEUR`),
  KEY `FK_VISITEUR_DEP_CODE_DEPARTEMENT` (`DEP_CODE_DEPARTEMENT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `visiteur`
--

INSERT INTO `visiteur` (`VIS_MATRICULE_VISITEUR`, `VIS_NOM_VISITEUR`, `VIS_ADRESSE_VISITEUR`, `VIS_CP_VISITEUR`, `VIS_VILLE_VISITEUR`, `VIS_DATEEMBAUCHE_VISITEUR`, `VIS_PRENOM_VISITEUR`, `SEC_CODE_SECTEUR`, `DEP_CODE_DEPARTEMENT`) VALUES
('1', 'm n', 'greta', '35025', 'o', '2019-03-01', 'y', 12, 35),
('1234', 'SERRES', '21 rue des LiLas', '35000', 'Rennes', '2018-09-01', 'Damien', NULL, 1),
('1718', 'TOM', '12 rue du paradis', '35000', 'Rennes', '2018-09-03', 'Olivier', NULL, 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `constituer`
--
ALTER TABLE `constituer`
  ADD CONSTRAINT `FK_CONSTITUER_CMP_CODE_COMPOSANT` FOREIGN KEY (`CMP_CODE_COMPOSANT`) REFERENCES `composant` (`CMP_CODE_COMPOSANT`),
  ADD CONSTRAINT `FK_CONSTITUER_MED_DEPOTLEGAL_MEDICAMENT` FOREIGN KEY (`MED_DEPOTLEGAL_MEDICAMENT`) REFERENCES `medicament` (`MED_DEPOTLEGAL_MEDICAMENT`);

--
-- Contraintes pour la table `formuler`
--
ALTER TABLE `formuler`
  ADD CONSTRAINT `FK_FORMULER_MED_DEPOTLEGAL_MEDICAMENT` FOREIGN KEY (`MED_DEPOTLEGAL_MEDICAMENT`) REFERENCES `medicament` (`MED_DEPOTLEGAL_MEDICAMENT`),
  ADD CONSTRAINT `FK_FORMULER_PRE_CODE_PRESENTATION` FOREIGN KEY (`PRE_CODE_PRESENTATION`) REFERENCES `presentation` (`PRE_CODE_PRESENTATION`);

--
-- Contraintes pour la table `interagir`
--
ALTER TABLE `interagir`
  ADD CONSTRAINT `FK_INTERAGIR_MED_DEPOTLEGAL_MEDICAMENT` FOREIGN KEY (`MED_DEPOTLEGAL_MEDICAMENT`) REFERENCES `medicament` (`MED_DEPOTLEGAL_MEDICAMENT`);

--
-- Contraintes pour la table `inviter`
--
ALTER TABLE `inviter`
  ADD CONSTRAINT `FK_INVITER_AC_NUM_ACTIVITE_COMPL` FOREIGN KEY (`AC_NUM_ACTIVITE_COMPL`) REFERENCES `activite_compl` (`AC_NUM_ACTIVITE_COMPL`),
  ADD CONSTRAINT `FK_INVITER_PRA_NUM_PRATICIEN` FOREIGN KEY (`PRA_NUM_PRATICIEN`) REFERENCES `praticien` (`PRA_NUM_PRATICIEN`);

--
-- Contraintes pour la table `medicament`
--
ALTER TABLE `medicament`
  ADD CONSTRAINT `FK_MEDICAMENT_FAM_CODE_FAMILLE` FOREIGN KEY (`FAM_CODE_FAMILLE`) REFERENCES `famille` (`FAM_CODE_FAMILLE`);

--
-- Contraintes pour la table `offrir`
--
ALTER TABLE `offrir`
  ADD CONSTRAINT `FK_OFFRIR_MED_DEPOTLEGAL_MEDICAMENT` FOREIGN KEY (`MED_DEPOTLEGAL_MEDICAMENT`) REFERENCES `medicament` (`MED_DEPOTLEGAL_MEDICAMENT`),
  ADD CONSTRAINT `FK_OFFRIR_RAP_NUM_RAPPORT_VISITE` FOREIGN KEY (`RAP_NUM_RAPPORT_VISITE`) REFERENCES `rapport_visite` (`RAP_NUM_RAPPORT_VISITE`);

--
-- Contraintes pour la table `posseder`
--
ALTER TABLE `posseder`
  ADD CONSTRAINT `FK_POSSEDER_PRA_NUM_PRATICIEN` FOREIGN KEY (`PRA_NUM_PRATICIEN`) REFERENCES `praticien` (`PRA_NUM_PRATICIEN`),
  ADD CONSTRAINT `FK_POSSEDER_SPE_CODE_SPECIALITE` FOREIGN KEY (`SPE_CODE_SPECIALITE`) REFERENCES `specialite` (`SPE_CODE_SPECIALITE`);

--
-- Contraintes pour la table `praticien`
--
ALTER TABLE `praticien`
  ADD CONSTRAINT `FK_PRATICIEN_TYP_CODE_TYPE_PRATICIEN` FOREIGN KEY (`TYP_CODE_TYPE_PRATICIEN`) REFERENCES `type_praticien` (`TYP_CODE_TYPE_PRATICIEN`);

--
-- Contraintes pour la table `prescrire`
--
ALTER TABLE `prescrire`
  ADD CONSTRAINT `FK_PRESCRIRE_DOS_CODE_DOSAGE` FOREIGN KEY (`DOS_CODE_DOSAGE`) REFERENCES `dosage` (`DOS_CODE_DOSAGE`),
  ADD CONSTRAINT `FK_PRESCRIRE_MED_DEPOTLEGAL_MEDICAMENT` FOREIGN KEY (`MED_DEPOTLEGAL_MEDICAMENT`) REFERENCES `medicament` (`MED_DEPOTLEGAL_MEDICAMENT`),
  ADD CONSTRAINT `FK_PRESCRIRE_TIN_CODE_TYPE_INDIVIDU` FOREIGN KEY (`TIN_CODE_TYPE_INDIVIDU`) REFERENCES `type_individu` (`TIN_CODE_TYPE_INDIVIDU`);

--
-- Contraintes pour la table `rapport_visite`
--
ALTER TABLE `rapport_visite`
  ADD CONSTRAINT `FK_RAPPORT_VISITE_PRA_NUM_PRATICIEN` FOREIGN KEY (`PRA_NUM_PRATICIEN`) REFERENCES `praticien` (`PRA_NUM_PRATICIEN`),
  ADD CONSTRAINT `FK_RAPPORT_VISITE_VIS_MATRICULE_VISITEUR` FOREIGN KEY (`VIS_MATRICULE_VISITEUR`) REFERENCES `visiteur` (`VIS_MATRICULE_VISITEUR`);

--
-- Contraintes pour la table `realiser`
--
ALTER TABLE `realiser`
  ADD CONSTRAINT `FK_REALISER_AC_NUM_ACTIVITE_COMPL` FOREIGN KEY (`AC_NUM_ACTIVITE_COMPL`) REFERENCES `activite_compl` (`AC_NUM_ACTIVITE_COMPL`),
  ADD CONSTRAINT `FK_REALISER_VIS_MATRICULE_VISITEUR` FOREIGN KEY (`VIS_MATRICULE_VISITEUR`) REFERENCES `visiteur` (`VIS_MATRICULE_VISITEUR`);

--
-- Contraintes pour la table `region`
--
ALTER TABLE `region`
  ADD CONSTRAINT `FK_REGION_SEC_CODE_SECTEUR` FOREIGN KEY (`SEC_CODE_SECTEUR`) REFERENCES `secteur` (`SEC_CODE_SECTEUR`);

--
-- Contraintes pour la table `travailler`
--
ALTER TABLE `travailler`
  ADD CONSTRAINT `FK_TRAVAILLER_JJMMAA_DATE` FOREIGN KEY (`JJMMAA_DATE`) REFERENCES `date` (`JJMMAA_DATE`),
  ADD CONSTRAINT `FK_TRAVAILLER_REG_CODE_REGION` FOREIGN KEY (`REG_CODE_REGION`) REFERENCES `region` (`REG_CODE_REGION`),
  ADD CONSTRAINT `FK_TRAVAILLER_VIS_MATRICULE_VISITEUR` FOREIGN KEY (`VIS_MATRICULE_VISITEUR`) REFERENCES `visiteur` (`VIS_MATRICULE_VISITEUR`);

--
-- Contraintes pour la table `visiteur`
--
ALTER TABLE `visiteur`
  ADD CONSTRAINT `FK_VISITEUR_DEP_CODE_DEPARTEMENT` FOREIGN KEY (`DEP_CODE_DEPARTEMENT`) REFERENCES `departement` (`DEP_CODE_DEPARTEMENT`),
  ADD CONSTRAINT `FK_VISITEUR_SEC_CODE_SECTEUR` FOREIGN KEY (`SEC_CODE_SECTEUR`) REFERENCES `secteur` (`SEC_CODE_SECTEUR`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
