package ebondshark.ejb;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 * Session Bean implementation class EbondSharkBean
 */
@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class EbondSharkBean implements EbondSharkBeanRemote, EbondSharkBeanLocal {

    /**
     * Default constructor. 
     */
    public EbondSharkBean() {
        // TODO Auto-generated constructor stub
    }

}
