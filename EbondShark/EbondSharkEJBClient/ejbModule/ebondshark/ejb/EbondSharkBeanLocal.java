package ebondshark.ejb;

import java.util.List;

import javax.ejb.Local;

import ebondshark.jpa.Bond;
import ebondshark.jpa.Trader;
import ebondshark.jpa.Tradesview;

@Local
public interface EbondSharkBeanLocal {
	public List<Bond> getAllBonds();

	public List<Bond> getBondsByCategory(String category);

	public List<Bond> getBondsByISIN(String isin);

	public List<Bond> getBondsByField(String field);

	public List<Bond> getBondsByCurrency(String currency);

	public List<Bond> getBondsByFrequency(int frequency);

	public List<Bond> getBondsByYieldRange(int min, int max);

	public String login(String uername, String password);

	public String register(Trader tObj);

	public List<Tradesview> getAllTrades();

	public List<Trader> getAllTraders();

	public Bond getBondByISIN(String isin);

	public List<Tradesview> getAllTradesByTrader();
	
	public Trader getTraderbyTraderName();


}