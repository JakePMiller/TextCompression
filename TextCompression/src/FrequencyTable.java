import java.util.HashMap;

public class FrequencyTable extends HashMap<Character, Integer> {
  /**
   * Constructs an empty table.
   */
  public FrequencyTable() {
    super();
  }
    
  /**
   * Constructs a table of character counts from the given text string.
   */
  public FrequencyTable(String text) {
	  
	  for(int i = 0; i < text.length(); i++){
		   char ch = text.charAt(i);
		   Integer value = super.get(ch);
		   if(value == null)
               value = 0;
		   if(value != 0){
		     super.put(ch, value + 1);
		   }
		   else{
		     super.put(ch,1);
		   }
		}
  }
  
  /**
   * 
   * Returns the count associated with the given character. In the case that
   * there is no association of ch in the map, return 0.
   */
  @Override
  public Integer get(Object ch) {
      return getOrDefault(ch, 0);
  }
  
  
  
  public static void main(String args[]){
	  
  }
}
