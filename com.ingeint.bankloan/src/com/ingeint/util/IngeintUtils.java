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
package com.ingeint.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.MBroadcastMessage;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.SimplePDFViewer;
import org.compiere.model.MAccount;
import org.compiere.model.MBankAccount;
import org.compiere.model.MClient;
import org.compiere.model.MClientInfo;
import org.compiere.model.MInOut;
import org.compiere.model.MInvoice;
import org.compiere.model.MMailText;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.model.MProduction;
import org.compiere.model.MRequest;
import org.compiere.model.MRequisition;
import org.compiere.model.MSysConfig;
import org.compiere.model.MUser;
import org.compiere.model.MUserMail;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.EMail;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.idempiere.broadcast.BroadcastMsgUtil;

import com.ingeint.model.MBankLoan;
import com.ingeint.model.MBankLoanLine;

public class IngeintUtils {

	private static CLogger log = CLogger.getCLogger(IngeintUtils.class);

	/**
	 * Send Messages
	 * 
	 * @author Orlando Curieles - www.ingeint.com
	 * @param ctx        context
	 * @param AD_Org_ID  id
	 * @param Message    String
	 * @param AD_User_ID id
	 * @return boolean
	 */
	public static boolean SendMessagebyUser(Properties ctx, Integer AD_Org_ID, String Msg, Integer AD_User_ID) {

		MBroadcastMessage message = new MBroadcastMessage(ctx, 0, null);

		message.setTarget(MBroadcastMessage.TARGET_User);
		message.setBroadcastType(MBroadcastMessage.BROADCASTTYPE_ImmediatePlusLogin);
		message.setBroadcastFrequency(MBroadcastMessage.BROADCASTFREQUENCY_UntilAcknowledge);
		message.setBroadcastMessage(Msg);
		message.setAD_User_ID(AD_User_ID);
		message.saveEx();

		BroadcastMsgUtil.publishBroadcastMessage(message.get_ID(), null);

		return true;
	}

	public static boolean SendMessagebyRole(Properties ctx, Integer AD_Org_ID, String Msg, Integer AD_Role_ID) {

		MBroadcastMessage message = new MBroadcastMessage(ctx, 0, null);

		message.setTarget(MBroadcastMessage.TARGET_Role);
		message.setBroadcastType(MBroadcastMessage.BROADCASTTYPE_ImmediatePlusLogin);
		message.setBroadcastFrequency(MBroadcastMessage.BROADCASTFREQUENCY_UntilAcknowledge);
		message.setBroadcastMessage(Msg);
		message.setAD_Role_ID(AD_Role_ID);
		message.saveEx();

		BroadcastMsgUtil.publishBroadcastMessage(message.get_ID(), null);

		return true;
	}

	/**
	 * @author Orlando Curieles - www.ingeint.com
	 * @param ctx            context
	 * @param ctx
	 * @param AD_Org_ID
	 * @param AD_Table_ID
	 * @param Record_ID
	 * @param RequestType_ID
	 * @param content
	 * @param SalesRepID
	 * @param C_BPartner_ID
	 * @param isLoanRequest
	 * @param trxname
	 * @return
	 */
	public static String createRequest(Properties ctx, Integer AD_Org_ID, Integer AD_Table_ID, Integer Record_ID,
			Integer RequestType_ID, String content, Integer SalesRepID, Integer C_BPartner_ID, Boolean isLoanRequest,
			String trxname) {

		MRequest request = new MRequest(ctx, 0, trxname);
		request.setAD_Org_ID(AD_Org_ID);
		request.setAD_Table_ID(AD_Table_ID);
		request.setRecord_ID(Record_ID);
		request.setR_RequestType_ID(RequestType_ID);
		request.setSummary(content);
		request.setSalesRep_ID(SalesRepID);
		request.setC_BPartner_ID(C_BPartner_ID);
		request.set_ValueOfColumn("IsLoanRequest", isLoanRequest);
		request.saveEx();

		return request.getDocumentNo().toString();
	}

	/**
	 * @author Orlando Curieles - www.ingeint.com
	 * @param ctx                 context
	 * @param ctx
	 * @param AD_Org_ID
	 * @param AD_Table_ID
	 * @param Record_ID
	 * @param RequestType_ID
	 * @param content
	 * @param SalesRepID
	 * @param C_BPartner_ID
	 * @param isLoanRequest
	 * @param StartDate
	 * @param CloseDate
	 * @param R_RequestRelated_ID
	 * @param trxname
	 * @return
	 */
	public static MRequest createRequestAll(Properties ctx, Integer AD_Org_ID, Integer AD_Table_ID, Integer Record_ID,
			Integer RequestType_ID, String content, Integer SalesRepID, Integer C_BPartner_ID, Boolean isLoanRequest,
			Timestamp StartDate, Timestamp EndDate, Integer R_RequestRelated_ID, String trxname) {

		MRequest request = new MRequest(ctx, 0, trxname);
		request.setAD_Org_ID(AD_Org_ID);
		request.setAD_Table_ID(AD_Table_ID);
		request.setRecord_ID(Record_ID);
		request.setR_RequestType_ID(RequestType_ID);
		request.setSummary(content);
		request.setSalesRep_ID(SalesRepID);
		request.setC_BPartner_ID(C_BPartner_ID);
		request.setStartDate(StartDate);
		request.set_ValueOfColumn("IsLoanRequest", isLoanRequest);
		request.setDateNextAction(StartDate);
		request.setR_RequestRelated_ID(R_RequestRelated_ID);
		request.saveEx();

		return request;
	}

	/**
	 * @author Orlando Curieles - www.ingeint.com
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date calculateDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		int WeekDay = calendar.get(Calendar.DAY_OF_WEEK);

		if (WeekDay == 7)
			calendar.add(Calendar.DAY_OF_YEAR, 2);
		else if (WeekDay == 1)
			calendar.add(Calendar.DAY_OF_YEAR, 1);

		return calendar.getTime();
	}

	/**
	 * @author Orlando Curieles - www.ingeint.com
	 * @param date
	 * @param Integer hours
	 * @return
	 */
	public static Date calculateTime(Date fecha, int horas) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.HOUR, horas);
		return calendar.getTime();
	}

	/**
	 * Send createPayment
	 * 
	 * @author Orlando Curieles - www.ingeint.com
	 * @param line_ID          LineID
	 * @param loan_ID          Loan_ID
	 * @param C_BankAccount_ID C_BankAccount_ID
	 * @param AD_User_ID       id
	 * @return MPayment
	 */
	public static MPayment createPayment(Integer line_ID, Integer loan_ID, Integer C_BankAccount_ID, String type,
			Integer C_Charge_ID, BigDecimal amt, Integer C_DocType_ID, String description, Timestamp DateAcct,
			String trxName, Properties ctx) {

		MBankLoanLine line = null;

		if (line_ID > 0)
			line = new MBankLoanLine(ctx, line_ID, trxName);

		MBankLoan loan = new MBankLoan(ctx, loan_ID, trxName);
		MBankAccount ba = new MBankAccount(ctx, loan.getC_BankAccount_ID(), trxName);

		MPayment payment = new MPayment(null, 0, trxName);

		payment.setAD_Org_ID(loan.getAD_Org_ID());
		payment.setDateAcct(DateAcct);
		payment.setC_BPartner_ID(ba.get_ValueAsInt("C_BPartner_ID"));
		payment.setC_BankAccount_ID(ba.get_ID());
		payment.setDateTrx(DateAcct);
		payment.setTenderType(type);
		payment.setC_DocType_ID(C_DocType_ID);
		payment.setDescription(description);
		payment.setC_Currency_ID(100);

		if (line != null) {
			payment.setPayAmt(amt);
			payment.setC_Charge_ID(C_Charge_ID);
			payment.set_ValueOfColumn("ING_BankLoanLine_ID", line.get_ID());
			payment.setDescription(line.getHelp());
		} else {
			payment.setPayAmt(loan.getAmount());
			payment.setC_Charge_ID(C_Charge_ID);
			payment.set_ValueOfColumn("ING_BankLoan_ID", loan.get_ID());
			payment.setDescription(loan.getHelp());
		}

		payment.saveEx();

		return payment;
	}

	/**
	 * Send createPayment MPayScheduling_Line
	 * 
	 * @author Luis Perez - www.ingeint.com
	 * @param MInvoice     invoice
	 * @param MBankAccount bankAccount
	 * @param int          C_DocType_ID
	 * @param String       TenderType
	 * @param boolean      ToComplete
	 * @return MPayment
	 */
	public static MPayment createPayment(MInvoice invoice, MBankAccount bankAccount, int C_DocType_ID,
			String TenderType, Boolean ToComplete) {
		MPayment payment = new MPayment(invoice.getCtx(), 0, invoice.get_TrxName());

		payment.setAD_Org_ID(invoice.getAD_Org_ID());
		payment.setDateAcct(invoice.getDateAcct());
		payment.setC_BPartner_ID(invoice.getC_BPartner_ID());
		payment.setC_Invoice_ID(invoice.getC_Invoice_ID());
		payment.setC_Currency_ID(bankAccount.getC_Currency_ID());
		payment.setDateTrx(invoice.getDateAcct());
		payment.setTenderType(TenderType);
		payment.setC_DocType_ID(C_DocType_ID);
		payment.setDescription(invoice.getDescription());
		payment.setPayAmt(invoice.getOpenAmt());
		payment.setC_BankAccount_ID(bankAccount.getC_BankAccount_ID());

		payment.saveEx();

		if (ToComplete) {
			payment.prepareIt();
			payment.setDocAction(DocAction.ACTION_Complete);
			String StrTemp = payment.completeIt();
			if (!StrTemp.equals(DocAction.ACTION_Complete)) {
				throw new AdempiereException(Msg.getMsg(payment.getCtx(), payment.getProcessMsg()));
			}
			payment.setDocStatus(StrTemp);
			payment.setDocAction(StrTemp);
			payment.saveEx();
			payment.setProcessed(true);
		}

		return payment;
	}

	public static File genFileProdubancoPayroll(Integer HR_PaymentSelection_ID, String DocumentNo, String trx)
			throws SQLException, IOException {

		String ruta = "/tmp/" + DocumentNo + ".xls";
		File archivo = new File(ruta);
		FileWriter w = null;
		try {
			w = new FileWriter(archivo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(w);
		PrintWriter wr = new PrintWriter(bw);
		String linea = null;
		StringBuffer serial = new StringBuffer();

		StringBuffer sql = new StringBuffer();

		sql.append(
				" SELECT tipo, cuentaempresa, row_number() OVER (order by HR_PaymentSelection_ID) as secuencia, numcomprobante, taxid, moneda, payamt, " // 1
																																							// -
																																							// 7
						+ "formapago, routingno, accounttype, accountno, identificacion, name, direccion, " // 8 - 14
						+ "ciudad, tel, localidad, contactname, COALESCE(email,'') " // 15 - 19
						+ "FROM RV_TransferProdubanco WHERE HR_PaymentSelection_ID = ? ");

		PreparedStatement pstmt = DB.prepareStatement(sql.toString(), trx);
		pstmt.setInt(1, HR_PaymentSelection_ID);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			serial.append(rs.getString(1));
			serial.append('\t');
			serial.append(rs.getString(2));
			serial.append('\t');
			serial.append(rs.getString(3));
			serial.append('\t');
			serial.append(rs.getString(4));
			serial.append('\t');
			serial.append(rs.getString(5));
			serial.append('\t');
			serial.append(rs.getString(6));
			serial.append('\t');
			serial.append(rs.getString(7));
			serial.append('\t');
			serial.append(rs.getString(8));
			serial.append('\t');
			serial.append(rs.getString(9));
			serial.append('\t');
			serial.append(rs.getString(10));
			serial.append('\t');
			serial.append(rs.getString(11));
			serial.append('\t');
			serial.append(rs.getString(12));
			serial.append('\t');
			serial.append(rs.getString(13));
			serial.append('\t');
			serial.append(rs.getString(14));
			serial.append('\t');
			serial.append(rs.getString(15));
			serial.append('\t');
			serial.append(rs.getString(16));
			serial.append('\t');
			serial.append(rs.getString(17));
			serial.append('\t');
			serial.append(rs.getString(18));
			serial.append('\t');
			serial.append(rs.getString(19));
			serial.append('\n');
			linea = serial.toString();

		}
		wr.println(linea);// escribimos en el archivo
		wr.close();
		bw.close();
		rs.close();
		pstmt.close();

		return archivo;
	}

	public static String LPad(String str, Integer length, char car) {
		return str + String.format("%" + (length - str.length()) + "s", "").replace(" ", String.valueOf(car));
	}

	public static String RPad(String str, Integer length, char car) {
		return String.format("%" + (length - str.length()) + "s", "").replace(" ", String.valueOf(car)) + str;
	}

	public static void printShipmentPOS(MOrder order) {

		Window win;
		try {
			win = new SimplePDFViewer("NE #" + order.getDocumentNo(), new FileInputStream(order.createPDF()));
			win.setAttribute(Window.MODE_KEY, Window.MODE_EMBEDDED);
			SessionManager.getAppDesktop().showWindow(win, "center");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int diferenciaEnDias(Date fechaMayor, Date fechaMenor) {
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
		return (int) dias;
	}

	public static MProduction[] getProductionFromOrderLine(Integer C_OrderLine_ID, Properties ctx, String trx) {

		List<MProduction> productions = new Query(ctx, MProduction.Table_Name,
				"C_OrderLine_ID = ? AND DocStatus IN ('CO','CL')", trx).setParameters(C_OrderLine_ID).list();

		return productions.toArray(new MProduction[productions.size()]);
	}

	public static MInOut[] getInOutFromOrder(Integer C_Order_ID, Properties ctx, String trx) {

		List<MInOut> inouts = new Query(ctx, MInOut.Table_Name, "C_Order_ID = ? AND DocStatus IN ('CL','CO') ", trx)
				.setParameters(C_Order_ID).list();

		return inouts.toArray(new MInOut[inouts.size()]);
	}

	public static MPayment[] getPaymentFromInvoice(Properties ctx, Integer C_Invoice_ID, String trx) {

		List<MPayment> payments = new Query(ctx, MPayment.Table_Name, "C_Invoice_ID = ? AND DocStatus IN ('CO','CL')",
				trx).setParameters(C_Invoice_ID).list();

		return payments.toArray(new MPayment[payments.size()]);

	}

	public static MInvoice[] getInvoiceFromOrder(Properties ctx, Integer C_Order_ID, String trx) {

		List<MInvoice> invoices = new Query(ctx, MInvoice.Table_Name,
				"IsActive='Y' AND C_Order_ID = ? AND DocStatus IN ('CO','CL')", trx).setParameters(C_Order_ID).list();

		return invoices.toArray(new MInvoice[invoices.size()]);
	}

	public static int getProductByValue(String value, Integer AD_Client_ID) {

		Integer M_Product_ID = DB.getSQLValue(null,
				"SELECT M_Product_ID " + "FROM M_Product " + "WHERE VALUE = ? " + "		AND AD_Client_ID = ?",
				new Object[] { value, AD_Client_ID });

		return M_Product_ID;
	}

	public static int getMasiFromProduct(String trxName, String lot, int AD_Client_ID) {

		Integer lote = DB.getSQLValue(trxName,
				"SELECT m_attributesetinstance_ID FROM m_attributesetinstance " + "WHERE lot = ? AND AD_Client_ID = ? ",
				new Object[] { lot, AD_Client_ID });

		return lote;
	}

	public static MOrderLine[] getOrderLinestoUpdate(Properties ctx, int M_Product_ID, String Lot, int C_BPartner_ID,
			int AD_Client_ID, String trx) {

		List<MOrderLine> orderlines = new Query(ctx, MOrderLine.Table_Name,
				"QtyInvoiced <> QtyOrdered " + "AND M_Product_ID = ? " + "AND M_AttributeSetInstance_ID IN ("
						+ "		SELECT M_AttributeSetInstance_ID " + "		FROM M_AttributeSetInstance "
						+ "		WHERE Lot = ? " + "			AND AD_Client_ID = ? ) "
						+ "			AND C_Order_ID IN (SELECT C_Order_ID " + "				FROM C_Order "
						+ "				WHERE IssoTrx = 'Y' " + "					AND C_BPartner_ID = ? "
						+ "					AND DocStatus in ('CO','CL')) ",
				trx).setParameters(M_Product_ID, Lot, AD_Client_ID, C_BPartner_ID).setOrderBy("C_OrderLine_ID").list();

		return orderlines.toArray(new MOrderLine[orderlines.size()]);

	}

	public static String createEmail(MRequisition req) {

		MClient client = new MClient(req.getCtx(), req.getAD_Client_ID(), req.get_TrxName());
		Integer AD_User_ID = req.get_ValueAsInt("SalesRep_ID");

		MMailText mText = new MMailText(req.getCtx(), 0, req.get_TrxName());
		mText.setPO(req);
		MUser notifyTo = new MUser(req.getCtx(), AD_User_ID, req.get_TrxName());

		if (notifyTo.isNotificationEMail()) {

			String msg = null;
			String subject = "Requisicion generada # " + req.getDocumentNo();
			StringBuffer message = new StringBuffer();

			message.append("Se ha generado una nueva requisición de materiales que requiere su atención \n");
			String url = MSysConfig.getValue(MSysConfig.APPLICATION_URL, req.getAD_Client_ID());
			String lnk = "";
			if (url != null && !url.equals("USE_HARDCODED"))
				lnk = url + "?Action=Zoom&AD_Table_ID=" + req.get_Table_ID() + "&Record_ID=" + req.get_ID();

			message.append("\n");
			message.append("Encuentrelo en el siguiente hipervinculo: " + lnk);
			EMail email = client.createEMail(notifyTo.getEMail(), subject, message.toString());
			msg = email.send();
			MUserMail um = new MUserMail(mText, AD_User_ID, email);
			um.setSubject(subject);
			um.setMailText(message.toString());
			um.saveEx();

			if (msg.equals(EMail.SENT_OK))
				return msg.toString();
		}
		return null;
	}

	public static MAccount get_MAccountCreditCardDebit(Properties ctx, Integer AD_Org_ID, String trxName) {
		Integer AD_Client_ID = Env.getAD_Client_ID(ctx);
		Integer CreditCreditCard_ID = MSysConfig.getIntValue("CreditCreditCard_ID", 0, AD_Client_ID);
		MClientInfo cli = MClientInfo.get(ctx, AD_Client_ID);

		MAccount account = MAccount.get(ctx, AD_Client_ID, AD_Org_ID, cli.getC_AcctSchema1_ID(), CreditCreditCard_ID, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, trxName);
		return account;

	}

	public static MAccount get_MAccountDebitCCWH(Properties ctx, Integer AD_Org_ID, String trxName) {
		Integer AD_Client_ID = Env.getAD_Client_ID(ctx);
		Integer CreditCreditCard_ID = MSysConfig.getIntValue("CreditCreditCardCredit_ID", 0, AD_Client_ID);
		MClientInfo cli = MClientInfo.get(ctx, AD_Client_ID);

		MAccount account = MAccount.get(ctx, AD_Client_ID, AD_Org_ID, cli.getC_AcctSchema1_ID(), CreditCreditCard_ID, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, trxName);
		return account;
	}

	public static MAccount get_MAccountCreditCCWH(Properties ctx, Integer AD_Org_ID, String trxName) {

		Integer AD_Client_ID = Env.getAD_Client_ID(ctx);
		Integer CreditCreditCard_ID = MSysConfig.getIntValue("CreditCreditCardWHCredit_ID", 0, AD_Client_ID);
		MClientInfo cli = MClientInfo.get(ctx, AD_Client_ID);

		MAccount account = MAccount.get(ctx, AD_Client_ID, AD_Org_ID, cli.getC_AcctSchema1_ID(), CreditCreditCard_ID, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, trxName);
		return account;
	}

}
