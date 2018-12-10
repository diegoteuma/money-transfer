package com.revolut.moneytransfer.controller;

import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;

import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.service.AccountService;

/**
 * this will create the routes and manage the request for CRUD accounts
 * @author Diego Umaña
 *
 */
public class AccountController extends AbstractController {

	private AccountService accountService;

	/**
	 * Constructor that will initialize the routes
	 */
	public AccountController() {
		this.setAccountService(new AccountService());
		this.initRoutes();
	}

	@Override
	protected void initRoutes() {
		/**
		 * method to persist an account
		 */
		post("/accounts", (request, response) -> {
			response.type("application/json");
			Account account = mapper.readValue(request.body(), Account.class);
			Account accountSaved = this.getAccountService().create(account);
			return accountSaved;
		}, mapper::writeValueAsString);

		/**
		 * method to list the accounts
		 */
		get("/accounts", (req, res) -> {
			res.type("application/json");
			return this.getAccountService().findAll();
		}, mapper::writeValueAsString);

		/**
		 * method to find an account by id
		 */
		get("/accounts/:id", (request, response) -> {
			response.type("application/json");
			return this.getAccountService().findById(Long.valueOf(request.params(":id")));
		}, mapper::writeValueAsString);

		/**
		 * method to udpate an account
		 */
		put("/accounts/:id", (request, response) -> {
			response.type("application/json");
			Account toEdit = mapper.readValue(request.body(), Account.class);
			Account editedAccount = this.getAccountService().update(toEdit);
			return editedAccount;
		}, mapper::writeValueAsString);

		/**
		 * method to know if an account exists
		 */
		options("/accounts/:id", (request, response) -> {
			response.type("application/json");
			Account findById = this.getAccountService().findById(Long.valueOf(request.params(":id")));
			String message = findById != null ? "Account exists" : "Account does not exists";
			return "{\"message\": \"" + message + "\"}";
		});

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
