package salon.clients;

public class HairClientDriver 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		HairClient client= new HairClient();
		if(client.initConnection())
		{
			System.out.println("Client connection successful");
			System.out.println("Today's Special:"+ client.getTodaysSpecial());
			client.closeConnection();
		}
	}

}
