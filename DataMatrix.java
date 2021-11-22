// Created by Justin, Ryan and Ana

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
  int width = 0;
  for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++)
  {
    if(image.getPixel(BarcodeImage.MAX_HEIGHt - 1,i) == true)
    {
      width++;
    }
  }
  return width; 
}
private int computeSignalHeight()
{
  int height = 0;
  for(int i = 0; i < BarcodeImage.MAX_HEIGHT; i++)
  {
    if(image.getPixel(i,0) == true)
    {
      height++;
    }
  }
  return height;
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
  text = "";
  //step across the columns from bottom left (r[35]c[0]), incrimenting column to readCharFromCol(i)
  for (int i = 0; i <= actualWidth; i++)
  {
    text += String.valueOf(readCharFromCol(i));
    return true;
  }  
  return false;
}

//Get the ASCII character of a column
private char readCharFromCol(int col)
{
  char colChar;
  //step up through the column from bottom left (r[35]c[0]), incrimenting row to add each row's value if char is true
  for (int i = actualHeight; i >= 0; i--)
  {
    if (image.getPixel(i, col))
    {
      colChar += Math.pow(i,2);
    }
    colChar += 0;
  }
  return colChar;
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
  moveImageToLowerLeft();
}

//cleanImage helper methods. move in specific direction.
//ryan
private void moveImageToLowerLeft()
{
  int xOffset = 0;
  int yOffset = 0;
  boolean found = false;

  //search for lower left corner of signal. corner of closed limitation lines.
  for(int i = BarcodeImage.MAX_HEIGHT - 1; i >= 0; i--)
  {
    for(int j = 0; j < BarcodeImage.MAX_WIDTH; j++)
    {
      if(image.getPixel(j, i) && !found)
      {
        xOffset = j;
        yOffset = i;
        found = true;
      }
    }
  }

  if(yOffset > 0)
  {
    shiftImageDown(yOffset);
  }

  if(xOffset > 0)
  {
    shiftImageLeft(xOffset);
  }
}

//shift image down 1 row at a time
//ryan
private void shiftImageDown(int offset)
{
  for(int n = 0; n < offset; n++)
  {
    //j == height
    for(int j = BarcodeImage.MAX_HEIGHT - 1; j >= 0; j--)
    {
      //i == width
      for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++)
      {
        if(j == 0)
        {
          //make top row blank
          image.setPixel(i, j, false);
        }
        else
        {
          //set to pixel above it
          image.setPixel(i, j, image.getPixel(i, j - 1));
        }
      }
    }
  }
}

//shift image left 1 column at a time
//ryan
private void shiftImageLeft(int offset)
{
  for(int n = 0; n < offset; n++)
  {
    //j == height
    for(int j = BarcodeImage.MAX_HEIGHT - 1; j >= 0; j--)
    {
      //i == width
      for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++)
      {
        if(i == BarcodeImage.MAX_WIDTH - 1)
        {
          //make right-most column blank
          image.setPixel(i, j, false);
        }
        else
        {
          //set to pixel right of it
          image.setPixel(i, j, image.getPixel(i + 1, j));
        }
      }
    }
  }
}

//prints out the image to the console
//ryan
void displayImageToConsole()
{
  //don't forget borders
  //System.out.println(BLACK_CHAR);
  //System.out.println(WHITE_CHAR);
}

//sets the entire image to white (false)
//ryan
private void clearImage()
{
  for(int j = BarcodeImage.MAX_HEIGHT - 1; j >= 0; j--)
    {
      for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++)
      {
        image.setPixel(i, j, false);
      }
    }
}

} // end of Datamatrix