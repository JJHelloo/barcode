// Created by Justin, Ryan and Ana

/*
I just wrote out some of the methods just to
get some of the outline of it going - justin
*/
class DataMatrix 
{
// public
public static final char BLACK_CHAR = '*';
public static final char WHITE_CHAR = ' '; 

// private variables
private BarcodeImage image;
private String text;
private int actualWidth; //max 65
private int actualHeight; //max 30

//constructors
//ryan
public DataMatrix()
{
  image = new BarcodeImage(); //default blank constructor
  actualWidth = 0;
  actualHeight = 0;
  text = "";
}

public DataMatrix(BarcodeImage image)
{
  scan(image);
  text = "";
}

public DataMatrix(String text)
{
  image = new BarcodeImage();
  actualWidth = 0;
  actualHeight = 0;
  readText(text);
}


//Justin: Accessors, computeSignalWidth/Height, scan, readText

// mutator for text
public boolean readText(String text)
{
  if (text == null)
  {
    return false;
  }
  this.text = text;
  return true;
}
// 
public boolean scan(BarcodeImage image)
{
  return true;
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

//looks at image and translates to text
public boolean translateImagetoText()
{
  for (int i = actualHeight; i >= 0; i--)
  {
    text += String.valueOf(readCharFromCol(i));
     return true;
  }  
  //or return false;
}

//take a column index input and return a char based on the value of each row in that column - one ASCII character
private char readCharFromCol(int col)
{
  char someChar = '';
  for (int i = 0; i < actualHeight; i++)
  {
    if (image[30-i][col])
    {
      someChar += Math.pow(i,2);
    }
    someChar += 0;
  }
  return someChar;
}

//take a column index and ASCII code for the char and turn it into an encoded column (*) why return a boolean here if it is a helper for the above method?
private boolean writeCharToCol(int col, int code)
{
  return true;
  //or return false;
}

//moves the image(signal) to the lower left corner
//ryan
private void cleanImage()
{
  //image.getPixel(1, 2);
  //image.setPixel(1, 2, true)
  //System.out.println(BLACK_CHAR);
  //System.out.println(WHITE_CHAR);
}

//cleanImage helper methods. move in specific direction.
//ryan
private void moveImageToLowerLeft()
{

}

private void shiftImageDown(int offset)
{
  
}

private void shiftImageLeft(int offset)
{
  
}

//prints out the image to the console
//ryan
void displayImageToConsole()
{
  //don't forget borders
}

//sets the image to white
//ryan
private void clearImage()
{
  
}

} // end of Datamatrix