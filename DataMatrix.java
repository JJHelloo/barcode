// Created by Justin, Ryan and Ana

/*
I just wrote out some of the methods just to
get some of the outline of it going - justin
*/
/*class DataMatrix 
{
// public
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
public boolean readText(String text)
{

}
public boolean scan(BarcodeImage image)
{

}
private int computeSignalWidth()
{

}
private int computeSignalHeight()
{

}
// accessors for width and height
public int getActualWidth()
{
return actualWidth;
}
public int getActualHeight()
{
  return actualHeight;
}


// Anna: generateImageFromText(), translateImagetoText(), readCharFromCol(int col), writeCharToCol(int col, int code)

//looks at the internal text stored in the implementing class and produces a companion BarcodeImage, internally (or an image in whatever format the implementing class uses).  After this is called, we expect the implementing object to contain a fully-defined image and text that are in agreement with each other.
public boolean generateImageFromText()
{
  return true;
  //or return false;
}

//looks at the internal image stored in the implementing class, and produces a companion text string, internally.  After this is called, we expect the implementing object to contain a fully defined image and text that are in agreement with each other.
public boolean translateImagetoText()
{
  return true;
  //or return false;
}

//take a column index input and return a char based on the value of each row in that column - one ASCII character
private char readCharFromCol(int col)
{
  char someChar = ' ';
  return someChar;
}

//take a column index and ASCII code for the char and turn it into an encoded column (*) why return a boolean here if it is a helper for the above method?
private boolean writeCharToCol(int col, int code)
{
  return true;
  //or return false;
}



} // end of Datamatrix*/