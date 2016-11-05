package edu.pragmatic.bookstore;

import edu.pragmatic.interfaces.BookEntries;


public class Book implements BookEntries{
	private Integer bookId;
	private String catalogNumber;
	private String title;
	private String author;
	private Double price;
	private String publishing;
	private Boolean isForeignLiterature;	
	private int numbersOfBook;
	
	

	@Override
	public String getTitle() {
		return title;		
	}

	@Override
	public String getAuthor() {
		
		return author;
	}

	@Override
	public Double getPrice() {
		return price;
	}

	@Override
	public String getNameOfPublishing() {
		return publishing;
	}

	@Override
	public Boolean IsForeignLiterature() {
		return isForeignLiterature;
	}

	@Override
	public String addTitle(String title) {
		this.title =  title;
		return title;		
	}
	
	@Override
	public String addAuthor(String author) {
		this.author =  author;
		return author;		
	}

	@Override
	public Double setPrice(Double price) {
		this.price =  price;
		return price;
	}

	@Override
	public String addPublishing(String publishing) {
		this.publishing =  publishing;
		return publishing;
	}

	@Override
	public Boolean setIsForeignLiterature(Boolean isForeignLiterature) {
		this.isForeignLiterature =  isForeignLiterature;
		return isForeignLiterature;
	}

	@Override
	public Integer getBookId() {		
		return bookId;
	}

	@Override
	public String getCatalogNumber() {
		return catalogNumber;
	}

	@Override
	public int getNumbersOfBook() {
		return numbersOfBook;
	}

	@Override
	public String setCatalogNumber(String catalogNumber) {
		this.catalogNumber =  catalogNumber;
		return catalogNumber;
	}

	@Override
	public int setNumbersOfBook(int numbersOfBook) {
		this.numbersOfBook =  numbersOfBook;
		return numbersOfBook;
	}

	@Override
	public int setBookId(int bookId) {
		this.bookId =  bookId;
		return this.numbersOfBook;
	}


}
