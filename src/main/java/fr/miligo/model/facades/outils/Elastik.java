/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.model.facades.outils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.miligo.model.entities.emprunt.EmpruntImmediat;

/**
 *
 * @author codeur
 */
public class Elastik {

	private final TransportClient client;
	private final PreBuiltTransportClient preBuiltTransportClient;

	public Elastik() throws UnknownHostException {
		InetSocketTransportAddress ista = new InetSocketTransportAddress(InetAddress.getByName("172.16.128.178"), 9200);
		preBuiltTransportClient = new PreBuiltTransportClient(Settings.EMPTY);
		client = preBuiltTransportClient.addTransportAddress(ista);
	}

	public IndexResponse sendToElastik(EmpruntImmediat ei) throws JsonProcessingException {
		return client.prepareIndex("Miligo", "EmpruntImmediat").setSource(ei).get();
	}
}
