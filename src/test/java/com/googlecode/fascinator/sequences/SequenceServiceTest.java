/*
 * The Fascinator - Sequence Service Test
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
package com.googlecode.fascinator.sequences;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/test/resources/test-applicationContext.xml"})
public class SequenceServiceTest {
    
	@Autowired
	public String databaseString;
	public static String staticDBString;

	@Autowired
	private SequenceService sequenceService;
	
    
	@Test
	public void testGetSequence() throws SQLException {
		Assert.assertEquals(1L,sequenceService.getSequence("sampleSequence1").longValue());
		Assert.assertEquals(2L,sequenceService.getSequence("sampleSequence1").longValue());
		Assert.assertEquals(1L,sequenceService.getSequence("sampleSequence2").longValue());
		Assert.assertEquals(3L,sequenceService.getSequence("sampleSequence1").longValue());
		Assert.assertEquals(2L,sequenceService.getSequence("sampleSequence2").longValue());
		Assert.assertEquals(4L,sequenceService.getSequence("sampleSequence1").longValue());
	}

}
