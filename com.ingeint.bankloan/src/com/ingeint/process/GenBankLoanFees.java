package com.ingeint.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import com.ingeint.base.CustomProcess;
import com.ingeint.model.MBankLoan;
import com.ingeint.model.MBankLoanLine;
import com.ingeint.util.IngeintUtils;

public class GenBankLoanFees extends CustomProcess{

	@Override
	protected void prepare() {
	}

	@Override
	protected String doIt() throws Exception {
		
		MBankLoan loan = new MBankLoan(getCtx(), getRecord_ID(), get_TrxName());		
		MBankLoanLine[] lines = loan.getLines();
						
		if (lines.length>0)
			MBankLoanLine.deleteLines(loan);		
		
		Date StartDate=new Date(loan.getStartDate().getTime());
		
		for (int i = 1; i <= loan.getFeeNumbers(); i++)  {
				MBankLoanLine line = new MBankLoanLine(getCtx(), 0, get_TrxName());
				line.setAD_Org_ID(loan.getAD_Org_ID());
				line.setLine(i);
				line.setING_BankLoan_ID(loan.get_ID());
				line.setC_Charge_ID(loan.getC_Charge_ID());
				//line.setC_ChargeForInterest_ID(DefaultSysconfig);
				line.setCapital(loan.getAmount().divide(BigDecimal.valueOf(loan.getFeeNumbers()),2, RoundingMode.HALF_UP));
				line.setOpenAmt(loan.getAmount().divide(BigDecimal.valueOf(loan.getFeeNumbers()),2, RoundingMode.HALF_UP));
				line.setDueDate(new java.sql.Timestamp(IngeintUtils.calculateDate(StartDate,Integer.valueOf(loan.getFeefrequency())).getTime()));
				StartDate = new Date (line.getDueDate().getTime());
				line.saveEx();				
			}
		return null;
	}	
}
