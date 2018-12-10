package com.revolut.moneytransfer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Abstract controller to control requests
 * @author Diego Umaña
 *
 */
public abstract class AbstractController {

	protected ObjectMapper mapper = new ObjectMapper();

	/**
	 * this method will create the routes for the possible rest requests.
	 */
	protected abstract void initRoutes();

}
