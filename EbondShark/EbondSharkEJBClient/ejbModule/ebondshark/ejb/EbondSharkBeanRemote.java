package ebondshark.ejb;

import java.util.List;
import javax.ejb.Remote;
import ebondshark.jpa.Bond;

@Remote
public interface EbondSharkBeanRemote {
	List<Bond> getAllBonds();
	List<Bond> getBondsByCategory(String category);
	List<Bond> getBondsByISIN(String isin);
	List<Bond> getBondsByField(String field);
	List<Bond> getBondsByCurrency(String currency);
	List<Bond> getBondsByYieldRange1();
	List<Bond> getBondsByYieldRange2();
	List<Bond> getBondsByYieldRange3();
}
