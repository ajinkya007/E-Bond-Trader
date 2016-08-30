package ebondshark.web;

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
		this.cleanPrice = cleanPrice;
	}

	public double getDirtyPrice() {
		return dirtyPrice;
	}

	public void setDirtyPrice(double dirtyPrice) {
		this.dirtyPrice = dirtyPrice;
	}

	public double getFacevalue() {
		return faceValue;
	}

	public double accruedAmount(Bond bond) {

		@SuppressWarnings("deprecation")
		Date date = new Date(bond.getIssueYear(), bond.getIssueMonth(), bond.getIssueDay());
		int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
		long endInstant = Calendar.getInstance().getTimeInMillis();
		double couponDuration = 365 / bond.getFrequency();
		double reqNumberOfDays = ((int) ((endInstant - date.getTime()) / MILLIS_IN_DAY) % couponDuration);
		double couponRate = (bond.getCoupon()).doubleValue();
		double accAmount = faceValue * (bond.getFrequency()) * couponRate * (reqNumberOfDays / couponDuration);
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
		return couponAmount / this.cleanPrice * 100;
	}

}