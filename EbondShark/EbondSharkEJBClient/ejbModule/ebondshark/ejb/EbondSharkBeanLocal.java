package ebondshark.ejb;

import javax.ejb.Local;
import ebondshark.jpa.*;
import java.util.List;

@Local
public interface EbondSharkBeanLocal {
	public List<Bond> getAllBonds();
	public List<Bond> getBondsByCategory(String category);
	public List<Bond> getBondsByISIN(String isin);
	public List<Bond> getBondsByField(String field);
	public List<Bond> getBondsByCurrency(String currency);
	public List<Bond> getBondsByFrequency(int frequency);
	public List<Bond> getBondsByYieldRange(int min, int max);
	public int getYearsToMaturity();
	public String login(String username, String password);
	public String register(Trader tObj);
	public int yearsToMaturity(String ISIN);

}
