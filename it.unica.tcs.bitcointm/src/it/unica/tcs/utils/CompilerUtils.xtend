/*
 * Copyright 2017 Nicola Atzei
 */

package it.unica.tcs.utils

import com.google.inject.Singleton
import it.unica.tcs.balzac.AddressType
import it.unica.tcs.balzac.BooleanType
import it.unica.tcs.balzac.HashType
import it.unica.tcs.balzac.IntType
import it.unica.tcs.balzac.KeyType
import it.unica.tcs.balzac.Network
import it.unica.tcs.balzac.Parameter
import it.unica.tcs.balzac.PubkeyType
import it.unica.tcs.balzac.SignatureType
import it.unica.tcs.balzac.StringType
import it.unica.tcs.balzac.TransactionType
import it.unica.tcs.balzac.Type
import it.unica.tcs.compiler.CompileException
import it.unica.tcs.lib.model.Address
import it.unica.tcs.lib.model.Hash
import it.unica.tcs.lib.model.ITransactionBuilder
import it.unica.tcs.lib.model.PrivateKey
import it.unica.tcs.lib.model.PublicKey
import it.unica.tcs.lib.model.Signature
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.EcoreUtil2

@Singleton
class CompilerUtils {

    def String compileFormalParams(List<Parameter> formalParams) {
        formalParams.map[p|p.type.compileType+" "+p.name].join(", ")
    }

    def String compileType(Type type) {
        if(type instanceof IntType) return "Long"
        if(type instanceof HashType) return "Hash"
        if(type instanceof StringType) return "String"
        if(type instanceof BooleanType) return "Boolean"
        if(type instanceof SignatureType) return "byte[]"

        throw new CompileException("Unexpected type "+type.class.simpleName)
    }

    def String compileNetworkParams(EObject obj) {
        val list = EcoreUtil2.getAllContentsOfType(EcoreUtil2.getRootContainer(obj), Network);

        if (list.size()==0) // network undeclared, assume testnet
            return "NetworkParameters.fromID(NetworkParameters.ID_TESTNET)"

        if (list.size()==1)
            return if (list.get(0).isTestnet())
                   "NetworkParameters.fromID(NetworkParameters.ID_TESTNET)"
                   else "NetworkParameters.fromID(NetworkParameters.ID_MAINNET)"

        throw new IllegalStateException();
    }

    def Class<?> convertType(Type type) {
        if(type instanceof IntType) return Long
        if(type instanceof StringType) return String
        if(type instanceof BooleanType) return Boolean
        if(type instanceof HashType) return Hash
        if(type instanceof KeyType) return PrivateKey
        if(type instanceof PubkeyType) return PublicKey
        if(type instanceof AddressType) return Address
        if(type instanceof TransactionType) return ITransactionBuilder
        if(type instanceof SignatureType) return Signature

        throw new CompileException("Unexpected type "+type?.class?.simpleName)
    }
}
