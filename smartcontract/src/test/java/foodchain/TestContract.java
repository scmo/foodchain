
package foodchain;

import org.junit.Assert;
import org.junit.Test;
import org.web3j.crypto.CipherException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TestContract {
    @Test
    public void testVersion() throws ExecutionException, InterruptedException {
        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        Assert.assertEquals("Geth/v1.5.7-stable/linux/go1.7.4", clientVersion);
    }

    @Test
    public void testDeploy() throws ExecutionException, InterruptedException, IOException, CipherException {
        SmartContract sm = new SmartContract();
        String adr = sm.deploy("farmX");
        System.out.println(adr);
    }
}