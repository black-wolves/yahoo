package com.blackwolves.mail.util;

public interface Constant {

	public static final String ROUTE = "/var/www/";
	public static final String EMPTY_STRING = "";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String BLANK_SPACE = " ";
	public static final String GENERATE_CAMPAIGN = "/bin/sh /Users/gastondapicetest.sh";

	public interface Yahoo{
		public static final String IMAP_YAHOO = "imap.mail.yahoo.com";
		public static final String PICKUP_ROUTE = "/ramcache/pmta/pickup/";
		public static final String INBOX = "Inbox";
		public static final String BODIES = "Bodies";

		public static final String HOST = "smtp.mail.yahoo.com";
		public static final String PORT = "587";
		public static final String CONTENT_TYPE = "text/html; charset=utf-8";
		public static final String CONTENT_TRANSFER_ENCODING = "7bit";
	}
	
	
	
}
