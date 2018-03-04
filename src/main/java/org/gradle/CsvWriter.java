package org.gradle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import com.orangesignal.csv.Csv;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.handlers.BeanListHandler;


public class CsvWriter {
	
	static Long LOOP_COUNT = Long.MAX_VALUE;

	public static <T> void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		CsvConfig cfg = new CsvConfig();
		
//		List<Person> list = new ArrayList<Person>();
//		for (int i = 0; i < 100; i++)
//			list.add(new Person(i, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
		
		Client client = ClientBuilder.newClient();		
		System.out.println("START!!");
		try(OutputStream out = new FileOutputStream("sample.csv");){
			for (long i = 0; i < LOOP_COUNT; i++) {
				List<Person> list = client.target("http://localhost:8882")
						.path("/sample_json").request()
						.get(new GenericType<List<Person>>() {});
				
				Csv.save(list, out, cfg, new BeanListHandler<Person>(Person.class));
				if(i % 1000 == 0){
					System.out.println("途中経過 = " + i);
					Thread.sleep(100L);
				}
			}					
		}
		System.out.println("END!!");
	}

}
