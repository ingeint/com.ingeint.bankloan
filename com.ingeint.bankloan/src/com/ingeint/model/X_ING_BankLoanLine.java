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

/** Generated Model for ING_BankLoanLine
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_ING_BankLoanLine extends PO implements I_ING_BankLoanLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190521L;

    /** Standard Constructor */
    public X_ING_BankLoanLine (Properties ctx, int ING_BankLoanLine_ID, String trxName)
    {
      super (ctx, ING_BankLoanLine_ID, trxName);
      /** if (ING_BankLoanLine_ID == 0)
        {
			setING_BankLoanLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_ING_BankLoanLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_ING_BankLoanLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Charge getC_ChargeForInterest() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getC_ChargeForInterest_ID(), get_TrxName());	}

	/** Set Charge for Interest.
		@param C_ChargeForInterest_ID Charge for Interest	  */
	public void setC_ChargeForInterest_ID (int C_ChargeForInterest_ID)
	{
		if (C_ChargeForInterest_ID < 1) 
			set_Value (COLUMNNAME_C_ChargeForInterest_ID, null);
		else 
			set_Value (COLUMNNAME_C_ChargeForInterest_ID, Integer.valueOf(C_ChargeForInterest_ID));
	}

	/** Get Charge for Interest.
		@return Charge for Interest	  */
	public int getC_ChargeForInterest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ChargeForInterest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
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
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Capital.
		@param Capital Capital	  */
	public void setCapital (BigDecimal Capital)
	{
		set_Value (COLUMNNAME_Capital, Capital);
	}

	/** Get Capital.
		@return Capital	  */
	public BigDecimal getCapital () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Capital);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Due Date.
		@param DueDate 
		Date when the payment is due
	  */
	public void setDueDate (Timestamp DueDate)
	{
		set_Value (COLUMNNAME_DueDate, DueDate);
	}

	/** Get Due Date.
		@return Date when the payment is due
	  */
	public Timestamp getDueDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DueDate);
	}

	/** Set Fee Amount.
		@param FeeAmt 
		Fee amount in invoice currency
	  */
	public void setFeeAmt (BigDecimal FeeAmt)
	{
		set_Value (COLUMNNAME_FeeAmt, FeeAmt);
	}

	/** Get Fee Amount.
		@return Fee amount in invoice currency
	  */
	public BigDecimal getFeeAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FeeAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set GenLoanPayment.
		@param GenLoanPayment GenLoanPayment	  */
	public void setGenLoanPayment (String GenLoanPayment)
	{
		set_Value (COLUMNNAME_GenLoanPayment, GenLoanPayment);
	}

	/** Get GenLoanPayment.
		@return GenLoanPayment	  */
	public String getGenLoanPayment () 
	{
		return (String)get_Value(COLUMNNAME_GenLoanPayment);
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Bank Loan Line.
		@param ING_BankLoanLine_ID Bank Loan Line	  */
	public void setING_BankLoanLine_ID (int ING_BankLoanLine_ID)
	{
		if (ING_BankLoanLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ING_BankLoanLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ING_BankLoanLine_ID, Integer.valueOf(ING_BankLoanLine_ID));
	}

	/** Get Bank Loan Line.
		@return Bank Loan Line	  */
	public int getING_BankLoanLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ING_BankLoanLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set ING_BankLoanLine_UU.
		@param ING_BankLoanLine_UU ING_BankLoanLine_UU	  */
	public void setING_BankLoanLine_UU (String ING_BankLoanLine_UU)
	{
		set_Value (COLUMNNAME_ING_BankLoanLine_UU, ING_BankLoanLine_UU);
	}

	/** Get ING_BankLoanLine_UU.
		@return ING_BankLoanLine_UU	  */
	public String getING_BankLoanLine_UU () 
	{
		return (String)get_Value(COLUMNNAME_ING_BankLoanLine_UU);
	}

	public com.ingeint.model.I_ING_BankLoan getING_BankLoan() throws RuntimeException
    {
		return (com.ingeint.model.I_ING_BankLoan)MTable.get(getCtx(), com.ingeint.model.I_ING_BankLoan.Table_Name)
			.getPO(getING_BankLoan_ID(), get_TrxName());	}

	/** Set Bank Loan.
		@param ING_BankLoan_ID Bank Loan	  */
	public void setING_BankLoan_ID (int ING_BankLoan_ID)
	{
		if (ING_BankLoan_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_ING_BankLoan_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_ING_BankLoan_ID, Integer.valueOf(ING_BankLoan_ID));
	}

	/** Get Bank Loan.
		@return Bank Loan	  */
	public int getING_BankLoan_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ING_BankLoan_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Interest Amount.
		@param InterestAmt 
		Interest Amount
	  */
	public void setInterestAmt (BigDecimal InterestAmt)
	{
		set_Value (COLUMNNAME_InterestAmt, InterestAmt);
	}

	/** Get Interest Amount.
		@return Interest Amount
	  */
	public BigDecimal getInterestAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InterestAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Interest Default.
		@param InterestDefault Interest Default	  */
	public void setInterestDefault (BigDecimal InterestDefault)
	{
		set_Value (COLUMNNAME_InterestDefault, InterestDefault);
	}

	/** Get Interest Default.
		@return Interest Default	  */
	public BigDecimal getInterestDefault () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InterestDefault);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Interest Variance.
		@param InterestVariance Interest Variance	  */
	public void setInterestVariance (BigDecimal InterestVariance)
	{
		set_Value (COLUMNNAME_InterestVariance, InterestVariance);
	}

	/** Get Interest Variance.
		@return Interest Variance	  */
	public BigDecimal getInterestVariance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InterestVariance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Paid.
		@param IsPaid 
		The document is paid
	  */
	public void setIsPaid (boolean IsPaid)
	{
		set_Value (COLUMNNAME_IsPaid, Boolean.valueOf(IsPaid));
	}

	/** Get Paid.
		@return The document is paid
	  */
	public boolean isPaid () 
	{
		Object oo = get_Value(COLUMNNAME_IsPaid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Open Amount.
		@param OpenAmt 
		Open item amount
	  */
	public void setOpenAmt (BigDecimal OpenAmt)
	{
		set_Value (COLUMNNAME_OpenAmt, OpenAmt);
	}

	/** Get Open Amount.
		@return Open item amount
	  */
	public BigDecimal getOpenAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OpenAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set PartialPayment.
		@param PartialPayment PartialPayment	  */
	public void setPartialPayment (BigDecimal PartialPayment)
	{
		set_Value (COLUMNNAME_PartialPayment, PartialPayment);
	}

	/** Get PartialPayment.
		@return PartialPayment	  */
	public BigDecimal getPartialPayment () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PartialPayment);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}