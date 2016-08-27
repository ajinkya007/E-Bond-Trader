package ebondshark.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the trades database table.
 * 
 */
@Embeddable
public class TradePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String trade_id;

	@Column(insertable=false, updatable=false)
	private String isin;

	@Column(insertable=false, updatable=false)
	private String trader_id;

	public TradePK() {
	}
	public String getTrade_id() {
		return this.trade_id;
	}
	public void setTrade_id(String trade_id) {
		this.trade_id = trade_id;
	}
	public String getIsin() {
		return this.isin;
	}
	public void setIsin(String isin) {
		this.isin = isin;
	}
	public String getTrader_id() {
		return this.trader_id;
	}
	public void setTrader_id(String trader_id) {
		this.trader_id = trader_id;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TradePK)) {
			return false;
		}
		TradePK castOther = (TradePK)other;
		return 
			this.trade_id.equals(castOther.trade_id)
			&& this.isin.equals(castOther.isin)
			&& this.trader_id.equals(castOther.trader_id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.trade_id.hashCode();
		hash = hash * prime + this.isin.hashCode();
		hash = hash * prime + this.trader_id.hashCode();
		
		return hash;
	}
}