package edu.kit.informatik;

import static org.junit.Assert.*;

import org.junit.Before;

public class Test {

  Tile t1;
  Tile t2;
  Tile t3;
  Tile t4;
  
  Tile k0;
  Tile k1;
  
  @Before
  public void before() {
    t1 = new Tile();
    t2 = new Tile(new LineType[] {LineType.NONE, LineType.YELLOW, LineType.NONE, LineType.NONE, LineType.NONE, LineType.YELLOW });
    t3 = new Tile(new LineType[] {LineType.NONE, LineType.YELLOW, LineType.RED, LineType.NONE, LineType.RED, LineType.YELLOW });
    t4 = t3.copy();
  
    k0 = new Tile(new LineType[] {LineType.RED, LineType.RED, LineType.GREEN, LineType.NONE, LineType.GREEN, LineType.NONE });
    k1 = new Tile(new LineType[] {LineType.RED, LineType.NONE, LineType.GREEN, LineType.GREEN, LineType.RED, LineType.NONE });
  }
  
  @org.junit.Test
  // A2.4
  public void testGetNumberOfColors() {
    assertEquals(0, t1.getNumberOfColors());
  }
  
  @org.junit.Test
  // A2.5
  public void testIsExactlyEqualTo() {
    assertTrue(t4.isExactlyEqualTo(t3));
    
    t4.rotateClockwise();
    t4.rotateClockwise();
    t4.rotateClockwise();
    assertFalse(t4.isExactlyEqualTo(t3));
  }
  
  @org.junit.Test
  // A2.6
  public void testCopy() {
    
  }
  
  @org.junit.Test
  // A2.7
  public void testRotateClockwise() {
    
  }
  
  @org.junit.Test
  // A2.8
  public void testRotateCounterClockwise() {
    
  }
  
  @org.junit.Test
  // A2.9
  public void testIsEmpty() {
    assertTrue(t1.isEmpty());
  }
  
  @org.junit.Test
  // A2.10
  public void testIsRotationEqualTo() {
    assertTrue(t1.isEmpty());
    
    t4.rotateClockwise();
    t4.rotateClockwise();
    t4.rotateClockwise();
    assertTrue(t4.isRotationEqualTo(t3));
  }
  
  @org.junit.Test
  // A2.11
  public void testCanBeRecolored() {
    assertFalse(t1.canBeRecoloredTo(t2));
    assertFalse(t2.canBeRecoloredTo(t3));
    
    t4.rotateClockwise();
    t4.rotateClockwise();
    t4.rotateClockwise();
    assertTrue(t4.canBeRecoloredTo(t3));
    
    // Same case
    // RR----.canBeRecoloredTo(RR----) == true
    Tile rr = new Tile(new LineType[]{LineType.RED, LineType.RED, LineType.NONE, LineType.NONE, LineType.NONE, LineType.NONE});
    Tile rr2 = new Tile(new LineType[]{LineType.RED, LineType.RED, LineType.NONE, LineType.NONE, LineType.NONE, LineType.NONE});
    assertTrue(rr.canBeRecoloredTo(rr2));
    assertTrue(rr2.canBeRecoloredTo(rr));
    
    // Only rotated differently
    // RR----.canBeRecoloredTo(-RR---) == false
    Tile rrRotated = new Tile(new LineType[]{LineType.NONE, LineType.RED, LineType.RED, LineType.NONE, LineType.NONE, LineType.NONE});
    assertFalse(rrRotated.canBeRecoloredTo(rr));
    assertFalse(rr.canBeRecoloredTo(rrRotated));
    
    //
  }
  
  @org.junit.Test
  // A2.12
  public void testDominates() {
    assertTrue(t3.dominates(t1));
    assertTrue(t3.dominates(t2));
  }
  
  @org.junit.Test
  // A2.13
  public void testHasSameColorsAs() {
    assertTrue(new Tile().hasSameColorsAs(new Tile()));
  }
  
  @org.junit.Test
  // A2.14
  public void testToString() {
    assertEquals("------", t1.toString());
    assertEquals("-Y---Y", t2.toString());
    
    t4.rotateClockwise();
    t4.rotateClockwise();
    t4.rotateClockwise();
    assertEquals("-RY-YR", t4.toString());
  }
  
  @org.junit.Test
  // A2.14
  public void testFitsTo() {
    
  }
  
  @org.junit.Test
  // A2.
  public void test() {
    assertTrue(k0.fitsTo(k1, 1));
    assertTrue(k0.fitsTo(k1, 2));
    assertFalse(k0.fitsTo(k1, 0));
  }
}
