/*
 * Copyright 2019 Nicola Atzei
 */
package it.unica.tcs.lib.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.bitcoinj.script.ScriptPattern;
import org.junit.Test;

public class PlaceholderUtilsTest {

    @Test
    public void placeholderIntIsZero() {
        assertEquals(0, PlaceholderUtils.INT);
    }

    @Test
    public void placeholderStringIsEmpty() {
        assertEquals("", PlaceholderUtils.STRING);
    }

    @Test
    public void placeholderBooleanIsFalse() {
        assertEquals(false, PlaceholderUtils.BOOLEAN);
    }

    @Test
    public void placeholderHashIsZeroBites() {
        assertArrayEquals(new byte[0], PlaceholderUtils.HASH.getBytes());
    }

    @Test
    public void placeholderSignatureIsZeroBites() {
        assertArrayEquals(new byte[0], PlaceholderUtils.SIGNATURE.getSignature());
    }

    @Test
    public void publicKeyIsDerivedFromPrivateOneMainnet() {
    	_publicKeyIsDerivedFromPrivateOne(NetworkType.MAINNET);
    }

    @Test
    public void publicKeyIsDerivedFromPrivateOneTestnet() {
    	_publicKeyIsDerivedFromPrivateOne(NetworkType.TESTNET);
    }

    private void _publicKeyIsDerivedFromPrivateOne(NetworkType params) {
    	assertEquals(
    			PlaceholderUtils.KEY(params).toPublicKey(),
    			PlaceholderUtils.PUBKEY(params)
    			);
    }

    @Test
    public void addressIsDerivedFromPrivateKeyMainnet() {
    	_addressIsDerivedFromPrivateKey(NetworkType.MAINNET);
    }

    @Test
    public void addressIsDerivedFromPrivateKeyTestnet() {
    	_addressIsDerivedFromPrivateKey(NetworkType.TESTNET);
    }

    private void _addressIsDerivedFromPrivateKey(NetworkType params) {
        assertEquals(
                PlaceholderUtils.KEY(params).toAddress(),
                PlaceholderUtils.ADDRESS(params)
                );
    }

    @Test
    public void placeholderTransactionMainnet() {
    	_placeholderTransaction(NetworkType.MAINNET);
    }

    @Test
    public void placeholderTransactionTestnet() {
    	_placeholderTransaction(NetworkType.TESTNET);
    }

    private void _placeholderTransaction(NetworkType params) {
    	ITransactionBuilder tx = PlaceholderUtils.TX(params);
    	// is coinbase
    	assertTrue(tx.isCoinbase());

    	// have 10 outputs
    	assertEquals(10, tx.getOutputs().size());

    	// each one
    	for (Output output : tx.getOutputs()) {
    		// is P2PKH
    		assertTrue(output.getScript().isP2PKH());
    		// worth 50 BTC
    		assertEquals(50_0000_0000L, output.getValue());
    		// address is from placeholder
    		assertArrayEquals(
    				PlaceholderUtils.ADDRESS(params).getBytes(),
    				ScriptPattern.extractHashFromP2PKH(output.getScript().getOutputScript()));
    	}
    }
}