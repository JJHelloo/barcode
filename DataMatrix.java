// Created by Justin, Ryan and Ana

class DataMatrix 
{
// public
public static final char BLACK_CHAR = '*';
public static final char WHITE_CHAR = ' '; 

// private variables
private BarcodeImage image;
private String text;
private int actualHeight; //max 30
private int actualWidth; //max 65

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
  //placed within a try catch block
  //this.image = image.clone();

  //cleanImage();

  //actualWidth = computerSignalWidth();
  //actualHeight = computerSignalHeight();

  return true;
}
private int computeSignalWidth()
{
  int width = 0;
  for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++)
  {
    if(image.getPixel(BarcodeImage.MAX_HEIGHT - 1,i) == true)
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
  //offsets from lower left corner
  int rowOffset = 0;
  int colOffset = 0;
  boolean found = false;

  //search for lower left corner of signal. corner of closed limitation lines.
  for(int j = BarcodeImage.MAX_HEIGHT - 1; j >= 0; j--)
  {
    for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++)
    {
      if(image.getPixel(j, i) && !found)
      {
        rowOffset = j;
        colOffset = i;
        found = true;
      }
    }
  }

  if(rowOffset > 0)
  {
    shiftImageDown(rowOffset);
  }

  if(colOffset > 0)
  {
    shiftImageLeft(colOffset);
  }
}

//shift image down 1 row at a time
//ryan
private void shiftImageDown(int offset)
{
  for(int n = 0; n < offset; n++)
  {
    for(int j = BarcodeImage.MAX_HEIGHT - 1; j >= 0; j--) //j == row
    {
      for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++) //i == column
      {
        if(j == 0)
        {
          //make top row blank
          image.setPixel(j, i, false);
        }
        else
        {
          //set to pixel above it
          image.setPixel(j, i, image.getPixel(j, i - 1));
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
    for(int j = BarcodeImage.MAX_HEIGHT - 1; j >= 0; j--)
    {
      for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++)
      {
        if(i == BarcodeImage.MAX_WIDTH - 1)
        {
          //make right-most column blank
          image.setPixel(j, i, false);
        }
        else
        {
          //set to pixel right of it
          image.setPixel(j, i, image.getPixel(j + 1, i));
        }
      }
    }
  }
}

//prints out the image to the console
//ryan
void displayImageToConsole()
{
  //top border
  for(int i = 0; i < actualWidth + 2; i++)
  {
    System.out.print("-");
  }
  System.out.print("\n");
  

  for(int j = 0; j < actualHeight; j++)
  {
    System.out.print("|"); //left border
    
    for(int i = 0; i < actualWidth; i++)
    {
      if(image.getPixel(j, i))
      {
        System.out.print(BLACK_CHAR);
      }
      else
      {
        System.out.print(WHITE_CHAR);
      }
    }

    System.out.print("|\n"); //right border
  }

  //bottom border
  for(int i = 0; i < actualWidth + 2; i++)
  {
    System.out.print("-");
  }
  System.out.print("\n");
}

//sets the entire image to white (false)
//ryan
private void clearImage()
{
  for(int j = BarcodeImage.MAX_HEIGHT - 1; j >= 0; j--)
    {
      for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++)
      {
        image.setPixel(j, i, false);
      }
    }
}

} // end of Datamatrix