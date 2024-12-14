-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.44-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for works
CREATE DATABASE IF NOT EXISTS `works` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `works`;

-- Dumping structure for table works.account
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table works.account: ~4 rows (approximately)
INSERT INTO `account` (`id`, `username`, `password`, `role`) VALUES
	(1, 'company', '123', 'COMPANY'),
	(2, 'candidate', '123', 'CANDIDATE'),
	(3, 'candidate1', '123', 'CANDIDATE'),
	(4, 'company1', '123', 'COMPANY');

-- Dumping structure for table works.address
CREATE TABLE IF NOT EXISTS `address` (
  `add_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(50) DEFAULT NULL,
  `country` smallint(6) DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL,
  `street` varchar(150) DEFAULT NULL,
  `zipcode` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`add_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

-- Dumping data for table works.address: ~22 rows (approximately)
INSERT INTO `address` (`add_id`, `city`, `country`, `number`, `street`, `zipcode`) VALUES
	(1, 'HCM', 258, '248', 'Quang Trung', '77029'),
	(2, 'HCM', 258, '324', 'Quang Trung', '74748'),
	(3, 'HCM', 258, '690', 'Quang Trung', '72196'),
	(4, 'HCM', 258, '575', 'Quang Trung', '75159'),
	(5, 'HCM', 258, '658', 'Quang Trung', '72069'),
	(6, 'HCM', 258, '848', 'Quang Trung', '73341'),
	(7, 'HCM', 258, '749', 'Quang Trung', '75264'),
	(8, 'HCM', 258, '222', 'Quang Trung', '71753'),
	(9, 'HCM', 258, '995', 'Quang Trung', '75852'),
	(10, 'HCM', 258, '659', 'Quang Trung', '73699'),
	(11, 'HCM', 258, '583', 'Quang Trung', '79326'),
	(12, 'HCM', 258, '919', 'Quang Trung', '77193'),
	(13, 'HCM', 258, '623', 'Quang Trung', '70792'),
	(14, 'HCM', 258, '446', 'Quang Trung', '79442'),
	(15, 'HCM', 258, '919', 'Quang Trung', '77508'),
	(16, 'HCM', 258, '744', 'Quang Trung', '70231'),
	(17, 'HCM', 258, '604', 'Quang Trung', '77445'),
	(18, 'HCM', 258, '312', 'Quang Trung', '73623'),
	(19, 'HCM', 258, '551', 'Quang Trung', '78881'),
	(20, 'HCM', 258, '930', 'Quang Trung', '78366'),
	(21, 'Hanoi', 258, '123', 'Le Loi', '100000'),
	(22, 'Da Nang', 258, '456', 'Nguyen Hue', '550000');

-- Dumping structure for table works.candidate
CREATE TABLE IF NOT EXISTS `candidate` (
  `can_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dob` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `status` int(11) NOT NULL,
  `address` bigint(20) NOT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`can_id`),
  UNIQUE KEY `UKqfut8ruekode092nlkipgl09g` (`email`),
  UNIQUE KEY `UK9i5yt1gvm0chg5e10qkns7tll` (`phone`),
  UNIQUE KEY `UKm8qhlm4wu215gr34dhxp0dour` (`address`),
  UNIQUE KEY `UKr09ojuqppptb5tf8f640kim17` (`account_id`),
  CONSTRAINT `FKa8gnyyhbb2qnhp94grci1n0o9` FOREIGN KEY (`address`) REFERENCES `address` (`add_id`),
  CONSTRAINT `FKj4889h0mbv3h6rbbxuuyoyame` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

-- Dumping data for table works.candidate: ~22 rows (approximately)
INSERT INTO `candidate` (`can_id`, `dob`, `email`, `full_name`, `phone`, `status`, `address`, `account_id`) VALUES
	(1, '1998-07-27', 'email_1@gmail.com', 'Nguyen Gia Huy', '8346244324', 1, 1, 2),
	(2, '1998-08-16', 'email_2@gmail.com', 'Nguyen Van A', '1233491761', 1, 2, 3),
	(3, '1998-08-10', 'email_3@gmail.com', 'Name #3', '4962823112', 1, 3, NULL),
	(4, '1998-09-17', 'email_4@gmail.com', 'Name #4', '1559872891', 1, 4, NULL),
	(5, '1998-04-03', 'email_5@gmail.com', 'Name #5', '7514970332', 1, 5, NULL),
	(6, '1998-05-03', 'email_6@gmail.com', 'Name #6', '8823537774', 1, 6, NULL),
	(7, '1998-03-18', 'email_7@gmail.com', 'Name #7', '2297167881', 1, 7, NULL),
	(8, '1998-03-03', 'email_8@gmail.com', 'Name #8', '2528896215', 1, 8, NULL),
	(9, '1998-01-03', 'email_9@gmail.com', 'Name #9', '6776563756', 1, 9, NULL),
	(10, '1998-11-14', 'email_10@gmail.com', 'Name #10', '3435470676', 1, 10, NULL),
	(11, '1998-04-23', 'email_11@gmail.com', 'Name #11', '4604423463', 1, 11, NULL),
	(12, '1998-06-28', 'email_12@gmail.com', 'Name #12', '7833873248', 1, 12, NULL),
	(13, '1998-08-02', 'email_13@gmail.com', 'Name #13', '6414196268', 1, 13, NULL),
	(14, '1998-12-08', 'email_14@gmail.com', 'Name #14', '2476533158', 1, 14, NULL),
	(15, '1998-11-13', 'email_15@gmail.com', 'Name #15', '3369324861', 1, 15, NULL),
	(16, '1998-06-04', 'email_16@gmail.com', 'Name #16', '3179513006', 1, 16, NULL),
	(17, '1998-03-19', 'email_17@gmail.com', 'Name #17', '2533227058', 1, 17, NULL),
	(18, '1998-11-02', 'email_18@gmail.com', 'Name #18', '3036488896', 1, 18, NULL),
	(19, '1998-03-25', 'email_19@gmail.com', 'Name #19', '9942340704', 1, 19, NULL),
	(20, '1998-04-27', 'email_20@gmail.com', 'Name #20', '3267400388', 1, 20, NULL),
	(21, '1990-05-15', 'john.doe@example.com', 'John Doe', '0123456789', 1, 21, NULL),
	(22, '1985-08-25', 'jane.smith@example.com', 'Jane Smith', '0987654321', 1, 22, NULL);

-- Dumping structure for table works.candidate_skill
CREATE TABLE IF NOT EXISTS `candidate_skill` (
  `more_infos` varchar(1000) DEFAULT NULL,
  `skill_level` int(11) NOT NULL,
  `skill_id` bigint(20) NOT NULL,
  `can_id` bigint(20) NOT NULL,
  PRIMARY KEY (`can_id`,`skill_id`),
  KEY `FKb7cxhiqhcah7c20a2cdlvr1f8` (`skill_id`),
  CONSTRAINT `FKb0m5tm3fi0upa3b3kjx3vrlxs` FOREIGN KEY (`can_id`) REFERENCES `candidate` (`can_id`),
  CONSTRAINT `FKb7cxhiqhcah7c20a2cdlvr1f8` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table works.candidate_skill: ~10 rows (approximately)
INSERT INTO `candidate_skill` (`more_infos`, `skill_level`, `skill_id`, `can_id`) VALUES
	('Expert in Java programming', 5, 1, 1),
	('Advanced in JavaScript programming', 3, 3, 1),
	('Intermediate in SQL database management', 2, 4, 1),
	('Excellent communication skills', 5, 7, 1),
	('Beginner in CSS style sheet language', 1, 6, 2),
	('Strong problem-solving skills', 3, 9, 2),
	('Good project management skills', 2, 10, 2),
	('Beginner in HTML markup language', 1, 5, 3),
	('Great teamwork abilities', 4, 8, 4),
	('Professional in Python programming', 4, 2, 19);

-- Dumping structure for table works.company
CREATE TABLE IF NOT EXISTS `company` (
  `com_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `about` varchar(2000) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `comp_name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `web_url` varchar(255) DEFAULT NULL,
  `address` bigint(20) NOT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`com_id`),
  UNIQUE KEY `UKrvp2hunsq4sgmpxe3a7i1ym3m` (`address`),
  UNIQUE KEY `UKtq5kr7wfeog5tjjr1eqtww5oh` (`account_id`),
  CONSTRAINT `FK75jxrkyqa7rjrx2sv72j7mnrf` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKd5occp4cjwihejbxdbpvkp5tv` FOREIGN KEY (`address`) REFERENCES `address` (`add_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table works.company: ~2 rows (approximately)
INSERT INTO `company` (`com_id`, `about`, `email`, `comp_name`, `phone`, `web_url`, `address`, `account_id`) VALUES
	(1, 'Leading software development company', 'contact@companya.com', 'Samsung Electronics', '0123456789', 'https://www.companya.com', 21, 1),
	(2, 'Innovative data solutions provider', 'contact@companyb.com', 'Dell Technologies', '0987654321', 'https://www.companyb.com', 22, 4);

-- Dumping structure for table works.experience
CREATE TABLE IF NOT EXISTS `experience` (
  `exp_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `company` varchar(120) NOT NULL,
  `from_date` date NOT NULL,
  `role` varchar(100) NOT NULL,
  `to_date` date NOT NULL,
  `work_desc` varchar(400) NOT NULL,
  `can_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`exp_id`),
  KEY `FK8d5oqe0wxh52v352i04qnuady` (`can_id`),
  CONSTRAINT `FK8d5oqe0wxh52v352i04qnuady` FOREIGN KEY (`can_id`) REFERENCES `candidate` (`can_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table works.experience: ~0 rows (approximately)

-- Dumping structure for table works.job
CREATE TABLE IF NOT EXISTS `job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_desc` varchar(2000) NOT NULL,
  `job_name` varchar(255) NOT NULL,
  `company` bigint(20) DEFAULT NULL,
  `company_id` bigint(20) NOT NULL,
  PRIMARY KEY (`job_id`),
  KEY `FKbaqlvluu78phmo9ld89um7wnm` (`company`),
  CONSTRAINT `FKbaqlvluu78phmo9ld89um7wnm` FOREIGN KEY (`company`) REFERENCES `company` (`com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table works.job: ~4 rows (approximately)
INSERT INTO `job` (`job_id`, `job_desc`, `job_name`, `company`, `company_id`) VALUES
	(1, 'Develop and maintain software applications.', 'Software Engineer', 1, 1),
	(2, 'Analyze and interpret complex data.', 'Data Scientist', 1, 1),
	(3, 'Manage and oversee projects.', 'Project Manager', 2, 2),
	(4, 'Design user-friendly interfaces.', 'UX Designer', 2, 2);

-- Dumping structure for table works.job_skill
CREATE TABLE IF NOT EXISTS `job_skill` (
  `more_infos` varchar(1000) DEFAULT NULL,
  `skill_level` int(11) NOT NULL,
  `job_id` bigint(20) NOT NULL,
  `skill_id` bigint(20) NOT NULL,
  PRIMARY KEY (`job_id`,`skill_id`),
  KEY `FKj33qbbf3vk1lvhqpcosnh54u1` (`skill_id`),
  CONSTRAINT `FK9ix4wg520ii2gu2felxdhdup6` FOREIGN KEY (`job_id`) REFERENCES `job` (`job_id`),
  CONSTRAINT `FKj33qbbf3vk1lvhqpcosnh54u1` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`skill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table works.job_skill: ~10 rows (approximately)
INSERT INTO `job_skill` (`more_infos`, `skill_level`, `job_id`, `skill_id`) VALUES
	('Expert in Java programming', 5, 1, 1),
	('Professional in Python programming', 4, 1, 2),
	('Strong problem-solving skills', 3, 1, 9),
	('Beginner in HTML markup language', 1, 2, 5),
	('Great teamwork abilities', 4, 2, 8),
	('Good project management skills', 3, 2, 10),
	('Intermediate in SQL database management', 2, 3, 4),
	('Excellent communication skills', 5, 3, 7),
	('Advanced in JavaScript programming', 5, 4, 2),
	('Beginner in CSS style sheet language', 1, 4, 6);

-- Dumping structure for table works.skill
CREATE TABLE IF NOT EXISTS `skill` (
  `skill_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `skill_desc` varchar(300) NOT NULL,
  `skill_name` varchar(150) NOT NULL,
  `skill_type` int(11) NOT NULL,
  PRIMARY KEY (`skill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Dumping data for table works.skill: ~10 rows (approximately)
INSERT INTO `skill` (`skill_id`, `skill_desc`, `skill_name`, `skill_type`) VALUES
	(1, 'Programming language', 'Java', 1),
	(2, 'Programming language', 'Python', 1),
	(3, 'Programming language', 'JavaScript', 1),
	(4, 'Database management', 'SQL', 1),
	(5, 'Markup language', 'HTML', 1),
	(6, 'Style sheet language', 'CSS', 1),
	(7, 'Soft skill', 'Communication', 2),
	(8, 'Soft skill', 'Teamwork', 2),
	(9, 'Soft skill', 'Problem-solving', 2),
	(10, 'Soft skill', 'Time Management', 2);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
