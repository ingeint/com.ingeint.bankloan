/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package com.ingeint.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for ING_BankLoan
 *  @author iDempiere (generated)
 *  @version Release 11 - $Id$ */
@org.adempiere.base.Model(table="ING_BankLoan")
public class X_ING_BankLoan extends PO implements I_ING_BankLoan, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20260415L;

    /** Standard Constructor */
    public X_ING_BankLoan (Properties ctx, int ING_BankLoan_ID, String trxName)
    {
      super (ctx, ING_BankLoan_ID, trxName);
      /** if (ING_BankLoan_ID == 0)
        {
			setING_BankLoan_ID (0);
			setIsApproved (true);
// Y
			setProcessed (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_ING_BankLoan (Properties ctx, int ING_BankLoan_ID, String trxName, String ... virtualColumns)
    {
      super (ctx, ING_BankLoan_ID, trxName, virtualColumns);
      /** if (ING_BankLoan_ID == 0)
        {
			setING_BankLoan_ID (0);
			setIsApproved (true);
// Y
			setProcessed (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_ING_BankLoan (Properties ctx, String ING_BankLoan_UU, String trxName)
    {
      super (ctx, ING_BankLoan_UU, trxName);
      /** if (ING_BankLoan_UU == null)
        {
			setING_BankLoan_ID (0);
			setIsApproved (true);
// Y
			setProcessed (false);
// N
        } */
    }

    /** Standard Constructor */
    public X_ING_BankLoan (Properties ctx, String ING_BankLoan_UU, String trxName, String ... virtualColumns)
    {
      super (ctx, ING_BankLoan_UU, trxName, virtualColumns);
      /** if (ING_BankLoan_UU == null)
        {
			setING_BankLoan_ID (0);
			setIsApproved (true);
// Y
			setProcessed (false);
// N
        } */
    }

    /** Load Constructor */
    public X_ING_BankLoan (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_ING_BankLoan[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amount Amount in a defined currency
	*/
	public void setAmount (BigDecimal Amount)
	{
		set_ValueNoCheck (COLUMNNAME_Amount, Amount);
	}

	/** Get Amount.
		@return Amount in a defined currency
	  */
	public BigDecimal getAmount()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException
	{
		return (org.compiere.model.I_C_Activity)MTable.get(getCtx(), org.compiere.model.I_C_Activity.Table_ID)
			.getPO(getC_Activity_ID(), get_TrxName());
	}

	/** Set Activity.
		@param C_Activity_ID Business Activity
	*/
	public void setC_Activity_ID (int C_Activity_ID)
	{
		if (C_Activity_ID < 1)
			set_Value (COLUMNNAME_C_Activity_ID, null);
		else
			set_Value (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
	}

	/** Get Activity.
		@return Business Activity
	  */
	public int getC_Activity_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Activity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BankAccount getC_BankAccount() throws RuntimeException
	{
		return (org.compiere.model.I_C_BankAccount)MTable.get(getCtx(), org.compiere.model.I_C_BankAccount.Table_ID)
			.getPO(getC_BankAccount_ID(), get_TrxName());
	}

	/** Set Bank Account.
		@param C_BankAccount_ID Account at the Bank
	*/
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1)
			set_Value (COLUMNNAME_C_BankAccount_ID, null);
		else
			set_Value (COLUMNNAME_C_BankAccount_ID, Integer.valueOf(C_BankAccount_ID));
	}

	/** Get Bank Account.
		@return Account at the Bank
	  */
	public int getC_BankAccount_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Bank getC_Bank() throws RuntimeException
	{
		return (org.compiere.model.I_C_Bank)MTable.get(getCtx(), org.compiere.model.I_C_Bank.Table_ID)
			.getPO(getC_Bank_ID(), get_TrxName());
	}

	/** Set Bank.
		@param C_Bank_ID Bank
	*/
	public void setC_Bank_ID (int C_Bank_ID)
	{
		if (C_Bank_ID < 1)
			set_Value (COLUMNNAME_C_Bank_ID, null);
		else
			set_Value (COLUMNNAME_C_Bank_ID, Integer.valueOf(C_Bank_ID));
	}

	/** Get Bank.
		@return Bank
	  */
	public int getC_Bank_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Bank_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException
	{
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_ID)
			.getPO(getC_Charge_ID(), get_TrxName());
	}

	/** Set Charge.
		@param C_Charge_ID Additional document charges
	*/
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1)
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocTypePayment() throws RuntimeException
	{
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_ID)
			.getPO(getC_DocTypePayment_ID(), get_TrxName());
	}

	/** Set C_DocTypePayment_ID.
		@param C_DocTypePayment_ID C_DocTypePayment_ID
	*/
	public void setC_DocTypePayment_ID (int C_DocTypePayment_ID)
	{
		if (C_DocTypePayment_ID < 1)
			set_Value (COLUMNNAME_C_DocTypePayment_ID, null);
		else
			set_Value (COLUMNNAME_C_DocTypePayment_ID, Integer.valueOf(C_DocTypePayment_ID));
	}

	/** Get C_DocTypePayment_ID.
		@return C_DocTypePayment_ID	  */
	public int getC_DocTypePayment_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocTypePayment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocTypeTarget() throws RuntimeException
	{
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_ID)
			.getPO(getC_DocTypeTarget_ID(), get_TrxName());
	}

	/** Set Target Document Type.
		@param C_DocTypeTarget_ID Target document type for conversing documents
	*/
	public void setC_DocTypeTarget_ID (int C_DocTypeTarget_ID)
	{
		if (C_DocTypeTarget_ID < 1)
			set_Value (COLUMNNAME_C_DocTypeTarget_ID, null);
		else
			set_Value (COLUMNNAME_C_DocTypeTarget_ID, Integer.valueOf(C_DocTypeTarget_ID));
	}

	/** Get Target Document Type.
		@return Target document type for conversing documents
	  */
	public int getC_DocTypeTarget_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocTypeTarget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_DocType getC_DocType() throws RuntimeException
	{
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_ID)
			.getPO(getC_DocType_ID(), get_TrxName());
	}

	/** Set Document Type.
		@param C_DocType_ID Document type or rules
	*/
	public void setC_DocType_ID (int C_DocType_ID)
	{
		if (C_DocType_ID < 0)
			set_ValueNoCheck (COLUMNNAME_C_DocType_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_C_DocType_ID, Integer.valueOf(C_DocType_ID));
	}

	/** Get Document Type.
		@return Document type or rules
	  */
	public int getC_DocType_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DocType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set CreateLines.
		@param CreateLines CreateLines
	*/
	public void setCreateLines (String CreateLines)
	{
		set_Value (COLUMNNAME_CreateLines, CreateLines);
	}

	/** Get CreateLines.
		@return CreateLines	  */
	public String getCreateLines()
	{
		return (String)get_Value(COLUMNNAME_CreateLines);
	}

	/** Set Account Date.
		@param DateAcct Accounting Date
	*/
	public void setDateAcct (Timestamp DateAcct)
	{
		set_Value (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct()
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
	}

	/** Set Description.
		@param Description Optional short description of the record
	*/
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription()
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** DocAction AD_Reference_ID=135 */
	public static final int DOCACTION_AD_Reference_ID=135;
	/** &lt;None&gt; = -- */
	public static final String DOCACTION_None = "--";
	/** Approve = AP */
	public static final String DOCACTION_Approve = "AP";
	/** Close = CL */
	public static final String DOCACTION_Close = "CL";
	/** Complete = CO */
	public static final String DOCACTION_Complete = "CO";
	/** Invalidate = IN */
	public static final String DOCACTION_Invalidate = "IN";
	/** Post = PO */
	public static final String DOCACTION_Post = "PO";
	/** Prepare = PR */
	public static final String DOCACTION_Prepare = "PR";
	/** Reverse - Accrual = RA */
	public static final String DOCACTION_Reverse_Accrual = "RA";
	/** Reverse - Correct = RC */
	public static final String DOCACTION_Reverse_Correct = "RC";
	/** Re-activate = RE */
	public static final String DOCACTION_Re_Activate = "RE";
	/** Reject = RJ */
	public static final String DOCACTION_Reject = "RJ";
	/** Void = VO */
	public static final String DOCACTION_Void = "VO";
	/** Wait Complete = WC */
	public static final String DOCACTION_WaitComplete = "WC";
	/** Unlock = XL */
	public static final String DOCACTION_Unlock = "XL";
	/** Set Document Action.
		@param DocAction The targeted status of the document
	*/
	public void setDocAction (String DocAction)
	{

		set_Value (COLUMNNAME_DocAction, DocAction);
	}

	/** Get Document Action.
		@return The targeted status of the document
	  */
	public String getDocAction()
	{
		return (String)get_Value(COLUMNNAME_DocAction);
	}

	/** DocStatus AD_Reference_ID=131 */
	public static final int DOCSTATUS_AD_Reference_ID=131;
	/** Unknown = ?? */
	public static final String DOCSTATUS_Unknown = "??";
	/** Approved = AP */
	public static final String DOCSTATUS_Approved = "AP";
	/** Closed = CL */
	public static final String DOCSTATUS_Closed = "CL";
	/** Completed = CO */
	public static final String DOCSTATUS_Completed = "CO";
	/** Drafted = DR */
	public static final String DOCSTATUS_Drafted = "DR";
	/** Invalid = IN */
	public static final String DOCSTATUS_Invalid = "IN";
	/** In Progress = IP */
	public static final String DOCSTATUS_InProgress = "IP";
	/** Not Approved = NA */
	public static final String DOCSTATUS_NotApproved = "NA";
	/** Reversed = RE */
	public static final String DOCSTATUS_Reversed = "RE";
	/** Voided = VO */
	public static final String DOCSTATUS_Voided = "VO";
	/** Waiting Confirmation = WC */
	public static final String DOCSTATUS_WaitingConfirmation = "WC";
	/** Waiting for Management = WM */
	public static final String DOCSTATUS_WaitingForManagement = "WM";
	/** Waiting Payment = WP */
	public static final String DOCSTATUS_WaitingPayment = "WP";
	/** Set Document Status.
		@param DocStatus The current status of the document
	*/
	public void setDocStatus (String DocStatus)
	{

		set_Value (COLUMNNAME_DocStatus, DocStatus);
	}

	/** Get Document Status.
		@return The current status of the document
	  */
	public String getDocStatus()
	{
		return (String)get_Value(COLUMNNAME_DocStatus);
	}

	/** Set Document No.
		@param DocumentNo Document sequence number of the document
	*/
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo()
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Fee Numbers.
		@param FeeNumbers Fee Numbers
	*/
	public void setFeeNumbers (int FeeNumbers)
	{
		set_Value (COLUMNNAME_FeeNumbers, Integer.valueOf(FeeNumbers));
	}

	/** Get Fee Numbers.
		@return Fee Numbers	  */
	public int getFeeNumbers()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FeeNumbers);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Semestral = 180 */
	public static final String FEEFREQUENCY_Semestral = "180";
	/** Mensual = 30 */
	public static final String FEEFREQUENCY_Mensual = "30";
	/** Anual = 360 */
	public static final String FEEFREQUENCY_Anual = "360";
	/** Bi-Mensual = 60 */
	public static final String FEEFREQUENCY_Bi_Mensual = "60";
	/** Trimestral = 90 */
	public static final String FEEFREQUENCY_Trimestral = "90";
	/** Set Fee frequency.
		@param Feefrequency Fee frequency
	*/
	public void setFeefrequency (String Feefrequency)
	{

		set_Value (COLUMNNAME_Feefrequency, Feefrequency);
	}

	/** Get Fee frequency.
		@return Fee frequency	  */
	public String getFeefrequency()
	{
		return (String)get_Value(COLUMNNAME_Feefrequency);
	}

	/** Set Comment/Help.
		@param Help Comment or Hint
	*/
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp()
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Bank Loan.
		@param ING_BankLoan_ID Bank Loan
	*/
	public void setING_BankLoan_ID (int ING_BankLoan_ID)
	{
		if (ING_BankLoan_ID < 1)
			set_ValueNoCheck (COLUMNNAME_ING_BankLoan_ID, null);
		else
			set_ValueNoCheck (COLUMNNAME_ING_BankLoan_ID, Integer.valueOf(ING_BankLoan_ID));
	}

	/** Get Bank Loan.
		@return Bank Loan	  */
	public int getING_BankLoan_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ING_BankLoan_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ING_BankLoan_UU.
		@param ING_BankLoan_UU ING_BankLoan_UU
	*/
	public void setING_BankLoan_UU (String ING_BankLoan_UU)
	{
		set_Value (COLUMNNAME_ING_BankLoan_UU, ING_BankLoan_UU);
	}

	/** Get ING_BankLoan_UU.
		@return ING_BankLoan_UU	  */
	public String getING_BankLoan_UU()
	{
		return (String)get_Value(COLUMNNAME_ING_BankLoan_UU);
	}

	public org.compiere.model.I_C_DocType getING_DocTypeReceipt() throws RuntimeException
	{
		return (org.compiere.model.I_C_DocType)MTable.get(getCtx(), org.compiere.model.I_C_DocType.Table_ID)
			.getPO(getING_DocTypeReceipt_ID(), get_TrxName());
	}

	/** Set Document Type for Receipt.
		@param ING_DocTypeReceipt_ID Document Type for Receipt
	*/
	public void setING_DocTypeReceipt_ID (int ING_DocTypeReceipt_ID)
	{
		if (ING_DocTypeReceipt_ID < 1)
			set_Value (COLUMNNAME_ING_DocTypeReceipt_ID, null);
		else
			set_Value (COLUMNNAME_ING_DocTypeReceipt_ID, Integer.valueOf(ING_DocTypeReceipt_ID));
	}

	/** Get Document Type for Receipt.
		@return Document Type for Receipt	  */
	public int getING_DocTypeReceipt_ID()
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ING_DocTypeReceipt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Interest in percent.
		@param InterestPercent Percentage interest to charge on overdue invoices
	*/
	public void setInterestPercent (BigDecimal InterestPercent)
	{
		set_Value (COLUMNNAME_InterestPercent, InterestPercent);
	}

	/** Get Interest in percent.
		@return Percentage interest to charge on overdue invoices
	  */
	public BigDecimal getInterestPercent()
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InterestPercent);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Approved.
		@param IsApproved Indicates if this document requires approval
	*/
	public void setIsApproved (boolean IsApproved)
	{
		set_ValueNoCheck (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved()
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processed.
		@param Processed The document has been processed
	*/
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed()
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now
	*/
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing()
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null)
		{
			 if (oo instanceof Boolean)
				 return ((Boolean)oo).booleanValue();
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Start Date.
		@param StartDate First effective day (inclusive)
	*/
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate()
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}
}