package de.metalcon.socialgraph.algorithms;

import org.json.simple.JSONObject;
import org.neo4j.graphdb.Node;

import de.metalcon.socialgraph.NeoUtils;
import de.metalcon.socialgraph.Properties;
import de.metalcon.socialgraph.SocialGraphRelationshipType;

/**
 * user used within first social networking approach's reading process
 * 
 * @author Sebastian Schlicht
 * 
 */
public class StatusUpdateUser {

	/**
	 * last recent status update node of the user
	 */
	private Node nextStatusUpdateNode;

	/**
	 * current status update's time stamp
	 */
	private long statusUpdateTimestamp;

	/**
	 * create a new user for the reading process
	 * 
	 * @param user
	 *            user node represented
	 */
	public StatusUpdateUser(final Node user) {
		this.nextStatusUpdateNode = user;

		this.nextStatusUpdate();
	}

	/**
	 * check if another status update is available
	 * 
	 * @return true - if another status update is available<br>
	 *         false otherwise
	 */
	public boolean hasStatusUpdate() {
		return (this.nextStatusUpdateNode != null);
	}

	/**
	 * load another status update<br>
	 * (will cause an exception if called twice while none available)
	 */
	private void nextStatusUpdate() {
		this.nextStatusUpdateNode = NeoUtils.getNextSingleNode(
				this.nextStatusUpdateNode, SocialGraphRelationshipType.UPDATE);

		// load status update time stamp if existing
		if (this.nextStatusUpdateNode != null) {
			this.statusUpdateTimestamp = (long) this.nextStatusUpdateNode
					.getProperty(Properties.StatusUpdate.TIMESTAMP);
		}
	}

	/**
	 * access the status update loaded<br>
	 * (calls will cause an exception if you did not successfully load one
	 * before)
	 * 
	 * @return status update loaded before
	 */
	public JSONObject getStatusUpdate() {
		final JSONObject statusUpdate = (JSONObject) this.nextStatusUpdateNode
				.getProperty(Properties.StatusUpdate.CONTENT);
		this.nextStatusUpdate();
		return statusUpdate;
	}

	/**
	 * access the time stamp of the status update loaded before<br>
	 * (calls will cause an exception if you did not successfully load one
	 * before)
	 * 
	 * @return time stamp of the status update loaded before
	 */
	public long getStatusUpdateTimestamp() {
		return this.statusUpdateTimestamp;
	}

}