package ebondshark.web;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import ebondshark.jpa.Bond;

public class BondDetails {

	private double desiredYield;
	private String ISIN;
	private int quantity;
	private double cleanPrice;
	private double dirtyPrice;
	private static final double faceValue = 100;
	private double settlementAmount;
	private double accruedAmount;

	public double getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(double settlementAmount) {
		Double truncatedSettlementAmount = new BigDecimal(settlementAmount).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		this.settlementAmount = truncatedSettlementAmount;
	}

	public double getAccruedAmount() {
		return accruedAmount;
	}

	public void setAccruedAmount(double accruedAmount) {
		Double truncatedAccruedAmount = new BigDecimal(accruedAmount).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		this.accruedAmount = truncatedAccruedAmount;
	}

	public BondDetails() {
		// TODO Auto-generated constructor stub
		this.desiredYield = 1;
		this.cleanPrice = 0;
		this.dirtyPrice = 0;

	}

	public double getDesiredYield() {
		return desiredYield;
	}

	public void setDesiredYield(double desiredYield) {
		this.desiredYield = desiredYield;
	}

	public String getISIN() {
		return ISIN;
	}

	public void setISIN(String iSIN) {
		ISIN = iSIN;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCleanPrice() {
		return cleanPrice;
	}

	public void setCleanPrice(double cleanPrice) {
		Double truncatedCleanPrice = new BigDecimal(cleanPrice).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		this.cleanPrice = truncatedCleanPrice;
	}

	public double getDirtyPrice() {
		return dirtyPrice;
	}

	public void setDirtyPrice(double dirtyPrice) {
		Double truncatedDirtyPrice = new BigDecimal(dirtyPrice).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		this.dirtyPrice = truncatedDirtyPrice;
	}

	public double getFacevalue() {
		return faceValue;
	}

	public double accruedAmount(Bond bond) {
		Date date = new Date();
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String dateStop = sdf.format(date);
		Date isDate = new Date(bond.getIssueYear() - 1900, bond.getIssueMonth() - 1, bond.getIssueDay());
		String issueDate = sdf.format(isDate);

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = sdf.parse(issueDate);
			d2 = sdf.parse(dateStop);

			// in milliseconds

		} catch (Exception e) {
			e.printStackTrace();
		}
		long diff = d2.getTime() - d1.getTime();
		double diffDays = diff / (24 * 60 * 60 * 1000);
		double couponDuration = 360 / bond.getFrequency();
		double couponRate = (bond.getCoupon()).doubleValue();
		double accAmount = faceValue * (couponRate / 100) * (1.0 / bond.getFrequency())
				* ((diffDays % couponDuration) / couponDuration);
		//System.out.println(diff + " " + diffDays + "  " + " " + accAmount);
		return accAmount;
	}

	public double calculateCleanPrice(Bond bond) {
		double couponRate = (bond.getCoupon()).doubleValue();
		double couponAmount = faceValue * couponRate / 100;
		int couponFrequency = bond.getFrequency();
		int yearsToMaturity = BondDetails.yearsToMaturity(bond);
		double m = Math.pow(1 + (desiredYield / (100 * couponFrequency)), (yearsToMaturity * couponFrequency));
		double cleanPrice = (couponAmount / couponFrequency) * ((m - 1) / m) + (faceValue / m);
		return cleanPrice;
	}

	public static int yearsToMaturity(Bond bond) {
		int maturityYear = bond.getMaturityYear();
		int presentYear = Calendar.getInstance().get(Calendar.YEAR);
		return (maturityYear - presentYear);
	}

	public double getYield(Bond bond) {
		double couponRate = (bond.getCoupon()).doubleValue();
		double couponAmount = faceValue * couponRate / 100;
		double yield = couponAmount / cleanPrice * 100;
		Double truncatedYield = new BigDecimal(yield).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(truncatedYield + " " + yield);
		return truncatedYield;
	}

}