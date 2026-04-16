package com.ingeint.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import org.adempiere.exceptions.AdempiereException;

import com.ingeint.base.CustomProcess;
import com.ingeint.model.MBankLoan;
import com.ingeint.model.MBankLoanLine;
import com.ingeint.util.IngeintUtils;

public class GenBankLoanFees extends CustomProcess{

	@Override
	protected void prepare() {/*****/}

	@Override
	protected String doIt() throws Exception {

		final MBankLoan loan = new MBankLoan(getCtx(), getRecord_ID(), get_TrxName());

		if (loan.getFeeNumbers() <= 0)
			throw new AdempiereException("@FeeNumbers@ <= 0");
			
		if (loan.getAmount() == null || loan.getAmount().signum() <= 0)
			throw new AdempiereException("@Amount@ <= 0");
			
		if (loan.getStartDate() == null)
			throw new AdempiereException("@Mandatory@ @StartDate@");
			
		if (loan.getInterestPercent() == null)
			throw new AdempiereException("@Mandatory@ @InterestPercent@");

		final MBankLoanLine[] lines = loan.getLines();
		
		if (lines.length > 0)
			MBankLoanLine.deleteLines(loan);

		final int feeNumbers = loan.getFeeNumbers();
		final BigDecimal totalAmount = loan.getAmount().setScale(2, RoundingMode.HALF_UP);
		final BigDecimal interestPercent = loan.getInterestPercent().setScale(2, RoundingMode.HALF_UP);

		final BigDecimal baseInstallment = totalAmount.divide(BigDecimal.valueOf(feeNumbers),2,RoundingMode.HALF_UP);

		BigDecimal remainingAmount = totalAmount;
		Date dueDateBase = new Date(loan.getStartDate().getTime());

		for (int i = 1; i <= feeNumbers; i++) {

			final BigDecimal capitalAmt = (i == feeNumbers)
				? remainingAmount
				: baseInstallment;

			final BigDecimal interestAmt = capitalAmt
				.multiply(interestPercent)
				.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

			MBankLoanLine line = new MBankLoanLine(getCtx(), 0, get_TrxName());
			line.setAD_Org_ID(loan.getAD_Org_ID());
			line.setLine(i);
			line.setING_BankLoan_ID(loan.get_ID());
			line.setC_Charge_ID(loan.getC_Charge_ID());

			line.setCapital(capitalAmt);
			line.setOpenAmt(capitalAmt);
			line.setInterestAmt(interestAmt);

			java.util.Date calculatedDate = IngeintUtils.calculateDate(
				dueDateBase,
				Integer.valueOf(loan.getFeefrequency())
			);
			line.setDueDate(new java.sql.Timestamp(calculatedDate.getTime()));

			line.saveEx();

			remainingAmount = remainingAmount.subtract(capitalAmt);
			dueDateBase = new Date(line.getDueDate().getTime());
		}

		return "@OK@";
	}
}