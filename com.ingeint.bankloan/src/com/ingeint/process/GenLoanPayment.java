package com.ingeint.process;

import java.sql.Timestamp;

import org.adempiere.base.annotation.Parameter;
import org.adempiere.base.annotation.Process;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPayment;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import com.ingeint.base.CustomProcess;
import com.ingeint.model.MBankLoan;
import com.ingeint.model.MBankLoanLine;
import com.ingeint.util.IngeintUtils;

@Process(name = "GenLoanPayment")
public class GenLoanPayment extends CustomProcess{

	@Parameter
	int C_DocType_ID = 0;
	@Parameter
	Timestamp DateAcct = null;

	@Override
	protected void prepare() {/*****/}

	@Override
	protected String doIt() throws Exception {

		MBankLoanLine line = new MBankLoanLine(getCtx(), getRecord_ID(), get_TrxName());
		
		if (line.isPaid())
			throw new AdempiereException("@FeeAlreadyPaid@");
		
		MBankLoan loan = new MBankLoan(getCtx(), line.getING_BankLoan_ID(), get_TrxName());
		
		if(line.getOpenAmt().signum() > 0) {
			
			MPayment payment = null;

			StringBuilder msg = new StringBuilder("@ING_BankLoan_ID@: ")
					.append(loan.getDocumentNo());
			
			String finalMsg = Msg.parseTranslation(getCtx(), msg.toString());
			
			if(line.getPartialPayment().signum() > 0) {
				
				payment = IngeintUtils.createPayment(getCtx(), loan, line, "D", line.getPartialPayment(), finalMsg, false, get_TrxName());
				
				line.setOpenAmt(line.getOpenAmt().subtract(line.getPartialPayment()));
				line.setPartialPayment(Env.ZERO);
				
			} else {
				
				payment = IngeintUtils.createPayment(getCtx(), loan, line, "D", line.getOpenAmt(), finalMsg, false, get_TrxName());
				line.setOpenAmt(Env.ZERO);
			}
			
			addBufferLog(payment.get_ID(), payment.getDateAcct()
					,null , payment.getDocumentNo()
					, payment.get_Table_ID(),payment.get_ID());
			
			if(line.getInterestAmt().signum() > 0) {
				
				payment = IngeintUtils.createPayment(getCtx(), loan, line, "D", line.getInterestAmt(), finalMsg, true, get_TrxName());
				
				addBufferLog(payment.get_ID(), payment.getDateAcct()
						,null , payment.getDocumentNo()
						, payment.get_Table_ID(),payment.get_ID());
			}
		}
		
		if (line.getOpenAmt().signum()==0)
			line.setIsPaid(true);
		
		line.saveEx();
		return "OK";
	}
}
