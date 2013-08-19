/*
 * The Fascinator - DBIntegrationTestSuite
 * Copyright (C) 2008-2010 University of Southern Queensland
 * Copyright (C) 2012 Queensland Cyber Infrastructure Foundation (http://www.qcif.edu.au/)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.googlecode.fascinator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.beans.BeansException;

import com.googlecode.fascinator.dao.SequenceDaoTest;
import com.googlecode.fascinator.sequences.SequenceServiceTest;

@RunWith(Suite.class)
@SuiteClasses({SequenceDaoTest.class, SequenceServiceTest.class})
public class DBIntegrationTestSuite {

	
	
	@AfterClass 
	public static void tearDown() throws IOException, BeansException, SQLException {
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/test/resources/database.properties"));
		String dbString = prop.getProperty("jdbc.databaseurl");
		try {
			DriverManager.getConnection(dbString+";shutdown=true");
		} catch (SQLNonTransientConnectionException exception) {
		} catch (SQLException e) {			
		}
      
		FileUtils.deleteDirectory(new File("target/database"));
	}


	
}
