/*	Name:Jared Miller
 * Title:Car Rental Reservation System Customer Page
 * Date: May 9, 2012
 * ID: 4556
 */


package edu.csumb.CtoF;

import android.content.Context;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Customer 
{
	private String userName;
	private static String tempName;
	private String passWord;
	private static int transactionNum;
	private static String transactionType;
	//private static String dateAndTime;
	private static int numOfDays;
	private static int carType;
	private static String carTypeString;
	private static double tAmount;
	private static boolean car1;
	private static boolean car2;
	private static boolean car3;
	private static int resNum;
	private static int returnDay;
	private static int pickupDay;
	private static int cusNum;
	private static Date pickupRes;
	private static Date returnRes;
	private static Customer [] array = new Customer[100];
	private Date [] reservation = new Date[100];
	private int [] resNums = new int[100];
	private int [] cars = new int [100];
	private static String [] customerLog = new String [100];
	private static String [] reservationLog = new String [100];
	private static String [] cancelLog = new String [100];
	private static int numOfCustomers =0;
	private int numOfReservation =0;
	private static int numOfLogs =0;
	private int numOfCars =numOfReservation;
	private static int totalNumRes = 0;
	private static int totalCanRes = 0;
	private static String tempPass;
	
	Customer()
	{
	Random randomGenerator = new Random();
		transactionNum = randomGenerator.nextInt(100000000) +1;
		transactionType = "New Account";
	}
	
	public void setTempName(String tName)
	{
		tempName = tName;
	}
	
	public void setTempPass(String pass)
	{
		tempPass = pass;
	}
	
	public int getTotalNum()
	{
		return totalNumRes;
	}
	
	public void addTotalRes()
	{
		totalNumRes++;
	}
	
	public void setCancelLog()
	{
		cancelLog[totalCanRes] = "\nReservation Number: " + this.getResNum() + "\nPickup Date/Time: " + this.getPickupRes() + "\nReturn Date/Time: " + this.getReturnRes() + "\nCar Type: " + this.getCar();
		totalCanRes++;
	}
	
	public int getTotalCan()
	{
		return totalCanRes;
	}
	
	public String getCancelLog(int x)
	{
		return cancelLog[x];

	}

	public String getUser()
	{
		return userName;
	}
	
	public String getUserName(int x)
	{
		return array[x].getUser();
	}
	
	public Date getPickupRes(int x)
	{
		return array[x].getPickupRes();
	}
	
	public Date getReturnRes(int x)
	{
		return array[x].getReturnRes();
	}
	
	public String getPass()
	{
		return passWord;
	}
	public void setPickupDay(int x)
	{
		pickupDay = x;
	}
	
	public void setReturnDay(int x)
	{
		returnDay = x;
	}
	
	public int getReturnDay()
	{
		return returnDay;
	}
	
	public int getPickupDay()
	{
		return pickupDay;
	}
	public int getTransNum()
	{
		return transactionNum;
	}
	
	public String getTransType()
	{
		return transactionType;
	}
	public String getTransType(int x)
	{
		return array[x].getTransType();
	}
	
	public void setUser(String user)
	{
		userName = user;
		tempName = user;
	}
	
	public void setPass(String pass)
	{
		passWord = pass;
	}
	
	public void setReturnRes(Date rRes)
	{
		returnRes = rRes;
	}
	
	public void setNumOfDays(int x, int y)
	{
		numOfDays = y-x;
	}
	
	public int getNumOfDays()
	{
		return numOfDays;
	}
	
	public int getNumOfDays(int x)
	{
		return array[x].getNumOfDays();
	}
	
	public void setPickupRes(Date pRes)
	{
		pickupRes = pRes;
	}
	
	public Date getReturnRes()
	{
		return returnRes;
	}
	
	public Date getReturnRes1(int x)
	{
		return array[x].getReturnRes();
	}
	
	public String getCar()

	{
		
		if(carType == 1)
		{
			carTypeString = "Minivan";
			return "Minivan";
		}
		else if(carType == 2)
		{
			carTypeString = "Sedan";
			return "Sedan";
		}
		else if(carType == 3)
		{
			carTypeString = "Truck";
			return "Truck";
		}
		else
			return "No Car Selected";
		
	}
	
	public int getCarType()
	{
		return carType;
	}
	
	public void setResNum()
	{
		Random randomGenerator = new Random();
		resNum = randomGenerator.nextInt(10000000) +1;
		
		resNums[numOfReservation] = resNum;
	}
	
	public int getResNum()
	{
		return resNum;
	}
	
	public void setCarType(int x)
	{
		carType = x;
		cars[numOfCustomers] = x;
	}

	public Date getPickupRes()
	{
		return pickupRes;
	}
	
	public void setAmount(double x)
	{
		tAmount = x;
	}
	
	public double getAmount()
	{
		return tAmount;
	}
	
	public String getTempUser()
	{
		return tempName;
	}
	
	public void setTempUser(String x)
	{
		tempName =x;
	}
	
	public void setCustomerLog()
	{
		customerLog[numOfCustomers] = " Transaction Number: " + Integer.toString(transactionNum) +"\n" + "Transaction Type: " + transactionType + "\n" + "Username: " + this.getUser() + "\n" + "Current Date & Time: " + this.getCurrentTime();  
		numOfLogs++;
	}
	
	public void setReservationLog()
	{
		reservationLog[totalNumRes]= "\nTransaction Number: " + Integer.toString(transactionNum) + "\nTransaction Type: Reservation" + "\nUsername: " + tempName + "\nPickup Date/Time: "  +this.getPickupRes() + "\nReturn Date/Time: "+ this.getReturnRes() + "\nCar Type: " + this.getCar() + "\nReservation Number: " + this.getResNum() + "\nTotal Amount: $" + Double.toString(this.getAmount()) + "\nCurrent Date/Time: "+ this.getCurrentTime();
		totalNumRes++;
	}
	
	public String getPickupResString()
	{
		DateFormat x = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return x.format(pickupRes);
	}
	
	public String getReturnResString()
	{
		DateFormat x = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return x.format(returnRes);
	}
	
	public String getReservationLog(int x)
	{
		return reservationLog[x];
	}

	public boolean findCustomerb(String user, String pass, int x)
	{
		boolean answer = false;
		for(int i = 0;i<x;i++)
		{
			if(array[i].getUser().equals(user) && pass.equals(array[i].getPass()))
			{
				//cusNum = i;
				return true;
			}
		}
		return false;
	}
	

	
	public Customer findCustomer(String user, String pass)
	{
		for(int i = 0; i < numOfCustomers; i++)
		{
			if(user.equals(array[i].getUser()) && pass.equals(array[i].getPass()))
			{
				cusNum = i;
				return array[i];
			}
			else
				return null;
		}
		return null;
	}
	
	public int getNumCustomers()
	{
		return numOfCustomers;
		
	}
	
	public int getNumOfRes()
	{
		return numOfReservation;
	}
	
	public boolean add(Customer a)
	{
		for(int i =0; i<numOfCustomers; i++)
		{
			if(a.getUser().equals(array[i].getUser()))
			{
				return false;
			}
		}
		//Toast.makeText(, array[numOfCustomers], Toast.LENGTH_SHORT).show();
		array[numOfCustomers] = a;
		numOfCustomers++;
		return true;
		
	}

	public int getCusNum()
	{
		return cusNum;
	}	
	
	//get current time
	public Date getCurrentTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return date;
	}
	//add reservations to reservation array
	public void addRes(Date x)
	{
		reservation[numOfReservation] = x;
		numOfReservation++;
	}
	
	public void addCar(int x)
	{
		cars[numOfCars]=x;
	}
	//Check to see if car is taken
	public boolean carTaken(int x, Date y)
	{
		//if()
		return false;
	}
	
	public boolean resExist(int x)
	{
		for(int i=0; i<numOfReservation; i++)
		{
			if(x == resNums[i])
			{
				return true;
			}
			else
				return false;
		}
		
		return false;
	}
	
	public void cancelRes(int x)
	{
		for(int i=0; i<numOfReservation; i++)
		{
			if(x == resNums[i])
			{
				reservation[i] = null;
				reservation[i+1] = null;
				resNums[i]= 0;
				numOfReservation--;
			}
		}
	}
	
	public boolean checkIfCarAvailable(Date x, Date y, int z)
	{
		for(int i=0; i< numOfReservation; i++)
		{
			if(reservation[i].equals(x) && reservation[i+1].equals(y))
			{
				if(cars[i] == z)
				{
					return false;
				}
			}
		}
		return true;
		
	}
	
	public int getNumOfLogs()
	{
		return numOfLogs;
	}
	
	public String getCustomerLog(int x)
	{
		return customerLog[x];
	}
	
	public void setCar1(boolean x)
	{
		car1 =x;
	}
	
	public void setCar2(boolean x)
	{
		car2 =x;
	}
	
	public void setCar3(boolean x)
	{
		car3 =x;
	}
	
	public boolean getCar1()
	{
		return car1;
	}
	public boolean getCar2()
	{
		return car2;
	}
	public boolean getCar3()
	{
		return car3;
	}

	public void setCars()
	{
		car1 = true;
		car2 = true;
		car3 = true;
	}

	public void checkDate(Date x, Date y, int z)
	{
		for(int k=0; k<numOfCustomers; k++)
		{
			for(int i=0; i<array[k].getNumOfReservation();i+=2)
			{
				if(array[k].getReservation(i+1).after(y) && array[k].getReservation(i).before(y))
				{
					if(array[k].getCarType() == z)
					{
						if(array[k].getCarType() == 1)
						{
							array[k].setCar1(false);
						}
						else if(array[k].getCarType() == 2)
						{
							array[k].setCar2(false);
						}
						else if(array[k].getCarType() == 3)
						{
							array[k].setCar3(false);
						}
					}
				}
				else if(array[k].getReservation(i).before(x) && array[k].getReservation(i+1).after(x))
				{
					if(array[k].getCarType() == z)
					{
						if(array[k].getCarType() == 1)
						{
							array[k].setCar1(false);
						}
						else if(array[k].getCarType() == 2)
						{
							array[k].setCar2(false);
						}
						else if(array[k].getCarType() == 3)
						{
							array[k].setCar3(false);
						}
					}
				}
				else if(x.after(array[k].getReservation(i+1)) && x.before(array[k].getReservation(i)))
				{
					if(array[k].getCarType() == z)
					{
						if(array[k].getCarType() == 1)
						{
							array[k].setCar1(false);
						}
						else if(array[k].getCarType() == 2)
						{
							array[k].setCar2(false);
						}
						else if(array[k].getCarType() == 3)
						{
							array[k].setCar3(false);
						}
					}
				}
				else if(y.before(array[k].getReservation(i)) && y.after(array[k].getReservation(i+1)))
				{
					if(array[k].getCarType() == z)
					{
						if(array[k].getCarType() == 1)
						{
							array[k].setCar1(false);
						}
						else if(array[k].getCarType() == 2)
						{
							array[k].setCar2(false);
						}
						else if(array[k].getCarType() == 3)
						{
							array[k].setCar3(false);
						}
					}
				}
			}
		}
		
	}
	
	public int getNumOfReservation()
	{
		return numOfReservation;
	}
	
	public void checkRes(int x)
	{
		
	}
	
	public Date getReservation(int x)
	{
		return reservation[x];
	}
	
	public Customer getArray(int x)
	{
		return array[x];
	}
}
