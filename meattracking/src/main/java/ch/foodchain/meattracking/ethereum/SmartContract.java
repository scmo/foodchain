package ch.foodchain.meattracking.ethereum;

import org.springframework.stereotype.Service;
import org.web3j.abi.Contract;
import org.web3j.abi.ManagedTransaction;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SmartContract {

    final private static Web3j web3j;
    final private static String ACCOUNT1 = "UTC--2016-04-28T14-49-53.053152899Z--d2dd8eab1d77732d43c3c079a18e25378b472a0a";
    final private static String ACCOUNT2 = "UTC--2016-04-28T14-50-13.743620079Z--faec1b67fee0c36ac5570b43ca0716a19f235ce1";

    static {
        web3j = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
    }

    public String deploy(String contractName) throws IOException, CipherException, ExecutionException, InterruptedException {
        Credentials credentials = WalletUtils.loadCredentials("123456", "/home/draft/.ethereum/testnet/keystore/"+ACCOUNT1);
        FoodChain contract = FoodChain.deploy(web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT, BigInteger.ZERO, new Utf8String(contractName)).get();
        return contract.getContractAddress();
    }


    public Address lookupName(String contractName) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials("123456", "/home/draft/.ethereum/testnet/keystore/"+ACCOUNT1);
        FIFSRegistrar fifs = FIFSRegistrar.load("0x2bc4c198325853db29293b6a62f36a62f7cbcf45", web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
        return fifs.lookup(new Utf8String(contractName)).get();
    }

    public void update(String contractName, String cowId,  String farmAddress, int stepCounter, int outsideTemp, int insideTemp) throws Exception {
        Address adr = lookupName(contractName);
        updateWithAddress(adr.toString(), cowId, farmAddress, stepCounter, outsideTemp, insideTemp);
    }

    public void updateWithAddress(String adr, String cowId,  String farmAddress, int stepCounter, int outsideTemp, int insideTemp) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials("123456", "/home/draft/.ethereum/testnet/keystore/"+ACCOUNT1);
        FoodChain contract = FoodChain.load(adr, web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
        //contract.update()
        byte[] tmp1 = new byte[32];
        System.arraycopy(cowId.getBytes(), 0, tmp1, 0, cowId.getBytes().length);

        byte[] tmp2 = new byte[32];
        System.arraycopy(farmAddress.getBytes(), 0, tmp2, 0, farmAddress.getBytes().length);

        contract.update(new Bytes32(tmp1), new Bytes32(tmp2), new Uint32(BigInteger.valueOf(stepCounter)), new Uint32(BigInteger.valueOf(outsideTemp)), new Uint32(BigInteger.valueOf(insideTemp))).get();
    }

    public Cow cow(String contractName, String cowId) throws Exception {
        Address adr = lookupName(contractName);
        return cowWithAddress(adr.toString(), cowId);
    }

    public Cow cowWithAddress(String adr, String cowId) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials("123456", "/home/draft/.ethereum/testnet/keystore/"+ACCOUNT1);
        FoodChain contract = FoodChain.load(adr, web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
        byte[] tmp = new byte[32];
        System.arraycopy(cowId.getBytes(), 0, tmp, 0, cowId.getBytes().length);
        List<Type> tmpList = contract.cow(new Bytes32(tmp)).get();
        Iterator<Type> it = tmpList.iterator();
        Cow cow = new Cow(((BigInteger)it.next().getValue()).intValue(), ((BigInteger)it.next().getValue()).intValue(),
                ((BigInteger)it.next().getValue()).intValue(), ((BigInteger)it.next().getValue()).intValue(), new String((byte[])it.next().getValue()).replaceAll("\0", ""));
        return cow;
    }


}