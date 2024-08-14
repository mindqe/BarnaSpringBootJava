package curso.cap.ejemploBootJpa;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Claves
{

	public static void main(String[] args)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
		for (int i = 0; i < 10; i++)
			System.out.println(encoder.encode("1234"));
		
		System.err.println(encoder.matches("1234", "$2a$04$CCrsMvKBVNIZuW8cv5WJq.KGIJLr4d2FXIWo0vUF.xKe7QNfTiTRS"));
	}

}
