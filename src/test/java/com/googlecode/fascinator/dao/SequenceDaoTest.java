/*
 * The Fascinator - Sequence Dao Test
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
package com.googlecode.fascinator.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.googlecode.fascinator.model.Sequence;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/test/resources/test-applicationContext.xml"})
public class SequenceDaoTest {
	
	@Autowired
	public GenericDao<Sequence, String> sequenceDao;	
		
	@Test
	public void testCreation() {
		Sequence sequence = new Sequence();
		sequence.setSequenceName("sequenceDaoSample");
		sequenceDao.create(sequence);
		
		Sequence sequence2 = sequenceDao.get("sequenceDaoSample");
		Assert.assertNotNull(sequence2);
		Assert.assertEquals("sequenceDaoSample",sequence2.getSequenceName());
		Assert.assertEquals(1,sequence2.getCounter());
	}
	
	@Test
	public void testUpdate() {
		Sequence sequence = new Sequence();
		sequence.setSequenceName("sequenceDaoSample2");
		sequenceDao.create(sequence);
		
		sequence = sequenceDao.get("sequenceDaoSample2");
		sequence.setCounter(sequence.getCounter() +1);
		sequenceDao.update(sequence);
		
		Sequence sequence2 = sequenceDao.get("sequenceDaoSample2");
		Assert.assertNotNull(sequence2);
		Assert.assertEquals("sequenceDaoSample2",sequence2.getSequenceName());
		Assert.assertEquals(2,sequence2.getCounter());
		
	}
	
	
}
