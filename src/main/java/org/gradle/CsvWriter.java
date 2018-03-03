package org.gradle;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.orangesignal.csv.Csv;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.handlers.BeanListHandler;

public class CsvWriter {
	
	static Long LOOP_COUNT = Long.MAX_VALUE;

	public static <T> void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		List<Person> list = new ArrayList<Person>();
		for (int i = 0; i < 100; i++)
			list.add(new Person("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
		
		System.out.println("START!!");
		try(OutputStream out = new FileOutputStream("sample.csv");){
			for (long i = 0; i < LOOP_COUNT; i++) {
				CsvConfig cfg = new CsvConfig();
				
				Csv.save(list, out, cfg, new BeanListHandler<Person>(Person.class));
//				out.flush();
				if(i % 1000 == 0){
					System.out.println("途中経過 = " + i);
					Thread.sleep(100L);
				}
			}					
		}
		System.out.println("END!!");
	}

}
