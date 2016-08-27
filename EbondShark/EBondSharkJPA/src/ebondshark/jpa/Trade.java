package ebondshark.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the trades database table.
 * 
 */
@Entity
@Table(name="trades")
@NamedQuery(name="Trade.findAll", query="SELECT t FROM Trade t")
public class Trade implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TradePK id;

	@Column(name="buy_sell")
	private String buySell;

	@Column(name="date_of_order")
	private String dateOfOrder;

	@Column(name="no_of_bonds")
	private String noOfBonds;

	private BigDecimal price;

	@Column(name="trade_status")
	private String tradeStatus;

	//bi-directional many-to-one association to Bond
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ISIN")
	private Bond bond;

	//bi-directional many-to-one association to Trader
	@ManyToOne
	@JoinColumn(name="Trader_id")
	private Trader trader;

	public Trade() {
	}

	public TradePK getId() {
		return this.id;
	}

	public void setId(TradePK id) {
		this.id = id;
	}

	public String getBuySell() {
		return this.buySell;
	}

	public void setBuySell(String buySell) {
		this.buySell = buySell;
	}

	public String getDateOfOrder() {
		return this.dateOfOrder;
	}

	public void setDateOfOrder(String dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public String getNoOfBonds() {
		return this.noOfBonds;
	}

	public void setNoOfBonds(String noOfBonds) {
		this.noOfBonds = noOfBonds;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTradeStatus() {
		return this.tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public Bond getBond() {
		return this.bond;
	}

	public void setBond(Bond bond) {
		this.bond = bond;
	}

	public Trader getTrader() {
		return this.trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

}