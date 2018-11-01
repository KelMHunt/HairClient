package salon.clients;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

import salon.models.Shampoo;
import salon.communication.*;

public class HairClient 
{
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;	
	
	
	public boolean initConnection()
	{
		boolean success=false;
		try
		{
			socket= new Socket(InetAddress.getLocalHost(), 8888);
			getStreams();
			success=true;
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
		return success;
	}
	
	public void getStreams()
	{
		try
		{
			oos= new ObjectOutputStream(socket.getOutputStream());
			ois= new ObjectInputStream(socket.getInputStream());
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
	}
	public void closeConnection()
	{
		try
		{
			oos.writeObject("exit");
			ois.close();
			oos.close();
			socket.close();
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
		}
	}
	public String getTodaysSpecial()
	{
		String todaysSpecial="No Special Today";
		try
		{
			oos.writeObject("get_special");
			todaysSpecial=(String)ois.readObject();
		}
		catch(NullPointerException |
				ClassCastException |
				ClassNotFoundException|
				IOException e)
		{
			e.printStackTrace();
		}
		return todaysSpecial;
	}
	public boolean addShampoo(Shampoo shampoo)
	{
		//boolean success=false;
		Request req=new Request();
		req.setAction("add_shampoo");
		req.setObj(shampoo);
		try
		{
			oos.writeObject(req);
			Response resp=(Response)ois.readObject();
			if(resp!=null)
			{
				if(!resp.isSuccess())
				{
					System.out.println("Client error:"+resp.getErrorMsg());
				}
			}
			return resp.isSuccess();
		}catch(ClassCastException |ClassNotFoundException |IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public List<Shampoo> getShampoos()
	{
		//boolean success=false;
		List<Shampoo> shampooList=null;
		Request req=new Request();
		req.setAction("get_shampoo");
		try
		{
			oos.writeObject(req);
			Response resp=(Response)ois.readObject();
			if(resp!=null)
			{
				if(!resp.isSuccess())
				{
					System.out.println("Client error:"+resp.getErrorMsg());
				}
			}
			return (java.util.List<Shampoo>)resp.getData();
		}catch(ClassCastException |ClassNotFoundException |IOException e)
		{
			e.printStackTrace();
		}
		return new java.util.ArrayList<Shampoo>();
	}
}
