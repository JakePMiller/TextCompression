import java.util.Comparator;
import java.util.Set;

/**
 * 
 * A HuffmanTree represents a variable-length code such that the shorter the
 * bit pattern associated with a character, the more frequently that character
 * appears in the text to be encoded.
 */

public class HuffmanTree {
  
  class Node {
    protected char key;
    protected int priority;
    protected Node left, right;
    
    public Node(int priority, char key) {
      this(priority, key, null, null);
    }
    
    public Node(int priority, Node left, Node right) {
      this(priority, '\0', left, right);
    }
    
    public Node(int priority, char key, Node left, Node right) {
      this.key = key;
      this.priority = priority;
      this.left = left;
      this.right = right;
    }
    
    public boolean isLeaf() {
      return left == null && right == null;
    }
  }
  
  protected Node root;
  
  /**
   * 
   * Creates a HuffmanTree from the given frequencies of letters in the
   * alphabet using the algorithm described in lecture.
   */
  public HuffmanTree(FrequencyTable charFreqs) {
    Comparator<Node> comparator = (x, y) -> {
      /**
       *  x comes before y if x's priority is less than y's priority
       */
    	
    	if(x.priority > y.priority){
    		return 1;
    	}
    	if(x.priority < y.priority){
    		return -1;
    	}
    	else
      return 0;
    };  
    
    PriorityQueue<Node> forest = new Heap<Node>(comparator);
    
    /**
     * Start by populating forest with leaves.
     */
    
    root = null;
    Set<Character> chars = charFreqs.keySet();
    for(char ch : chars){
        Node node = new Node(charFreqs.get(ch), ch);
        forest.insert(node);
    }
    while(forest.size() > 1){
        Node first = forest.delete();
        Node second = forest.delete();
        Node parent = new Node(first.priority + second.priority, first, second);
        forest.insert(parent);
        root = forest.peek();
    }
  }
  
  /**
   * 
   * Returns the character associated with the prefix of bits.
   * 
   * @throws DecodeException if bits does not match a character in the tree.
   */
  public char decodeChar(String bits){
      Node holder = root;
      char[] bit = bits.toCharArray();
      for(int i = 0; i<=bit.length-1; i++){
          if(holder != null && holder.isLeaf()){
              return holder.key;
          }
          char ch = bit[i];
          if(holder != null && ch == '1'){
              holder = holder.right;
          }
          if(holder != null && ch == '0'){
              holder = holder.left;
          }
      }
      if(holder!=null && holder.key != '\0'){
          return holder.key;
      }
      else{
          throw new DecodeException(bits);
      }
  }
    
  /**
   * 
   * Returns the bit string associated with the given character. Must
   * search the tree for a leaf containing the character. Every left
   * turn corresponds to a 0 in the code. Every right turn corresponds
   * to a 1. This function is used by CodeBook to populate the map.
   * 
   * @throws EncodeException if the character does not appear in the tree.
   */
  public String lookup(char ch) {
      Node holder = root;
      String bitstr = "";
      String str = lookupHelper(ch, holder, bitstr);
      if(str == null){
          throw new EncodeException(ch);
      }
      return str;
  }
  
  /**
   * This is the helper for lookup. 
   * Iterates through tree and determines the location.
   * 
   * @param ch
   * @param holder
   * @param bitstr
   * @return String of bits
   */
  private String lookupHelper(char ch, Node holder, String bitstr){
      if(holder == null){
          return null;
      }
      if(holder.isLeaf() && holder.key == ch){
          return bitstr;
      }
      String leftstr = lookupHelper(ch, holder.left, bitstr + "0");
      if(leftstr != null) {
          return leftstr;
      }
      return lookupHelper(ch, holder.right, bitstr + "1");
  }
}