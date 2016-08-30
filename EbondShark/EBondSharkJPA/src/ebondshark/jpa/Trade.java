package ebondshark.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

	@Id
	@SequenceGenerator(name="TRADES_TRADE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRADES_TRADE_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private int trade_id;

	@Column(name="buy_sell", nullable=false, length=50)
	private String buySell;

	@Column(nullable=false)
	private int day;

	@Column(nullable=false)
	private int hour;

	@Column(nullable=false)
	private int minutes;

	@Column(nullable=false)
	private int month;

	@Column(name="no_of_bonds", nullable=false)
	private int noOfBonds;

	@Column(nullable=false, precision=10)
	private BigDecimal price;

	@Column(nullable=false)
	private int seconds;

	@Column(name="trade_status", nullable=false, length=50)
	private String tradeStatus;

	@Column(nullable=false)
	private int year;

	//bi-directional many-to-one association to Bond
	@ManyToOne(fetch= FetchType.EAGER,cascade={CascadeType.ALL})
	@JsonBackReference
	@JoinColumn(name="ISIN", nullable=false)
	private Bond bond;

	//bi-directional many-to-one association to Trader
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="Trader_id", nullable=false)
	@JsonBackReference
	private Trader trader;

	public Trade() {
	}

	public int getTrade_id() {
		return this.trade_id;
	}

	public void setTrade_id(int trade_id) {
		this.trade_id = trade_id;
	}

	public String getBuySell() {
		return this.buySell;
	}

	public void setBuySell(String buySell) {
		this.buySell = buySell;
	}

	public int getDay() {
		return this.day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return this.hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinutes() {
		return this.minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getNoOfBonds() {
		return this.noOfBonds;
	}

	public void setNoOfBonds(int noOfBonds) {
		this.noOfBonds = noOfBonds;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getSeconds() {
		return this.seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public String getTradeStatus() {
		return this.tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
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