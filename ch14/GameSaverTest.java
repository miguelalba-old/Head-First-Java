package ch14;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameSaverTest {
	
	public static void main(String[] args) {
		// Make some characters
		GameCharacter one = new GameCharacter(50, "Elf",
			new String[] {"bow", "sword", "dust"});
		GameCharacter two = new GameCharacter(200, "Troll",
			new String[] {"bare hands", "big ax"});
		GameCharacter three = new GameCharacter(120, "Magician",
			new String[]{"spells", "invisibility"});
		
		try {
			ObjectOutputStream os = new ObjectOutputStream(
				new FileOutputStream("Game.ser"));
			os.writeObject(one);
			os.writeObject(two);
			os.writeObject(three);
			os.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		// We set them to null so we can't access the objects on the heap
		one = null;
		two = null;
		three = null;
		
		// Now read them back in from the file
		try {
			ObjectInputStream is = new ObjectInputStream(
				new FileInputStream("Game.ser"));
			GameCharacter oneRestore = (GameCharacter) is.readObject();
			GameCharacter twoRestore = (GameCharacter) is.readObject();
			GameCharacter threeRestore = (GameCharacter) is.readObject();
			
			System.out.println("One's type: " + oneRestore.getType());
			System.out.println("Two's type: " + twoRestore.getType());
			System.out.println("Three's type: " + threeRestore.getType());

			System.out.println("One's weapons: " + oneRestore.getWeapons());
			System.out.println("Two's weapons: " + twoRestore.getWeapons());
			System.out.println("Three's weapons: " + threeRestore.getWeapons());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
