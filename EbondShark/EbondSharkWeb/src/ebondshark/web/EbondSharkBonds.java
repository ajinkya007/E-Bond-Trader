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
import ebondshark.jpa.Bond;

/**
 * @author Grad94
 *
 */
@RequestScoped
@Path("/ebonds")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class EbondSharkBonds {

	private EbondSharkBeanLocal bean;

	public EbondSharkBonds() {
		try {
			InitialContext context = new InitialContext();
			bean = (EbondSharkBeanLocal) context
					.lookup("java:app/EbondSharkEJB/EbondSharkBean!ebondshark.ejb.EbondSharkBeanLocal");
		} catch (NamingException ex) {
		}

	}

	@GET
	@Produces("application/json")
	public List<Bond> getProducts() {

		if (bean == null)
			return null;

		return bean.getAllBonds();

	}

	@GET
	@Produces("application/json")
	public List<Bond> getProductsByISIN(@QueryParam("ISIN") @DefaultValue("") String filter) {

		if (bean == null)
			return null;

		System.out.println(filter);
		return bean.getBondsByISIN(filter);
	}

	@GET
	@Produces("application/json")
	public List<Bond> getProductsByField(@QueryParam("Field") @DefaultValue("") String filter) {

		if (bean == null)
			return null;

		System.out.println(filter);
		return bean.getBondsByField(filter);
	}

	@GET
	@Produces("application/json")
	public List<Bond> getProductsByCurrency(@QueryParam("Currency") @DefaultValue("") String filter) {

		if (bean == null)
			return null;

		System.out.println(filter);
		return bean.getBondsByCurrency(filter);
	}

	@GET
	@Produces("application/json")
	public List<Bond> getProductsByCategory(@QueryParam("Category") @DefaultValue("") String filter) {

		if (bean == null)
			return null;

		System.out.println(filter);
		return bean.getBondsByCategory(filter);
	}

	@GET
	@Produces("application/json")
	public List<Bond> getProductsByFrequency(@QueryParam("Frequency") @DefaultValue("") String filter) {

		if (bean == null)
			return null;

		System.out.println(filter + "Hello");
		return bean.getBondsByFrequency(Integer.parseInt(filter));
	}

	@GET
	@Produces("application/json")
	public List<Bond> getProductsByYield(@QueryParam("Yield") @DefaultValue("") String filter) {

		if (bean == null)
			return null;

		System.out.println(filter);
		return bean.getBondsByYieldRange(Integer.parseInt(filter) % 10, Integer.parseInt(filter) / 100);
	}

}
