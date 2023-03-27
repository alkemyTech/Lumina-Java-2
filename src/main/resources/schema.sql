USE `ljt2`;
CREATE TABLE IF NOT EXISTS `ljt2`.`roles`(
    `roleId` BIGINT(255) NOT NULL AUTO_INCREMENT,
    `name`VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `creationDate`  TIMESTAMP DEFAULT NOW(),
    `updateDate` TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY(`roleId`));

    CREATE TABLE IF NOT EXISTS `ljt2`.`fixed_term_deposits`(
        `fixedTermDepositId` BIGINT(255) NOT NULL AUTO_INCREMENT,
        `amount` DOUBLE NOT NULL,
  --    `accountId` BIGINT(255) NOT NULL,
        `interest` DOUBLE NOT NULL,
        `creationDate`  TIMESTAMP DEFAULT NOW(),
        `updateDate` TIMESTAMP DEFAULT NOW(),
         PRIMARY KEY(`fixedTermDepositId`));
         --KEY `FK_role` (`accountId`),
         --  CONSTRAINT `accountId`

