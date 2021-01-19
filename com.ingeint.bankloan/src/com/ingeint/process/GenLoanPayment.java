package com.ingeint.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPayment;
import org.compiere.model.MSysConfig;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.util.Env;

import com.ingeint.base.CustomProcess;
import com.ingeint.model.MBankLoan;
import com.ingeint.model.MBankLoanLine;

public class GenLoanPayment extends CustomProcess{

	int C_DocType_ID = 0;
	Timestamp DateAcct = null;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null);
			else if (name.equals("C_DocType_ID")) 
				C_DocType_ID = para[i].getParameterAsInt();
			else if (name.equals("DateAcct"))
				DateAcct = para[i].getParameterAsTimestamp();
			else	
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}		
	}

	@Override
	protected String doIt() throws Exception {

		MBankLoanLine line = new MBankLoanLine(getCtx(), getRecord_ID(), get_TrxName());
		if (line.isPaid())
			throw new AdempiereException("Cuota ya fue pagada");

		Integer DefaultDoc = MSysConfig.getIntValue("C_PaymentDocType_ID", 0, getAD_Client_ID());
		MBankLoan loan = new MBankLoan(getCtx(), line.getING_BankLoan_ID(), get_TrxName());
		//Generate payment capital
		if (line.getOpenAmt().signum()>0) {

			MPayment payment = null;
			if (line.getPartialPayment().signum()>0) {
				payment = com.ingeint.util.IngeintUtils.createPayment(line.get_ID(), loan.get_ID(), loan.getC_BankAccount_ID(), "D", line.getC_Charge_ID(), line.getPartialPayment(), DefaultDoc, "Abono a Capital", DateAcct, get_TrxName(), getCtx());
				addBufferLog(payment.get_ID(), payment.getDateAcct(),null,"Abono a Capital N.: "+payment.getDocumentNo(), payment.get_Table_ID(),payment.get_ID());
				line.setOpenAmt(line.getOpenAmt().subtract(line.getPartialPayment()));
				line.setPartialPayment(Env.ZERO);
			} else {
				payment = com.ingeint.util.IngeintUtils.createPayment(line.get_ID(), loan.get_ID(), loan.getC_BankAccount_ID(), "D", line.getC_Charge_ID(), line.getOpenAmt(), DefaultDoc, line.getHelp(), DateAcct, get_TrxName(), getCtx());
				addBufferLog(payment.get_ID(), payment.getDateAcct(),null,"Pago de Capital N.: "+payment.getDocumentNo(), payment.get_Table_ID(),payment.get_ID());
				line.setOpenAmt(Env.ZERO);
			}
		}
		// Interest Payment
		if (line.getInterestAmt().signum()>0) {
			MPayment payment2 = com.ingeint.util.IngeintUtils.createPayment(line.get_ID(), loan.get_ID(), loan.getC_BankAccount_ID(), "D", line.getC_ChargeForInterest_ID(),
					line.getInterestAmt().add(line.getInterestVariance()), DefaultDoc, line.getHelp(), DateAcct, get_TrxName(), getCtx());	
			addBufferLog(payment2.get_ID(), payment2.getDateAcct(),null,"Pago de Intereses N.: "+payment2.getDocumentNo(), payment2.get_Table_ID(),payment2.get_ID());
		}

		//Interest default
		MPayment payment3 = null;

		if (line.getInterestDefault().signum()>0) {
			Integer C_ChargeForMora = MSysConfig.getIntValue("C_CargoMora", 0, getAD_Client_ID());
			if (C_ChargeForMora==0)
				throw new AdempiereException("Debe configurar la variable C_CargoMora");

			payment3 = com.ingeint.util.IngeintUtils.createPayment(line.get_ID(), loan.get_ID(), loan.getC_BankAccount_ID(), "D", C_ChargeForMora, line.getInterestDefault(), DefaultDoc, line.getHelp(), DateAcct, get_TrxName(), getCtx());
			addBufferLog(payment3.get_ID(), payment3.getDateAcct(),null,"Pago de Mora N.: "+payment3.getDocumentNo(), payment3.get_Table_ID(),payment3.get_ID());
		}

		if (line.getOpenAmt().signum()==0)
			line.setIsPaid(true);
		line.saveEx();

		return null;
	}
}
