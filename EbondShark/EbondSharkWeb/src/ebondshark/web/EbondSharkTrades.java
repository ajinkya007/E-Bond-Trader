/**
 * 
 */
package ebondshark.web;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ebondshark.ejb.EbondSharkBeanLocal;
import ebondshark.jpa.Trade;

/**
 * @author Grad94
 *
 */
@RequestScoped
@Path("/tradedetails")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class EbondSharkTrades {

private EbondSharkBeanLocal bean; 
	
	public EbondSharkTrades(){
		try {
        	InitialContext context = new InitialContext();
            bean = (EbondSharkBeanLocal) context.lookup("java:app/EbondSharkEJB/EbondSharkBean!ebondshark.ejb.EbondSharkBeanLocal");
        }
		catch (NamingException ex) {}

	}
	
	@GET
	@Produces("application/json")
	public List<Trade> getTrades(@QueryParam("filter") @DefaultValue("") String filter) {

		if (bean == null) 
			return null;
		
		if (filter.length() == 0) {
			return bean.getAllTrades();
		}
		else return bean.getAllTrades();
	}


}
