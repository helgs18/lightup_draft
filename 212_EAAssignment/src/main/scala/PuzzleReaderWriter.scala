import scala.io._

class PuzzleReaderWriter {

  def fileRead(): Unit ={
    var fname: String = "src/puzzles/infile.txt"
    import scala.io.Source
    for(line <-Source.fromFile(fname).getLines){
      println(line)
    }
  }

}

object FileRead{
  var fname: String = "src/puzzles/infile.txt"
  import scala.io.Source
  for(line <-Source.fromFile(fname).getLines){
    println(line)
  }
}
