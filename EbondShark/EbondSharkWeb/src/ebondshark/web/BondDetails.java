package ebondshark.web;

public class BondDetails {

	private double desiredYield;
	private String ISIN;
	private int quantity;
	private double cleanPrice;
	private double dirtyPrice;

	public BondDetails() {
		// TODO Auto-generated constructor stub
		
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

}
