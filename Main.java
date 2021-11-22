/*Created by Justin, Jolene, Ana, and Ryan
Optical Barcode
November 2021
*/


class Main 
{
  public static void main(String[] args)
  {
    //Some testing - Jolene
    BarcodeImage bc = new BarcodeImage();
    bc.displayToConsole();

    System.out.println();

    BarcodeImage bcCopy = null;

    try
    {
      bcCopy = (BarcodeImage)bc.clone();
    }
    catch (CloneNotSupportedException e)
    {
      bcCopy = null;
    }
    
    bcCopy.displayToConsole();

    bcCopy.setPixel(0, 0, true);

    System.out.println("Testing true in first at 0,0");
    bc.displayToConsole();
    System.out.println();
    bcCopy.displayToConsole();
  }
}