CREATE TABLE IF NOT EXISTS `CUSTOMER_DATA` (
  `REF` varchar(16)  PRIMARY KEY,
  `NAME` varchar(100),
  `ADDRLINE1` varchar(100),
  `ADDRLINE2` varchar(100),
  `TOWN` varchar(64),
  `COUNTY` varchar(64),
  `COUNTRY` varchar(48),
  `POSTCODE` varchar(16)
);