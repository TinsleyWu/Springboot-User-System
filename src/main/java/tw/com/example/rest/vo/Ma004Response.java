package tw.com.example.rest.vo;

public class Ma004Response {
	
	private Ma004ResponseHeader header;
	
	public Ma004ResponseHeader getHeader() {
		return header;
	}

	public void setHeader(Ma004ResponseHeader header) {
		this.header = header;
	}

	public Ma004ResponseBody getBody() {
		return body;
	}

	public void setBody(Ma004ResponseBody body) {
		this.body = body;
	}

	private Ma004ResponseBody body;
	
}
