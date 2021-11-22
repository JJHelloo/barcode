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


// Anna: generateImageFromText(), translateImagetoText(), readCharFromCol(int col), writeCharToCol(int col, int code), displayTextToConsole()

//looks at text translate to image
public boolean generateImageFromText()
{
  return true;
  //or return false;
}

//looks at image and translates to text
public boolean translateImageToText()
{
  text = "";
  //step across the columns from bottom left (r[35]c[0]), incrimenting column to readCharFromCol(i). first column at index [r][0] is not used.
  for (int i = 1; i <= actualWidth; i++)//I think i starts at 1 to skip the first column
  {
    text += String.valueOf(readCharFromCol(i));
    return true;
  }
  return false;
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
void displayImageToConsole()
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

  //bottom border
  for(int i = 0; i < actualWidth + 2; i++)
  {
    System.out.print("-");
  }
  System.out.print("\n");
}

//display text to console
//Anna
void displayTextToConsole()
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