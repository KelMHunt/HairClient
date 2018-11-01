package salon.clients;
import salon.models.Shampoo;
public class HairClientDriver 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		HairClient client= new HairClient();
		Shampoo shampoo= new Shampoo(101,2000.0f,"SheaMoisture","coconut");
		if(client.initConnection())
		{
			System.out.println("Client connection successful");
			System.out.println("Available shampoos are:"+ client.addShampoo(shampoo));
			//client.closeConnection();
		}
	}

}
