# money-transfer
Rest Api to transfer money, spark and hibernate frameworks used.

* import.sql init some defaults data as follows

      INSERT INTO clients(idCard,firstName,lastName, email) VALUES (12345678,'Diego', 'Umana','diego@mail.com');

      INSERT INTO accounts(accountNumber,balance,client_id) VALUES ('5678901-0 012-3',5000,1);

* The database used is H2, the configuration can be found in persistence.xml as follows
	    
      <property name="javax.persistence.jdbc.url" value="jdbc:h2:./db/moneytransfer" />
			<property name="javax.persistence.jdbc.user" value="user" />
			<property name="javax.persistence.jdbc.password" value="" />
			<property name="javax.persistence.jdbc.driver" value="org.h2.Driver" />
      
* For money transfer the amouts are manage as long, asumming that the unit is cents, avoiding convertions

* Test were made using SoapUI 
    https://www.soapui.org/downloads/download-soapui-pro-trial.html
    
    project file: moneyTransfer-readyapi-project.xml





