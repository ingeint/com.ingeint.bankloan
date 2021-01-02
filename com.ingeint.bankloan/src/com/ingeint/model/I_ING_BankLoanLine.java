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
package com.ingeint.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for ING_BankLoanLine
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_ING_BankLoanLine 
{

    /** TableName=ING_BankLoanLine */
    public static final String Table_Name = "ING_BankLoanLine";

    /** AD_Table_ID=1000102 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name C_ChargeForInterest_ID */
    public static final String COLUMNNAME_C_ChargeForInterest_ID = "C_ChargeForInterest_ID";

	/** Set Charge for Interest	  */
	public void setC_ChargeForInterest_ID (int C_ChargeForInterest_ID);

	/** Get Charge for Interest	  */
	public int getC_ChargeForInterest_ID();

	public org.compiere.model.I_C_Charge getC_ChargeForInterest() throws RuntimeException;

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name Capital */
    public static final String COLUMNNAME_Capital = "Capital";

	/** Set Capital	  */
	public void setCapital (BigDecimal Capital);

	/** Get Capital	  */
	public BigDecimal getCapital();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DueDate */
    public static final String COLUMNNAME_DueDate = "DueDate";

	/** Set Due Date.
	  * Date when the payment is due
	  */
	public void setDueDate (Timestamp DueDate);

	/** Get Due Date.
	  * Date when the payment is due
	  */
	public Timestamp getDueDate();

    /** Column name FeeAmt */
    public static final String COLUMNNAME_FeeAmt = "FeeAmt";

	/** Set Fee Amount.
	  * Fee amount in invoice currency
	  */
	public void setFeeAmt (BigDecimal FeeAmt);

	/** Get Fee Amount.
	  * Fee amount in invoice currency
	  */
	public BigDecimal getFeeAmt();

    /** Column name GenLoanPayment */
    public static final String COLUMNNAME_GenLoanPayment = "GenLoanPayment";

	/** Set GenLoanPayment	  */
	public void setGenLoanPayment (String GenLoanPayment);

	/** Get GenLoanPayment	  */
	public String getGenLoanPayment();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name ING_BankLoanLine_ID */
    public static final String COLUMNNAME_ING_BankLoanLine_ID = "ING_BankLoanLine_ID";

	/** Set Bank Loan Line	  */
	public void setING_BankLoanLine_ID (int ING_BankLoanLine_ID);

	/** Get Bank Loan Line	  */
	public int getING_BankLoanLine_ID();

    /** Column name ING_BankLoanLine_UU */
    public static final String COLUMNNAME_ING_BankLoanLine_UU = "ING_BankLoanLine_UU";

	/** Set ING_BankLoanLine_UU	  */
	public void setING_BankLoanLine_UU (String ING_BankLoanLine_UU);

	/** Get ING_BankLoanLine_UU	  */
	public String getING_BankLoanLine_UU();

    /** Column name ING_BankLoan_ID */
    public static final String COLUMNNAME_ING_BankLoan_ID = "ING_BankLoan_ID";

	/** Set Bank Loan	  */
	public void setING_BankLoan_ID (int ING_BankLoan_ID);

	/** Get Bank Loan	  */
	public int getING_BankLoan_ID();

	public com.ingeint.model.I_ING_BankLoan getING_BankLoan() throws RuntimeException;

    /** Column name InterestAmt */
    public static final String COLUMNNAME_InterestAmt = "InterestAmt";

	/** Set Interest Amount.
	  * Interest Amount
	  */
	public void setInterestAmt (BigDecimal InterestAmt);

	/** Get Interest Amount.
	  * Interest Amount
	  */
	public BigDecimal getInterestAmt();

    /** Column name InterestDefault */
    public static final String COLUMNNAME_InterestDefault = "InterestDefault";

	/** Set Interest Default	  */
	public void setInterestDefault (BigDecimal InterestDefault);

	/** Get Interest Default	  */
	public BigDecimal getInterestDefault();

    /** Column name InterestVariance */
    public static final String COLUMNNAME_InterestVariance = "InterestVariance";

	/** Set Interest Variance	  */
	public void setInterestVariance (BigDecimal InterestVariance);

	/** Get Interest Variance	  */
	public BigDecimal getInterestVariance();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsPaid */
    public static final String COLUMNNAME_IsPaid = "IsPaid";

	/** Set Paid.
	  * The document is paid
	  */
	public void setIsPaid (boolean IsPaid);

	/** Get Paid.
	  * The document is paid
	  */
	public boolean isPaid();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name OpenAmt */
    public static final String COLUMNNAME_OpenAmt = "OpenAmt";

	/** Set Open Amount.
	  * Open item amount
	  */
	public void setOpenAmt (BigDecimal OpenAmt);

	/** Get Open Amount.
	  * Open item amount
	  */
	public BigDecimal getOpenAmt();

    /** Column name PartialPayment */
    public static final String COLUMNNAME_PartialPayment = "PartialPayment";

	/** Set PartialPayment	  */
	public void setPartialPayment (BigDecimal PartialPayment);

	/** Get PartialPayment	  */
	public BigDecimal getPartialPayment();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
