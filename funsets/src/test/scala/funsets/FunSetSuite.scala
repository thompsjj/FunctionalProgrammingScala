package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
     assert(1 + 2 === 3)
  }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val s6 = singletonSet(6)
    val s7 = singletonSet(7)
    val s8 = singletonSet(8)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      val s_a = union(s1, s2)
      val s_b = union(s_a, s3)
      val s_c = union(s_a, s4)
      
      
      assert(contains(s_a, 1), "Union 1")
      assert(contains(s_b, 2), "Union 2")
      assert(!contains(s_c, 3), "Union 3")
    }
  }

  test("difference between sets is detected"){
    new TestSets {
      val s = union(s1, s2)
      val s_a = union(s1, s2)
      val s_b = union(s_a, s3)
      val s_c = union(s_a, s4)
      
      assert(contains(diff(s_b, s_c),3), "diff union b and c")
      assert(contains(diff(s_c, s_b),4), "diff union c and b")
     
    }   
  }
  
  test("intersection between sets is detected"){
      new TestSets {
        
      val s = union(s1, s2)
      val s_a = union(s1, s3)
      val s_b = union(s1, s4)

      assert(contains(intersect(s_a, s_b),1), "intersect b and c == a")
     
    }    
  }
  
  test("filter successfully implemented"){
  
      new TestSets {
        
      val s_a = union(s1, s2)
      val s_b = union(s_a, s3)

      assert(contains(intersect(s_a, (x: Int) => x<2), 1), "filter {1, 2, 3}, x<2 == 1")
     
    }
  }
  
  test("map successfully implemented"){
    
     new TestSets {  
       
     
     val s_a = union(s1, union(s2, s3))
     val s_b = union(s2, union(s4, s6))
     
     assert(contains(map(s_a, (x: Int) => 2*x), 6), "map successful")
     }
  }
}
