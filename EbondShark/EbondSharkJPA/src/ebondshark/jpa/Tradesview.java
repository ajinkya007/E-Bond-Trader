package ebondshark.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tradesview database table.
 * 
 */
@Entity
@NamedQuery(name="Tradesview.findAll", query="SELECT t FROM Tradesview t")
public class Tradesview implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="buy_sell")
	private String buySell;

	private int day;

	private int hour;

	private String isin;

	private int minutes;

	private int month;

	@Column(name="no_of_bonds")
	private int noOfBonds;

	private BigDecimal price;

	private int seconds;

	@Id
	private int trade_id;

	@Column(name="trade_status")
	private String tradeStatus;

	private String trader_id;

	private int year;

	public Tradesview() {
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

	public String getIsin() {
		return this.isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
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

	public int getTrade_id() {
		return this.trade_id;
	}

	public void setTrade_id(int trade_id) {
		this.trade_id = trade_id;
	}

	public String getTradeStatus() {
		return this.tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTrader_id() {
		return this.trader_id;
	}

	public void setTrader_id(String trader_id) {
		this.trader_id = trader_id;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}