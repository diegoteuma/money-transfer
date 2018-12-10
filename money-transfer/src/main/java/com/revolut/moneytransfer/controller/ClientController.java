package com.revolut.moneytransfer.controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.Optional;

import com.revolut.moneytransfer.model.Account;
import com.revolut.moneytransfer.model.Client;
import com.revolut.moneytransfer.service.ClientService;
import com.revolut.moneytransfer.service.TransferService;

/**
 * this will create the routes and manage the request for CRUD clients
 * @author Diego Umaña
 *
 */
public class ClientController extends AbstractController {

	private ClientService clientService;
	private TransferService transferService;

	/**
	 * Constructor that will initialize the routes
	 */
	public ClientController() {
		this.setClientService(new ClientService());
		this.setTransferService(new TransferService());
		this.initRoutes();
	}

	@Override
	protected void initRoutes() {

		/**
		 * method to persist a client
		 */
		post("/clients", (request, response) -> {
			response.type("application/json");

			Client client = mapper.readValue(request.body(), Client.class);
			Client clientSaved = this.getClientService().create(client);
			return clientSaved;
		}, mapper::writeValueAsString);

		/**
		 * method to list clients
		 */
		get("/clients", (req, res) -> {
			res.type("application/json");
			return this.getClientService().findAll();
		}, mapper::writeValueAsString);

		/**
		 * method to find a client by id
		 */
		get("/clients/:id", (request, response) -> {
			response.type("application/json");
			Client client = this.getClientService().findById(Long.valueOf(request.params(":id")));
			if (client == null) {
				response.status(404); // 404 Not found
				return "{\"message\": \"Client not found \"}";
			}
			return client;

		}, mapper::writeValueAsString);

		/**
		 * method to list all transfers given a client id and a account id
		 */
		get("/clients/:clientId/accounts/:accountId/transfers", (request, response) -> {
			response.type("application/json");

			Client client = this.getClientService().findById(Long.valueOf(request.params(":clientId")));

			Optional<Account> account = client.getAccounts().stream()
					.filter(a -> a.getId() == Long.valueOf(request.params(":accountId"))).findFirst();

			return this.getTransferService().findByAccountId(account.get().getId());

		}, mapper::writeValueAsString);
		
		/**
		 * method to update a client
		 */
		put("/clients/:id", (request, response) -> {
			response.type("application/json");
			Client toEdit = mapper.readValue(request.body(), Client.class);
			Client editedUser = this.getClientService().update(toEdit);
			return editedUser;
		}, mapper::writeValueAsString);

		/**
		 * method to delete a client by id, it checks that the client have no accouts with money
		 */
		delete("/clients/:id", (request, response) -> {
			response.type("application/json");
			Client client = this.getClientService().findById(Long.valueOf(request.params(":id")));
			if (	(client.getAccounts()==null) ||
					(client.getAccounts().size() == 0) || 
					(client.getAccounts().stream().filter(a -> a.getBalance() > 0).count() == 0)) {
				this.getClientService().delete(client);
				return "{\"message\": \"Client Deleted \"}";
			} else {
				return "{\"message\": \"Client can not be Deleted, accounts active\"}";
			}
		});

		/**
		 * method to know if a client exists
		 */
		options("/clients/:id", (request, response) -> {
			response.type("application/json");
			Client client = this.getClientService().findById(Long.valueOf(request.params(":id")));
			String message = client != null ? "Client exists" : "Client does not exists";
			return "{\"message\": \"" + message + "\"}";
		});

	}

	/**
	 * @return the clientService
	 */
	public ClientService getClientService() {
		return clientService;
	}

	/**
	 * @param clientService the clientService to set
	 */
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
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

}
