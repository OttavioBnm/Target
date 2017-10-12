-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mar. 10 oct. 2017 à 15:12
-- Version du serveur :  5.7.17
-- Version de PHP :  5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `target`
--

-- --------------------------------------------------------

--
-- Structure de la table `demandes`
--

CREATE TABLE `demandes` (
  `idDemandes` int(5) NOT NULL,
  `idUserReciver` int(5) NOT NULL,
  `idUserAsking` int(5) NOT NULL,
  `matching` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `idUser` int(10) NOT NULL,
  `email` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(50) COLLATE utf8_bin NOT NULL,
  `login` varchar(20) COLLATE utf8_bin NOT NULL,
  `nom` varchar(20) COLLATE utf8_bin NOT NULL,
  `prenom` varchar(20) COLLATE utf8_bin NOT NULL,
  `caracteristiques` varchar(50) COLLATE utf8_bin NOT NULL,
  `description` varchar(50) COLLATE utf8_bin NOT NULL,
  `cheminphoto` varchar(50) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUser`, `email`, `password`, `login`, `nom`, `prenom`, `caracteristiques`, `description`, `cheminphoto`) VALUES
(1, 'qay@qay.com', 'fa8754f5bb796d6c5129b02b646bbd8bd4cb779c', 'wdfs', 'qay', 'fdgdfgd', 'gentil et doux comme un elephant', 'qay', ''),
(3, 'qwe@qwe.com', '056eafe7cf52220de2df36845b8ed170c67e23e3', 'qwe', 'qwe', 'qwe', '', 'qwe', ''),
(4, 'asd@qwe.com', 'f10e2821bbbea527ea02200352313bc059445190', 'asd', 'asd', 'asd', '', 'asd', ''),
(6, 'plop@plop.com', '64faf5d0b1dc311fd0f94af64f6c296a03045571', 'plop', 'plop', 'plop', '', 'plop', ''),
(7, 'zhn@zhn.com', 'fa8754f5bb796d6c5129b02b646bbd8bd4cb779c', 'qay', 'qay', 'qay', '', 'qay', NULL),
(8, '951@951.com', '8e63fd3e77796b102589b1ba1e4441c7982e4132', '951', '951', '951', '', '951', NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `demandes`
--
ALTER TABLE `demandes`
  ADD PRIMARY KEY (`idDemandes`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `demandes`
--
ALTER TABLE `demandes`
  MODIFY `idDemandes` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `idUser` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
