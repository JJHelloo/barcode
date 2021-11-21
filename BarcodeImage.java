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
    imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
    for (int i = 0; i < MAX_HEIGHT; i++)
      for(int j = 0; i < MAX_WIDTH; j++)
        imageData[i][j] = false;
  }

  //Constructor receives an array of Strings and creates an array of booleans, placed in the lower-left corner of the max array
  BarcodeImage(String[] strData)
  {

  }

  //Accessor for chars in the array
  boolean getPixel(int row, int col)
  {
    if (row > MAX_HEIGHT || col > MAX_WIDTH)
      return false;
    else
      return imageData[row][col];
  }

  //Mutator for chars in the array
  boolean setPixel(int row, int col, boolean value)
  {
    if (row > MAX_HEIGHT || col > MAX_WIDTH)
      return false;
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
    else if (longestStr.length > MAX_WIDTH)
      return false;

    //There are no errors with the data
    return true;
  }

  public void displayToConsole()
  {
    int i = 0;
    int j = 0;

    while (i < imageData.length)
    {
      while (j < imageData[i].length)
      {
        System.out.print(imageData[i][j]);
        j++;
      }
      j = 0;
      i++;
      System.out.println();
    }
  }

  //Creates a clone of the BarcodeImage object
  public Object clone() throws CloneNotSupportedException
  {
    BarcodeImage copyBC = (BarcodeImage)super.clone();

    // Creating a deep copy for copyBC
    copyBC.imageData = this.imageData;

    return copyBC;
  }
}