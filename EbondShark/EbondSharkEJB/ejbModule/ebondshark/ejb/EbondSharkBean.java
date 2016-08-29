package ebondshark.ejb;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import ebondshark.jpa.Bond;


/**
 * Session Bean implementation class EbondSharkBean
 */
@Stateful
@Remote(EbondSharkBeanRemote.class)
@Local(EbondSharkBeanLocal.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class EbondSharkBean implements EbondSharkBeanRemote, EbondSharkBeanLocal {

	@PersistenceContext(name="EbondSharkJPA")
    private EntityManager em;
    /**
     * Default constructor. 
     */
	
	
	// Execute the query, and get all the bonds back.
	@Override
	public List<Bond> getAllBonds()	{		
		TypedQuery<Bond> query = em.createQuery("SELECT p FROM Bond AS p", Bond.class);
        List<Bond> bonds = query.getResultList();
        System.out.println(bonds);
        return bonds;
	}
	
	@Override
	public List<Bond> getBondsByCategory(String category){
		String sql = "SELECT p FROM Bond AS p WHERE p.category LIKE '%" + category + "%'";
        //System.out.println(sql);
        TypedQuery<Bond> query = em.createQuery(sql, Bond.class);

        // Execute the query, and get a collection of products back.
        List<Bond> bonds = query.getResultList();

//        for (Bond bond: bonds) {
//            displayProductOnServerConsole("Got product in getProductsByName()", product);
//        }

        return bonds;
	}
	
	public List<Bond> getBondsByISIN(String isin) {
		 
        String sql = "SELECT p FROM Bond AS p WHERE p.isin LIKE '%" + isin + "%'";
        //System.out.println(sql);
        TypedQuery<Bond> query = em.createQuery(sql, Bond.class);
        List<Bond> bondsByIsin = query.getResultList();
        return bondsByIsin;
    }
   
    public List<Bond> getBondsByField(String field) {
 
        String sql = "SELECT p FROM Bond AS p WHERE p.isin LIKE '%" + field + "%'";
        //System.out.println(sql);
        TypedQuery<Bond> query = em.createQuery(sql, Bond.class);
        List<Bond> bondsByField = query.getResultList();
        return bondsByField;
    }
    
    public List<Bond> getBondsByCurrency(String currency) {
    	 
        String sql = "SELECT p FROM Bond AS p WHERE p.currency LIKE '%" + currency + "%'";
        //System.out.println(sql);
        TypedQuery<Bond> query = em.createQuery(sql, Bond.class);
        List<Bond> bondsByCurrency = query.getResultList();
        return bondsByCurrency;
    }
    
    public List<Bond> getBondsByFrequency(int frequency) {
   	 
    	TypedQuery<Bond> sql = em.createQuery("SELECT p FROM Bond AS p WHERE p.currency = :freq", Bond.class);
        sql.setParameter("freq", frequency);
        //System.out.println(sql);
        //TypedQuery<Bond> query = em.createQuery(sql, Bond.class);
        List<Bond> bondsByFrequency = sql.getResultList();
        return bondsByFrequency;
    }
//    public EbondSharkBean() {
//        // TODO Auto-generated constructor stub
//    }

}
