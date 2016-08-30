package ebondshark.jpa;

import java.io.Serializable;

import javax.lang.model.element.PackageElement;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@SequenceGenerator(name="BONDS_ISIN_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BONDS_ISIN_GENERATOR")
	@Column(unique=true, nullable=false, length=50)
	private String isin;

	@Column(nullable=false, precision=10)
	private BigDecimal ask;

	@Column(nullable=false, precision=10)
	private BigDecimal bid;

	@Column(nullable=false, length=50)
	private String category;

	@Column(nullable=false, precision=10)
	private BigDecimal change;

	@Column(nullable=false, precision=10)
	private BigDecimal coupon;

	@Column(nullable=false)
	private int creditRating;

	@Column(nullable=false, length=50)
	private String currency;

	@Column(nullable=false, length=50)
	private String field;

	@Column(nullable=false)
	private int frequency;

	@Column(nullable=false, precision=10)
	private BigDecimal high;

	@Column(nullable=false)
	private int id;

	@Column(name="issue_day", nullable=false)
	private int issueDay;

	@Column(name="issue_month", nullable=false)
	private int issueMonth;

	@Column(name="issue_year", nullable=false)
	private int issueYear;

	@Column(nullable=false, length=50)
	private String issuerName;

	@Column(nullable=false, precision=10)
	private BigDecimal last;

	@Column(nullable=false, precision=10)
	private BigDecimal low;

	@Column(name="maturity_day", nullable=false)
	private int maturityDay;

	@Column(name="maturity_month", nullable=false)
	private int maturityMonth;

	@Column(name="maturity_year", nullable=false)
	private int maturityYear;

	@Column(nullable=false)
	private int time;

	@Column(nullable=false, precision=10)
	private BigDecimal yield;

	//bi-directional many-to-one association to Trade
	@OneToMany(fetch= FetchType.EAGER, mappedBy="bond", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JsonManagedReference
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

	public int getMaturityYear() {
		return this.maturityYear;
	}

	public void setMaturityYear(int maturityYear) {
		this.maturityYear = maturityYear;
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