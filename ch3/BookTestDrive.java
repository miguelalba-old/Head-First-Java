// Be the compiler exercise
package ch3;

class Book {
	public String title;
	public String author;
}

class BookTestDrive {
	public static void main(String[] args) {
		Book[] myBooks = new Book[3];

		myBooks[0] = new Book();
		myBooks[0].title = "The Grapes of Java";
		myBooks[0].author = "bob";

		myBooks[1] = new Book();
		myBooks[1].title = "The Java Gastby";
		myBooks[1].author = "sue";

		myBooks[2] = new Book();
		myBooks[2].title = "The Java Cookbook";
		myBooks[2].author = "ian";

		for (Book book : myBooks) {
			System.out.println(book.title + " by " + book.author);
		}
	}

}