package de.metalcon.server.tomcat.NSSP.delete;

import de.metalcon.server.tomcat.NSSProtocol;

/**
 * response to delete user requests according to NSSP
 * 
 * @author sebschlicht
 * 
 */
public class DeleteUserResponse extends DeleteResponse {

	/**
	 * add status message: user identifier missing
	 */
	public void userIdentifierMissing() {
		this.parameterMissing(
				NSSProtocol.Parameters.Delete.User.USER_IDENTIFIER,
				"Please provide a user identifier matching to an existing user.");
	}

	/**
	 * add status message: user identifier invalid
	 * 
	 * @param userId
	 *            user identifier passed
	 */
	public void userIdentifierInvalid(final String userId) {
		this.addStatusMessage(
				NSSProtocol.StatusCodes.Delete.User.USER_NOT_EXISTING,
				"there is no user with the identifier \"" + userId
						+ "\". Please provide a valid user identifier.");
	}

}