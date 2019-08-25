INSERT INTO `country` (`countryId`, `country`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (1, 'Australia', '1979-03-23 20:45:55', 'Erick', '1970-06-09 02:47:38', 'Skylar');
INSERT INTO `country` (`countryId`, `country`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (2, 'Madagascar', '2004-01-16 07:59:28', 'Faye', '1983-03-13 15:37:25', 'Cleta');
INSERT INTO `country` (`countryId`, `country`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (3, 'South Africa', '1985-12-28 08:46:09', 'Blanche', '1979-01-21 01:17:43', 'Richmond');
INSERT INTO `country` (`countryId`, `country`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (4, 'Puerto Rico', '2017-07-21 11:03:33', 'Kathryne', '1992-10-02 03:54:32', 'Amie');
INSERT INTO `country` (`countryId`, `country`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (5, 'Cocos (Keeling) Islands', '1979-11-02 14:52:09', 'Otilia', '2003-12-24 06:49:38', 'Elwyn');

INSERT INTO `city` (`cityId`, `city`, `countryId`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (1, 'Port Filomenaberg', 1, '1993-11-08 15:33:53', 'Odie', '1981-03-03 00:37:10', 'Mrs. Phyllis Rowe MD');
INSERT INTO `city` (`cityId`, `city`, `countryId`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (2, 'Rathside', 2, '2007-02-05 10:13:58', 'Aditya', '1974-08-07 22:11:43', 'Kenton Balistreri');
INSERT INTO `city` (`cityId`, `city`, `countryId`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (3, 'South Markusstad', 3, '2002-11-08 03:18:54', 'Bernhard', '1980-03-18 03:05:51', 'Holly Koss');
INSERT INTO `city` (`cityId`, `city`, `countryId`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (4, 'New Estel', 4, '1986-09-21 14:00:44', 'Emely', '1999-07-08 09:46:01', 'Mr. Robin Roberts');
INSERT INTO `city` (`cityId`, `city`, `countryId`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (5, 'North Agustina', 5, '2010-11-02 11:22:22', 'Brendan', '1984-07-04 23:13:50', 'Jalyn Sanford');

INSERT INTO `address` (`addressId`, `address`, `address2`, `cityId`, `postalCode`, `phone`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (1, '92744 Boyle Tunnel North Name, NM 51164', 'Suite 249', 1, 'osvq', '1-637-539-1733x4775', '1987-08-06 13:17:27', 'Kristina', '1998-12-31 15:58:54', 'Barry');
INSERT INTO `address` (`addressId`, `address`, `address2`, `cityId`, `postalCode`, `phone`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (2, '51909 Jamal Heights Helenaland, DC 49581-7800', 'Suite 151', 2, 'ukou', '863.641.1959x955', '1975-06-09 09:15:23', 'Kenyatta', '1990-08-18 20:34:43', 'Antwon');
INSERT INTO `address` (`addressId`, `address`, `address2`, `cityId`, `postalCode`, `phone`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (3, '56131 Vernice River Padbergmouth, RI 60508-3645', 'Suite 613', 3, 'guag', '547-198-9520', '1979-10-17 05:54:54', 'Chaim', '1978-02-06 09:09:10', 'Abbie');
INSERT INTO `address` (`addressId`, `address`, `address2`, `cityId`, `postalCode`, `phone`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (4, '416 Jany Fields Lake Angelita, NY 45715', 'Apt. 868', 4, 'obki', '(237)419-6458', '2018-02-07 04:25:50', 'Cydney', '1970-01-30 09:58:24', 'Jevon');
INSERT INTO `address` (`addressId`, `address`, `address2`, `cityId`, `postalCode`, `phone`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (5, '572 Leuschke Tunnel Stellabury, AR 94456-7000', 'Apt. 278', 5, 'cdcu', '(637)954-7235x29638', '2000-11-07 02:42:01', 'Jana', '1987-12-10 22:59:38', 'Erwin');

INSERT INTO `customer` (`customerId`, `customerName`, `addressId`, `active`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (1, 'corporis', 1, 1, '1971-04-07 00:38:46', 'Maryam', '2008-02-06 07:54:09', 'Annalise');
INSERT INTO `customer` (`customerId`, `customerName`, `addressId`, `active`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (2, 'sed', 2, 0, '2012-01-31 15:13:10', 'Kenyon', '2007-09-04 10:55:23', 'Antone');
INSERT INTO `customer` (`customerId`, `customerName`, `addressId`, `active`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (3, 'nam', 3, 1, '2015-07-17 15:27:47', 'Destinee', '1973-04-23 09:24:22', 'German');
INSERT INTO `customer` (`customerId`, `customerName`, `addressId`, `active`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (4, 'exercitationem', 4, 0, '2006-10-20 03:42:37', 'Marian', '2004-12-19 21:55:48', 'Baby');
INSERT INTO `customer` (`customerId`, `customerName`, `addressId`, `active`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) VALUES (5, 'et', 5, 0, '1983-09-17 17:45:01', 'Cordelia', '2001-01-01 12:02:35', 'Susie');

ALTER TABLE address MODIFY addressId INTEGER NOT NULL AUTO_INCREMENT;
ALTER TABLE appointment MODIFY appointmentId INTEGER NOT NULL AUTO_INCREMENT;
ALTER TABLE city MODIFY cityId INTEGER NOT NULL AUTO_INCREMENT;
ALTER TABLE country MODIFY countryId INTEGER NOT NULL AUTO_INCREMENT;
ALTER TABLE customer MODIFY customerId INTEGER NOT NULL AUTO_INCREMENT;
ALTER TABLE user MODIFY userId INTEGER NOT NULL AUTO_INCREMENT;
