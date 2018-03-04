package org.gradle;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.manager.CsvBeanManager;

public class CsvWriterMain {
	static Long LOOP_COUNT = 3L;

	public static <T> void main(String[] args) throws IOException,
			CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		CsvWriterMain writerMain = new CsvWriterMain();

		System.out.println("引数:" + args[0]);

		if (args.length == 0 || "1".equals(args[0]))
			writerMain.orangesignal();
		else if ("2".equals(args[0]))
			writerMain.opencsv();
	}

	void orangesignal() throws IOException {
		CsvConfig cfg = new CsvConfig();
		cfg.setQuoteDisabled(false);// デフォルトでは無効となっている囲み文字を有効にします。
		cfg.setEscapeDisabled(false);

		Client client = ClientBuilder.newClient();
		CsvBeanManager csvBeanManager = new CsvBeanManager(cfg);

		System.out.println("START -- orangesignal -- ");
		try (Writer out = Files.newBufferedWriter(Paths.get("sample.csv"))) {
			for (long i = 0; i < LOOP_COUNT; i++) {
				List<Person> list = client.target("http://localhost:8882")
						.path("/sample_json").queryParam("token", "xxxxx")
						.request().get(new GenericType<List<Person>>() {
						});

				csvBeanManager.save(list, Person.class).to(out);

				if (i % 1000 == 0)
					System.out.println("途中経過 = " + i);
			}
		}
		System.out.println("END!!");
	}

	void opencsv() throws IOException, CsvDataTypeMismatchException,
			CsvRequiredFieldEmptyException {
		Client client = ClientBuilder.newClient();

		System.out.println("START -- opencsv -- ");
		try (Writer writer = Files.newBufferedWriter(Paths.get("sample2.csv"))) {
			StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder<Person>(
					writer).build();
			
			for (long i = 0; i < LOOP_COUNT; i++) {
				List<Person> list = client.target("http://localhost:8882")
						.path("/sample_json").queryParam("token", "xxxxx")
						.request().get(new GenericType<List<Person>>() {});
				
				beanToCsv.write(list);

				if (i % 1000 == 0)
					System.out.println("途中経過 = " + i);
			}
		}
		System.out.println("END!!");
	}
}

// List<Person> list = new ArrayList<Person>();
// for (int i = 0; i < 100; i++)
// list.add(new Person(i,
// "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));