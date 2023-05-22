package tw.com.example.rest.vo;

public class Ma002Response {
	
	private Ma002ResponseHeader header;
	
	public Ma002ResponseHeader getHeader() {
		return header;
	}

	public void setHeader(Ma002ResponseHeader header) {
		this.header = header;
	}

	public Ma002ResponseBody getBody() {
		return body;
	}

	public void setBody(Ma002ResponseBody body) {
		this.body = body;
	}

	private Ma002ResponseBody body;
	
}
