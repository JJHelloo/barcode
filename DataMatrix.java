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
  if (text == null || text.length() > BarcodeImage.MAX_WIDTH - 2)
  {
    return false;
  }
  this.text = text;
  return true;
}
// 
public boolean scan(BarcodeImage image)
{
  try
  {
    this.image = (BarcodeImage)image.clone();
  }
  catch (CloneNotSupportedException e)
  {
    
  }

  cleanImage();

  actualWidth = computeSignalWidth();
  actualHeight = computeSignalHeight();

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

// Anna: generateImageFromText(), translateImagetoText(), readCharFromCol(int col), writeCharToCol(int col, int code), displayTextToConsole()

//looks at text, translate to image
public boolean generateImageFromText()
{
  if(text == null)
  {
    return false;
  }

  clearImage();

  actualWidth = text.length() + 2;
  actualHeight = 10;

  buildBorders();

  //step across String text, incrimenting to the end
  for (int i = 0; i < text.length(); i++)
  {
    //get one char. result will be an ascii numerical value
    char oneChar = text.charAt(i);
    writeCharToCol(i + 1, oneChar); //column, ascii val
  }
  return true;
}

//build borders
private boolean buildBorders()
{
  if(actualWidth == 0 || actualHeight == 0)
  {
    return false;
  }

  //left border
  for(int i = BarcodeImage.MAX_HEIGHT - 1; i >= BarcodeImage.MAX_HEIGHT - actualHeight; i--)
  {
    image.setPixel(i, 0, true);
  }

  //bottom border
  for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++)
  {
    image.setPixel(BarcodeImage.MAX_HEIGHT - 1, i, true);
  }

  //top border
  for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++)
  {
    if(i % 2 == 0)
    {
      image.setPixel(BarcodeImage.MAX_HEIGHT - actualHeight, i, true);
    }
  }
  
  //right border
  for(int i = BarcodeImage.MAX_HEIGHT - 1; i >= BarcodeImage.MAX_HEIGHT - actualHeight; i--)
  {
    if(i % 2 == 1)
    {
      image.setPixel(i, actualWidth - 1, true);
    }
  }

  return true;
}
//looks at image and translates to text
public boolean translateImageToText()
{
  if (image == null)
    return false;
    
  text = "";
  //step across the columns from bottom left (r[35]c[0]), incrimenting column to readCharFromCol(i). first column at index [r][0] is not used.
  for (int i = 1; i <= actualWidth - 2; i++)
  {
    text += String.valueOf(readCharFromCol(i));
  }

  return true;
}

//Get the ASCII character of a column
private char readCharFromCol(int col)
{
  char colChar = 0;
  int k = 0;
  //step up through the column from bottom left (r[30]c[0]), incrimenting row to add each row's value if char is true. 
  for (int i = BarcodeImage.MAX_HEIGHT - 2; i >= BarcodeImage.MAX_HEIGHT - actualHeight +1; i--)
  {
    if (image.getPixel(i, col))
    {  
      colChar += Math.pow(2,k);
    }
    k++;
  }
  return colChar;
}

//take a column index and ASCII code for the char and turn it into an encoded column (*) 
private boolean writeCharToCol(int col, char code)
{
  // Convert ASCII value to binary
  String bin = "";
  for (int i = 0; i < actualHeight - 2; i++)
  {
    if (code % 2 == 1)
    {
      bin += '1';
    }
    else
      bin += '0';
    code /= 2;
  }

  if (bin == "")
    return false;
    
  //step up through column placing boolean based off of strToBinary
  for (int i = 0; i < bin.length(); i++)
  {
    if(bin.charAt(i) == 1)
    {
      image.setPixel(i + 1, col, true);
    }
  }
  return true;
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
  for(int j = BarcodeImage.MAX_HEIGHT - 1; j >= 0; j--) //j == row
  {
    for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++) //i == column
    {
      if(image.getPixel(j, i) && !found)
      {
        rowOffset = BarcodeImage.MAX_HEIGHT - 1 - j;
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

//shift image down by offset amount
//ryan
private void shiftImageDown(int offset)
{
  for(int j = BarcodeImage.MAX_HEIGHT - 1; j >= 0; j--) //j == row
  {
    for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++) //i == column
    {
      if(j < offset)
      {
        //make top row(s) blank
        image.setPixel(j, i, false);
      }
      else
      {
        //set to pixel above by the offset amount
        image.setPixel(j, i, image.getPixel(j - offset, i));
      }
    }
  }
}

//shift image left by offset amount
//ryan
private void shiftImageLeft(int offset)
{
  for(int j = BarcodeImage.MAX_HEIGHT - 1; j >= 0; j--) //j == row
  {
    for(int i = 0; i < BarcodeImage.MAX_WIDTH; i++) //i == column
    {
      if(i > BarcodeImage.MAX_WIDTH - 1 - offset)
      {
        //make right-most column(s) blank
        image.setPixel(j, i, false);
      }
      else
      {
        //set to the pixel right of current pixel by offset amount
        image.setPixel(j, i, image.getPixel(j, i + offset));
      }
    }
  }
}

//prints out the image to the console
//ryan
public void displayImageToConsole()
{
  //top border
  for(int i = 0; i < actualWidth + 2; i++)
  {
    System.out.print("-");
  }
  System.out.print("\n");
  

  for(int j = BarcodeImage.MAX_HEIGHT - actualHeight; j < BarcodeImage.MAX_HEIGHT; j++)
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

  System.out.print("\n");
}

//display text to console
//Anna
public void displayTextToConsole()
{
  System.out.println(text);
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