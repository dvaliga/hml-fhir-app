package org.nmdp.kafkaproducer.util;

/**
 * Created by Andrew S. Brown, Ph.D., <andrew@nmdp.org>, on 8/1/17.
 * <p>
 * service-kafka-producer
 * Copyright (c) 2012-2017 National Marrow Donor Program (NMDP)
 * <p>
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 3 of the License, or (at
 * your option) any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; with out even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
 * License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library;  if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA.
 * <p>
 * > http://www.fsf.org/licensing/licenses/lgpl.html
 * > http://www.opensource.org/licenses/lgpl-license.php
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.apache.log4j.Logger;

public class KafkaMessageSerializer<T extends Serializable> implements Serializer<T> {

    private static final Logger LOG = Logger.getLogger(KafkaMessageSerializer.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, T data) {
        try {
            return MAPPER.writeValueAsString(data).getBytes();
        } catch (JsonProcessingException ex) {
            LOG.error(ex);
        }

        return "".getBytes();
    }

    @Override
    public void close() {

    }
}