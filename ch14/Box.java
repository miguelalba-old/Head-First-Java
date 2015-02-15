package ch14;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Box implements Serializable {
	private static final long serialVersionUID = 3676847168971166184L;
	private int width;
	private int height;
	
	public void setWidth(int w) {
		width = w;
	}
	
	public void setHeight(int h) {
		height = h;
	}
	
	public static void main(String[] args) {
		Box myBox = new Box();
		myBox.setWidth(50);
		myBox.setHeight(20);
		
		try {
			FileOutputStream fs = new FileOutputStream("foo.ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(myBox);
			os.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
