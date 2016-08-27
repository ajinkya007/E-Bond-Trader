package ebondshark.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the bonds database table.
 * 
 */
@Entity
@Table(name="bonds")
@NamedQuery(name="Bond.findAll", query="SELECT b FROM Bond b")
public class Bond implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="BONDS_ISIN_GENERATOR", sequenceName="ISIN")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BONDS_ISIN_GENERATOR")
	private String isin;

	private BigDecimal ask;

	private BigDecimal bid;

	private String category;

	private BigDecimal change;

	private BigDecimal coupon;

	private int creditRating;

	private String currency;

	private String field;

	private int frequency;

	private BigDecimal high;

	private int id;

	@Column(name="issue_day")
	private int issueDay;

	@Column(name="issue_month")
	private int issueMonth;

	@Column(name="issue_year")
	private int issueYear;

	private String issuerName;

	private BigDecimal last;

	private BigDecimal low;

	@Column(name="maturity_day")
	private int maturityDay;

	@Column(name="maturity_month")
	private int maturityMonth;

	@Column(name="maturity_yaer")
	private int maturityYaer;

	private int time;

	private BigDecimal yield;

	//bi-directional many-to-one association to Trade
	@OneToMany(mappedBy="bond", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	private List<Trade> trades;

	public Bond() {
	}

	public String getIsin() {
		return this.isin;
	}

	public void setIsin(String isin) {
		this.isin = isin;
	}

	public BigDecimal getAsk() {
		return this.ask;
	}

	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}

	public BigDecimal getBid() {
		return this.bid;
	}

	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getChange() {
		return this.change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	public BigDecimal getCoupon() {
		return this.coupon;
	}

	public void setCoupon(BigDecimal coupon) {
		this.coupon = coupon;
	}

	public int getCreditRating() {
		return this.creditRating;
	}

	public void setCreditRating(int creditRating) {
		this.creditRating = creditRating;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getFrequency() {
		return this.frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public BigDecimal getHigh() {
		return this.high;
	}

	public void setHigh(BigDecimal high) {
		this.high = high;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIssueDay() {
		return this.issueDay;
	}

	public void setIssueDay(int issueDay) {
		this.issueDay = issueDay;
	}

	public int getIssueMonth() {
		return this.issueMonth;
	}

	public void setIssueMonth(int issueMonth) {
		this.issueMonth = issueMonth;
	}

	public int getIssueYear() {
		return this.issueYear;
	}

	public void setIssueYear(int issueYear) {
		this.issueYear = issueYear;
	}

	public String getIssuerName() {
		return this.issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public BigDecimal getLast() {
		return this.last;
	}

	public void setLast(BigDecimal last) {
		this.last = last;
	}

	public BigDecimal getLow() {
		return this.low;
	}

	public void setLow(BigDecimal low) {
		this.low = low;
	}

	public int getMaturityDay() {
		return this.maturityDay;
	}

	public void setMaturityDay(int maturityDay) {
		this.maturityDay = maturityDay;
	}

	public int getMaturityMonth() {
		return this.maturityMonth;
	}

	public void setMaturityMonth(int maturityMonth) {
		this.maturityMonth = maturityMonth;
	}

	public int getMaturityYaer() {
		return this.maturityYaer;
	}

	public void setMaturityYaer(int maturityYaer) {
		this.maturityYaer = maturityYaer;
	}

	public int getTime() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public BigDecimal getYield() {
		return this.yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	public List<Trade> getTrades() {
		return this.trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	public Trade addTrade(Trade trade) {
		getTrades().add(trade);
		trade.setBond(this);

		return trade;
	}

	public Trade removeTrade(Trade trade) {
		getTrades().remove(trade);
		trade.setBond(null);

		return trade;
	}

}