-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-01-2019 a las 22:10:28
-- Versión del servidor: 10.1.32-MariaDB
-- Versión de PHP: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `scrumprojectmanager`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `permisoID` int(11) NOT NULL,
  `permiso_name` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`permisoID`, `permiso_name`) VALUES
(1, 'Developer'),
(2, 'Administrador'),
(3, 'Scrum Master'),
(4, 'Product Owner');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `projects`
--

CREATE TABLE `projects` (
  `projectID` int(11) NOT NULL,
  `project_name` varchar(20) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `productOwnerID` int(15) NOT NULL,
  `scrumMasterID` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `projects`
--

INSERT INTO `projects` (`projectID`, `project_name`, `descripcion`, `productOwnerID`, `scrumMasterID`) VALUES
(1, 'Gestor de proyectos ', 'Aplicacion que gestione todo lo relacionado a proyectos SCRUM', 4, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `specifications`
--

CREATE TABLE `specifications` (
  `specID` int(11) NOT NULL,
  `description` varchar(100) NOT NULL,
  `projectID` int(11) NOT NULL,
  `sprintID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sprints`
--

CREATE TABLE `sprints` (
  `sprintID` int(11) NOT NULL,
  `projectID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usergroups`
--

CREATE TABLE `usergroups` (
  `groupID` int(11) NOT NULL,
  `group_name` varchar(25) NOT NULL,
  `projectID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usergroups`
--

INSERT INTO `usergroups` (`groupID`, `group_name`, `projectID`) VALUES
(1, 'Pachon and Co', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `nickname` varchar(25) NOT NULL,
  `complete_name` varchar(50) NOT NULL,
  `password` varchar(25) NOT NULL,
  `mail` varchar(30) NOT NULL,
  `groupID` int(11) DEFAULT NULL,
  `permisoID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`userID`, `nickname`, `complete_name`, `password`, `mail`, `groupID`, `permisoID`) VALUES
(1, 'pachon', 'Alberto Pachon', '123123', 'pachon@gmail.com', 1, 1),
(2, 'vsalas', 'Victor Salas Fernandez', '123123', 'vsf_2525@hotmail.com', 1, 2),
(3, 'dsoubouti', 'Darius Sobouti', '123123', 'dsoubouti@hotmail.com', 1, 3),
(4, 'rcarballo', 'Roger Carballo Rodriguez', '123123', 'rcarballo@everis.com', NULL, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usersprojects`
--

CREATE TABLE `usersprojects` (
  `userID` int(11) NOT NULL,
  `projectID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usersprojects`
--

INSERT INTO `usersprojects` (`userID`, `projectID`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`permisoID`);

--
-- Indices de la tabla `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`projectID`),
  ADD KEY `foreign_key_poid` (`productOwnerID`),
  ADD KEY `foreign_key_sid` (`scrumMasterID`);

--
-- Indices de la tabla `specifications`
--
ALTER TABLE `specifications`
  ADD PRIMARY KEY (`specID`),
  ADD KEY `foreign_key_projectID` (`projectID`),
  ADD KEY `foreign_key_sprintID` (`sprintID`);

--
-- Indices de la tabla `sprints`
--
ALTER TABLE `sprints`
  ADD PRIMARY KEY (`sprintID`),
  ADD KEY `foreign_key_projectID` (`projectID`);

--
-- Indices de la tabla `usergroups`
--
ALTER TABLE `usergroups`
  ADD PRIMARY KEY (`groupID`),
  ADD KEY `foreign_key_projectID` (`projectID`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`),
  ADD KEY `foreign_key_groupID` (`groupID`),
  ADD KEY `foreign_key_permisoID` (`permisoID`);

--
-- Indices de la tabla `usersprojects`
--
ALTER TABLE `usersprojects`
  ADD KEY `foreign_key_userID` (`userID`),
  ADD KEY `foreign_key_projectID` (`projectID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `permisoID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `projects`
--
ALTER TABLE `projects`
  MODIFY `projectID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `specifications`
--
ALTER TABLE `specifications`
  MODIFY `specID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sprints`
--
ALTER TABLE `sprints`
  MODIFY `sprintID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usergroups`
--
ALTER TABLE `usergroups`
  MODIFY `groupID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `projects`
--
ALTER TABLE `projects`
  ADD CONSTRAINT `projects_ibfk_1` FOREIGN KEY (`productOwnerID`) REFERENCES `users` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `projects_ibfk_2` FOREIGN KEY (`scrumMasterID`) REFERENCES `users` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `specifications`
--
ALTER TABLE `specifications`
  ADD CONSTRAINT `specifications_ibfk_1` FOREIGN KEY (`projectID`) REFERENCES `projects` (`projectID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `specifications_ibfk_2` FOREIGN KEY (`sprintID`) REFERENCES `sprints` (`sprintID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `sprints`
--
ALTER TABLE `sprints`
  ADD CONSTRAINT `sprints_ibfk_1` FOREIGN KEY (`projectID`) REFERENCES `projects` (`projectID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usergroups`
--
ALTER TABLE `usergroups`
  ADD CONSTRAINT `usergroups_ibfk_1` FOREIGN KEY (`projectID`) REFERENCES `projects` (`projectID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`groupID`) REFERENCES `usergroups` (`groupID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `users_ibfk_2` FOREIGN KEY (`permisoID`) REFERENCES `permisos` (`permisoID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usersprojects`
--
ALTER TABLE `usersprojects`
  ADD CONSTRAINT `usersprojects_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usersprojects_ibfk_2` FOREIGN KEY (`projectID`) REFERENCES `projects` (`projectID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
