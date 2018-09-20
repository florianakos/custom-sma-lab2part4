package lab.two.part.four;

public class DNSClient {

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Usage: DNSClient host port url");
		}
		String host = args[0];
		int port;
		try {
			port = Integer.parseInt(args[1]);
		} catch(Exception e) {
			port = DNSService.DNS_SERVICE_PORT;
		}
		String url = args[2];
		MessageClient conn;
		try {
			conn = new MessageClient(host,port);
		} catch(Exception e) {
			System.err.println(e);
			return;
		}
		Message m = new Message();
		m.setType(DNSService.DNS_SERVICE_MESSAGE);
		m.setParam("url", url);
		m = conn.call(m);
		System.out.println("Resolved IP: " + m.getParam("resolved_ip"));
		System.out.println("Resolved by: " + m.getParam("resolved_by"));
		conn.disconnect();
	}
}