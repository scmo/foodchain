package foodchain;

import org.web3j.abi.Contract;
import org.web3j.abi.ManagedTransaction;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
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
        FIFSRegistrar fifs = FIFSRegistrar.load("0x80ee913e8d8aa3ce1468d40a0292112bceb75fc4", web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
        return fifs.lookup(new Utf8String(contractName)).get();
    }

    public void update(String contractName, String cowId,  String farmAddress, int stepCounter, int outsideTemp, int insideTemp) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials("123456", "/home/draft/.ethereum/testnet/keystore/"+ACCOUNT1);
        Address adr = lookupName(contractName);
        FoodChain contract = FoodChain.load(adr.toString(), web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);
        //contract.update()
        //contract.update()
    }

    public Cow cow(String contractName, Bytes32 cowId) throws Exception {
        Credentials credentials = WalletUtils.loadCredentials("123456", "/home/draft/.ethereum/testnet/keystore/"+ACCOUNT1);
        Address adr = lookupName(contractName);
        FoodChain contract = FoodChain.load(adr.toString(), web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT);

        List<Type> tmp = contract.cow(cowId).get();
        Iterator<Type> it = tmp.iterator();
        Cow cow = new Cow((int)it.next().getValue(), (int)it.next().getValue(),
                (int)it.next().getValue(), (int)it.next().getValue(), (String)it.next().getValue());
        return cow;
    }


}