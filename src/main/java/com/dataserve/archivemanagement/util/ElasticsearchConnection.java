package com.dataserve.archivemanagement.util;

import java.io.IOException;

import com.dataserve.archivemanagement.exception.ConnectionException;
import com.dataserve.archivemanagement.model.dto.AuditDTO;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

public class ElasticsearchConnection implements AutoCloseable {
	private static final String INDEX = "integration_audit";

	private RestClient restClient = null;
	private ElasticsearchTransport transport = null;
	private ElasticsearchClient client = null;
	
	public ElasticsearchConnection() throws ConnectionException {
		try {
			restClient = RestClient.builder(new HttpHost(
					ConfigManager.getElasticSearchHost(), 
					ConfigManager.getElasticSearchPort())
			).build();
	
			// Create the transport with a Jackson mapper
			transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
	
			// And create the API client
			client = new ElasticsearchClient(transport);
		} catch (Exception e) {
			throw new ConnectionException("Failed to open connection to elasticsearch server", e);
		}
	}
	
	public void audit(Long recordId, AuditDTO dto) throws ConnectionException {
		try {
			IndexRequest<AuditDTO> request = IndexRequest.of(i -> i
				    .index(INDEX)
				    .id(recordId.toString())
				    .document(dto)
				);
			client.index(request);
		} catch (Exception e) {
			throw new ConnectionException("Failed to index record with id: " + recordId, e);
		}
	}

	@Override
	public void close() throws Exception {
		if (restClient != null) {
			try {
				restClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}			
		if (transport != null) {
			try {
				transport.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
