package test;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Date;

public class PayeTtransc implements SQLData {
	public static final String ORACLE_OBJECT_NAME = "PAYE_TTRANSC";
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("PayeTtransc={\n");
		result.append("  bodyNb=" + bodyNb + ",\n");
		result.append("  acctNb=" + acctNb + ",\n");
		result.append("  transcNb=" + transcNb + ",\n");
		result.append("  transcTy=" + transcTy + ",\n");
		result.append("  transcAm=" + transcAm + ",\n");
		result.append("  transcDtCr=" + transcDtCr + ",\n");
		result.append("  paymntNb=" + paymntNb + ",\n");
		result.append("  tySpPayment=" + tySpPayment + ",\n");
		result.append("  cdFundSpecial=" + cdFundSpecial + ",\n");
		result.append("  trnstyLbAbb=" + trnstyLbAbb + ",\n");
		result.append("  spepmtLb=" + spepmtLb + ",\n");
		result.append("  fcdLb=" + fcdLb + "\n");
		result.append("}");
		
		return result.toString();
	}
	
	@Override
	public String getSQLTypeName() throws SQLException {
		return ORACLE_OBJECT_NAME;
	}
	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		setBodyNb(stream.readInt());
		setAcctNb(stream.readInt());
		setTranscNb(stream.readInt());
		setTranscTy(stream.readInt());
		setTranscAm(stream.readDouble());
		setTranscDtCr(stream.readDate());
		setPaymntNb(stream.readInt());
		if (stream.wasNull()) {
			setPaymntNb(null);
		}
		setTySpPayment(stream.readString());
		setCdFundSpecial(stream.readString());
		setTrnstyLbAbb(stream.readString());
		setSpepmtLb(stream.readString());
		setFcdLb(stream.readString());
	}
	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeInt(bodyNb);
		stream.writeInt(acctNb);
		stream.writeInt(transcNb);
		stream.writeInt(transcTy);
		stream.writeDouble(transcAm);
		stream.writeDate(transcDtCr != null ? new java.sql.Date(transcDtCr.getTime()) : null);
		stream.writeInt(paymntNb);
		stream.writeString(tySpPayment);
		stream.writeString(cdFundSpecial);
		stream.writeString(trnstyLbAbb);
		stream.writeString(spepmtLb);
		stream.writeString(fcdLb);
	}
	private Integer bodyNb;
	private Integer acctNb;
	private Integer transcNb;
	private Integer transcTy;
	private Double transcAm;
	private Date transcDtCr;
	private Integer paymntNb;
	private String tySpPayment;
	private String cdFundSpecial;
	private String trnstyLbAbb;
	private String spepmtLb;
	private String fcdLb;

	public Integer getBodyNb() {
		return bodyNb;
	}

	public void setBodyNb(Integer bodyNb) {
		this.bodyNb = bodyNb;
	}

	public Integer getAcctNb() {
		return acctNb;
	}

	public void setAcctNb(Integer acctNb) {
		this.acctNb = acctNb;
	}

	public Integer getTranscNb() {
		return transcNb;
	}

	public void setTranscNb(Integer transcNb) {
		this.transcNb = transcNb;
	}

	public Integer getTranscTy() {
		return transcTy;
	}

	public void setTranscTy(Integer transcTy) {
		this.transcTy = transcTy;
	}

	public Double getTranscAm() {
		return transcAm;
	}

	public void setTranscAm(Double transcAm) {
		this.transcAm = transcAm;
	}

	public Date getTranscDtCr() {
		return transcDtCr;
	}

	public void setTranscDtCr(Date transcDtCr) {
		this.transcDtCr = transcDtCr;
	}

	public Integer getPaymntNb() {
		return paymntNb;
	}

	public void setPaymntNb(Integer paymntNb) {
		this.paymntNb = paymntNb;
	}

	public String getTySpPayment() {
		return tySpPayment;
	}

	public void setTySpPayment(String tySpPayment) {
		this.tySpPayment = tySpPayment;
	}

	public String getCdFundSpecial() {
		return cdFundSpecial;
	}

	public void setCdFundSpecial(String cdFundSpecial) {
		this.cdFundSpecial = cdFundSpecial;
	}

	public String getTrnstyLbAbb() {
		return trnstyLbAbb;
	}

	public void setTrnstyLbAbb(String trnstyLbAbb) {
		this.trnstyLbAbb = trnstyLbAbb;
	}

	public String getSpepmtLb() {
		return spepmtLb;
	}

	public void setSpepmtLb(String spepmtLb) {
		this.spepmtLb = spepmtLb;
	}

	public String getFcdLb() {
		return fcdLb;
	}

	public void setFcdLb(String fcdLb) {
		this.fcdLb = fcdLb;
	}
}
