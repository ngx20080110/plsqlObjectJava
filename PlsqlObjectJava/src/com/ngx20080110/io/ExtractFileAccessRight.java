package com.ngx20080110.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class ExtractFileAccessRight {

	///  ls -l -R /backups/u01_app/app/oracle/* > /tmp/accessright_old2.txt
	public static void main(String[] args) {
		File src = new File("d:/accessright_new2.txt");
		File out = new File("d:/accessright_new2_out.txt");
		try {
			List<String> lines = IOUtils.readLines(new FileReader(src));
			List<String> outLines = new ArrayList<>();
			
			String parent = "";
			
			int len = lines.size();
			for (int i = 0; i < len; i++) {
				String line = lines.get(i);
				if (line.length() > 0) {
					if (line.startsWith("-") || line.startsWith("d")) {
						String accessRight = line.substring(0, 10);
						String current = line.substring(line.lastIndexOf(" ") + 1);
						outLines.add(accessRight + " " + parent + current);
					}
					else if (line.startsWith("/")) {
						parent = line.substring(0, line.length() - 1) + "/";
					}
				}
			}

			FileWriter writer = new FileWriter(out);
			IOUtils.writeLines(outLines, "\n", writer);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
