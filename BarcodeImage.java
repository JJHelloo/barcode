//Class that receives a String or array of Strings
//and creates a 2D array to house a BarcodeImage
//created by Jolene
class BarcodeImage implements Cloneable
{
  //Variable declaration
  public static final int MAX_HEIGHT = 30;
  public static final int MAX_WIDTH = 65;

  private boolean[][] imageData;

  //Default constructor creates an all-white array of max size
  BarcodeImage()
  {
    //Creates new array of max size
    imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
    //Sets all data in the array to false
    for (int i = 0; i < MAX_HEIGHT; i++)
      for(int j = 0; j < MAX_WIDTH; j++)
        imageData[i][j] = false;
  }

  //Constructor receives an array of Strings and creates an array of booleans, placed in the lower-left corner of the max array
  BarcodeImage(String[] strData)
  {

  }

  //Accessor for chars in the array
  boolean getPixel(int row, int col)
  {
    //Returns false if there is an outOfIndex issue
    if (row > MAX_HEIGHT || col > MAX_WIDTH)
      return false;
    //Returns the boolean value of the requested pixel
    else
      return imageData[row][col];
  }

  //Mutator for chars in the array
  boolean setPixel(int row, int col, boolean value)
  {
    //Returns false if there is an outOfIndex issue
    if (row > MAX_HEIGHT || col > MAX_WIDTH)
      return false;
    //Sets the pixel to the specified value and returns true
    else
    {
      imageData[row][col] = value;
      return true;
    }
  }

  //Validates the String array for errors
  private boolean checkSize(String[] data)
  {
    //Local variable declaration
    //Flags a null element in the array
    boolean nullFlag = false;
    //Holds longest string length
    int longestStr = 0;

    //If the array is null, there is an error
    if(data == null)
      return false;

    //Checks each String in the array for a null value
    for (String str : data)
    {
      if (str == null)
      {
        nullFlag = true;
        break;
      }
    }

    //If an element of the array is null, there is an error
    if(nullFlag = true)
      return false;

    //Checks every String in the array to find the longest one
    for (int i = 0; i < data.length; i++)
    {
      if (data[i].length() > longestStr)
        longestStr = data[i].length();
    }

    //If there are more strings in the array than the MAX_HEIGHT, there is an error
    if (data.length > MAX_HEIGHT)
      return false;
    //If the longest string is longer than MAX_WIDTH, there is an error
    else if (longestStr > MAX_WIDTH)
      return false;

    //There are no errors with the data
    return true;
  }

  public void displayToConsole()
  {
    //Local variable declaration
    int i = 0;
    int j = 0;

    //Iterates through the 2D array rows
    while (i < imageData.length)
    {
      //Iterates through the current row by column
      while (j < imageData[i].length)
      {
        //Prints the elements consecutively
        System.out.print(imageData[i][j]);
        j++;
      }
      j = 0;
      i++;
      //New line for new row
      System.out.println();
    }
  }

  //Creates a clone of the BarcodeImage object
  public Object clone() throws CloneNotSupportedException
  {
    //Clone the calling object
    BarcodeImage copyBC = (BarcodeImage)super.clone();

    //Removes the reference copy in the clone
    copyBC.imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];

    //Creates a deep copy for copyBC imageData
    for (int i = 0; i < MAX_HEIGHT; i++)
      for(int j = 0; j < MAX_WIDTH; j++)
        copyBC.setPixel(i, j, this.imageData[i][j]);

    //Return the clone
    return copyBC;
  }
}