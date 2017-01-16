package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
    	if (c==r) 1
    	else if (c==0 || r==0) 1
    	else pascal(c-1, r-1)+pascal(c-1, r)  
    }     
    
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def loop(chars: List[Char], open: Int): Boolean = {
          if (chars.isEmpty) open == 0 // if (boolean) boolean => open == 0
          else if (chars.head == '(') loop(chars.tail,open+1) // if (boolean) boolean => loop(chars[1:], open+1)
          else if (chars.head == ')') open>0 && loop(chars.tail,open-1) // if (boolean) boolean=> open>0 && loop(chars[1:], open-1) requires that we have one open
          else loop(chars.tail,open) // move down list
      }
      loop(chars,0)
    }       
  
  /**
   * Exercise 3
   */
      def countChange(money: Int, coins: List[Int]): Int = { 
    		if (money<0 || coins.isEmpty) 0
    		else if (money == 0) 1
    		else countChange(money, coins.tail)+countChange(money-coins.head, coins)
      }    
  }
