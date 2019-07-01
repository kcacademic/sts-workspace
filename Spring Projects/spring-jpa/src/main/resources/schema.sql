CREATE TABLE customer(  
   customerId INT NOT NULL AUTO_INCREMENT,  
   firstName VARCHAR(100) NOT NULL,  
   lastName VARCHAR(100) NOT NULL,  
   PRIMARY KEY ( customerId )  
); 

CREATE TABLE purchase(  
   orderId INT NOT NULL AUTO_INCREMENT,  
   address VARCHAR(100) NOT NULL,  
   payment VARCHAR(100) NOT NULL,  
   PRIMARY KEY ( orderId )  
); 