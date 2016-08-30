package ebondshark.ejb;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ebondshark.jpa.Bond;
import ebondshark.jpa.Trade;
import ebondshark.jpa.Trader;

/**
 * Session Bean implementation class EbondSharkBean
 */
@Stateful
@Remote(EbondSharkBeanRemote.class)
@Local(EbondSharkBeanLocal.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class EbondSharkBean implements EbondSharkBeanRemote, EbondSharkBeanLocal {

	@PersistenceContext(name = "EbondSharkJPA")
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
		System.out.println(bondsByIsin);
		return bondsByIsin;
	}

	@Override
	public List<Bond> getBondsByField(String field) {

		String sql = "SELECT p FROM Bond AS p WHERE p.field = :field";
		TypedQuery<Bond> query = em.createQuery(sql, Bond.class);
		query.setParameter("field",field);
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
		TypedQuery<Trader> login = em.createQuery("SELECT p FROM Trader WHERE p.trader_id like :user", Trader.class);
		login.setParameter("user", username);
		List<Trader> result = login.getResultList();
		if (result == null) {
			return "Trader does not exist";
		} else if (password.compareTo(result.get(0).getPassword()) == 0) {
			setUsername(username);
			setPassword(password);
			return "Succesful Login";
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
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String register(Trader tObj) {
		TypedQuery<Trader> check = em.createQuery("SELECT p FROM Trader WHERE p.trader_id like :user", Trader.class);
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
	public int yearsToMaturity(String ISIN) {
		TypedQuery<Bond> bond = em.createQuery("SELECT p.maturity_year FROM Bond WHERE p.ISIN like :ISIN", Bond.class);
		bond.setParameter("ISIN", ISIN);
		Bond bondResult = bond.getSingleResult();
		int maturityYear = bondResult.getMaturityYear();
		int presentDate = Calendar.getInstance().get(Calendar.YEAR);
		return (maturityYear - presentDate);
	}

	@Override
	public List<Trade>  getAllTrades() {
		// TODO Auto-generated method stub
		TypedQuery<Trade> query = em.createQuery("SELECT p FROM Trade AS p", Trade.class);
		List<Trade> trades = query.getResultList();
		System.out.println(trades);
		return trades;
	}

}