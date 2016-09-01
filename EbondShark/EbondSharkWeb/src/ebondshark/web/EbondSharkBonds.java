/**
 * 
 */
package ebondshark.web;

import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ebondshark.ejb.EbondSharkBeanLocal;
import ebondshark.jpa.Bond;
import ebondshark.jpa.Trade;
import ebondshark.jpa.Trader;
import ebondshark.jpa.Tradesview;

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
			System.out.println(bean.hashCode());
		} catch (NamingException ex) {
		}

	}

	@GET
	@Produces("application/json")
	public List<Bond> getProducts(@QueryParam("filter") @DefaultValue("") String filter) {

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

		System.out.println(filter);
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

	@GET
	@Path("/alltradedetails")
	@Produces("application/json")
	public List<Tradesview> getTradeDetails() {

		if (bean == null)
			return null;
		return bean.getAllTrades();
	}

	@GET
	@Path("/tradertradedetails")
	@Produces("application/json")
	public List<Tradesview> getTraderTradeDetails(@QueryParam("Username") @DefaultValue("") String username) {

		if (bean == null)
			return null;
		return bean.getAllTradesByTrader(username);
	}

	@GET
	@Path("/traders")
	@Produces("application/json")
	public List<Trader> getTraders() {

		if (bean == null)
			return null;
		return bean.getAllTraders();

	}

	@GET
	@Path("/bonddetails")
	@Produces("application/json")
	public BondDetails getBondDetails(@QueryParam("ISIN") @DefaultValue("") String ISIN,
			@QueryParam("Qty") @DefaultValue("10") String Qty, @QueryParam("param") @DefaultValue("") String param,
			@QueryParam("value") @DefaultValue("") String value) {

		Bond bond = bean.getBondByISIN(ISIN);
		BondDetails bondDetails = new BondDetails();
		System.out.println(bondDetails.accruedAmount(bond));
		bondDetails.setISIN(ISIN);
		bondDetails.setQuantity(Integer.parseInt(Qty));
		if (param.equals("Yield")) {
			bondDetails.setDesiredYield(Double.parseDouble(value));
			bondDetails.setCleanPrice(bondDetails.calculateCleanPrice(bond));
			bondDetails.setDirtyPrice(bondDetails.accruedAmount(bond) + bondDetails.getCleanPrice());
		} else if (param.equals("CleanPrice")) {
			bondDetails.setCleanPrice(Double.parseDouble(value));
			bondDetails.setDesiredYield(bondDetails.getYield(bond));
			bondDetails.setDirtyPrice(bondDetails.getCleanPrice() + bondDetails.accruedAmount(bond));
		} else if (param.equals("DirtyPrice")) {
			bondDetails.setDirtyPrice(Double.parseDouble(value));
			bondDetails.setCleanPrice(bondDetails.getDirtyPrice() - bondDetails.accruedAmount(bond));
			bondDetails.setDesiredYield(bondDetails.getYield(bond));
		}
		bondDetails.setSettlementAmount(bondDetails.getFacevalue() * bondDetails.getQuantity());
		bondDetails.setAccruedAmount(bondDetails.accruedAmount(bond));
		System.out.println(bondDetails);
		return bondDetails;

	}

	@GET
	@Path("/login")
	@Produces("text/plain")
	public String getUserLogin(@QueryParam("User") @DefaultValue("") String user,
			@QueryParam("password") @DefaultValue("") String password) {

		if (bean == null)
			return null;
		String output = bean.login(user, password);
		/*
		 * if (output.equals("Succesful Login")) return
		 * Response.status(200).entity(output).build(); else if (output.equals(
		 * "Trader does not exist")) return
		 * Response.status(404).entity(output).build(); else if (output.equals(
		 * "Wrong password entered")) return
		 * Response.status(401).entity(output).build();
		 */
		return output;

	}

	@GET
	@Path("/tradePlaced")
	@Produces("text/plain")
	public String setTradePlaced1(@QueryParam("Username") String username, @QueryParam("ISIN") String ISIN,
			@QueryParam("year") String year, @QueryParam("month") String month, @QueryParam("day") String day,
			@QueryParam("hour") String hour, @QueryParam("minutes") String minutes,
			@QueryParam("seconds") String seconds, @QueryParam("buySell") String buySell,
			@QueryParam("price") String price, @QueryParam("Qty") String Qty) {

		if (bean == null)
			return null;

		bean.saveTrade(username, ISIN, year, month, day, hour, minutes, seconds, buySell, price, Qty);
		return Qty;

	}

	@GET
	@Path("/Register")
	@Produces("text/plain")
	public String setTradePlaced1(@QueryParam("Username") String username, @QueryParam("Password") String password,
			@QueryParam("TraderName") String traderName, @QueryParam("Age") String age, @QueryParam("Sex") String Sex,
			@QueryParam("Address") String address, @QueryParam("PhoneNo") String phoneNo,
			@QueryParam("CreditRating") String creditRating) {

		if (bean == null)
			return null;

		return bean.register(username, password, traderName, age, Sex, address, phoneNo, creditRating);

	}

}
