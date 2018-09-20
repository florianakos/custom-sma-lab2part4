package lab.two.part.four;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class DNSService implements Deliverable {
	
	public static final int DNS_SERVICE_MESSAGE = 100;
	public static final int DNS_SERVICE_PORT = 1999;
	
	public Message send(Message m) {
		String address = resolveDNS(m.getParam("url"));
		m.setParam("resolved_ip", address + "\nCool isn't it? :D");
		m.setParam("resolved_by", "Flo");
		return m;
	}
	
	public String resolveDNS(String url) {
		InetAddress address;
		try {
			address = InetAddress.getByName(url);
			return address.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			return "Invalid hostname";
		} 
		
	}
	
	public static void main(String args[]) {
		DNSService ds = new DNSService();
		MessageServer ms;
		try {
			ms = new MessageServer(DNS_SERVICE_PORT);
		} catch(Exception e) {
			System.err.println("Could not start service " + e);
			return;
		}
		Thread msThread = new Thread(ms);
		ms.subscribe(DNS_SERVICE_MESSAGE, ds);
		msThread.start();
	}
}
