package ebondshark.web;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

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
		this.settlementAmount = settlementAmount;
	}

	public double getAccruedAmount() {
		return accruedAmount;
	}

	public void setAccruedAmount(double accruedAmount) {
		this.accruedAmount = accruedAmount;
	}

	public BondDetails() {
		// TODO Auto-generated constructor stub
		this.desiredYield = 0;
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

		int MILI_SECS_IN_DAY = 1000 * 60 * 60 * 24;
		Calendar endInstant = Calendar.getInstance();
		endInstant.set(bond.getIssueYear(), bond.getIssueMonth(), bond.getIssueDay());

		double couponDuration = 360 / bond.getFrequency();
		double reqNumberOfDays = ((int) ((Calendar.getInstance().getTimeInMillis() - endInstant.getTimeInMillis())
				/ MILI_SECS_IN_DAY) % couponDuration);
		double couponRate = (bond.getCoupon()).doubleValue();
		double accAmount = faceValue * (1 / bond.getFrequency()) * couponRate * (reqNumberOfDays / couponDuration);
		return accAmount;

	}

	public double calculateCleanPrice(Bond bond) {
		double couponRate = (bond.getCoupon()).doubleValue();
		double couponAmount = faceValue * couponRate / 100;
		int couponFrequency = bond.getFrequency();
		int yearsToMaturity = BondDetails.yearsToMaturity(bond);
		double cleanPrice = 0;
		for (int i = 1; i <= (yearsToMaturity * couponFrequency); i++) {
			cleanPrice += couponAmount / Math.pow((1 + (desiredYield / couponFrequency)), i);
		}
		return cleanPrice += faceValue
				/ Math.pow((1 + (desiredYield / couponFrequency)), (yearsToMaturity * couponFrequency));

	}

	public static int yearsToMaturity(Bond bond) {
		int maturityYear = bond.getMaturityYear();
		int presentYear = Calendar.getInstance().get(Calendar.YEAR);
		return (maturityYear - presentYear);
	}

	public double getYield(Bond bond) {
		double couponRate = (bond.getCoupon()).doubleValue();
		double couponAmount = faceValue * couponRate / 100;
		double yield = couponAmount / this.cleanPrice * 100;
		Double truncatedYield = new BigDecimal(dirtyPrice).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		return truncatedYield;
	}

}