package de.metalcon.server.tomcat;

import java.util.Queue;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.neo4j.kernel.AbstractGraphDatabase;

import de.metalcon.server.Server;
import de.metalcon.socialgraph.operations.SocialGraphOperation;

/**
 * basic Tomcat HTTP serlevt for requests to the server
 * 
 * @author sebschlicht
 * 
 */
public abstract class GraphityHttpServlet extends HttpServlet {

	/**
	 * serialization information
	 */
	private static final long serialVersionUID = 591330391893402568L;

	/**
	 * graph database to <b>read</b>
	 */
	protected AbstractGraphDatabase graphDB;

	/**
	 * command queue to stack commands created
	 */
	protected Queue<SocialGraphOperation> commandQueue;

	@Override
	public void init(final ServletConfig config) throws ServletException {
		super.init(config);
		final ServletContext context = this.getServletContext();
		final Server server = (Server) context.getAttribute("server");
		this.graphDB = server.getGraphDatabase();
		this.commandQueue = server.getCommandQueue();
	}

}