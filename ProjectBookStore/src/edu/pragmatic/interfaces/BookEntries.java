package edu.pragmatic.interfaces;

public interface BookEntries {
   Integer	getBookId();
   String getTitle();
   String getAuthor();
   Double getPrice();
   String getCatalogNumber();
   String getNameOfPublishing();
   int getNumbersOfBook();
   Boolean IsForeignLiterature();
   String addTitle(String title);
   String addAuthor(String author);
   Double setPrice(Double price);
   String addPublishing(String publishing);
   Boolean setIsForeignLiterature(Boolean isForeignLiterature);  
   String setCatalogNumber(String catalogNumber); 
   int setNumbersOfBook(int numbersOfBook);
   int setBookId(int bookId);

   
}
