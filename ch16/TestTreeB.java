package ch16;

import java.util.Comparator;
import java.util.TreeSet;

public class TestTreeB {
	public static void main(String[] args) {
		new TestTreeB().go();
	}

	public void go() {
		Book b1 = new Book("How Cats Work");
		Book b2 = new Book("Remix Your Body");
		Book b3 = new Book("Finding Emo");

		BookCompare bCompare = new BookCompare();
		TreeSet<Book> tree = new TreeSet<Book>(bCompare);
		tree.add(b1);
		tree.add(b2);
		tree.add(b3);
		System.out.println(tree);
	}
}

class Book {
	String title;

	public Book(String t) {
		title = t;
	}
}

class BookCompare implements Comparator<Book> {
	public int compare(Book one, Book two) {
		return one.title.compareTo(two.title);
	}
}