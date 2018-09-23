package jp.co.sparkworks.restaurant.backoffice.controller.param;

public class HelloWorld {

	private long id;
	private String content;

	public HelloWorld() {
		// constructor Code
		// hahaha test for slack!!!
		// test again
	}

	public HelloWorld(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public String toString() {
		return "HelloWorld<<" + id + "-" + content + ">>";
	}
}
