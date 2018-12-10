package com.revolut.moneytransfer;
import static spark.Spark.internalServerError;

import com.revolut.moneytransfer.controller.AccountController;
import com.revolut.moneytransfer.controller.ClientController;
import com.revolut.moneytransfer.controller.TransferController;

/**
 * this application creates a Rest Api to transfer
 * money between accounts.
 * @author  Diego Umaña
 */
public class Main {


	private static ClientController clientController;
	private static AccountController accountController;
	private static TransferController transferController;

	/**
	 * Main method of the application
	 * @param args
	 */
	public static void main(String[] args) {
		
		internalServerError((req, res) -> {
		    res.type("application/json");
		    res.status(500);
		    return "{\"message\":\"Internal Error, the operation couldn't be executed\"}";
		});
		
		init();
	}

	/**
	 * this method initialize the controllers that will manage the requests
	 */
	private static void init() {
		setClientController(new ClientController());
		setAccountController(new AccountController());
		setTransferController(new TransferController());
	}

	/**
	 * @return the clientController
	 */
	public static ClientController getClientController() {
		return clientController;
	}

	/**
	 * @param clientController the clientController to set
	 */
	public static void setClientController(ClientController clientController) {
		Main.clientController = clientController;
	}

	/**
	 * @return the accountController
	 */
	public static AccountController getAccountController() {
		return accountController;
	}

	/**
	 * @param accountController the accountController to set
	 */
	public static void setAccountController(AccountController accountController) {
		Main.accountController = accountController;
	}

	/**
	 * @return the transferController
	 */
	public static TransferController getTransferController() {
		return transferController;
	}

	/**
	 * @param transferController the transferController to set
	 */
	public static void setTransferController(TransferController transferController) {
		Main.transferController = transferController;
	}

}
