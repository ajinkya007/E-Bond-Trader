package ebondshark.ejb;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import ebondshark.jpa.Bond;
import ebondshark.jpa.Trade;
import ebondshark.jpa.Trader;
import ebondshark.jpa.Tradesview;

/**
 * Session Bean implementation class EbondSharkBean
 */
@Stateful
@Remote(EbondSharkBeanRemote.class)
@Local(EbondSharkBeanLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EbondSharkBean implements EbondSharkBeanRemote, EbondSharkBeanLocal {

	@PersistenceContext(name = "EbondSharkJPA", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	private String username;
	private String password;

	/**
	 * Default constructor.
	 */

	// Execute the query, and get all the bonds back.
	@Override
	public List<Bond> getAllBonds() {
		TypedQuery<Bond> query = em.createQuery("SELECT p FROM Bond AS p", Bond.class);
		List<Bond> bonds = query.getResultList();
		System.out.println(bonds);
		return bonds;
	}

	@Override
	public List<Bond> getBondsByCategory(String category) {
		String sql = "SELECT p FROM Bond AS p WHERE p.category LIKE '%" + category + "%'";
		TypedQuery<Bond> query = em.createQuery(sql, Bond.class);

		// Execute the query, and get a collection of products back.
		List<Bond> bonds = query.getResultList();
		System.out.println(bonds);
		return bonds;
	}

	@Override
	public List<Bond> getBondsByISIN(String isin) {

		String sql = "SELECT p FROM Bond AS p WHERE p.isin LIKE '%" + isin + "%'";
		TypedQuery<Bond> query = em.createQuery(sql, Bond.class);
		List<Bond> bondsByIsin = query.getResultList();
		// System.out.println(bondsByIsin);
		return bondsByIsin;
	}

	@Override
	public Bond getBondByISIN(String isin) {

		TypedQuery<Bond> query = em.createQuery("SELECT p FROM Bond as p WHERE p.isin = :ISIN", Bond.class);
		query.setParameter("ISIN", isin);
		// System.out.println(isin);
		Bond bond = query.getSingleResult();
		System.out.println("Bond by ISIN: " + bond);
		return bond;
	}

	@Override
	public List<Bond> getBondsByField(String field) {

		String sql = "SELECT p FROM Bond AS p WHERE p.field = :field";
		TypedQuery<Bond> query = em.createQuery(sql, Bond.class);
		query.setParameter("field", field);
		List<Bond> bondsByField = query.getResultList();
		System.out.println(bondsByField);
		return bondsByField;
	}

	@Override
	public List<Bond> getBondsByCurrency(String currency) {

		String sql = "SELECT p FROM Bond AS p WHERE p.currency = :currency";
		TypedQuery<Bond> query = em.createQuery(sql, Bond.class);
		query.setParameter("currency", currency);
		List<Bond> bondsByCurrency = query.getResultList();
		System.out.println(bondsByCurrency);
		return bondsByCurrency;
	}

	@Override
	public List<Bond> getBondsByFrequency(int frequency) {

		TypedQuery<Bond> sql = em.createQuery("SELECT p FROM Bond AS p WHERE p.frequency = :freq", Bond.class);
		sql.setParameter("freq", frequency);
		List<Bond> bondsByFrequency = sql.getResultList();
		System.out.println(bondsByFrequency);
		return bondsByFrequency;
	}

	@Override
	public List<Bond> getBondsByYieldRange(int min, int max) {
		TypedQuery<Bond> sql = em.createQuery("SELECT p FROM Bond WHERE p.yield BETWEEN :min AND :max", Bond.class);
		sql.setParameter("min", min);
		sql.setParameter("max", max);
		List<Bond> bondsByYieldRange = sql.getResultList();
		System.out.println(bondsByYieldRange);
		return bondsByYieldRange;
	}

	@Override
	public String login(String username, String password) {
		TypedQuery<Trader> login = em.createQuery("SELECT p FROM Trader p WHERE p.trader_id = :user", Trader.class);
		login.setParameter("user", username);
		List<Trader> result = login.getResultList();
		if (result == null) {
			return "Trader does not exist";
		} else if (password.compareTo(result.get(0).getPassword()) == 0) {
			setUsername(username);
			setPassword(password);
			System.out.println(getUsername() + " " + getPassword());
			return "Successful Login";
		}
		return "Wrong password entered";
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	@Override
	public String register(Trader tObj) {
		TypedQuery<Trader> check = em.createQuery("SELECT p FROM Trader WHERE p.trader_id = :user", Trader.class);
		check.setParameter("user", tObj.getTrader_id());
		if (check.getResultList() != null) {
			return "Trader with " + tObj.getTraderName() + "already exists";
		}

		TypedQuery<Trader> register = em.createQuery(
				"Insert into trader values(:id, :name, :password, :age, :sex, :address, :phonenumber, :crating)",
				Trader.class);
		register.setParameter("id", tObj.getTrader_id());
		register.setParameter("name", tObj.getTraderName());
		register.setParameter("password", tObj.getPassword());
		register.setParameter("age", tObj.getAge());
		register.setParameter("sex", tObj.getSex());
		register.setParameter("address", tObj.getAddress());
		register.setParameter("phonenumber", tObj.getPhoneNum());
		register.setParameter("crating", tObj.getCreditRating());
		if (register.executeUpdate() == 0) {
			return "Registration successful";
		} else {
			return "Encountered an error while registration";
		}
	}

	@Override
	public List<Tradesview> getAllTrades() {
		// TODO Auto-generated method stub
		TypedQuery<Tradesview> query = em.createQuery("SELECT p FROM Tradesview AS p", Tradesview.class);
		// Query query = em.createNativeQuery("SELECT *, b.isin FROM Trades p
		// ,Bonds b where p.isin = b.isin",
		// Trade.class);
		List<Tradesview> trades = query.getResultList();
		System.out.println(trades);
		return trades;
	}

	@Override
	public List<Tradesview> getAllTradesByTrader(String username) {
		// TODO Auto-generated method stub
		TypedQuery<Tradesview> query = em.createQuery("SELECT p FROM Tradesview AS p where p.trader_id = :user",
				Tradesview.class);
		// Query query = em.createNativeQuery("SELECT *, b.isin FROM Trades p
		// ,Bonds b where p.isin = b.isin",
		// Trade.class);
		query.setParameter("user", username);
		List<Tradesview> trades = query.getResultList();
		System.out.println(trades);
		return trades;
	}

	@Override
	public List<Trader> getAllTraders() {
		// TODO Auto-generated method stub
		TypedQuery<Trader> query = em.createQuery("SELECT p FROM Trader AS p ", Trader.class);
		List<Trader> traders = query.getResultList();
		System.out.println(traders);
		return traders;
	}

	@Override
	public Trader getTraderbyTraderName(String username) {
		TypedQuery<Trader> query = em.createQuery("SELECT p FROM Trader AS p where p.trader_id = :user", Trader.class);
		query.setParameter("user", username);
		Trader trader = query.getSingleResult();
		System.out.println("Ajinkya");
		System.out.println(trader);
		return trader;
	}

	@Override
	public void saveTrade(String username, String ISIN, String year, String month, String day, String hour,
			String minutes, String seconds, String buySell, String price, String Qty) {
		// TODO Auto-generated method stub

		// List<Tradesview> list = bean.getAllTrades();
		Trade placedTrade = new Trade();
		// placedTrade.setTrade_id(list.size() + 1);
		Bond bond = this.getBondByISIN(ISIN);
		Trader trader = this.getTraderbyTraderName(username);
		placedTrade.setBond(bond);
		placedTrade.setTrader(trader);
		bond.addTrade(placedTrade);
		/*
		 * bond.getTrades().add(placedTrade);
		 * trader.getTrades().add(placedTrade);
		 */
		placedTrade.setNoOfBonds(Integer.parseInt(Qty));
		placedTrade.setBuySell(buySell);
		placedTrade.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
		placedTrade.setTradeStatus("processed");
		placedTrade.setDay(Integer.parseInt(day));
		placedTrade.setMonth(Integer.parseInt(month));
		placedTrade.setYear(Integer.parseInt(year));
		placedTrade.setHour(Integer.parseInt(hour));
		placedTrade.setMinutes(Integer.parseInt(minutes));
		placedTrade.setSeconds(Integer.parseInt(seconds));
		em.persist(placedTrade);
		System.out.println("Trade done successfully.");

		// Update the bondprice
		// Bond tbond = this.getBondByISIN(ISIN);
		// tbond.addTrade(placedTrade);
		// tbond.getTrades().add(placedTrade);
		// placedTrade.setBond(tbond);
		// tbond.setLast(BigDecimal.valueOf(Double.parseDouble(price)));
		/*
		 * if (tbond.getLast().compareTo(tbond.getHigh()) == 1)
		 * tbond.setHigh(placedTrade.getPrice()); else if
		 * (tbond.getLast().compareTo(tbond.getLow()) == -1)
		 * tbond.setLow(placedTrade.getPrice()); em.persist(tbond);
		 */

		Query query = em.createQuery("Update Bond  as b SET  b.last = :last where b.isin = :isin");
		query.setParameter("last", placedTrade.getPrice());
		query.setParameter("isin", ISIN);
		int count = query.executeUpdate();
		if (count > 0)
			System.out.println("Bond details merged.");
		else
			System.out.println("Err");

		/*Double lastIni = bond.getLast().doubleValue();
		*/BigDecimal last = placedTrade.getPrice();
		BigDecimal low = bond.getLow().compareTo(last) == 1 ? last : bond.getLow();
		BigDecimal high = bond.getHigh().compareTo(last) == -1 ? last : bond.getHigh();

		query = em.createQuery("Update Bond  as b SET  b.low = :low where b.isin = :isin");
		query.setParameter("low", low);
		query.setParameter("isin", ISIN);
		count = query.executeUpdate();
		
		/*query = em.createQuery("Update Bond  as b SET  b.change = :change where b.isin = :isin");
		query.setParameter("change", BigDecimal.valueOf((last.doubleValue() - lastIni) * 100.0 / lastIni));
		query.setParameter("isin", ISIN);
		count = query.executeUpdate();
*/
		query = em.createQuery("Update Bond  as b SET  b.high = :high where b.isin = :isin");
		query.setParameter("high", high);
		query.setParameter("isin", ISIN);
		count = query.executeUpdate();

	}

	@Override
	public String register(String username, String password, String traderName, String age, String sex, String address,
			String phoneNo, String creditRating) {
		// TODO Auto-generated method stub
		/*Query query = em.createQuery(
				"INSERT INTO Trader VALUES (' "
						+ username + "',' " + password + "',' " + traderName + "','" + age + "','" + sex + "','"
						+ address + "','" + phoneNo + "','" + creditRating + "')");
		*//*
		 * query.setParameter("username", username);
		 * query.setParameter("password", password);
		 * query.setParameter("traderName", traderName);
		 * query.setParameter("age", age); query.setParameter("sex", sex);
		 * query.setParameter("address", address); query.setParameter("phoneNo",
		 * phoneNo); query.setParameter("creditRating", creditRating);
		 */
		em.createNativeQuery("INSERT INTO Trader VALUES (' "
						+ username + "',' " + password + "',' " + traderName + "','" + age + "','" + sex + "','"
						+ address + "','" + phoneNo + "','" + creditRating + "')");
		int res = em.createNativeQuery("INSERT INTO Trader VALUES (' "
				+ username + "',' " + password + "',' " + traderName + "','" + age + "','" + sex + "','"
				+ address + "','" + phoneNo + "','" + creditRating + "')").executeUpdate();
		//query.executeUpdate();
		if (res > 0)
			return "User created successfully.";
		else
			return "User not created.";
	}

}