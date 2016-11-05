package edu.pragmatic.enums;

public enum EnumBooks {
	
	FOREIGN("foreign"),
    LOCAL("local");
	
	private String whereFromBook;
	
	private EnumBooks(String whereFromBook) {
		this.whereFromBook = whereFromBook; 
	}

}
