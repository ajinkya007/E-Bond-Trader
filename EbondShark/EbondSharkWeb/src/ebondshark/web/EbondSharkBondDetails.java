/**
 * 
 */
package ebondshark.web;

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
@Path("/bonddetails")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class EbondSharkBondDetails {

	private EbondSharkBeanLocal bean;

	public EbondSharkBondDetails() {
		try {
			InitialContext context = new InitialContext();
			bean = (EbondSharkBeanLocal) context
					.lookup("java:app/EbondSharkEJB/EbondSharkBean!ebondshark.ejb.EbondSharkBeanLocal");
		} catch (NamingException ex) {
		}
	}

	@GET
	@Produces("application/json")
	public BondDetails getBondDetails(@QueryParam("ISIN") @DefaultValue("") String ISIN,
			@QueryParam("Qty") @DefaultValue("10") String Qty, @QueryParam("param") @DefaultValue("") String param,
			@QueryParam("value") @DefaultValue("") String value) {

		Bond bond = bean.getBondByISIN(ISIN);
		
		BondDetails bondDetails = new BondDetails();
		bondDetails.accruedAmount(bond);
		bondDetails.setISIN(ISIN);
		bondDetails.setQuantity(Integer.parseInt(Qty));
		if(param.equals("Yield")){
		    bondDetails.setDesiredYield(Double.parseDouble(value));
			bondDetails.setCleanPrice(bondDetails.calculateCleanPrice(bond));
		    bondDetails.setDirtyPrice(bondDetails.accruedAmount(bond)+bondDetails.getCleanPrice());
		}
		else if(param.equals("CleanPrice")){
			bondDetails.setCleanPrice(Double.parseDouble(value));
			bondDetails.setDesiredYield(bondDetails.getYield(bond));
			bondDetails.setDirtyPrice(bondDetails.getCleanPrice()+bondDetails.accruedAmount(bond));
		}else if(param.equals("DirtyPrice")){
			bondDetails.setDirtyPrice(Double.parseDouble(value));
			bondDetails.setCleanPrice(bondDetails.getDirtyPrice()-bondDetails.accruedAmount(bond));
			bondDetails.setDesiredYield(bondDetails.getYield(bond));
		}
		return bondDetails;

	}

}
