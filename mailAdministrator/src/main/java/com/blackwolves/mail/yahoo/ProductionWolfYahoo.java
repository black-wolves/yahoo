/**
 * 
 */
package com.blackwolves.mail.yahoo;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.blackwolves.mail.util.Constant;

/**
 * @author gastondapice
 *
 */
public class ProductionWolfYahoo extends WolfYahoo {

	/* (non-Javadoc)
	 * @see com.blackwolves.mail.yahoo.WolfYahoo#generateAndSendMail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
//	@Override
//	public void generateAndSendMail(String user, String pass, String offerFrom,
//			String to, String subject, String body) {
//		super.generateAndSendMail(user, pass, offerFrom, to, subject, body);
//
//	}

	/*
	 * (non-Javadoc)
	 * @see com.blackwolves.mail.yahoo.WolfYahoo#readEmailsAndGenerateBodies(java.lang.String, int, int)
	 */
	@Override
	public void readEmailsAndGenerateBodies(String offer) {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		Session session = Session.getDefaultInstance(props, null);
		try {
			Store store = session.getStore("imaps");
			// IMAP host for yahoo.
			store.connect(Constant.Yahoo.IMAP_YAHOO, "yaninadefays03@yahoo.com", "wolf2015.");
			Folder bodiesFolder = store.getFolder(offer);
			bodiesFolder.open(Folder.READ_ONLY);
			Message msg[] = bodiesFolder.getMessages();
			StringBuilder mail = new StringBuilder();
			String vmta = "awu9";
//			List<String[]> contacts;
//			contacts = generateList("/root/blackwolves/lists/" + offer + "/" , "sup");
//				for (String[] contact : contacts) {
//			for (; from < to; from++) {
			for (int i = 0; i < msg.length; i++) {
//				String[] contact = contacts.get(from);
//				String[] c = contact[0].split("\\|");
				mail = new StringBuilder();
				mail.append("x-virtual-mta: " + vmta);
//				int radomBody = randInt(0, msg.length-1);
				Message message = msg[i];
				iterateHeaders(message, mail);
				mail.append("\n");
				mail.append("\n");
				mail.append(message.getContent());
				PrintWriter out = new PrintWriter(Constant.Yahoo.PICKUP_ROUTE + "897/" + i);
				out.println(mail);
				out.close();
			}
			bodiesFolder.close(true);
			store.close();
		} catch (NoSuchProviderException e) {
			logger.error(e.getMessage(), e);
		} catch (MessagingException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}


}
