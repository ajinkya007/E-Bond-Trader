package ebondshark.ejb;

import javax.ejb.Local;
import ebondshark.jpa.*;
import java.util.List;

@Local
public interface EbondSharkBeanLocal {
	List<Bond> getAllBonds();
	List<Bond> getBondsByCategory(String category);
	List<Bond> getBondsByISIN(String isin);
	List<Bond> getBondsByField(String field);
	List<Bond> getBondsByCurrency(String currency);
	List<Bond> getBondsByYieldRange1();
	List<Bond> getBondsByYieldRange2();
	List<Bond> getBondsByYieldRange3();
}
