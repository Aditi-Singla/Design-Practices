import java.io.*;
public class playerClass implements Serializable
{
		private static final long serialVersionUID = 5950169519310163575L;
		public int playerNumber;
		public int total;
		public String address;
		public playerClass(int playerNumber, int total, String address)
		{
			this.playerNumber = playerNumber;
			this.total = total;
			this.address = address;
		}

		public boolean equals(Object o) 
		{
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;	
        playerClass student = (playerClass) o;
        // if (id != student.id) return false;
        // if (name != null ? !name.equals(student.name) : student.name != null) return false	
        if (address.equals(student.address))
        	return true;
        else
        	return false;
    }
}