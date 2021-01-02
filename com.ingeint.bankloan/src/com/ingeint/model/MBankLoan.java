/**********************************************************************
* This file is part of iDempiere ERP <http://www.idempiere.org>       *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Orlando Curieles - ingeint                            *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Orlando Curieles - ingeint                                        *
*                                                                     *
* Sponsors:                                                           *
* - Company (https://www.ingeint.com)                                 *
**********************************************************************/
package com.ingeint.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDocType;
import org.compiere.model.MPayment;
import org.compiere.model.MPeriod;
import org.compiere.model.MSysConfig;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Env;
import org.compiere.util.Util;

public class MBankLoan extends X_ING_BankLoan implements DocAction, DocOptions {
	/**
	 * 
	 */
	private static final long serialVersionUID = 806817150882501578L;

	public MBankLoan(Properties ctx, int ING_BankLoan_ID, String trxName) {
		super(ctx, ING_BankLoan_ID, trxName);
		if (ING_BankLoan_ID == 0) {
			setDocStatus(DOCSTATUS_Drafted);
			setDocAction(DOCACTION_Prepare);
		}
	}

	public MBankLoan(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	/** Process Message */
	protected String m_processMsg = null;
	/** Just Prepared Flag */
	protected boolean m_justPrepared = false;

	/** Loan Lines */
	protected MBankLoanLine[] m_lines = null;

	@Override
	public int customizeValidActions(String docStatus, Object processing, String orderType, String isSOTrx,
			int AD_Table_ID, String[] docAction, String[] options, int index) {
		if (options == null)
			throw new IllegalArgumentException("Option array parameter is null");
		if (docAction == null)
			throw new IllegalArgumentException("Doc action array parameter is null");

		// If a document is drafted or invalid, the users are able to complete, prepare
		// or void
		if (docStatus.equals(DocumentEngine.STATUS_Drafted) || docStatus.equals(DocumentEngine.STATUS_Invalid)) {
			options[index++] = DocumentEngine.ACTION_Complete;
			options[index++] = DocumentEngine.ACTION_Prepare;

			// If the document is already completed, we also want to be able to reactivate
			// or void it instead of only closing it
		} else if (docStatus.equals(DocumentEngine.STATUS_Completed)) {
			options[index++] = DocumentEngine.ACTION_Void;
		}

		return index;
	}

	/**************************************************************************
	 * Get Lines of Loan
	 * 
	 * @param whereClause
	 *            where clause or null (starting with AND)
	 * @param orderClause
	 *            order clause
	 * @return lines
	 */
	public MBankLoanLine[] getLines(String whereClause, String orderClause) {
		StringBuilder whereClauseFinal = new StringBuilder(MBankLoanLine.COLUMNNAME_ING_BankLoan_ID + "=? ");
		if (!Util.isEmpty(whereClause, true))
			whereClauseFinal.append(whereClause);
		if (orderClause.length() == 0)
			orderClause = MBankLoanLine.COLUMNNAME_Line;
		//
		List<MBankLoanLine> list = new Query(getCtx(), MBankLoanLine.Table_Name, whereClauseFinal.toString(),
				get_TrxName()).setParameters(get_ID()).setOrderBy(orderClause).list();
		for (MBankLoanLine ol : list) {
			ol.setHeaderInfo(this);
		}
		//
		return list.toArray(new MBankLoanLine[list.size()]);
	} // getLines

	public MBankLoanLine[] getLines(boolean requery, String orderBy) {
		if (m_lines != null && !requery) {
			set_TrxName(m_lines, get_TrxName());
			return m_lines;
		}
		//
		String orderClause = "";
		if (orderBy != null && orderBy.length() > 0)
			orderClause += orderBy;
		else
			orderClause += "Line";
		m_lines = getLines(null, orderClause);
		return m_lines;
	} // getLines

	public MBankLoanLine[] getLines() {
		return getLines(false, null);
	} // getLines

	@Override
	public String prepareIt() {
		if (log.isLoggable(Level.INFO))
			log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		MDocType dt = MDocType.get(getCtx(), getC_DocTypeTarget_ID());

		// Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateAcct(), dt.getDocBaseType(), getAD_Org_ID())) {
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}

		MBankLoanLine[] lines = getLines(true, null);

		if (lines.length == 0) {
			throw new AdempiereException("Debe Generar las lineas del prestamo");

		}

		BigDecimal TotalCapital = Env.ZERO;

		for (MBankLoanLine line : lines) {

			if (line.getCapital() == null)
				line.setCapital(Env.ZERO);
			TotalCapital = TotalCapital.add(line.getCapital());
		}

		if (TotalCapital.compareTo(getAmount()) != 0)
			throw new AdempiereException(
					"El monto de las lineas no concuerda con el total del prestamo, total calculado: "
							+ TotalCapital.toString());

		setC_DocType_ID(getC_DocTypeTarget_ID());
		return DocAction.STATUS_InProgress;
	}

	@Override
	public boolean processIt(String action) throws Exception {
		log.warning(
				"Processing Action=" + action + " - DocStatus=" + getDocStatus() + " - DocAction=" + getDocAction());
		DocumentEngine engine = new DocumentEngine(this, getDocStatus());
		return engine.processIt(action, getDocAction());
	}

	@Override
	public boolean unlockIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean invalidateIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean approveIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rejectIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String completeIt() {

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;

		Integer DefaultDocTypeForPayment = MSysConfig.getIntValue("C_ReceiptDocType_ID", 0, getAD_Client_ID());
		com.ingeint.util.IngeintUtils.createPayment(0, get_ID(), getC_BankAccount_ID(), "A", getC_Charge_ID(), Env.ZERO,
				DefaultDocTypeForPayment, "", getDateAcct(), get_TrxName(), getCtx());

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}

	@Override
	public boolean voidIt() {

		List<MPayment> pays = new Query(getCtx(), MPayment.Table_Name, "ING_BankLoanLine_ID = ?", get_TrxName())
				.setParameters(new Object[] { getING_BankLoan_ID() }).list();

		for (MPayment pay : pays) {

			if (pay.getDocStatus().equals(DOCACTION_Complete) || (pay.getDocStatus().equals(DOCACTION_Close)))
				throw new AdempiereException("No puede reversar un prestamo que tiene pago de cuotas asociadas, pago: "
						+ pay.getDocumentNo());
		}

		MPayment payment = new Query(getCtx(), MPayment.Table_Name, "ING_BankLoan_ID =  ?", get_TrxName())
				.setParameters(new Object[] { getING_BankLoan_ID() }).first();

		if (payment != null) {

			payment.reverseCorrectIt();
			payment.saveEx();
			
			setAmount(Env.ZERO);
			setInterestPercent(Env.ZERO);
			setFeeNumbers(0);
			setDocStatus(DOCSTATUS_Voided);
			setDocAction(DOCACTION_None);
		}
		return true;
	}

	@Override
	public boolean closeIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reverseCorrectIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reverseAccrualIt() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reActivateIt() {

		return false;
	}

	@Override
	public String getSummary() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDocumentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File createPDF() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProcessMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDoc_User_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getC_Currency_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BigDecimal getApprovalAmt() {
		// TODO Auto-generated method stub
		return null;
	}

}
