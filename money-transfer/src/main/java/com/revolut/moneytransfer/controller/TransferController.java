package com.revolut.moneytransfer.controller;

import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;

import com.revolut.moneytransfer.model.Transfer;
import com.revolut.moneytransfer.service.AccountService;
import com.revolut.moneytransfer.service.TransferService;

/**
 * this will create the transfers
 * @author Diego Umaña
 *
 */
public class TransferController extends AbstractController {

	private TransferService transferService;
	private AccountService accountService;

	/**
	 * Constructor that will initialize the routes
	 */
	public TransferController() {
		this.setTransferService(new TransferService());
		this.setAccountService(new AccountService());
		this.initRoutes();
	}

	@Override
	protected void initRoutes() {
		
		/**
		 * method to create a transfer, it checks that the amount has value
		 * bigger than 0 and also if the origin account have enough money
		 */
		post("/transfers", (request, response) -> {
			response.type("application/json");
			Transfer transfer = mapper.readValue(request.body(), Transfer.class);
			if (transfer.getAmount() > 0) {
				if (transfer.getOriginAccount().getBalance() > transfer.getAmount()) {
				    this.getTransferService().create(transfer);
					return transfer;
				} else {
					return "{\"message\": \"No enought money in account\"}";
					
				}
			} else {
				return "{\"message\": \"Check the amount\"}";
			}

		}, mapper::writeValueAsString);

		/**
		 * method to list tranfers
		 */
		get("/transfers", (req, res) -> {
			res.type("application/json");
			return this.getTransferService().findAll();
		}, mapper::writeValueAsString);
		
		/**
		 * method to know if a transfer exists
		 */
		options("/transfers/:id", (request, response) -> {
            response.type("application/json");
            Transfer transfer = this.getTransferService().findById(Long.valueOf(request.params(":id")));
            String message= transfer!=null ? "Transfer exists" : "Transfer does not exists";
            return "{\"message\": \""+ message + "\"}";
        });

	}

	/**
	 * @return the transferService
	 */
	public TransferService getTransferService() {
		return transferService;
	}

	/**
	 * @param transferService the transferService to set
	 */
	public void setTransferService(TransferService transferService) {
		this.transferService = transferService;
	}

	/**
	 * @return the accountService
	 */
	public AccountService getAccountService() {
		return accountService;
	}

	/**
	 * @param accountService the accountService to set
	 */
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
