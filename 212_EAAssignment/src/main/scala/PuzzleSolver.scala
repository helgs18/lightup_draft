object PuzzleSolver {
  // constants
  val numOfColumns = 6 // checking for neighbours will need to know the number of the final element
  val numOfRows = 6 // Using list numbering atm. Eventually width and height of the rows and columns will
  // dephend on the input file

  def main(args: Array[String]): Unit = {
    // these squares are only for testing, and trying to work out what variables and functions we need
    var emptySquare = new Square(0, 0)
    var square01 = new Square(0, 1, 1)
    var square02 = new Square(0, 2, 2)
    // (0,4),(2,4),(1,3),(1,5), skal bli belyst pga kode som bruker lightbulbAroundValue4()
    var square03 = new Square(0, 4)
    var square04 = new Square(1, 4, 4)
    var square05 = new Square(2, 4)
    var square06 = new Square(1, 3)
    var square07 = new Square(1, 5)
    // De mellom den og kommentaren brukes for å teste funksjon for å gjøre alle squares rundt 4-tall til lyspærer
    var square11 = new Square(1, 1, 1)
    var square20 = new Square(2, 0, 0)
    var square21 = new Square(2, 1, 1)
    var square24 = new Square(2, 4, 0)
    var square32 = new Square(3, 1)
    var squares = List(square01, square02, square03, square04, square05, square06, square07,
      square11, square20, square21, square24, square32)

    def findValue(s: List[Square], value: Int): List[Square] = {
      s.filter(x => x.value == value)
    }

    // sett lyspære på naboer. Husk å ta med x i if-setning.
    // Can this also be used for putting red crosses around squares with value 0, by using lambda?
    def lightbulbAroundValue4(s: Square): Unit = {
      if (s.row != 0) {
        var temp: Square = squares.filter(x => x.row == s.row - 1 && x.column==s.column).head
        temp.squareIsLight()
      }
      if (s.row != numOfRows) {
        var temp: Square = squares.filter(x => x.row == s.row + 1  && x.column==s.column).head
        temp.squareIsLight()
      }
      if (s.column != 0) {
        var temp: Square = squares.filter(x => x.column == s.column - 1 && x.row == s.row).head
        temp.squareIsLight()
      }
      if (s.column != numOfColumns){
        var temp: Square = squares.filter(x => x.column == s.column + 1 && x.row == s.row).head
        temp.squareIsLight()
      }
    }

    // sett lyspære på naboer. Husk å ta med x i if-setning.
    // Can this also be used for putting red crosses around squares with value 0, by using lambda?
    def redCrossAroundZeros(s: Square): Unit = {
      if (s.row != 0) {
        var temp: Square = squares.filter(x => x.row == s.row - 1 && x.column==s.column).head
        temp.setRedCross()
      }
      if (s.row != numOfRows) {
        var temp: Square = squares.filter(x => x.row == s.row + 1  && x.column==s.column).head
        temp.setRedCross()
      }
      if (s.column != 0) {
        var temp: Square = squares.filter(x => x.column == s.column - 1 && x.row == s.row).head
        temp.setRedCross()
      }
      if (s.column != numOfColumns){
        var temp: Square = squares.filter(x => x.column == s.column + 1 && x.row == s.row).head
        temp.setRedCross()
      }
    }
    // ToDo: Checks that runs only once / these checks should be the first ones that we run:
    //  ...
    //  Check to set up a filter that finds all blocks with value of 0 or 4, and uses either
    //  'lightbulbAroundValue4(s: Square)' or 'redCrossAroundZeros(s: Square)' on them.
    //  Setup a filter that has the value 3. All it's corner should get red crosses.
    //  ...
    //  check to find all value 3 on edges or value 2 in corners.This might not happen often,
    //  but is a check that only needs to run once.
    //  checks would be something like this:
    //      2: (if row == 0 or last) && (column == 0 or last)
    //      3: (if row == 0 or last) or (column == 0 or last)
    //      3: (if square to the left, right, above or under is a block (value > -1). set lightbulb on all.
    //  Blocked in squares. If all a squares neighbours are blocks, then set a lightbulb on it (doesn't happen often,
    //      but without this check, there will be some puzzle that cannot be solved.

    //  ToDo: Checks that runs more than once
    //   3: if one neighbour square is either lit or has a red cross(cannot have lightbulb), then set lightbulb on the rest
    //   2: check if 2 neighbours are either lit or has a redCross
    //   2: check if 2 neighbour has lightbulbs, and set redCross on the other 2
    //   1: check if 1 neighbours has lightbulbs, and set redCross on the rest
    //   2: if 1 neighbour is lit or has a red cross, then set red crosses on the two corners on the opposite side
    //        (if the square below (south), then set red squares on the 2 squares northeast and northwest for the value 2.
    //   Lonely nonlit square, where not all neighbours and all squares on non-blocked rows and columns are lit, then set lightbulb
    //      on the lonely square, so that it can "lightup" itself.
    //   Two non-lit neighbours where one has a red cross, and the other is without a redcross. Then check if the one without red-
    //       cross, is the only lightbulb that can light up the two squares.
    //   1. if 2 neighbours with a shared corner, cannot be lightbubl (has redcross or is lit), then the corner of the opposite side
    //        of the diagonal cannot be a lightbulb (if north and norteast is lit, then southwest cannot be lightbulb, because
    //        that would make it impossible to put a lightbulb on the south or west neighbour)
    //   if a corner below possible lightbulb has a red cross, and no squares on the same row and columns as the one with red cross can
    //      lighth it up, then the possible lightbulb should be changed to a red cross. Remember to also check squares that shares a
    //      row or column with the red cross. This will most likely happen late in the game. See game 7x7 Light Up Normal Puzzle ID: 4,127,712
    //      for an example of this (squares below the value 1 in the center).
    //   2: if one of its neighbours is a red cross, then none of the corners that's not neighbour to the red-cross, can have a ligthbulb.
    //      example: 7x7 Light Up Normal Puzzle ID: 362,491. The square to the left/west of the square with value 2 to the top right,
    //         gets a red cross, which means that the northeast and southeast corners cannot have lightbulbs (lits to many squares)
    //   1: if a number one has 2 neighbours with a shared corner, the corner on the opposite side of the diagonal, cannot have lightbulb
    //      Check the value 1 on the top row on this puzzle for it: 7x7 Light Up Normal Puzzle ID: 362,491



    def checkRow(row: Int, col: Int = 0): Unit = {
      // Check row all the way to the edge or till a block (value 0,1,2,3,4 or 255(All)
      // 0 can be used for the start, if we are doing some pattern checking and needs to start on the first
      // square in the row.
      var i: Int = 0
      val rows = squares.filter(x => x.row == row) // Find all squares in the row. Now we need to use a
      // loop to find out if there's any blocks (squares with value 0, 1, 2, 3, 4 or 255(All)).
      // Use filter instead of loop to find out if there's any blocks on the row, and if so, pick out the
      // closes ones.
      // Then return the squares that's between the closes blocks. After the blocks have been returned to the
      // calling function, the calling function can do it's changes, like lighting up all the blocks, put down
      // a lightbulb on squares that can only get light from themself, and possible other changes.
    }

    checkRow(0)
    checkRow(1)
    var allfours = findValue(squares, 4);
    print("All fours: ")
    allfours.foreach(x => print("(" + x.row + "," + x.column + ")"))
    var lightUsMyNeighbors = allfours.head

    lightbulbAroundValue4(lightUsMyNeighbors) // chan

    println("\nThese squares are not blocks:")
    squares
      .filter(x => x.value == -1)
      .foreach(x => print("(" + x.row + ", " + x.column + ")"))


    var reader = new PuzzleReaderWriter()
    reader.fileRead()
  }
}