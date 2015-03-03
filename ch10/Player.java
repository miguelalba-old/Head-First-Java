// Example from page 281

package ch10;

public class Player {
  static int playerCount = 0;
  private String name;

  public Player(String n) {
    name = n;
    playerCount++;
  }
  
  public static void main(String[] args) {
    System.out.println(Player.playerCount);
    Player one = new Player("TigerWoods");
    System.out.println(Player.playerCount);
  }
  
}