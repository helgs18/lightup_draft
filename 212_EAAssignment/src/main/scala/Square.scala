class Square(x: Int, y: Int, v:Int = -1){
  var isLit: Boolean = false
  var isLightBulb = false
  var isRedCross = false
  //var solvedList: List[Square]
  var row: Int = x  // using rows and columns instead, since it's more understandable for this board layout
  var column: Int = y
  var value: Int = v  // 0, 1, 2, 3, 4, 254(All)

  def lightUpSquare():Unit = {
    this.isLit = true
    this.isRedCross = true
  }
  def squareIsLight(): Unit ={
    this.isLightBulb = true
  }
  def setRedCross(): Unit = {
    this.isRedCross = true
  }
  def isLightPossible(): Boolean = {
    if(this.isLit || this.isRedCross) false else true
  }




}

class Block {
  var neighBours: List[Square] = List()
  var value: Int = 0
}

//class EmptyBlock extends Block{ this.value = -1 }
//class Block0 extends Block{ this.value = 0 }
//class Block1 extends Block{ this.value = 1 }
//class Block2 extends Block{ this.value = 2 }
//class Block3 extends Block { this.value = 3 }
//class Block4 extends Block{ this.value = 4 }
//class FullBlock extends Block{ this.value = Int.MaxValue }
//// no limits (in practice max value can only be 4, but it can be any number from 0-4). Erstatte dette med Enum?

//class Point (posX: Int, posY: Int){
//  //var x: Int = posX
//  //var y: Int = posY
//  def apply():List[Int] ={ return List(this.posX, this.posY) }
//  override def toString(): String ={
//    "(" + this.posX + "," + this.posY + ")"
//  }
//}