// Created by Justin, Ryan and Ana

/*
I just wrote out some of the methods just to
get some of the outline of it going - justin
*/
class DataMatrix 
{

public static final char BLACK_CHAR = '*';
public static final char WHITE_CHAR = ' '; 

// private variables
private BarcodeImage image;
private String text;
private int actualWidth;
private int actualHeight;

public DataMatrix()
{
  
}

public DataMatrix(BarcodeImage image)
{

}
public DataMatrix(String text) {

}


//Justin: Accessors, computeSignalWidth/Height, scan, readText
// accessors for width and height
public int getActualWidth()
{
return actualWidth;
}
public int getActualHeight()
{
  return actualHeight;
}


} // end of Datamatrix