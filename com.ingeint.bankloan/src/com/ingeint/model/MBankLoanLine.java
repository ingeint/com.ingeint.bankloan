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

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;


public class MBankLoanLine extends X_ING_BankLoanLine {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8133811233445746759L;

	public MBankLoanLine(Properties ctx, int ING_BankLoanLine_ID, String trxName) {
		super(ctx, ING_BankLoanLine_ID, trxName);
	}
	
	protected MBankLoan m_parent = null; 

	public MBankLoanLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public void setHeaderInfo(MBankLoan mBankLoan) {
		m_parent = mBankLoan;
	}

	public static void deleteLines(MBankLoan loan) {
		
		DB.getSQLValue(loan.get_TrxName(), "DELETE FROM "
				+ "ING_BankLoanLine WHERE ING_BankLoan_ID = ? ",loan.get_ID());
		
	}
}
