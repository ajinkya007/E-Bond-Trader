package ebondshark.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import ebondshark.ejb.EbondSharkBeanRemote;
import ebondshark.jpa.Bond;
import ebondshark.web.BondDetails;

public class TestEbondShark {
	Bond bond = new Bond();
	{
		bond.setIssueDay(19);
		bond.setIssueMonth(8);
		bond.setIssueYear(2015);
		bond.setFrequency(2);
		BigDecimal bd = new BigDecimal(1.924);
		bond.setYield(bd);
		bond.setMaturityDay(19);
		bond.setMaturityMonth(8);
		bond.setMaturityYear(2028);
		BigDecimal cd = new BigDecimal(1.92);
		bond.setCoupon(cd);
		bond.setCategory("gov");
		bond.setField("Infra");
		bond.setCurrency("GBP");
		bond.setIsin("ABX97193");
		
		
	}
	BondDetails bonddetails = new BondDetails();
	{
		bonddetails.setISIN("ABX97193");
		bonddetails.setDesiredYield(2.0);
	}
	
	private EbondSharkBeanRemote bean;

	public TestEbondShark() {
		try {
			InitialContext context = new InitialContext();
			bean = (EbondSharkBeanRemote) context
					.lookup("java:app/EbondSharkEJB/EbondSharkBean!ebondshark.ejb.EbondSharkBeanRemote");
		} catch (NamingException ex) {
		}
	}


//	@Test
//	public void testGetAllBonds() {
//		List<Bond> bonds = new ArrayList<Bond>();
//		bonds = bean.getAllBonds();
//		assertNotNull(bonds);
//	}
//	
//	@Test
//	public void testGetBondsByCategory(){
//		List<Bond> bonds = bean.getBondsByCategory("gov");
//		assertNotNull(bonds);		
//	}
//	
//	@Test
//	public void testGetBondsByISIN(){
//		List<Bond> bonds = bean.getBondsByISIN("LXBC2328");
//		assertNotNull(bonds);		
//	}
	
	@Test
	public void testYearsToMaturity(){
		int m = bonddetails.yearsToMaturity(bond);
		assertEquals(12,m);
		
	}
	
	double DELTA = .00001;
	
	@Test
	public void testAccruedAmount(){
		double m = bonddetails.accruedAmount(bond);
		Double truncatedAccruedAmount = new BigDecimal(m).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		assertEquals(0.1013,truncatedAccruedAmount ,DELTA);
		}
	
	@Test
	public void testCleanPrice(){
		double m = bonddetails.calculateCleanPrice(bond);
		Double truncatedCleanPrice = new BigDecimal(m).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		assertEquals(truncatedCleanPrice,78.9605,DELTA);
		
	}
	
	@Test
	public void testDesiredYield(){
		bonddetails.setCleanPrice(78.960549);
		double m = bonddetails.getYield(bond);
		assertEquals(2.4316,m,DELTA);
		
	}
	
/*	@Test
	public void testAccruedAmountNotNull(Bond bonds){
		assertNotNull(bonds);	
	}
	
	@Test
	public void testAccruedAmountNoOfDays(Bond bonds){
		assertNotNull(bonds);	
		
	}
	
	@Test
	public void testAccruedAmountCalculation(Bond bonds){
		assertNotNull(bonds);	
	}*/
}