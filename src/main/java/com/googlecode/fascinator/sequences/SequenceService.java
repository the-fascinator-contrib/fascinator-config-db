/*
 * The Fascinator - Sequence Service
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.fascinator.dao.GenericDao;
import com.googlecode.fascinator.model.Sequence;

@Component(value = "sequenceService")
public class SequenceService {

	/** Logging */
	private final Logger log = LoggerFactory.getLogger(SequenceService.class);
	@Autowired
	private GenericDao<Sequence, String> sequenceDao;
	
	@Transactional
	public synchronized Integer getSequence(String sequenceName)
			throws SQLException {
		log.info("Getting sequence for: "+ sequenceName);
		Integer sequenceCount = null;
		Sequence sequence = sequenceDao.get(sequenceName);
		
		//Sequence doesn't yet exist so lets create it
		if(sequence == null) {
			log.debug("Sequence " + sequenceName + " not in database, creating new row for it");
			sequence = new Sequence();
			sequence.setSequenceName(sequenceName);
			sequenceDao.create(sequence);
		}
		
		sequenceCount = sequence.getCounter();
		log.debug("Sequence Count" + sequenceCount  + " for sequence " + sequenceName);
		//now increment the counter
		sequence.setCounter(sequenceCount +1);
		sequenceDao.update(sequence);
		
		return sequenceCount;
	}
}
