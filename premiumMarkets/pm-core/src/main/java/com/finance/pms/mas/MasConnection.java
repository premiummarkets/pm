/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.finance.pms.mas;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.threads.SourceClient;



// TODO: Auto-generated Javadoc
/**
 * The Class MasConnection.
 * 
 * @author Guillaume Thoreton
 */
public class MasConnection implements SourceClient {
	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(MasConnection.class);
	
	/** The Constant LOGIN_REQUEST. */
	public static final int LOGIN_REQUEST = 6;
	
	/** The Constant LOGOUT_REQUEST. */
	public static final int LOGOUT_REQUEST = 8;
	
	// Server response IDs
	/** The Constant ERROR. */
	public static final int ERROR = 101;
	
	/** The Constant OK. */
	public static final int OK = 102;
	
	/** The Constant INVALID_SYMBOL. */
	public static final int INVALID_SYMBOL = 103;
	
	/** The Constant WARNING. */
	public static final int WARNING = 104;
	
	// Server response strings
	/** The Constant NO_OPEN_SESSION. */
	public static final String NO_OPEN_SESSION = "no_open";
	
	/** The Constant Open_interest_flag. */
	public static final String Open_interest_flag = "oi";
	
	// String constants
	/** The Constant EOM. */
	public static final String EOM = "";
	
	/** The Constant Eot. */
	public static final String Eot = "";
	
	/** The Constant Compression_on_flag. */
	public static final String Compression_on_flag = "<@z@>";
	
	/** The Constant Message_record_separator. */
	public static final String Message_record_separator = "\n";
	
	//Variables
	/** The _hostname. */
	private String _hostname;
	
	/** The _port number. */
	private Integer _portNumber;
	//private Socket socket;
	//private SocketChannel socketc;
	/** The session key. */
	private Integer sessionKey;
	//private PrintWriter out;			// output to server via io_connection
	//private BufferedReader in;				// input from server via io_connection
	/** The connection timeout. */
	private int connectionTimeout;


	// Precondition: io_conn != null
	/**
	 * Instantiates a new mas connection.
	 * 
	 * @param hostname the hostname
	 * @param port the port
	 * @param loginParams the login params
	 * @param connectionTimeout the connection timeout
	 * 
	 * @throws RestartServerException the restart server exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 * @author Guillaume Thoreton
	 */
	public MasConnection(String hostname, Integer port, String loginParams,int connectionTimeout) throws RestartServerException,IOException {
		this._hostname=hostname;
		this._portNumber=port;
		this.connectionTimeout = connectionTimeout;
		
		try {
				LOGGER.debug("Login params : "+loginParams);
				this.login(loginParams);
		} catch (RestartServerException s) {
			throw s;
		} catch (IOException e) {
				LOGGER.debug("",e);
				throw e;
		}

	}
	
	//login params
	//start_date      daily   now - 5 years   
	//start_date      hourly  2000/07/01      
	//start_date      30-minute       2000/08/01  
	//start_date       15-minute       2000/08/14      
	//start_date      10-minute       2000/08/14      
	//start_date      5-minute    2000/08/14       
	//start_date      weekly  now - 4 years   
	//start_date      monthly now - 8 years   
	//start_date      quarterly   now - 10 years 
	//end_date        daily   now
	
	//cf /root/.mas/.ma_clientrc
	/**
	 * Login.
	 * 
	 * @param lParams the l params
	 * 
	 * @return the string
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws RestartServerException the restart server exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public String login(String lParams) throws IOException, RestartServerException {
		
		DataInspector response;
		Socket soc = null;
		try {
			soc = connect();
			sendMsg(LOGIN_REQUEST,lParams, 0, soc);
			response = receiveMsg(soc);
			LOGGER.debug("logged in :"+response.getAckNumber() + " ; "+ response.getMessage());
			if (response.getAckNumber() != OK ) {
				// Failure of login request is a fatal error.
				throw new IOException (response.toString());
			}
		} catch (RestartServerException e1) {
			LOGGER.error("",e1);
			throw e1;
		} catch (IOException e) {
			LOGGER.error("",e);
			throw new IOException("FATAL : Attempt to login to server failed: " + e);
		} finally {
			disconnect(soc);
		}
	
		sessionKey = initSession(response.getMessage());
		
		return response.getMessage();
	}

	// Send a logout request to the server to end the current session.
	// Precondition:  logged_in()
	/**
	 * Logout.
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 * @author Guillaume Thoreton
	 */
	public void logout() throws IOException {
		
		Socket soc = null;
		try {
			soc = connect();
			sendMsg(LOGOUT_REQUEST, new String(), sessionKey.intValue(),soc);
		} catch (RestartServerException e) {
			LOGGER.debug("Silent logout",e);
		} catch (IOException e) {
			LOGGER.debug("Silent logout",e);
		} finally {
			disconnect(soc);
		}
	}
	

	/**
	 * Disconnect.
	 * 
	 * @param socket the socket
	 * 
	 * @author Guillaume Thoreton
	 */
	public void disconnect(Socket socket) {
		try {
			if (socket != null ) {
				socket.shutdownInput();
				socket.shutdownOutput();
				socket.close();
			}
		} catch (IOException e1) {
			LOGGER.debug("Silent disconnection",e1);
		}
	}

	// Send a request to the server.
	// Precondition:  logged_in()
	// Postcondition: `result()' gives the data resulting from this request.
	/**
	 * Send request.
	 * 
	 * @param request_code the request_code
	 * @param request the request
	 * 
	 * @return the data inspector
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws RestartServerException the restart server exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public DataInspector sendRequest(int request_code, String request)
			throws IOException,RestartServerException {
		DataInspector retour;
		Socket soc = null;
		try {
			soc = connect();
			sendMsg(request_code, request, sessionKey.intValue(),soc);
			retour = receiveMsg(soc);
		} catch (SocketTimeoutException stE) {
			LOGGER.debug("",stE);
			throw new RestartServerException("No response : Mas Server is in time out");
		} catch (RestartServerException e1) {
			LOGGER.debug("",e1);
			throw e1;
		} catch (IOException e) {
			LOGGER.error("Error : "+this._hostname+":"+this._portNumber,e);
			LOGGER.error("Error : Request code : "+request_code+": Request : "+request,e);
			throw e;
		} finally {
			disconnect(soc);
		}
		
		return retour;
	}

	// Receive the current pending message from the server.
	/**
	 * Receive msg.
	 * 
	 * @param socket the socket
	 * 
	 * @return the data inspector
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws RestartServerException the restart server exception
	 * 
	 * @author Guillaume Thoreton
	 */
	private DataInspector receiveMsg(Socket socket) throws IOException,RestartServerException {

		DataInspector msgScanner;
		
		if(socket.isConnected()){
			
			BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
			//in.reset();
			try {
				msgScanner = new DataInspector(in);
				int ack = msgScanner.getAckNumber();
				
				switch(ack)  {
					case OK : 
					case WARNING :
						break;
					case ERROR :
						System.err.println(msgScanner.getMessage());
						// This error means there is a problem with the protocol of the
						// last request passed to the server.  Since this is a coding
						// error (probably in the client), it is treated as fatal.
						throw new IOException("This error means there is a problem with the protocol of the "+
						 "last request passed to the server.  Since this is a coding "+
						 "error (probably in the client), it is treated as fatal. Request Result : " 
						 + msgScanner.getMessage());
						//break;
					case INVALID_SYMBOL :
						System.err.println("Invalid symbol : "+msgScanner.getMessage());
						break;
					default :
						System.err.println("Fatal error: received invalid message ID from server: " 
								+ ack);
						throw new IOException("Fatal error: received invalid message ID from server: " 
								+ ack);
				}
			} finally {
				//in.close();
				//in.mark(512);
			}
		} else {
			throw new IOException("Connection closed ???");
		}
		
		return msgScanner;
	}

	// Send the `msgID', the session key, and `msg' - with field delimiters.
	/**
	 * Send msg.
	 * 
	 * @param msgID the msg id
	 * @param msg the msg
	 * @param session_key the session_key
	 * @param socket the socket
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 * @author Guillaume Thoreton
	 */
	private void sendMsg(int msgID, String msg, int session_key, Socket socket) throws IOException {
		
		if (socket.isConnected()) {	
			PrintWriter out = new PrintWriter(socket.getOutputStream(), false);	
			try {
				out.print(msgID);
				out.print(MasSource.MESSAGE_FIELD_SEPARATOR + session_key);
				out.print(MasSource.MESSAGE_FIELD_SEPARATOR + msg);
				out.print(EOM);
			} finally {
				out.flush();
				//out.close();
			}
		} else {
			throw new IOException("Connection closed ???");
		}

	}
	

	/**
	 * Connect.
	 * 
	 * @return the socket
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws RestartServerException the restart server exception
	 * 
	 * @author Guillaume Thoreton
	 */
	public Socket connect() throws IOException,RestartServerException {
		
		Socket socket;
		
		try {
			socket = new Socket();
			socket.setSoTimeout(connectionTimeout);
			socket.connect(new InetSocketAddress(_hostname, _portNumber.intValue()));

			//out = new PrintWriter(socket.getOutputStream(), false);
			//in = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
			//in.mark(256);
		}
		catch (UnknownHostException e) {
			throw new UnknownHostException("Don't know about host: " +
					_hostname);
		}
		catch (IOException e) {
			LOGGER.error("Couldn't get I/O for the connection to : "+_hostname+":"+ _portNumber.intValue(),e);
			LOGGER.error("Couldn't get I/O for the connection, details : "+ e.getMessage(),e);
			throw new RestartServerException("Couldn't get I/O for the connection to: " +
					_hostname+":"+ _portNumber.intValue() + "\n(" + e + ")");
		}
		
		return socket;
	}


	
	/**
	 * Inits the session.
	 * 
	 * @param message the message
	 * 
	 * @return the integer
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * 
	 * @author Guillaume Thoreton
	 */
	private Integer initSession(String message) throws IOException {
		StringTokenizer t = new StringTokenizer(message, MasSource.MESSAGE_FIELD_SEPARATOR);
		Integer retour;
		String s = new String();
		
		if (!t.hasMoreTokens()) {
			throw new IOException("Received empty key from server.");
		}
		// Obtain the session key.
		try {
			s = t.nextToken();
			retour = Integer.valueOf(s);
		}
		catch (Exception e) {
			throw new IOException("Received invalid key from " +
				"server: " + s + " - " + e);
		}
		// Obtain any session state information sent by the server.
		while (t.hasMoreTokens()) {
			s = t.nextToken();
			LOGGER.debug("Server login Response : "+ s);
			if (s.equals(NO_OPEN_SESSION)) {
				throw new IOException("Not Connected");
			}
		}
		
		return retour;
	}


	/**
	 * Gets the _port number.
	 * 
	 * @return the _port number
	 */
	public Integer get_portNumber() {
		return _portNumber;
	}
}
